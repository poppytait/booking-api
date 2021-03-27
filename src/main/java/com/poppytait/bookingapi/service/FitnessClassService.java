package com.poppytait.bookingapi.service;

import com.poppytait.bookingapi.exception.FitnessClassNotFoundException;
import com.poppytait.bookingapi.model.FitnessClass;
import com.poppytait.bookingapi.repository.IFitnessClassRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FitnessClassService implements IFitnessClassService {
    private final IFitnessClassRepository repository;

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
    public FitnessClass findById(Long id) throws FitnessClassNotFoundException {
        return repository.findById(id)
                .orElseThrow(() -> new FitnessClassNotFoundException(id));
    }

    @Override
    public Long delete(Long id) throws FitnessClassNotFoundException {
        Long resultId = findById(id).getId();
        repository.deleteById(resultId);

        return resultId;
    }
}
