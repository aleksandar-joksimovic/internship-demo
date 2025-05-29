package com.levi9.internship.model.dto;

import com.levi9.internship.model.Item;
import com.levi9.internship.model.ItemDTO;

public class ItemMapper {

    public ItemResponseDTO toItemResponseDto(Item item) {
        return ItemResponseDTO.builder()
                .name(item.getName())
                .price(item.getPrice())
                .build();
    }
}
