package com.pandacreep.onlineshop.repo;

import com.pandacreep.onlineshop.model.Producer;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProducersRepository extends CrudRepository<Producer, String> {}
