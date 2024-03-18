package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }
    Connection connection =  Util.getConnection();
    public void createUsersTable() {

        try (Statement statement = connection.createStatement()){
            statement.executeUpdate("CREATE TABLE IF NOT EXISTS user (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50),age INT )");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void dropUsersTable(){

        try (Statement statement = connection.createStatement())
            {statement.executeUpdate("DROP TABLE IF EXISTS  user");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {

        try (PreparedStatement pStatement = connection.prepareStatement("INSERT user(name,lastName,age) VALUES (?,?,?)")) {
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

        try (PreparedStatement pStatement = connection.prepareStatement("DELETE FROM user WHERE Id = ?")) {
            pStatement.setInt(1,(int)id);
            pStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {

        List<User> listUser = new ArrayList<>();
        try ( Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("select * from user ");
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

        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM  user");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
