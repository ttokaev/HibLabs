package ru.sfedu.hibLabs.lab2;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import ru.sfedu.hibLabs.lab2.API.DataProviderHibernate;
import ru.sfedu.hibLabs.lab2.model.DaughterTestEntity;
import ru.sfedu.hibLabs.lab2.model.TestEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assert.assertFalse;

public class DataProviderHibernateTest {

    private static Logger log = LogManager.getLogger(DataProviderHibernateTest.class);

    public DataProviderHibernate tep = new DataProviderHibernate();

    public static void initRecord(DataProviderHibernate tep) {
        log.debug("initRecord[0]: Start initiate record");
        TestEntity entity = new TestEntity();
        DaughterTestEntity daughterTestEntity = new DaughterTestEntity();
        daughterTestEntity.setText("Any embedded text");
        entity.setId(1L);
        entity.setName("john");
        entity.setControl(true);
        entity.setDescription("any desc");
        entity.setDaughterTestEntity(daughterTestEntity);
        tep.addRecord(entity);
    }

    @Before
    public void beforeRun() {
        log.info("Before run:");
        tep.clearTable(TestEntity.class);
    }

    @Test
    public void loadListPositive() {
        log.info("loadListPositive:");
        initRecord(tep);
        List list = tep.loadList(TestEntity.class);
        assertFalse(list.isEmpty());
    }

    @Test
    public void loadListNegative() {
        log.info("loadListNegative:");
        List list = tep.loadList(TestEntity.class);
        assertTrue(list.isEmpty());
    }

    @Test
    public void receiveRecordByIdPositive() {
        log.info("receiveRecordByIdPositive:");
        initRecord(tep);
        Optional<TestEntity> testEntity;
        testEntity = tep.receiveRecordById(TestEntity.class, tep.loadList(TestEntity.class).get(0).getId());
        log.info(testEntity);
        log.info(tep.loadList(TestEntity.class));
        assertNotSame(Optional.empty(), testEntity);
    }

    @Test
    public void receiveRecordByIdNegative() {
        log.info("receiveRecordByIdNegative:");
        Optional<TestEntity> testEntity;
        testEntity = tep.receiveRecordById(TestEntity.class, 1L);
        assertSame(Optional.empty(), testEntity);
    }

    @Test
    public void deleteRecordPositive() {
        log.info("deleteRecordPositive:");
        initRecord(tep);
        assertTrue(tep.deleteRecord(TestEntity.class, tep.loadList(TestEntity.class).get(0).getId()));
    }

    @Test
    public void deleteRecordNegative() {
        log.info("deleteRecordNegative:");
        assertFalse(tep.deleteRecord(TestEntity.class, 1L));
    }

    @Test
    public void updateRecordPositive() {
        log.info("updateRecordPositive:");
        initRecord(tep);
        TestEntity entity = tep.loadList(TestEntity.class).get(0);
        entity.setName("UPDATED NAME");
        tep.updateRecord(entity);
        assertEquals(entity.getName(), "UPDATED NAME");
    }

    @Test
    public void updateRecordNegative() {
        log.info("updateRecordNegative:");
        initRecord(tep);
        TestEntity entity = new TestEntity();
        entity.setId(200L);
        assertNull(tep.receiveRecordById(TestEntity.class, 200L).orElse(null));
        tep.updateRecord(entity);
        assertNull(tep.receiveRecordById(TestEntity.class, 200L).orElse(null));
        tep.clearTable(TestEntity.class);
    }

    @Test
    public void addRecordPositive() {
        log.info("addRecordPositive:");
        assertTrue(tep.loadList(TestEntity.class).isEmpty());
        initRecord(tep);
        assertFalse(tep.loadList(TestEntity.class).isEmpty());
    }
}