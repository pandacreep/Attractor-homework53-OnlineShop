package com.pandacreep.onlineshop.service;

import com.pandacreep.onlineshop.model.Basket;
import com.pandacreep.onlineshop.model.Item;
import com.pandacreep.onlineshop.model.Order;
import com.pandacreep.onlineshop.repo.BasketRepository;
import com.pandacreep.onlineshop.repo.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class OrdersService {
    BasketService basketService;
    BasketRepository basketRepository;
    OrderRepository ordersRepository;

    public String add(String orderReceiverName, String phoneNumber,
                    String email, String address, String paymentType) {
        String idUser = basketService.getCurrentBasket().getId();
        LocalDateTime localDateTime = LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS);
        Order order = new Order(idUser, localDateTime, orderReceiverName,
                phoneNumber, email, address, paymentType);
        int orderAmount = basketService.getOrderAmount();
        order.setOrderAmount(orderAmount);
        Basket basket = basketService.getCurrentBasket();
        Map<String, Item> items = basket.getItems();
        order.setItems(items);
        Map<String, Integer> itemsQty = basket.getItemsQty();
        order.setItemsQty(itemsQty);
        ordersRepository.save(order);
        basket.getItems().clear();
        basket.getItemsQty().clear();
        basketRepository.save(basket);
        return "redirect:/";
    }

    public Iterable<Order> findAll() {
        return ordersRepository.findAll();
    }

    public List<Order> findByUser(String userId) {
        return ordersRepository.findByUser(userId);
    }
}
