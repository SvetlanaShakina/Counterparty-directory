package com.example.counterpartydirectory.controller;

import com.example.counterpartydirectory.entity.Counterparty;
import com.example.counterpartydirectory.service.CounterpartyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/counterparties")
public class CounterpartyController {

    @Autowired
    private CounterpartyService service;

    /**
     * Получение списка контрагентов
     * @param model - модель для отображения в UI
     * @param name - имя контрагента (если поиск ведется по имени)
     * @param bic - БИК контрагента (если поиск ведется по БИК и номеру счета)
     * @param accountNumber - номер счета контрагента (если поиск ведется по БИК и номеру счета)
     * @return - страница со списком контрагентов
     */
    @GetMapping()
    public String getAll(Model model, String name, String bic, String accountNumber) {
        if (name != null && !name.equals("")) {
            model.addAttribute("counterparties", service.getByName(name));
            return "index";
        } else if (bic != null && accountNumber != null) {
            model.addAttribute("counterparties", service.getByBicAndAccountNumber(bic, accountNumber));
            return "index";
        } else {
            return findPaginated(1, model);
        }
    }

    /**
     * Получение формы для добавления нового контрагента
     * @param counterparty - модель для создания контрагента
     * @return - страница с формой для добавления нового контрагента
     */
    @GetMapping("/new")
    public String newCounterparty(@ModelAttribute("counterparty") Counterparty counterparty) {
        return "new";
    }

    /**
     * Сохранение или обновление контрагента
     * @param counterparty - контрагент с заполненными данными
     * @param bindingResult - объект для хранения информации об обнаруженных ошибках
     * @return - страница со списком контрагентов
     */
    @PostMapping()
    public String save(@ModelAttribute("counterparty") @Valid Counterparty counterparty, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return "new";
        service.save(counterparty);
        return "redirect:/counterparties";
    }

    /**
     * Получение формы для редактирования контрагента
     * @param model - модель с заполненными данными контрагента
     * @param id - идентификатор контрагента
     * @return - страница для редактирования контрагента
     */
    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("counterparty", service.getById(id));
        return "edit";
    }

    /**
     * Удаление контрагента
     * @param id - идентификатор контрагента
     * @return - страница со списком контрагентов
     */
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        service.delete(id);
        return "redirect:/counterparties";
    }

    /**
     * Получение списка контрагентов с пагинацией
     * @param pageNumber - номер начальной страницы
     * @param model - модель для отображения данных
     * @return - страница со списком контрагентов
     */
    @GetMapping("/page/{pageNumber}")
    public String findPaginated(@PathVariable(value = "pageNumber") int pageNumber, Model model) {
        int pageSize = 7;
        Page<Counterparty> page = service.findPaginated(pageNumber, pageSize);
        if (page != null) {
            List<Counterparty> counterparties = page.getContent();
            model.addAttribute("currentPage", pageNumber);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("counterparties", counterparties);
        }
        return "index";
    }
}