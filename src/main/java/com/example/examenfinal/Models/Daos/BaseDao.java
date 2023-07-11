package com.example.examenfinal.Daos;

import java.sql.*;

public abstract class BaseDao {
    public Connection getConnection() throws SQLException {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }
        String url = "jdbc:mysql://localhost:3306/movies";
        String user = "root";
        String pass = "root";

        return DriverManager.getConnection(url,user,pass);
    }

}
