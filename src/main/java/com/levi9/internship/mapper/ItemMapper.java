package com.levi9.internship.mapper;

import com.levi9.internship.model.Item;
import com.levi9.internship.model.ItemDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ItemMapper {
    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);

    ItemDTO toDto(Item item);
    Item toDocument(ItemDTO itemDTO);
}
