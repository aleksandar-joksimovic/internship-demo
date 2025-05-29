package com.levi9.internship.service;

import com.levi9.internship.model.OrderDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrdersByUserId(String userId);
    OrderDTO createOrder(OrderDTO orderDTO);
}
