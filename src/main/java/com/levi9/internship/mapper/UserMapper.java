package com.levi9.internship.mapper;

import com.levi9.internship.model.User;
import com.levi9.internship.model.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDTO toDto(User user);
    User toDocument(UserDTO userDTO);

}
