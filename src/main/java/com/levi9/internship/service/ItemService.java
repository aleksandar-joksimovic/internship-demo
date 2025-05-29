package com.levi9.internship.service;

import com.levi9.internship.model.ItemDTO;

import java.util.List;

public interface ItemService {

    List<ItemDTO> getItems();
    ItemDTO createItem(ItemDTO itemDTO);
}
