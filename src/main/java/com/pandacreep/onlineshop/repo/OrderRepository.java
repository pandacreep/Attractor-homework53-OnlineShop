package com.pandacreep.onlineshop.repo;

import com.pandacreep.onlineshop.model.Order;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends CrudRepository<Order, String> {
    @Query(value = "{ 'emailUser' : ?0 }", sort = "{'dateTime':-1}")
    List<Order> findByUser(String userId);
}
