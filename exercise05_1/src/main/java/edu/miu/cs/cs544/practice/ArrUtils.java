package edu.miu.cs.cs544.practice;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.List;
import java.util.Properties;

public class ArrUtils {
    private static SessionFactory sessionFactory;
    
    private static Configuration configuration = new Configuration();
    
    @SuppressWarnings({ "rawtypes" })
    public static SessionFactory getSessionFactory(List<Class> entityClasses) {
        if (sessionFactory == null) {
            try {
                Properties settings = new Properties();
               settings.put(Environment.DRIVER, "com.microsoft.sqlserver.jdbc.SQLServerDriver");
//                settings.put(Environment.URL, "jdbc:sqlserver://cs544.cs.miu.edu:1433"); //10.10.10.15
//                settings.put(Environment.USER, "ea544");
//                settings.put(Environment.PASS, "ea544");
//                settings.put(Environment.DIALECT, "org.hibernate.dialect.SQLServer2008Dialect");
                
              settings.put(Environment.URL, "jdbc:mysql://localhost:3306/ea544");
              settings.put(Environment.USER, "ea544");
              settings.put(Environment.PASS, "ea544");
              settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

                settings.put(Environment.SHOW_SQL, "true");

                settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

                settings.put(Environment.HBM2DDL_AUTO, "create-update");

                configuration.setProperties(settings);
                
                entityClasses.forEach(entityClass -> configuration.addAnnotatedClass(entityClass));

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        return sessionFactory;
    }
    
}
