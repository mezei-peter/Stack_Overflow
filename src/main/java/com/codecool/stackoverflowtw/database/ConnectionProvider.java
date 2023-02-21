package com.codecool.stackoverflowtw.database;

import java.sql.Connection;

public interface ConnectionProvider {

    Connection getConnection() throws Exception;
}
