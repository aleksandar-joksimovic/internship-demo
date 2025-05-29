package com.levi9.internship.service;

import com.levi9.internship.mapper.ItemMapper;
import com.levi9.internship.model.ItemDTO;
import com.levi9.internship.repository.ItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    @Override
    public List<ItemDTO> getItems() {
        return itemRepository.findAll().stream().map(itemMapper::toDto).toList();
    }

    @Override
    public ItemDTO createItem(ItemDTO itemDTO) {
        return itemMapper.toDto(itemRepository.save(itemMapper.toDocument(itemDTO)));
    }
}
