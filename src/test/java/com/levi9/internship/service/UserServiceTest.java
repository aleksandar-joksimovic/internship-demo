package com.levi9.internship.service;

import com.levi9.internship.exception.BadRequestException;
import com.levi9.internship.exception.ConflictException;
import com.levi9.internship.exception.NotFoundException;
import com.levi9.internship.mapper.UserMapper;
import com.levi9.internship.model.User;
import com.levi9.internship.model.UserDTO;
import com.levi9.internship.repository.UserRepository;
import com.mongodb.client.result.UpdateResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Update;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock private MongoTemplate mongoTemplate;
    @Mock private UserMapper userMapper;
    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserByUsername_Success() {
        User user = new User();
        user.setUsername("john");

        when(userRepository.getUserByUsername("john")).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(new UserDTO());

        UserDTO result = userService.getUserByUsername("john");

        assertNotNull(result);
        verify(userRepository).getUserByUsername("john");
        verify(userMapper).toDto(user);
    }

    @Test
    void testGetUserByUsername_NotFound() {
        when(userRepository.getUserByUsername("notfound")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserByUsername("notfound"));
    }

    @Test
    void testGetUserByUserId_Success() {
        User user = new User();
        user.setUserId("123");

        when(userRepository.getUserByUserId("123")).thenReturn(Optional.of(user));
        when(userMapper.toDto(user)).thenReturn(new UserDTO());

        UserDTO result = userService.getUserByUserId("123");

        assertNotNull(result);
    }

    @Test
    void testGetUserByUserId_NotFound() {
        when(userRepository.getUserByUserId("missing")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.getUserByUserId("missing"));
    }

    @Test
    void testGetUsers() {
        List<User> users = List.of(new User(), new User());
        when(userRepository.findAll()).thenReturn(users);
        when(userMapper.toDto(any(User.class))).thenReturn(new UserDTO());

        List<UserDTO> result = userService.getUsers();

        assertEquals(2, result.size());
    }

    @Test
    void testGetUsersByFirstName_Success() {
        List<User> users = List.of(new User());
        when(userRepository.getUsersByFirstName("John")).thenReturn(users);

        List<User> result = userService.getUsersByFirstName("John");

        assertEquals(1, result.size());
    }

    @Test
    void testGetUsersByFirstName_NotFound() {
        when(userRepository.getUsersByFirstName("Xyz")).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class, () -> userService.getUsersByFirstName("Xyz"));
    }

    @Test
    void testCreateUser_UserAlreadyExists() {
        when(userRepository.getUserByUsername("exists")).thenReturn(Optional.of(new User()));
        User user = new User();
        user.setUsername("exists");

        assertThrows(ConflictException.class, () -> userService.createUser(user));
    }

    @Test
    void testCreateUser_Success() {
        User user = new User();
        user.setUsername("new");

        when(userRepository.getUserByUsername("new")).thenReturn(Optional.empty());
        when(userRepository.save(user)).thenReturn(user);

        User result = userService.createUser(user);

        assertNotNull(result);
    }

    @Test
    void testDeleteUserByUsername_Success() {
        when(userRepository.getUserByUsername("john"))
                .thenReturn(Optional.of(new User()))
                .thenReturn(Optional.empty());

        boolean result = userService.deleteUserByUsername("john");

        assertTrue(result);
        verify(userRepository).deleteByUsername("john");
    }

    @Test
    void testDeleteUserByUsername_UserNotFound() {
        when(userRepository.getUserByUsername("missing")).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, () -> userService.deleteUserByUsername("missing"));
    }

    @Test
    void testDeleteUserByUsername_StillPresent() {
        when(userRepository.getUserByUsername("john"))
                .thenReturn(Optional.of(new User()))
                .thenReturn(Optional.of(new User()));

        assertThrows(BadRequestException.class, () -> userService.deleteUserByUsername("john"));
    }

    @Test
    void testUpdateUser_Success() {
        User user = new User();
        user.setUserId("123");

        UpdateResult updateResult = mock(UpdateResult.class);
        when(updateResult.wasAcknowledged()).thenReturn(true);
        when(mongoTemplate.updateFirst(any(), any(Update.class), eq(User.class))).thenReturn(updateResult);

        boolean result = userService.updateUser(user);
        assertTrue(result);
    }

    @Test
    void testCreateUserDTO_Success() {
        UserDTO dto = new UserDTO();
        dto.setUsername("new");

        User user = new User();
        User saved = new User();

        when(userRepository.getUserByUsername("new")).thenReturn(Optional.empty());
        when(userMapper.toDocument(dto)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(saved);
        when(userMapper.toDto(saved)).thenReturn(new UserDTO());

        UserDTO result = userService.createUser(dto);

        assertNotNull(result);
    }

    @Test
    void testCreateUserDTO_NullDTO() {
        assertThrows(BadRequestException.class, () -> userService.createUser((UserDTO) null));
    }

    @Test
    void testCreateUserDTO_UsernameExists() {
        UserDTO dto = new UserDTO();
        dto.setUsername("exists");

        when(userRepository.getUserByUsername("exists")).thenReturn(Optional.of(new User()));

        assertThrows(ConflictException.class, () -> userService.createUser(dto));
    }
}
