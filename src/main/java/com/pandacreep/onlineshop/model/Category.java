package com.pandacreep.onlineshop.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "categories")
@Data
public class Category {
    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String description;

    public Category(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }
}
