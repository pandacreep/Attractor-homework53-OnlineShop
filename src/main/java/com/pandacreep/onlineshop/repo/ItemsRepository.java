package com.pandacreep.onlineshop.repo;

import com.pandacreep.onlineshop.model.Item;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemsRepository extends CrudRepository<Item, String> {}
