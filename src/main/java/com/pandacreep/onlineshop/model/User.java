package com.pandacreep.onlineshop.model;

import lombok.Data;
import lombok.NonNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users")
@Data
public class User {
    @Id
    @NonNull
    String email;

    @NonNull
    String fullName;

    @NonNull
    boolean isLogged;
}
