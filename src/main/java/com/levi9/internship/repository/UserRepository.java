package com.levi9.internship.repository;

import com.levi9.internship.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> getUserByUsername(String username);

    List<User> getUsersByFirstName(String firstName);

    void deleteByUsername(String username);

    Optional<User> getUserByUserId(String userId);
}
