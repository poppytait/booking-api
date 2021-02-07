package com.poppytait.bookingapi.repository;

import com.poppytait.bookingapi.model.FitnessClass;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IFitnessClassRepository extends JpaRepository<FitnessClass, Long> {

}
