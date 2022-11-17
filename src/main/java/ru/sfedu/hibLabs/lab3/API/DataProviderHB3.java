package ru.sfedu.hibLabs.lab3.API;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ru.sfedu.hibLabs.Constants;
import ru.sfedu.hibLabs.HibernateUtil;;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProviderHB3 {
    private static Logger log = LogManager.getLogger(DataProviderHB3.class);

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    public <T> List<T> loadList(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            log.debug("Try to load list of entities from table :" + entity.getSimpleName());
            Query query = session.createQuery(String.format(Constants.FROM_HQL, entity.getSimpleName()));
            List list = query.list();
            log.debug("Loading success!");
            log.trace("List of records: " + list);
            transaction.commit();
            session.close();
            return list;
        } catch (Exception e) {
            log.error("Loading list from table "+entity.getSimpleName()+ " Error");
            log.error("loadBeanList[1]: " + e.getClass().getName() + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public <T> void clearTable(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("Trying to clear table " + entity.getSimpleName());
        Query query = session.createQuery(String.format(Constants.DELETE_HQL,entity.getSimpleName()));
        query.executeUpdate();
        log.debug("Clearing table " + entity.getSimpleName()+" success!");
        transaction.commit();
        session.close();
    }

    public <T> Optional<T> receiveRecordById(Class<T> entity, Long id) {
        Session session = this.getSession();
        try{
            log.debug("Trying to receive entity by id:" + entity);
            T requiredEntity = session.get(entity, id);
            log.debug("Receiving complete");
            session.close();
            return Optional.of(requiredEntity);
        }catch (NullPointerException e){
            log.error("receiveRecordById[0] Record with id = "+id+" not found");
            log.error("receiveRecordById[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return Optional.empty();
        }
    }

    public <T> Boolean deleteRecord(Class<T> entity, Long id) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            log.debug("Trying to delete entity:" + entity);
            T requiredEntity = session.get(entity, id);
            session.delete(requiredEntity);
            log.debug("Entity successfully deleted!");
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            log.error("Record with id = "+id+" not found");
            log.error("deleteRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public <T> Boolean updateRecord(T entity) {
        try {
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            log.debug("Trying to update entity:" + entity);
            session.merge(entity);
            log.debug("Entity updated!");
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            log.error("Updating error!");
            log.error("updateRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public <T> Boolean addRecord(T entity) {
        try {
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            log.debug("Trying to save entity:" + entity);
            Long id = (Long) session.save(entity);
            transaction.commit();
            log.debug("Entity saved!");
            log.debug("Record id = " + id);
            session.close();
            return true;
        }catch(Exception e){
            log.error("Saving error!");
            log.error("addRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }
}
