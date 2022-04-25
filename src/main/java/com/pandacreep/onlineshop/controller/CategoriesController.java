package com.pandacreep.onlineshop.controller;

import com.pandacreep.onlineshop.service.CategoriesService;
import com.pandacreep.onlineshop.service.ItemsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class CategoriesController {
    private final CategoriesService categoriesService;
    private final ItemsService itemsService;

    @GetMapping("/categories")
    public String producer(Model model) {
        model.addAttribute("categories",
                categoriesService.getCategoriesWithUsageInfo());
        return "categories/categories";
    }

    @GetMapping("/categories/add")
    public String addCategory() {
        return "categories/category-add";
    }

    @PostMapping("/addCategory")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addCategory(@RequestParam String name,
                              @RequestParam String description) {
        return categoriesService.add(name, description);
    }

    @GetMapping("/categories/{categoryId}/details")
    public String showCategoryDetails(@PathVariable String categoryId, Model model) {
        model.addAttribute("category",
                categoriesService.getItemById(categoryId).get());
        return "categories/category-details";
    }

    @GetMapping("/categories/{categoryId}/edit")
    public String editCategory(@PathVariable String categoryId, Model model) {
        model.addAttribute("category",
                categoriesService.getItemById(categoryId).get());
        return "categories/category-edit";
    }

    @PostMapping("/editCategory")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String editCategory(@RequestParam String categoryId,
                               @RequestParam String name,
                               @RequestParam String description) {
        return categoriesService.edit(categoryId, name, description);
    }

    @GetMapping("/categories/{categoryId}/delete")
    public String deleteCategory(@PathVariable String categoryId, Model model) {
        model.addAttribute("category",
                categoriesService.getItemById(categoryId).get());
        model.addAttribute("categoryUsedInItems",
                itemsService.isCategoryUsedInItems(categoryId));
        return "categories/category-delete";
    }

    @PostMapping("/deleteCategory")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteCategory(@RequestParam String categoryId) {
        return categoriesService.delete(categoryId);
    }
}
