package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.service.IFitnessClassService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FitnessClassControllerTest {

    @Mock
    IFitnessClassService serviceMock;

    @InjectMocks
    FitnessClassController controller;

    @Test
    void shouldReturnFitnessClasses() {
        List<FitnessClass> classes = new ArrayList<>();

        Instant instant = Instant.now();

        classes.add(new FitnessClass(1234, "Body Pump", "Janine", instant, instant, "weights room", 12));

        when(serviceMock.findAll()).thenReturn(classes);

        List<FitnessClass> fitnessClasses = controller.findFitnessClasses();
        assertEquals(classes, fitnessClasses);
    }
}