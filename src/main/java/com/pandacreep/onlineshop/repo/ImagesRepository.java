package com.pandacreep.onlineshop.repo;

import com.pandacreep.onlineshop.model.Image;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ImagesRepository extends MongoRepository<Image, String> {}
