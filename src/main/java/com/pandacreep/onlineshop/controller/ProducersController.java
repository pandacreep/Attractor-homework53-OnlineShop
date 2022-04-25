package com.pandacreep.onlineshop.controller;

import com.pandacreep.onlineshop.service.ItemsService;
import com.pandacreep.onlineshop.service.ProducersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class ProducersController {
    private final ProducersService producersService;
    private final ItemsService itemsService;

    @GetMapping("/producers")
    public String producer(Model model) {
        model.addAttribute("producers", producersService.getProducersWithUsageInfo());
        return "producers/producers";
    }

    @GetMapping("/producers/add")
    public String addItem() {
        return "producers/producer-add";
    }

    @PostMapping("/addProducer")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addItem(@RequestParam String name,
                          @RequestParam String country) {
        return producersService.add(name, country);
    }

    @GetMapping("/producers/{producerId}/details")
    public String showProducerDetails(@PathVariable String producerId, Model model) {
        var producer = producersService.getItemById(producerId).get();
        model.addAttribute("producer", producer);
        return "producers/producer-details";
    }

    @GetMapping("/producers/{producerId}/edit")
    public String editProducer(@PathVariable String producerId, Model model) {
        var producer = producersService.getItemById(producerId).get();
        model.addAttribute("producer", producer);
        return "producers/producer-edit";
    }

    @PostMapping("/editProducer")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String editItem(@RequestParam String producerId,
                           @RequestParam String name,
                           @RequestParam String country) {
        return producersService.edit(producerId, name, country);
    }

    @GetMapping("/producers/{producerId}/delete")
    public String deleteProducer(@PathVariable String producerId, Model model) {
        model.addAttribute("producer",
                producersService.getItemById(producerId).get());
        model.addAttribute("producerUsedInItems",
                itemsService.isProductUsedInItems(producerId));
        return "producers/producer-delete";
    }

    @PostMapping("/deleteProducer")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteItem(@RequestParam String producerId) {
        producersService.delete(producerId);
        return "redirect:producers";
    }
}
