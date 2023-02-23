package com.codecool.stackoverflowtw.service.userService;

import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.dao.model.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface UserConverter {
    List<UserDTO> convert(List<User> users);

    UserDTO convert(User user);
}
