package ru.sfedu.hibLabs.lab1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import ru.sfedu.hibLabs.Constants;
import ru.sfedu.hibLabs.HibernateUtil;

import java.util.List;

public class DataProviderHibernate {
    private static final Logger log = LogManager.getLogger(DataProviderHibernate.class);

    public List getSize() {
        log.debug("getSize start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List res = session.createSQLQuery(Constants.GET_SIZE_DB).getResultList();
            log.info("The size - " + res);
            return res;
        } catch (Exception e) {
            log.error("getSize Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public List getTables() {
        log.debug("getTables start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List res = session.createSQLQuery(Constants.GET_TABLES_DB).getResultList();
            log.info("The tables - " + res);
            return res;
        } catch (Exception e) {
            log.error("getTables Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public List getUsers() {
        log.debug("getUsers start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List res = session.createSQLQuery(Constants.GET_USERS_DB).getResultList();
            log.info("Users - " + res);
            return res;
        } catch (Exception e) {
            log.error("getUsers Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }

    public List getColumns() {
        log.debug("getColumns start");
        try {
            SessionFactory factory = HibernateUtil.getSessionFactory();
            Session session = factory.openSession();
            List res = session.createSQLQuery(Constants.GET_COLUMNS_DB).getResultList();
            log.info("Columns - " + res);
            return res;
        } catch (Exception e) {
            log.error("getColumns Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
            return null;
        }
    }
}
