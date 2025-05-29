package com.levi9.internship.repository;

import com.levi9.internship.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.expression.spel.ast.Literal;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends MongoRepository<Item, String> {

}
