package com.pandacreep.onlineshop.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "items")
@Data
public class Item {
    @Id
    private String id;

    @NonNull
    private int price;

    @NonNull
    private String name;

    @NonNull
    private String description;

    @DBRef
    @Indexed
    @NonNull
    private Producer producer;

    @DBRef
    @Indexed
    @NonNull
    private Category category;

    @DBRef
    @Indexed
    @NonNull
    private Image image;

    public Item(int price, String name, String description, Producer producer, Category category) {
        this.id = UUID.randomUUID().toString();
        this.price = price;
        this.name = name;
        this.description = description;
        this.producer = producer;
        this.category = category;
    }
}
