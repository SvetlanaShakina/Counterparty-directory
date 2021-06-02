package com.example.counterpartydirectory.service;

import com.example.counterpartydirectory.entity.Counterparty;
import org.springframework.data.domain.Page;

import java.util.List;

public interface CounterpartyService {
    /**
     * Получение контрагента по идентификатору
     * @param id - идентификатор контрагента
     * @return - контрагент с указанным идентификатором
     */
    Counterparty getById(int id);

    /**
     * Сохранение контрагента
     * @param counterparty - контрагент
     */
    void save(Counterparty counterparty);

    /**
     * Удаление контрагента
     * @param id - идентификатор контрагента
     */
    void delete(int id);

    /**
     * Получение списка контрагентов с пагинацией
     * @param pageNumber - номер начальной страницы
     * @param pageSize - размер страницы
     * @return - страница с контрагентами
     */
    Page<Counterparty> findPaginated(int pageNumber, int pageSize);

    /**
     * Получение контрагентов по имени
     * @param name - имя контрагента
     * @return - список контрагентов
     */
    List<Counterparty> getByName(String name);

    /**
     * Получение контрагента по БИК и номеру счета
     * @param bic - БИК
     * @param accountNumber - номер счета
     * @return - контрагент с указанными БИК и номером счета
     */
    Counterparty getByBicAndAccountNumber(String bic, String accountNumber);
}
