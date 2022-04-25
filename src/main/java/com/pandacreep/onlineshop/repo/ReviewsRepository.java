package com.pandacreep.onlineshop.repo;

import com.pandacreep.onlineshop.model.Review;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewsRepository extends CrudRepository<Review, String> {
    @Query(value = "{ 'item.id' : ?0 }")
    List<Review> findByReviewId(String itemId);
}
