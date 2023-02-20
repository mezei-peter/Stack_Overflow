package com.codecool.stackoverflowtw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.SQLException;

@SpringBootApplication
public class StackoverflowTwApplication {

    public static void main(String[] args) throws SQLException {
        SpringApplication.run(StackoverflowTwApplication.class, args);
    }
}
