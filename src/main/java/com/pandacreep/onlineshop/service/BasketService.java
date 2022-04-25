package com.pandacreep.onlineshop.service;

import com.pandacreep.onlineshop.model.Basket;
import com.pandacreep.onlineshop.model.Item;
import com.pandacreep.onlineshop.model.User;
import com.pandacreep.onlineshop.repo.BasketRepository;
import com.pandacreep.onlineshop.repo.ItemsRepository;
import com.pandacreep.onlineshop.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@Service
@AllArgsConstructor
public class BasketService {
    private UserRepository userRepository;
    private BasketRepository basketRepository;
    private ItemsRepository itemsRepository;

    public String getLoggedUserOrNull() {
        for (User user : userRepository.findAll()) {
            if (user.isLogged()) return user.getEmail();
        }
        return null;
    }

    public String login(String userId) {
        userRepository.findAll()
                .forEach(user -> {
                    user.setLogged(false);
                    userRepository.save(user);
                });
        User user = userRepository.findById(userId).get();
        user.setLogged(true);
        userRepository.save(user);
        Optional<Basket> basketOpt = basketRepository.findById(userId);
        Basket basket;
        if (basketOpt.isPresent()) {
            basket = basketOpt.get();
        } else {
            basket = new Basket(userId, user);
            basketRepository.save(basket);
        }
        return "redirect:/";
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public String addItem(String userId, String itemId, String qty) {
        Optional<Basket> basketOpt = basketRepository.findById(userId);
        Basket basket;
        if (basketOpt.isPresent()) {
            basket = basketOpt.get();
        } else {
            User user = userRepository.findById(userId).get();
            basket = new Basket(userId, user);
        }
        Item item = itemsRepository.findById(itemId).get();
        basket.getItems().put(itemId, item);
        basket.getItemsQty().put(itemId, Integer.parseInt(qty));
        basketRepository.save(basket);
        return "redirect:/basket";
    }

    public String clearBasket(String checkText) {
        String loggedUser = getLoggedUserOrNull();
        if (checkText.equalsIgnoreCase(loggedUser)) {
            Basket basket = basketRepository.findById(loggedUser).get();
            basket.getItems().clear();
            basket.getItemsQty().clear();
            basketRepository.save(basket);
        }
        return "redirect:/basket";
    }

    public Basket getBasket(String userId) {
        return basketRepository.findById(userId).get();
    }

    public Basket getCurrentBasket() {
        String loggedUser = getLoggedUserOrNull();
        return basketRepository.findById(loggedUser).get();
    }

    public String deleteItemFromBasket(String itemId) {
        Item item = itemsRepository.findById(itemId).get();
        String loggedUser = getLoggedUserOrNull();
        Basket basket = getBasket(loggedUser);
        basket.getItems().remove(itemId);
        basket.getItemsQty().remove(itemId);
        basketRepository.save(basket);
        return "basket/basket";
    }

    public int getOrderAmount() {
        String loggedUser = getLoggedUserOrNull();
        Basket basket = basketRepository.findById(loggedUser).get();
        int amount = 0;
        Map<String, Item> items = basket.getItems();
        for (Map.Entry<String, Item> item : items.entrySet()) {
            int price = item.getValue().getPrice();
            int qty = basket.getItemsQty().get(item.getKey());
            amount += price * qty;
        }
        return amount;
    }
}
