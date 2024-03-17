package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.query.Query;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    String sqlCommand;

    public UserDaoHibernateImpl() {}


    @Override
    public void createUsersTable() {
        sqlCommand = "CREATE TABLE IF NOT EXISTS users (id INT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(50), lastName VARCHAR(50),age INT )";
        Session session = Util.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(sqlCommand).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        } finally {
            session.close();
        }
    }

    @Override
    public void dropUsersTable() {
        sqlCommand = "DROP TABLE IF EXISTS users ";
        Session session = Util.getSessionFactory().openSession();

        try {
            session.beginTransaction();
            session.createSQLQuery(sqlCommand).addEntity(User.class).executeUpdate();
            session.getTransaction().commit();

        }finally {
            session.close();
        }

    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = Util.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.save(new User(name,lastName,age));
            session.getTransaction().commit();
            System.out.printf("User  с именем %s добавлен в таблицу \n",name);
        }finally {
            session.close();
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = Util.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.createQuery("delete  User where id = " + id).executeUpdate();
            //session.delete(session.get(User.class,1));
            session.getTransaction().commit();

        }finally {
            session.close();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> listUser;
        Session session = Util.getSessionFactory().openSession();
        try{
            session.beginTransaction();
            listUser = session.createQuery("from User").getResultList();
            session.getTransaction().commit();
//            CriteriaBuilder critBuild = session.getCriteriaBuilder();
//            CriteriaQuery critQuery = critBuild.createQuery(User.class);
//            Root<User> root = critQuery.from(User.class);
//            listUser = session.createQuery(critQuery).getResultList();


        }finally {
            session.close();
        }
        listUser.stream().forEach(System.out::println);
        return listUser;
    }

    @Override
    public void cleanUsersTable() {
        Session session = Util.getSessionFactory().openSession();

        try{
            session.beginTransaction();
            session.createQuery("delete  User " ).executeUpdate();

            session.getTransaction().commit();

        }finally {
            session.close();
        }

    }
}
