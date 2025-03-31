package com.dhami.blog.service;

import com.dhami.blog.payloads.UserDTO;

import java.util.List;

public interface UserService {
    UserDTO createUser(UserDTO user);
    List<UserDTO> getAllUsers();
    UserDTO getUserById(Long id);
    UserDTO updateUser(UserDTO user, Long id);
    void deleteUser(Long id);
}
