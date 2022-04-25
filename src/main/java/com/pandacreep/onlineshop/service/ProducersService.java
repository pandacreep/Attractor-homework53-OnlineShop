package com.pandacreep.onlineshop.service;

import com.pandacreep.onlineshop.model.Producer;
import com.pandacreep.onlineshop.repo.ProducersRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProducersService {
    private final ProducersRepository repository;
    private final ItemsService itemsService;

    public String add(String name, String country) {
        Producer producer = new Producer(name, country);
        repository.save(producer);
        System.out.println("Producer added: " + producer);
        return "redirect:/producers";
    }

    public String edit(String producerId, String name, String country) {
        var producer = getItemById(producerId).get();
        producer.setName(name);
        producer.setCountry(country);
        repository.save(producer);
        System.out.println("Producer edited: " + producer);
        return "redirect:/producers";
    }

    public String delete(String producerId) {
        var producer = getItemById(producerId).get();
        repository.delete(producer);
        System.out.println("Producer deleted: " + producer);
        return "redirect:producers";
    }

    public Iterable<Producer> findAll() {
        return repository.findAll();
    }

    public HashMap<String, String> getProducers() {
        HashMap<String, String> producers = new HashMap<>();
        repository.findAll()
                .forEach(
                        (x) -> producers.put(x.getId(), x.getName())
                );
        return producers;
    }

    public Map<Producer, Boolean> getProducersWithUsageInfo() {
        Map<Producer, Boolean> producers = new HashMap<>();
        repository.findAll()
                .forEach(
                        (x) -> producers.put(x, itemsService.isProductUsedInItems(x.getId()))
                );
        return producers;
    }

    public Optional<Producer> getItemById(String id) {
        return repository.findById(id);
    }

    public Optional<Producer> getProducerById(String id) {
        return repository.findById(id);
    }
}
