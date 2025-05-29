package com.levi9.internship.service;

import com.levi9.internship.mapper.OrderMapper;
import com.levi9.internship.model.OrderDTO;
import com.levi9.internship.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    @Override
    public List<OrderDTO> getOrdersByUserId(String userId) {
        return orderRepository.getOrdersByUserId(userId).stream().map(orderMapper::toDto).toList();
    }

    @Override
    public OrderDTO createOrder(OrderDTO orderDTO) {
        return orderMapper.toDto(orderRepository.save(orderMapper.toDocument(orderDTO)));
    }
}
