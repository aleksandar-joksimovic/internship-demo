package com.levi9.internship.controller;

import com.levi9.internship.model.DeleteResponse;
import com.levi9.internship.model.OrderDTO;
import com.levi9.internship.model.UserDTO;
import com.levi9.internship.service.OrderService;
import com.levi9.internship.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin("*/*")
@RequestMapping("/api/v1")
public class UserController implements UsersApi {

    private final UserService userService;
    private final OrderService orderService;


    @Override
    public ResponseEntity<UserDTO> createUser(UserDTO userDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.createUser(userDTO));
    }

    @Override
    public ResponseEntity<DeleteResponse> deleteUserByUsername(String username) {
        DeleteResponse deleteResponse = new DeleteResponse();
        deleteResponse.setMessage("Deleted");
        deleteResponse.setValue(userService.deleteUserByUsername(username));
        return ResponseEntity.ok(deleteResponse);
    }

    @Override
    public ResponseEntity<List<OrderDTO>> getOrdersByUserId(String userId) {
        return ResponseEntity.ok(orderService.getOrdersByUserId(userId));
    }

    @Override
    public ResponseEntity<UserDTO> getUserById(String userId) {
        return ResponseEntity.ok(userService.getUserByUserId(userId));
    }

    @Override
    public ResponseEntity<UserDTO> getUserByUsername(String username) {
        return ResponseEntity.ok(userService.getUserByUsername(username));
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        return ResponseEntity.ok(userService.getUsers());
    }
}
