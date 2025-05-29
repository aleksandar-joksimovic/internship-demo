package com.levi9.internship.service;

import com.levi9.internship.exception.BadRequestException;
import com.levi9.internship.exception.ConflictException;
import com.levi9.internship.exception.NotFoundException;
import com.levi9.internship.mapper.UserMapper;
import com.levi9.internship.model.User;
import com.levi9.internship.model.UserDTO;
import com.levi9.internship.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MongoTemplate mongoTemplate;
    private final UserMapper userMapper;

    @Override
    public UserDTO getUserByUsername(String username) {

        Optional<User> optionalUser = userRepository.getUserByUsername(username);
        if (optionalUser.isPresent())
            return userMapper.toDto(optionalUser.get());
        else
            throw new NotFoundException("User with provided Username is not found!");
    }

    @Override
    public UserDTO getUserByUserId(String userId) {
        Optional<User> optionalUser = userRepository.getUserByUserId(userId);
        if (optionalUser.isPresent())
            return userMapper.toDto(optionalUser.get());
        else
            throw new NotFoundException("User with provided UserID is not found!");
    }

    @Override
    public List<UserDTO> getUsers() {
        return userRepository
                .findAll()
                .stream()
                .map(userMapper::toDto)
                .toList();

    }

    @Override
    public List<User> getUsersByFirstName(String firstName) {
        List<User> users = userRepository.getUsersByFirstName(firstName);
        if (users.isEmpty())
            throw new NotFoundException("Users with provided Firstname does not exists!");
        return users;
    }

    @Override
    public User createUser(User user) {
        if (isUsernameValid(user.getUsername())) {
            throw new ConflictException("Username already exist!");
        }
        return userRepository.save(user);
    }

    @Override
    public boolean deleteUserByUsername(String username) {

        if (isUserPresent(username)) {
            userRepository.deleteByUsername(username);
            if (isUserPresent(username)) {
                throw new BadRequestException("Something went wrong. User is not deleted!");
            }
            else {
                return true;
            }
        } else {
            throw new NotFoundException("User with provided ID is not found!");
        }

    }

    @Override
    public boolean updateUser(User updatedUser) {

        Query query = new Query(Criteria.where("_id").is(updatedUser.getUserId()));
        Update update = new Update()
                .set("username", updatedUser.getUsername())
                .set("firstName", updatedUser.getFirstName())
                .set("lastName", updatedUser.getLastName())
                .set("email", updatedUser.getEmail());

        UpdateResult updateResult = mongoTemplate.updateFirst(query, update, User.class);
        return updateResult.wasAcknowledged();
    }

    @Override
    public UserDTO createUser(UserDTO userDTO) {
        if (userDTO != null) {

            if (isUsernameValid(userDTO.getUsername())) {
                throw new ConflictException("Username already exist!");
            }

            User user = userMapper.toDocument(userDTO);
            User savedUser = userRepository.save(user);
            return userMapper.toDto(savedUser);
        } else {
            throw new BadRequestException("Something went wrong. User is not created!");
        }
    }

    private boolean isUsernameValid(String username) {
        return userRepository.getUserByUsername(username).isPresent();
    }

    private boolean isUserPresent(String username) {
        return userRepository.getUserByUsername(username).isPresent();
    }

    private boolean isUsernamePresent(UserDTO userDTO){
        return false;
    }
}
