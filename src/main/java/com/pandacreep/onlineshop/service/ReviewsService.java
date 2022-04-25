package com.pandacreep.onlineshop.service;

import com.pandacreep.onlineshop.model.Review;
import com.pandacreep.onlineshop.repo.ItemsRepository;
import com.pandacreep.onlineshop.repo.ReviewsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class ReviewsService {
    ReviewsRepository reviewsRepository;
    ItemsRepository itemsRepository;
    ItemsService itemsService;

    public String add(String itemId,
                      String reviewerName,
                      String phone,
                      String email,
                      int stars,
                      String review) {
        Review newReview = new Review(reviewerName, phone, email, stars, review);
        var item = itemsService.getItemById(itemId).get();
        newReview.setItem(item);
        reviewsRepository.save(newReview);
        return null;
    }

    public List<Review> findByReviewId(String itemId) {
        return reviewsRepository.findByReviewId(itemId);
    }

    public Map<String, Double> getStars(){
        HashMap<String, Double> items = new HashMap<>();
        itemsRepository.findAll()
                .forEach((i) -> items.put(i.getId(), 0.0));
        items.forEach( (k, v) -> {
            List<Review> reviews = findByReviewId(k);
            int count = reviews.size();
            double sum = reviews.stream().mapToDouble(r -> r.getStars()).sum();
            items.put(k, sum == 0 ? 0 : sum / count);
        });
        return items;
    }
}
