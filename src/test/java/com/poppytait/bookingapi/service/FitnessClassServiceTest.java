package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.repository.IFitnessClassRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FitnessClassServiceTest {

    @Mock
    IFitnessClassRepository repository;

    @InjectMocks
    FitnessClassService service;

    Instant instant = Instant.now();
    FitnessClass fitnessClass = new FitnessClass(2L, "Body Pump", "Janine", instant, instant, "weights room", 12);

    @Test
    void shouldFindById() throws Exception {
        when(repository.findById(2L)).thenReturn(Optional.of(fitnessClass));

        FitnessClass actualFitnessClass = service.findById(2L);

        assertEquals(fitnessClass, actualFitnessClass);
    }

    @Test
    void shouldThrowExceptionWhenCannotFindById() throws Exception {
        when(repository.findById(3L)).thenReturn(Optional.empty());

        assertThrows(FitnessClassNotFoundException.class, () -> {
            service.findById(3L);
        });
    }

    @Test
    void shouldDelete() throws Exception {
       when(repository.findById(2L)).thenReturn(Optional.of(fitnessClass));

        Long actualId = service.delete(2L);

        assertEquals(fitnessClass.getId(), actualId);
    }
}