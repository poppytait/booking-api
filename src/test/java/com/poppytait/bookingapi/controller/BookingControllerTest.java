package com.poppytait.bookingapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poppytait.bookingapi.config.ApplicationConfig;
import com.poppytait.bookingapi.model.Booking;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.model.User;
import com.poppytait.bookingapi.repository.IBookingRepository;
import com.poppytait.bookingapi.repository.IFitnessClassRepository;
import com.poppytait.bookingapi.repository.IUserRepository;
import com.poppytait.bookingapi.util.ResourceReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;

import static com.poppytait.bookingapi.constants.SeedUsers.KYLE;
import static com.poppytait.bookingapi.security.UserRole.CUSTOMER;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookingControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @Autowired
    private IFitnessClassRepository fitnessClassRepository;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IBookingRepository bookingRepository;

    Instant startsAt = Instant.parse("2017-02-03T11:25:00Z");
    Instant endsAt = Instant.parse("2017-02-03T12:25:00Z");

    FitnessClass fitnessClass = new FitnessClass(1L, "Pilates", "Benny Johnson", startsAt, endsAt, "Yoga studio", 12);
    User user = new User(2L, "kyle", "$2a$10$JNJy/WGkLhFnqKPy9FWdPub3e/tkvdsVMWnZ.BDeoGeKC4KzcIOZi", CUSTOMER);


    @BeforeEach
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).apply(springSecurity()).build();
        fitnessClassRepository.save(fitnessClass);
        userRepository.save(user);
    }

    @Test
    void shouldAddBooking() throws Exception {
        String expectedResponse = ResourceReader.readFileToString("add-booking.json");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/booking/2/1")
                .with(user(KYLE.getUsername()).authorities(KYLE.getRole().getGrantedAuthorities())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void shouldGetBookings() throws Exception {
        Booking booking = new Booking(3L, fitnessClass, user);
        bookingRepository.save(booking);

        String expectedResponse = ResourceReader.readFileToString("get-bookings.json");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/booking/2")
                .with(user(KYLE.getUsername()).authorities(KYLE.getRole().getGrantedAuthorities())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void shouldCancelBooking() throws Exception {
        Booking booking = new Booking(3L, fitnessClass, user);
        bookingRepository.save(booking);

        String expectedResponse = "3";

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/booking/3")
                .with(user(KYLE.getUsername()).authorities(KYLE.getRole().getGrantedAuthorities())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}
