package com.levi9.internship.mapper;

import com.levi9.internship.model.Order;
import com.levi9.internship.model.OrderDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    OrderDTO toDto(Order order);
    Order toDocument(OrderDTO orderDTO);
}
