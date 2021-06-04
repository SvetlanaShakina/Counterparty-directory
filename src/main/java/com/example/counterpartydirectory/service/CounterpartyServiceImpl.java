package com.example.counterpartydirectory.service;

import com.example.counterpartydirectory.entity.Counterparty;
import com.example.counterpartydirectory.repository.CounterpartyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CounterpartyServiceImpl implements CounterpartyService {

    @Autowired
    private CounterpartyRepository repository;

    @Override
    public Counterparty getById(int id) {
        return repository.getById(id);
    }

    @Override
    public void save(Counterparty counterparty) {
        repository.save(counterparty);
    }

    @Override
    public void delete(int id) {
        repository.deleteById(id);
    }

    @Override
    public Page<Counterparty> findPaginated(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        return repository.findAll(pageable);
    }

    @Override
    public List<Counterparty> getByName(String name) {
        return repository.getByName(name);
    }

    @Override
    public Counterparty getByBicAndAccountNumber(String bic, String accountNumber) {
        return repository.getByBicAndAccountNumber(bic, accountNumber);
    }
}
