package com.pandacreep.onlineshop.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.Map;

@Document(collection = "orders")
@Data
public class    Order {
    @Id
    @NonNull private String id;

    @NonNull private String emailUser;
    @NonNull private LocalDateTime dateTime;
    @NonNull private String orderReceiverName;
    @NonNull private String phoneNumber;
    @NonNull private String emailReceiver;
    @NonNull private String address;
    @NonNull private String paymentType;
    private int orderAmount;
    private Map<String, Item> items;
    private Map<String, Integer> itemsQty;

    public Order(String emailUser, LocalDateTime dateTime, String orderReceiverName,
                 String phoneNumber, String emailReceiver, String address,
                 String paymentType) {
        this.emailUser = emailUser;
        this.dateTime = dateTime;
        this.orderReceiverName = orderReceiverName;
        this.phoneNumber = phoneNumber;
        this.emailReceiver = emailReceiver;
        this.address = address;
        this.paymentType = paymentType;
        this.id = emailUser + String.valueOf(dateTime);
    }
}
