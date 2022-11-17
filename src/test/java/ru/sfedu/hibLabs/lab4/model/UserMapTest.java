package ru.sfedu.hibLabs.lab4.model;

import junit.framework.TestCase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.hibLabs.lab4.DataProviderHB4;

import java.util.*;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class UserMapTest extends TestCase {
    private static Logger log = LogManager.getLogger(UserMapTest.class);
    DataProviderHB4 dp = new DataProviderHB4();

    public static void initRecord(DataProviderHB4 dp) {
        log.debug("initRecord[0]: Start initiate record");
        UserMap User = createDefaultUser();
        dp.addRecord(User);
    }

    @Before
    public void beforeRun(){
        log.info("Before run:");
        dp.clearTableSet(UserSet.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(dp);
        List list = dp.loadList(UserSet.class);
        assertEquals(1, list.size());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = dp.loadList(UserSet.class);
        assertEquals(0, list.size());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(dp);
        Optional<UserSet> User= dp.receiveRecordById(UserSet.class,dp.loadList(UserSet.class).get(0).getId());
        assertNotSame(Optional.empty(), User);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<UserSet> User= dp.receiveRecordById(UserSet.class,1L);
        assertSame(Optional.empty(), User);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(dp);
        assertTrue(dp.deleteRecord(UserSet.class,dp.loadList(UserSet.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(dp.deleteRecord(UserSet.class,1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(dp);
        UserSet User = dp.loadList(UserSet.class).get(0);
        User.setName("UPDATED NAME");
        dp.updateRecord(User);
        assertEquals(User.getName(),"UPDATED NAME");
    }
    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(dp);
        UserSet User = new UserSet();
        User.setId(200L);
        assertNull(dp.receiveRecordById(UserSet.class, 200L).orElse(null));
        dp.updateRecord(User);
        assertNull(dp.receiveRecordById(UserSet.class, 200L).orElse(null));
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        log.info(UserSet.class.getSimpleName());
        assertTrue(dp.loadList(UserSet.class).isEmpty());
        initRecord(dp);
        assertFalse(dp.loadList(UserSet.class).isEmpty());
    }

    public static UserMap createDefaultUser(){
        UserMap User = new UserMap();
        User.setName("Tim");
        User.setSurname("Tok");
        Map<Integer, Integer> weight = new HashMap<>();
        weight.put(77, 177);
        User.setWeight(weight);
        return User;
    }
}