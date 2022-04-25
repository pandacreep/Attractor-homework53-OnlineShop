package com.pandacreep.onlineshop.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "producers")
@Data
public class Producer {
    @Id
    private String id;

    @NonNull
    private String name;

    @NonNull
    private String country;

    public Producer(String name, String country) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.country = country;
    }
}
