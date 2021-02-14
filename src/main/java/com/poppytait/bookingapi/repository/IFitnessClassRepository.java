package com.poppytait.bookingapi.repository;

import com.poppytait.bookingapi.model.FitnessClass;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IFitnessClassRepository extends CrudRepository<FitnessClass, Long> {
    @Override
    List<FitnessClass> findAll();
}
