package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    // реализуйте настройку соеденения с БД
//    public static final String DRIVER = "";
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lesson";
    public static final String USERNAME = "root";
    public static final String PASSWORD ="52532137";

    private  static  Connection connection ;



    public static Connection getConnection(){
        try {
            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);

        } catch (SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
