package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.mapping.Property;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
//    // реализуйте настройку соеденения с БД
//
////    public
//    private static final String URL = "jdbc:mysql://127.0.0.1:3306/lesson";
//    public static final String USERNAME = "root";
//    public static final String PASSWORD ="52532137";
//
//public static Connection connection ;
//
//
//    public Util()  {
//        try {
//            connection=DriverManager.getConnection(URL,USERNAME,PASSWORD);
//
//        } catch (SQLException e){
//            e.printStackTrace();
//        }
//    }
//    public static Connection getConnection(){
//        return connection;
//    }
    public static  SessionFactory sessionFactory ;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            try {
                Configuration  config = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://127.0.0.1:3306/lesson");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "52532137");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
                settings.put(Environment.SHOW_SQL, "true");
                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");
                settings.put(Environment.HBM2DDL_AUTO, "");
                config.setProperties(settings);
                config.addAnnotatedClass(User.class);
                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
                sessionFactory = config.buildSessionFactory(serviceRegistry);
            } catch (HibernateException e) {
                throw new RuntimeException(e);
            }

        }
        return sessionFactory;
    }
}
