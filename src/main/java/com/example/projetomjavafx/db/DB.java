package com.example.projetomjavafx.db;

import java.sql.*;

public class DB {

    private static Connection c = null;

    public static Connection getConnection(){
        if (c ==null){
            try {
                c = DriverManager.getConnection("jdbc:mysql://localhost:3306/musicadb","root","1234");
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return c;
    }

    public static void closeConnection(){
        if (c !=null){
            try {
                c.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public  static void closeResultSet(ResultSet rs){
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static void closeStatement(Statement st){
        if (st!=null){
            try {
                st.close();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

