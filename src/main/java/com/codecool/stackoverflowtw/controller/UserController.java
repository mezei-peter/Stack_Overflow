package com.codecool.stackoverflowtw.controller;

import com.codecool.stackoverflowtw.controller.dto.NewUserDTO;
import com.codecool.stackoverflowtw.controller.dto.UserDTO;
import com.codecool.stackoverflowtw.service.userService.UserAlreadyExistAuthenticationException;
import com.codecool.stackoverflowtw.controller.dto.UserLoginDTO;
import com.codecool.stackoverflowtw.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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

    @DeleteMapping("/logout/{sessionId}")
    public boolean deleteSessionBySessionId(@PathVariable String sessionId) {
        return userService.deleteSession(sessionId);
    }

    @GetMapping("/session/{sessionId}")
    public UserDTO getUserBySessionId(@PathVariable String sessionId) {
        return userService.getUserBySessionId(sessionId);
    }

    @PostMapping("/")
    public ResponseEntity<String> createUser(@RequestBody NewUserDTO newUserDTO) {
        try {
            userService.createNewUser(newUserDTO);
        } catch (UserAlreadyExistAuthenticationException e) {
            return new ResponseEntity<>("Username: " + newUserDTO.username() + " already exists",
                    HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>("User with username: " + newUserDTO.username() + " created.",
                HttpStatus.CREATED);
    }
}
