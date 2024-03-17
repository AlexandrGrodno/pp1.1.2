package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
        // реализуйте алгоритм здесь

        UserDao userDao = new UserDaoJDBCImpl();

        userDao.createUsersTable();
        userDao.saveUser("user1","name1", (byte) 1);
        userDao.saveUser("user2","name2", (byte) 2);
        userDao.saveUser("user3","name3", (byte) 3);
        userDao.saveUser("user4","name4", (byte) 4);
        userDao.getAllUsers();
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
//       }
    }
}
