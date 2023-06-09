package com.codecool.stackoverflowtw.service.userService;

import com.codecool.stackoverflowtw.controller.dto.NewAnswerDTO;
import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.dao.model.User;
import org.springframework.context.annotation.Bean;

import java.util.List;

public interface UserConverter {
    List<UserDTO> convert(List<User> users);

    User convertNewUserDtoToUser(NewUserDTO newUserDTO);

    UserDTO convert(User user);
}
