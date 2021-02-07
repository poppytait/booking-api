package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.model.FitnessClass;

import java.util.List;

public interface IFitnessClassService {
    List<FitnessClass> findAll();
    FitnessClass add(FitnessClass fitnessClass);
}
