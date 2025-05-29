package com.levi9.internship.repository;

import com.levi9.internship.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends MongoRepository<Order, String> {

    List<Order> getOrdersByUserId(String userId);
}
