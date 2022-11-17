package ru.sfedu.hibLabs;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;;
import org.hibernate.cfg.Configuration;
import ru.sfedu.hibLabs.lab2.model.TestEntity;
import ru.sfedu.hibLabs.lab4.model.*;


public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(HibernateUtil.class);

//        public static SessionFactory getSessionFactory() {
//        if (sessionFactory == null) {
//            try {
//                Configuration configuration;
//                String CFG_PATH = System.getProperty("path");
//                if(System.getProperty("path") == null)
//                    configuration = new Configuration().configure();
//                else configuration = new Configuration().configure(new File(CFG_PATH));
//                configuration.addAnnotatedClass(TestEntity.class);
//                configuration.addAnnotatedClass(UserSet.class);
//                configuration.addAnnotatedClass(UserList.class);
//                configuration.addAnnotatedClass(UserMap.class);
//                configuration.addAnnotatedClass(UserCol.class);
//                configuration.addAnnotatedClass(Measurement.class);
//                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
//                        .applySettings(configuration.getProperties()).build();
//                sessionFactory = configuration.buildSessionFactory(serviceRegistry);
//
//
//            } catch (Exception e) {
//                log.error("getSessionFactory Error");
//                log.error(e.getClass().getName() + ": " + e.getMessage());
//            }
//        }
//        return sessionFactory;

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            // loads configuration and mappings
            Configuration configuration = new Configuration().configure();
            log.debug("getSessionFactory[0]:");
            ServiceRegistry serviceRegistry
                    = new StandardServiceRegistryBuilder()
                    .applySettings(configuration.getProperties()).build();

            MetadataSources metadataSources =
                    new MetadataSources(serviceRegistry);

            metadataSources.addAnnotatedClass(TestEntity.class);

            metadataSources.addAnnotatedClass(UserSet.class);
            metadataSources.addAnnotatedClass(UserList.class);
            metadataSources.addAnnotatedClass(UserMap.class);
            metadataSources.addAnnotatedClass(UserCol.class);
            metadataSources.addAnnotatedClass(Measurement.class);

            sessionFactory = metadataSources.buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }
}
