package com.pandacreep.onlineshop.controller;

import com.pandacreep.onlineshop.service.BasketService;
import com.pandacreep.onlineshop.service.OrdersService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@AllArgsConstructor
public class OrdersController {
    OrdersService ordersService;
    BasketService basketService;

    @PostMapping("/addOrder")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String addOrder(@RequestParam String orderReceiverName,
                           @RequestParam String phoneNumber,
                           @RequestParam String email,
                           @RequestParam(defaultValue = "Self-pickup") String address,
                           @RequestParam String paymentType
                           ) {
        return ordersService.add(orderReceiverName, phoneNumber, email, address, paymentType);
    }

    @GetMapping("/orders")
    public String showOrders(Model model) {
        String loggedUser = basketService.getLoggedUserOrNull();
        if (loggedUser == null) {
            model.addAttribute("logins", basketService.findAll());
            return "redirect:/login";
        }
        model.addAttribute("userId", loggedUser);
        return "orders/orders-list-authorise";
    }

    @PostMapping("/showOrders")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String showOrders(@RequestParam String userId,
                             @RequestParam String checkText, Model model) {
        if (!userId.equalsIgnoreCase(checkText)) {
            return "redirect:/orders";
        }
        return "redirect:/orders/orders-list";
    }

    @GetMapping("/orders/orders-list")
    public String showOrdersList(Model model) {
        String loggedUser = basketService.getLoggedUserOrNull();
        model.addAttribute("userId", loggedUser);
        model.addAttribute("orders", ordersService.findByUser(loggedUser));
        return "orders/orders-list";
    }
}
