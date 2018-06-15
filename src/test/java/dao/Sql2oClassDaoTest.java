package dao;

import models.Class;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oClassDaoTest {

    private Sql2oClassDao classDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        classDao = new Sql2oClassDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void addGivesClassId() {
        Class newClass = setupClass();
        assertEquals(0, newClass.getId());
        classDao.add(newClass);
        assertNotEquals(0, newClass.getId());
    }

    @Test
    public void getAll() {
    }

    @Test
    public void findById() {
    }

    @Test
    public void update() {
    }

    @Test
    public void deleteById() {
    }

    //helper
    private Class setupClass() {
        return new Class("name", "description");
    }
}