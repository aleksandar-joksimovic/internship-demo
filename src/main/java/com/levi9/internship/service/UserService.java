package com.levi9.internship.service;

import com.levi9.internship.model.User;
import com.levi9.internship.model.UserDTO;

import java.util.List;

public interface UserService {

    UserDTO getUserByUsername(String username);
    UserDTO getUserByUserId(String userId);
    List<UserDTO> getUsers();
    List<User> getUsersByFirstName(String firstName);
    User createUser(User user);
    boolean deleteUserByUsername(String username);
    boolean updateUser(User user);
    UserDTO createUser(UserDTO userDTO);
}
