package com.pandacreep.onlineshop.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "reviews")
@Data
public class Review {

    @Id
    private String id = UUID.randomUUID().toString();

    private String reviewerName;
    private String phone;
    private String email;
    private int stars;
    private String review;

    @DBRef
    private Item item;

    public Review(String reviewerName, String phone, String email, int stars, String review) {
        this.reviewerName = reviewerName;
        this.phone = phone;
        this.email = email;
        this.stars = stars;
        this.review = review;
    }
}
