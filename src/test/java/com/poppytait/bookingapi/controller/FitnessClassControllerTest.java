package com.poppytait.bookingapi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poppytait.bookingapi.config.ApplicationConfig;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.repository.IFitnessClassRepository;
import com.poppytait.bookingapi.util.ResourceReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.poppytait.bookingapi.constants.SeedUsers.KYLE;
import static com.poppytait.bookingapi.constants.SeedUsers.LINDA;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { ApplicationConfig.class })
@WebAppConfiguration
class FitnessClassControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    private MockMvc mockMvc;

    @MockBean
    private IFitnessClassRepository fitnessClassRepository;

    Instant instant = Instant.parse("2021-04-04T10:37:30.00Z");
    FitnessClass fitnessClass = new FitnessClass(1L,"Body Pump", "Janine", instant, instant, "weights room", 12);

    @BeforeEach
    public void setup() {
        System.out.println();
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).apply(springSecurity()).build();
    }

    @Test
    void shouldReturnFitnessClasses() throws Exception {
        List<FitnessClass> classes = new ArrayList<>();
        classes.add(fitnessClass);

        when(fitnessClassRepository.findAll()).thenReturn(classes);
        String expectedResponse = ResourceReader.readFileToString("get-fitness-classes.json");

        this.mockMvc.perform(MockMvcRequestBuilders.get("/classes")
                .with(user(KYLE.getUsername()).authorities(KYLE.getRole().getGrantedAuthorities())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void shouldAddFitnessClass() throws Exception {
        when(fitnessClassRepository.save(any(FitnessClass.class))).thenReturn(fitnessClass);
        String expectedResponse = ResourceReader.readFileToString("add-fitness-class.json");

        this.mockMvc.perform(MockMvcRequestBuilders.post("/classes")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(fitnessClass))
                .with(user(LINDA.getUsername()).authorities(LINDA.getRole().getGrantedAuthorities())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }

    @Test
    void shouldDeleteFitnessClass() throws Exception {
        when(fitnessClassRepository.findById(1L)).thenReturn(Optional.of(fitnessClass));
        String expectedResponse = ResourceReader.readFileToString("delete-fitness-class.json");

        this.mockMvc.perform(MockMvcRequestBuilders.delete("/classes/1")
                .with(user(LINDA.getUsername()).authorities(LINDA.getRole().getGrantedAuthorities())))
                .andDo(print()).andExpect(status().isOk())
                .andExpect(content().json(expectedResponse));
    }
}