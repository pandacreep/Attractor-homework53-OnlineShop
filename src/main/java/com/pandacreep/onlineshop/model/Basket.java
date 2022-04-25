package com.pandacreep.onlineshop.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document(collection = "basket")
@Data
public class Basket {
    @Id @NonNull String id;

    @DBRef
    Map<String, Item> items;

    Map<String, Integer> itemsQty;

    @DBRef
    @NonNull
    User user;

    public Basket(String id, User user) {
        this.id = id;
        this.user = user;
        this.items = new HashMap<>();
        this.itemsQty = new HashMap<>();
    }
}
