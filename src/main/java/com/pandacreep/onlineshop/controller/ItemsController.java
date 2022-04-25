package com.pandacreep.onlineshop.controller;

import com.pandacreep.onlineshop.model.*;
import com.pandacreep.onlineshop.service.*;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.List;

@Controller
@AllArgsConstructor
public class ItemsController {
    private final ProducersService producersService;
    private final CategoriesService categoriesService;
    private final ItemsService itemService;
    private final BasketService basketService;
    private final ReviewsService reviewsService;

    @GetMapping("/items")
    public String showItems(Model model) {
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("avgScore", reviewsService.getStars());
        return "items/items";
    }

    @GetMapping("/items/add")
    public String addItem(Model model) {
        model.addAttribute("items", itemService.findAll());
        model.addAttribute("producers", producersService.getProducers());
        model.addAttribute("categories", categoriesService.getCategories());
        return "items/item-add";
    }

    @PostMapping("/addItem")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addItem(@RequestParam String name,
                          @RequestParam String categoryId,
                          @RequestParam String producerId,
                          @RequestParam String price,
                          @RequestParam String description,
                          @RequestParam MultipartFile file) {
        Category category = categoriesService.getCategoryById(categoryId).get();
        Producer producer = producersService.getProducerById(producerId).get();
        return itemService.add(name, category, producer, price, description, file);
    }

    @GetMapping("/items/{itemId}/details")
    public String showItemDetails(@PathVariable String itemId, Model model) {
        Item item = itemService.getItemById(itemId).get();
        model.addAttribute("item", item);
        Image image = item.getImage();
        if (image != null) {
            model.addAttribute("image",
                    Base64.getEncoder().encodeToString(image.getImage().getData()));
        }
        String s = Base64.getEncoder().encodeToString(image.getImage().getData());
        List<Review> reviews = reviewsService.findByReviewId(itemId);
        model.addAttribute("reviews", reviews);
        return "items/item-details";
    }

    @GetMapping("/items/{itemId}/edit")
    public String editItem(@PathVariable String itemId, Model model) {
        model.addAttribute("item",
                itemService.getItemById(itemId).get());
        return "items/item-edit";
    }

    @PostMapping("/editItem")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String editItem(@RequestParam String itemId,
                           @RequestParam String name,
                           @RequestParam String price,
                           @RequestParam String description) {
        return itemService.edit(itemId, name, price, description);
    }

    @GetMapping("/items/{itemId}/delete")
    public String deleteItem(@PathVariable String itemId, Model model) {
        model.addAttribute("item",
                itemService.getItemById(itemId).get());
        return "items/item-delete";
    }

    @PostMapping("/deleteItem")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String deleteItem(@RequestParam String itemId) {
        return itemService.delete(itemId);
    }

    @PostMapping("/addReview")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addReview(@RequestParam String itemId,
                            @RequestParam String reviewerName,
                            @RequestParam String phone,
                            @RequestParam String email,
                            @RequestParam int stars,
                            @RequestParam String review) {
        reviewsService.add(itemId, reviewerName, phone, email, stars, review);
        return "redirect:/items/" + itemId + "/details";
    }
}
