package com.pandacreep.onlineshop.controller;

import com.pandacreep.onlineshop.service.BasketService;
import com.pandacreep.onlineshop.service.ItemsService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@AllArgsConstructor
public class BasketController {
    private final BasketService basketService;
    private final ItemsService itemsService;

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("logins", basketService.findAll());
        return "login/login";
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String login(@RequestParam String userId) {
        return basketService.login(userId);
    }

    @GetMapping("/items/{itemId}/buy")
    public String addItemToBasket(@PathVariable String itemId, Model model) {
        String userLogged = basketService.getLoggedUserOrNull();
        if (userLogged == null)
            return "redirect:/login";
        model.addAttribute("item",
                itemsService.getItemById(itemId).get());
        model.addAttribute("userLogged", userLogged);
        model.addAttribute("qty", 1);
        return "basket/select-item-qty";
    }

    @PostMapping("/select-item")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String putItemToBasket(@RequestParam String userId,
                                  @RequestParam String itemId,
                                  @RequestParam String qty,
                                  Model model) {
        String template = basketService.addItem(userId, itemId, qty);
        model.addAttribute("userLogged",
                basketService.getLoggedUserOrNull());
        model.addAttribute("basket",
                basketService.getBasket(userId));
        model.addAttribute("items",
                basketService.getBasket(userId).getItems());
        return template;
    }

    @GetMapping("/basket/{basketId}/clear")
    public String clearBasket(@PathVariable String basketId, Model model) {
        model.addAttribute(basketId);
        return "basket/basket-clear";
    }

    @PostMapping("/clearBasket")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String clearBasket(@RequestParam String checkText) {
        return basketService.clearBasket(checkText);
    }

    @GetMapping("/basket")
    public String showBasket(Model model) {
        String loggedUser = basketService.getLoggedUserOrNull();
        if (loggedUser == null)
            return "redirect:/login";
        model.addAttribute("userLogged",
                basketService.getLoggedUserOrNull());
        model.addAttribute("basket",
                basketService.getBasket(loggedUser));
        model.addAttribute("items",
                basketService.getBasket(loggedUser).getItems());
        return "basket/basket";
    }

    @GetMapping("/basket/{itemId}/delete")
    public String deleteItemFromBasket(@PathVariable String itemId, Model model) {
        String template = basketService.deleteItemFromBasket(itemId);
        String loggedUser = basketService.getLoggedUserOrNull();
        model.addAttribute("userLogged", loggedUser);
        model.addAttribute("basket",
                basketService.getBasket(loggedUser));
        model.addAttribute("items",
                basketService.getBasket(loggedUser).getItems());
        return template;
    }

    @GetMapping("/basket/{itemId}/edit")
    public String editItemInBasket(@PathVariable String itemId, Model model) {
        String loggedUser = basketService.getLoggedUserOrNull();
        model.addAttribute("item",
                itemsService.getItemById(itemId).get());
        model.addAttribute("userLogged", loggedUser);
        model.addAttribute("qty",
                basketService.getBasket(loggedUser).getItemsQty().get(itemId));
        return "basket/select-item-qty";
    }

    @GetMapping("/orders/create")
    public String createOrder(Model model) {
        int orderAmount = basketService.getOrderAmount();
        if (orderAmount == 0) return "redirect:/basket/basket";
        model.addAttribute("amount", orderAmount);

        String loggedUser = basketService.getLoggedUserOrNull();
        model.addAttribute("userLogged",
                basketService.getLoggedUserOrNull());
        model.addAttribute("basket",
                basketService.getBasket(loggedUser));
        model.addAttribute("items",
                basketService.getBasket(loggedUser).getItems());
        return "orders/order";
    }
}
