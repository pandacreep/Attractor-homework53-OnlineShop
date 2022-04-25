package com.pandacreep.onlineshop.repo;

import com.pandacreep.onlineshop.model.Category;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends CrudRepository<Category, String> {}
