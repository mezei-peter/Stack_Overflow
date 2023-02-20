package com.codecool.stackoverflowtw.database;

import java.sql.Connection;
import java.sql.SQLException;

public interface Database {

    Connection getConnection() throws SQLException;
}
