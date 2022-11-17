package ru.sfedu.hibLabs.lab4;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import ru.sfedu.hibLabs.Constants;
import ru.sfedu.hibLabs.HibernateUtil;

import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DataProviderHB4 {

    private static Logger log = LogManager.getLogger(DataProviderHB4.class);

    private Session getSession(){
        SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        return sessionFactory.openSession();
    }

    public <T> List<T> loadList(Class<T> entity) {
        log.info("Starting to load list from table :" + entity.getSimpleName());

        Session session = this.getSession();
        log.info("Starting to load list from table :" + entity.getSimpleName());

        Transaction transaction = session.beginTransaction();
        try {
            log.info("Starting to load list from table :" + entity.getSimpleName());
            Query query = session.createQuery(String.format(Constants.FROM_HQL, entity.getSimpleName()));
            List list = query.list();
            log.info("Loading success!");
            log.trace("List of records: " + list);
            transaction.commit();
            session.close();
            return list;
        } catch (Exception e) {
            log.error("Loading from table "+entity.getSimpleName()+ " Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }
    public <T> void clearTableList(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("clearTable[0]: try to clear table " + entity.getSimpleName());
        Query query = session.createSQLQuery(String.format("truncate table lab4list.task",entity.getSimpleName()));
        query.executeUpdate();
        query = session.createQuery(String.format(Constants.DELETE_HQL,entity.getSimpleName()));
        query.executeUpdate();
        log.debug("clearTable[0]: clearing table " + entity.getSimpleName()+" success!");
        transaction.commit();
        session.close();
        return;
    }

    public <T> void clearTableMap(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("clearTable[0]: try to clear table " + entity.getSimpleName());
        Query query = session.createSQLQuery(String.format("truncate table lab4map.task",entity.getSimpleName()));
        query.executeUpdate();
        query = session.createQuery(String.format(Constants.DELETE_HQL,entity.getSimpleName()));
        query.executeUpdate();
        log.debug("clearTable[0]: clearing table " + entity.getSimpleName()+" success!");
        transaction.commit();
        session.close();
        return;
    }

    public <T> void clearTableSet(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("clearTable[0]: try to clear table " + entity.getSimpleName());
        Query query = session.createSQLQuery(String.format("truncate table lab4set.task",entity.getSimpleName()));
        query.executeUpdate();
        query = session.createQuery(String.format(Constants.DELETE_HQL,entity.getSimpleName()));
        query.executeUpdate();
        log.debug("clearTable[0]: clearing table " + entity.getSimpleName()+" success!");
        transaction.commit();
        session.close();
        return;
    }

    public <T> void clearTableCollection(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("clearTable[0]: try to clear table " + entity.getSimpleName());
        Query query = session.createSQLQuery(String.format("truncate table lab4coll.task",entity.getSimpleName()));
        query.executeUpdate();
        query = session.createQuery(String.format(Constants.DELETE_HQL,entity.getSimpleName()));
        query.executeUpdate();
        log.debug("clearTable[0]: clearing table " + entity.getSimpleName()+" success!");
        transaction.commit();
        session.close();
        return;
    }

    public <T> List<T> loadListNativeSql(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            log.info("Starting to load list from table :" + entity.getSimpleName());
            NativeQuery<T> query = session.createNativeQuery(String.format(Constants.FROM_SQL, entity.getSimpleName()), entity);
            List<T> list = query.getResultList();
            log.info("Loading success!");
            log.trace("List of records: " + list);
            transaction.commit();
            session.close();
            return list;
        } catch (Exception e) {
            log.error("Loading from table "+entity.getSimpleName()+ " Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public <T> List<T> loadListCriteria(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        try {
            log.info("Starting to load list from table :" + entity.getSimpleName());
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(entity);
            Root<T> root = query.from(entity);
            CriteriaQuery<T> select = query.select(root);
            TypedQuery<T> typedQuery = session.createQuery(select);
            List<T> list = typedQuery.getResultList();
            log.info("Loading success!");
            log.trace("List of records: " + list)
            ;
            transaction.commit();
            session.close();
            return list;
        } catch (Exception e) {
            log.error("Loading from table "+entity.getSimpleName()+ " Error");
            log.error(e.getClass().getName() + ": " + e.getMessage());
        }
        return new ArrayList<>();
    }

    public <T> void clearTable(Class<T> entity) {
        Session session = this.getSession();
        Transaction transaction = session.beginTransaction();
        log.debug("clearTable[0]: try to clear table " + entity.getSimpleName());
        Query query = session.createQuery(String.format(Constants.DELETE_HQL,entity.getSimpleName()));
        query.executeUpdate();
        log.debug("clearTable[0]: clearing table " + entity.getSimpleName()+" success!");
        transaction.commit();
        session.close();
        return ;
    }

    public <T> Optional<T> receiveRecordById(Class<T> entity, Long id) {
        Session session = this.getSession();
        try{
            log.debug("receiveRecordById[0]: try to receive entity by id:" + entity);
            T requiredEntity = session.get(entity, id);
            log.debug("receiveRecordById[0]: Receiving complete");
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
            log.debug("deleteRecord[0]: try to delete entity:" + entity);
            T requiredEntity = session.get(entity, id);
            session.delete(requiredEntity);
            log.debug("deleteRecord[1]: entity successfully deleted!");
            transaction.commit();
            session.close();
            return true;
        }catch (Exception e){
            log.error("deleteRecord[0] Record with id = "+id+" not found");
            log.error("deleteRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public <T> Boolean updateRecord(T entity) {
        try {
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            log.debug("updateRecord[0]: try to merge(update) entity:" + entity);
            session.merge(entity);
            log.debug("updateRecord[0]: entity updated!");
            transaction.commit();
            session.close();
            return true;
        }catch(Exception e){
            log.error("updateRecord[0]: updating error!");
            log.error("updateRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }

    public <T> Boolean addRecord(T entity) {
        try {
            Session session = this.getSession();
            Transaction transaction = session.beginTransaction();
            log.debug("addRecord[0]: try to save entity:" + entity);
            Long id = (Long) session.save(entity);

            transaction.commit();
            log.debug("addRecord[1]: entity saved!");
            log.debug("addRecord[1]: record id = " + id);
            session.close();
            return true;
        }catch(Exception e){
            log.error("addRecord[0]: saving error!");
            log.error("addRecord[1]:" + e.getClass().getName() + ": " + e.getMessage());
            return false;
        }
    }
}
