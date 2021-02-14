package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.repository.IFitnessClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FitnessClassService implements IFitnessClassService {
    private IFitnessClassRepository repository;

    public FitnessClassService(IFitnessClassRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<FitnessClass> findAll() {
        return this.repository.findAll();
    }

    @Override
    public FitnessClass add(FitnessClass fitnessClass) {
        return repository.save(fitnessClass);
    }

    @Override
    public Long delete(Long id) throws FitnessClassNotFoundException {
        Optional<FitnessClass> result = repository.findById(id);

        if(result.isEmpty()) {
            throw new FitnessClassNotFoundException("Fitness class with id: " + id + " not found");
        } else {
            repository.deleteById(id);
            return id;
        }

    }
}
