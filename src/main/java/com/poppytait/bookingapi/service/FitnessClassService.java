package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.model.FitnessClass;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class FitnessClassService implements IFitnessClassService {

    @Override
    public List<FitnessClass> findAll() {
        List<FitnessClass> classes = new ArrayList<>();

        Instant instant = Instant.now();

        classes.add(new FitnessClass(1234, "Body Pump", "Janine", instant, instant, "weights room", 12));

        return classes;
    }
}
