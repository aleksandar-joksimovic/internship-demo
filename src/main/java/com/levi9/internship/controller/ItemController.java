package com.levi9.internship.controller;

import com.levi9.internship.api.ItemsApi;
import com.levi9.internship.model.ItemDTO;
import com.levi9.internship.service.ItemService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ItemController implements ItemsApi {

    private final ItemService itemService;

    @Override
    public ResponseEntity<ItemDTO> createItem(@Valid ItemDTO itemDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.createItem(itemDTO));
    }

    @Override
    public ResponseEntity<List<ItemDTO>> getItems() {
        return ResponseEntity.ok(itemService.getItems());
    }
}


