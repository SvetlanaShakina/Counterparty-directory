package com.example.counterpartydirectory.controller;

import com.example.counterpartydirectory.entity.Counterparty;
import com.example.counterpartydirectory.service.CounterpartyService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.instanceOf;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CounterpartyController.class)
@AutoConfigureMockMvc
class CounterpartyControllerTest {

    @MockBean
    private CounterpartyService service;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getAll() throws Exception {
        Pageable pageable = PageRequest.of(0, 7);
        List<Counterparty> counterparties = new ArrayList<>();
        counterparties.add(new Counterparty());
        Page<Counterparty> itemPage = new PageImpl<>(counterparties, pageable, 20);
        when(service.findPaginated(0, 7)).thenReturn(itemPage);

        mockMvc.perform(get("/counterparties"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"));
    }

    @Test
    void testNewCounterparty() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/counterparties/new"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("new"));
    }

    @Test
    void testSave() throws Exception {
        Counterparty counterparty = new Counterparty();
        doNothing().when(service).save(counterparty);
        service.save(counterparty);
        mockMvc.perform(MockMvcRequestBuilders.post("/counterparties"))
                .andExpect(status().isOk());
        verify(service, times(1)).save(counterparty);
        verifyNoMoreInteractions(service);
    }

    @Test
    void testEdit() throws Exception {
        when(service.getById(1)).thenReturn(new Counterparty());
        mockMvc.perform(get("/counterparties/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("edit"))
                .andExpect(model().attribute("counterparty", instanceOf(Counterparty.class)));
    }

    @Test
    void testDelete() throws Exception {
        service.delete(1);
        verify(service, times(1)).delete(1);
        mockMvc.perform(MockMvcRequestBuilders.delete("/counterparties/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/counterparties"));
    }
}