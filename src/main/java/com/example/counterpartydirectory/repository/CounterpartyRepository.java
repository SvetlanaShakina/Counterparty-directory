package com.example.counterpartydirectory.repository;

import com.example.counterpartydirectory.entity.Counterparty;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CounterpartyRepository extends JpaRepository<Counterparty, Integer> {

    @Query(value = "SELECT * FROM counterparty c WHERE c.name LIKE %:name%", nativeQuery = true)
    List<Counterparty> getByName(@Param("name") String name);

    Counterparty getByBicAndAccountNumber(String bic, String accountNumber);

    Page<Counterparty> findAll(Pageable pageable);
}