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

    /**
     * Получение контрагента по имени
     * @param name - наименование контрагента (поиск по подстроке)
     * @return - список контрагентов с указанным наименованием
     */
    @Query(value = "SELECT * FROM counterparty c WHERE c.name LIKE %:name%", nativeQuery = true)
    List<Counterparty> getByName(@Param("name") String name);

    /**
     * Получение контрагента по БИК и номеру счета
     * @param bic - БИК
     * @param accountNumber - номер счета
     * @return - контрагент с указанными БИК и номером счета
     */
    Counterparty getByBicAndAccountNumber(String bic, String accountNumber);

    /**
     * Получение всех контрагентов (с пагинацией)
     * @param pageable - объект для разбиения списка по страницам с указанием номера начальной страницы
     *                 и кол-ва объектов на странице
     * @return - список контрагентов с пагинацией
     */
    Page<Counterparty> findAll(Pageable pageable);
}