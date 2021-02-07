package com.poppytait.bookingapi.controller;

import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.service.IFitnessClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("classes")
public class FitnessClassController {

    @Autowired
    private IFitnessClassService service;


    @GetMapping
    public List<FitnessClass> findFitnessClasses() {
        return service.findAll();
    }

    @PostMapping
    public FitnessClass addFitnessClass(@RequestBody FitnessClass fitnessClass) {
        return service.add(fitnessClass);
    }
}