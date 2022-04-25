package com.pandacreep.onlineshop.service;

import com.pandacreep.onlineshop.repo.CategoriesRepository;
import com.pandacreep.onlineshop.model.Category;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CategoriesService {
    private final CategoriesRepository repository;
    private final ItemsService itemsService;

    public String add(String name, String description) {
        Category category = new Category(name, description);
        repository.save(category);
        System.out.println("Category added: " + category);
        return "redirect:/categories";
    }

    public String edit(String categoryId, String name, String description) {
        var category = getItemById(categoryId).get();
        category.setName(name);
        category.setDescription(description);
        repository.save(category);
        System.out.println("Category edited: " + category);
        return "redirect:/categories";
    }

    public String delete (String categoryId) {
        var category = getItemById(categoryId).get();
        repository.delete(category);
        System.out.println("Category deleted: " + category);
        return "redirect:categories";
    }

    public Iterable<Category> findAll() {
        return repository.findAll();
    }

    public HashMap<String, String> getCategories() {
        HashMap<String, String> categories = new HashMap<>();
        repository.findAll()
                .forEach(
                        (x) -> categories.put(x.getId(), x.getName())
                );
        return categories;
    }

    public Map<Category, Boolean> getCategoriesWithUsageInfo() {
        Map<Category, Boolean> categories = new HashMap<>();
        repository.findAll()
                .forEach(
                        x -> categories.put(x, itemsService.isCategoryUsedInItems(x.getId()))
                );
        return categories;
    }

    public Optional<Category> getItemById(String id) {
        return repository.findById(id);
    }

    public void delete(Category category) {
        repository.delete(category);
    }

    public Optional<Category> getCategoryById(String id) {
        return repository.findById(id);
    }

    public void save(Category category) {
        repository.save(category);
    }
}
