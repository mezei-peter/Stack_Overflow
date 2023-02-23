package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserLoginDTO;
import com.codecool.stackoverflowtw.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public List<UserDTO> listAllUsers() {
       return userService.getAllUsers();
    }

    @PostMapping("/login")
    public String getSessionIdByLogin(@RequestBody UserLoginDTO userLoginDTO) {
        return userService.createSession(userLoginDTO);
    }
}
