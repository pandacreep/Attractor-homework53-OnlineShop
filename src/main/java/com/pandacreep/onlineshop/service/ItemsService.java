package com.pandacreep.onlineshop.service;

import com.pandacreep.onlineshop.model.Category;
import com.pandacreep.onlineshop.model.Image;
import com.pandacreep.onlineshop.model.Item;
import com.pandacreep.onlineshop.model.Producer;
import com.pandacreep.onlineshop.repo.ItemsRepository;
import com.pandacreep.onlineshop.repo.ReviewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ItemsService {
    private final ItemsRepository itemsRepository;
    private final ReviewsRepository reviewsRepository;
    private final ImageService imageService;

    public String add(String name, Category category, Producer producer,
                      String price, String description, MultipartFile file) {
        Item item = new Item(Integer.parseInt(price),
                name,
                description,
                producer,
                category);
        Image image = imageService.addImage(file);
        if (image != null) {
            item.setImage(image);
        }
        itemsRepository.save(item);

        System.out.println("Item added: " + item);
        return "redirect:/items";
    }

    public String edit(String itemId, String name, String price, String description) {
        var item = getItemById(itemId).get();
        item.setName(name);
        item.setPrice(Integer.parseInt(price));
        item.setDescription(description);
        itemsRepository.save(item);
        System.out.println("Item edited: " + item);
        return "redirect:/items";
    }

    public String delete(String itemId) {
        var item = getItemById(itemId).get();
        itemsRepository.delete(item);
        System.out.println("Item deleted: " + item);
        return "redirect:/items";
    }

    public Iterable<Item> findAll() {
        return itemsRepository.findAll();
    }

    public HashMap<String, String> getItems() {
        HashMap<String, String> items = new HashMap<>();
        itemsRepository.findAll()
                .forEach(
                        (x) -> items.put(x.getId(), x.getName())
                );
        return items;
    }

    public Optional<Item> getItemById(String id) {
        return itemsRepository.findById(id);
    }

    public void delete(Item item) {
        itemsRepository.delete(item);
    }

    public void save(Item item) {
        itemsRepository.save(item);
    }


    public boolean isProductUsedInItems(String productId) {
        for (Item item : itemsRepository.findAll()) {
            if (item.getProducer().getId().equals(productId)) return true;
        }
        return false;
    }

    public boolean isCategoryUsedInItems(String productId) {
        for (Item item : itemsRepository.findAll()) {
            if (item.getCategory().getId().equals(productId)) return true;
        }
        return false;
    }
}
