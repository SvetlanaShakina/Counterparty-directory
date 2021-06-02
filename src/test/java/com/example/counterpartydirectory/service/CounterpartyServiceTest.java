package com.example.counterpartydirectory.service;

import com.example.counterpartydirectory.entity.Counterparty;
import com.example.counterpartydirectory.repository.CounterpartyRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@WebMvcTest(CounterpartyService.class)
@AutoConfigureMockMvc
class CounterpartyServiceTest {

    @MockBean
    private CounterpartyRepository repository;

    @Test
    void getById() {
        Counterparty counterparty = new Counterparty();
        counterparty.setId(1);
        when(repository.getById(1)).thenReturn(counterparty);
        Counterparty foundCounterparty = repository.getById(1);
        assertEquals(counterparty.getId(), foundCounterparty.getId());
        verify(repository, times(1)).getById(1);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void save() {
        Counterparty counterparty = new Counterparty();
        counterparty.setName("ГКЦ");
        counterparty.setInn("5501203515");
        counterparty.setKpp("550701001");
        counterparty.setBic("045004774");
        counterparty.setAccountNumber("40702810023050003212");
        when(repository.save(counterparty)).thenReturn(counterparty);
        Counterparty returnedCounterparty = repository.save(counterparty);
        assertEquals(counterparty.getName(), returnedCounterparty.getName());
        assertEquals(counterparty.getInn(), returnedCounterparty.getInn());
        assertEquals(counterparty.getBic(), returnedCounterparty.getBic());
        assertEquals(counterparty.getKpp(), returnedCounterparty.getKpp());
        assertEquals(counterparty.getAccountNumber(), returnedCounterparty.getAccountNumber());
        verify(repository, times(1)).save(counterparty);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void delete() {
        Counterparty counterparty = new Counterparty();
        doNothing().when(repository).delete(counterparty);
        repository.delete(counterparty);
        verify(repository, times(1)).delete(counterparty);
        verifyNoMoreInteractions(repository);
    }

    @Test
    void findPaginated() {
        Pageable pageable = PageRequest.of(0, 7);
        List<Counterparty> counterparties = new ArrayList<>();
        counterparties.add(new Counterparty());
        Page<Counterparty> itemPage = new PageImpl<>(counterparties, pageable, 20);
        when(repository.findAll(pageable)).thenReturn(itemPage);
        Page<Counterparty> foundCounterparties = repository.findAll(pageable);
        verify(repository, times(1)).findAll(pageable);
        assertEquals(foundCounterparties.getNumberOfElements(), 1);
    }

    @Test
    void getByName() {
        String name = "АБС";
        Counterparty counterparty = new Counterparty();
        counterparty.setName(name);
        when(repository.getByName(name)).thenReturn(Collections.singletonList(counterparty));
        List<Counterparty> foundCounterparties = repository.getByName(name);
        assertEquals("АБС", foundCounterparties.get(0).getName());
        assertEquals(1, foundCounterparties.size());
    }

    @Test
    void getByBicAndAccountNumber() {
        String bic = "045004774";
        String accountNumber = "40702810023050003212";
        Counterparty counterparty = new Counterparty();
        counterparty.setBic(bic);
        counterparty.setAccountNumber(accountNumber);
        when(repository.getByBicAndAccountNumber(bic, accountNumber)).thenReturn(counterparty);
        Counterparty foundCounterparty = repository.getByBicAndAccountNumber(bic, accountNumber);
        assertEquals("045004774", foundCounterparty.getBic());
        assertEquals("40702810023050003212", foundCounterparty.getAccountNumber());
    }
}