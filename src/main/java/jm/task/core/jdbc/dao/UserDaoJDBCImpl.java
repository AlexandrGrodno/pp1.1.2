package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private String sqlCommand;
    public UserDaoJDBCImpl() {

    }
    Util ut = new Util();
    Connection connection =  ut.getConnection();
    public void createUsersTable() {
         sqlCommand = "CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50),age INT )";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    public void dropUsersTable(){
         sqlCommand = "DROP TABLE IF EXISTS  user";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        sqlCommand = "INSERT user(name,lastName,age) VALUES (?,?,?)";
        try {
            PreparedStatement pStatement = connection.prepareStatement(sqlCommand);
            pStatement.setString(1,name);
            pStatement.setString(2,lastName);
            pStatement.setByte(3,age);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.printf("User  с именем %s добавлен в таблицу \n",name);
    }

    public void removeUserById(long id) {
        sqlCommand = "DELETE FROM user WHERE Id = "+id;
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        sqlCommand = "select * from user ";
        List<User> listUser = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sqlCommand);
            while (resultSet.next()){
            listUser.add(new User(resultSet.getString(2),
                    resultSet.getString(3),resultSet.getByte(4)));

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        listUser.stream().forEach(System.out::println);
        return listUser;
    }

    public void cleanUsersTable() {
        sqlCommand = "DELETE FROM  user";
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sqlCommand);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
