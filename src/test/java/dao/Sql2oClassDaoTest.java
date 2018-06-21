package dao;

import models.Class;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2oClassDaoTest {

    private static Sql2oClassDao classDao;
    private static Connection con;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/dndspells_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        classDao = new Sql2oClassDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        List<Class> getAll = classDao.getAll();
        for (Class one:getAll) {
            classDao.deleteById(one.getId());
        }
    }

    @AfterClass
    public static void shutDown() throws Exception {
        System.out.println("connection closed");
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
        Class newClass = setupClass();
        Class newClass2 = setupClass();
        Class newClass3 = setupClass();
        Class newClass4 = setupClass();
        classDao.add(newClass);
        classDao.add(newClass2);
        classDao.add(newClass3);
        classDao.add(newClass4);
        assertEquals(4, classDao.getAll().size());
    }

    @Test
    public void findById() {
        Class newClass = setupClass();
        Class newClass2 = setupClass();
        newClass.setName("Paladin");
        classDao.add(newClass);
        classDao.add(newClass2);
        assertEquals("Paladin", classDao.findById(newClass.getId()).getName());
    }

    @Test
    public void update() {
        Class newClass = setupClass();
        HashMap<String, Object> propertiesToUpdate = new HashMap<>();
        propertiesToUpdate.put("name", "Kyle");
        propertiesToUpdate.put("description", "this is a new description");
        classDao.add(newClass);
        classDao.update(propertiesToUpdate, newClass.getId());
        assertEquals("Kyle", classDao.findById(newClass.getId()).getName());
        assertEquals("this is a new description", classDao.findById(newClass.getId()).getDescription());
    }

    @Test
    public void deleteById() {
        Class newClass = setupClass();
        Class newClass2 = setupClass();
        Class newClass3 = setupClass();
        Class newClass4 = setupClass();
        classDao.add(newClass);
        classDao.add(newClass2);
        classDao.add(newClass3);
        classDao.add(newClass4);
        assertEquals(4, classDao.getAll().size());
        classDao.deleteById(newClass2.getId());
        classDao.deleteById(newClass3.getId());
        assertEquals(2, classDao.getAll().size());
    }

    //helper
    private Class setupClass() {
        return new Class("name", "description");
    }
}