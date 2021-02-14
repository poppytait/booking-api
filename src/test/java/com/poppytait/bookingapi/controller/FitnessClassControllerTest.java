package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
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

    Instant instant = Instant.now();
    FitnessClass fitnessClass = new FitnessClass("Body Pump", "Janine", instant, instant, "weights room", 12);

    @Test
    void shouldReturnFitnessClasses() {
        List<FitnessClass> classes = new ArrayList<>();
        classes.add(fitnessClass);

        when(serviceMock.findAll()).thenReturn(classes);
        List<FitnessClass> fitnessClasses = controller.findFitnessClasses();
        assertEquals(classes, fitnessClasses);
    }

    @Test
    void shouldAddFitnessClass() {
        FitnessClass expectedClass = fitnessClass;

        when(serviceMock.add(expectedClass)).thenReturn(expectedClass);
        FitnessClass actualClass = controller.addFitnessClass(expectedClass);
        assertEquals(expectedClass, actualClass);
    }

    @Test
    void shouldDeleteFitnessClass() throws FitnessClassNotFoundException {
        Long expectedId = 1L;

        when(serviceMock.delete(expectedId)).thenReturn(expectedId);
        Long actualId = controller.deleteFitnessClass(expectedId);
        assertEquals(expectedId, actualId);
    }

}