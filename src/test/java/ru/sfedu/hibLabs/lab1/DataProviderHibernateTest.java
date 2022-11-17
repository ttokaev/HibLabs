package ru.sfedu.hibLabs.lab1;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import static junit.framework.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;

public class DataProviderHibernateTest {
    private static final Logger log = LogManager.getLogger(DataProviderHibernateTest.class);

    DataProviderHibernate dataProviderHibernate = new DataProviderHibernate();

    @Test
    public void getSizeDBTest() {
        assertNotNull(dataProviderHibernate.getSize());
    }

    @Test
    public void getTablesDBTest() {
        assertNotNull(dataProviderHibernate.getTables());
    }

    @Test
    public void getUsersDBTest() {
        assertNotNull(dataProviderHibernate.getUsers());
    }

    @Test
    public void getColumnsTest() {
        assertNotNull(dataProviderHibernate.getColumns());
    }

}