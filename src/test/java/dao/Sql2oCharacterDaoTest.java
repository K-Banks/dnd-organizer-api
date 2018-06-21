package dao;

import org.junit.*;
import org.sql2o.Sql2o;
import models.Character;

import org.sql2o.Connection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class Sql2oCharacterDaoTest {

    private static Sql2oCharacterDao characterDao;
    private static Connection con;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/dndspells_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        characterDao = new Sql2oCharacterDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        List<Character> getAll = characterDao.getAll();
        for (Character one:getAll) {
            characterDao.deleteById(one.getId());
        }
    }

    @AfterClass
    public static void shutDown() throws Exception{
        con.close();
        System.out.println("connection closed");
    }

    @Test
    public void addInstantiatesAnIdForTheCharacter() {
        Character newCharacter = setupCharacter();
        int currentId = newCharacter.getId();
        characterDao.add(newCharacter);
        assertNotEquals(currentId, newCharacter.getId());
    }

    @Test
    public void getAll() {
        Character newCharacter = setupCharacter();
        Character newCharacter2 = setupCharacter();
        Character newCharacter3 = setupCharacter();
        characterDao.add(newCharacter);
        characterDao.add(newCharacter2);
        characterDao.add(newCharacter3);
        assertEquals(3, characterDao.getAll().size());
    }

    @Test
    public void findById() {
        Character newCharacter = setupCharacter();
        Character newCharacter2 = setupCharacter();
        Character newCharacter3 = setupCharacter();
        characterDao.add(newCharacter);
        characterDao.add(newCharacter2);
        characterDao.add(newCharacter3);
        assertEquals(newCharacter.getName(), characterDao.findById(newCharacter.getId()).getName());
        assertEquals(newCharacter.getRace(), characterDao.findById(newCharacter.getId()).getRace());
        assertEquals(newCharacter.getCastingLevel(), characterDao.findById(newCharacter.getId()).getCastingLevel());
        assertEquals(newCharacter.getClassId(), characterDao.findById(newCharacter.getId()).getClassId());
    }

    @Test
    public void update() {
        Character newCharacter = setupCharacter();
        characterDao.add(newCharacter);
        HashMap<String, Object> propertiesToUpdate = new HashMap<>();
        propertiesToUpdate.put("name", "Kayl");
        propertiesToUpdate.put("castingLevel", 3);
        propertiesToUpdate.put("classId", 1);
        propertiesToUpdate.put("race", "Elf");
        characterDao.update(propertiesToUpdate, newCharacter.getId());
        assertEquals("Kayl", characterDao.findById(newCharacter.getId()).getName());
        assertEquals("Elf", characterDao.findById(newCharacter.getId()).getRace());
        assertEquals(3, characterDao.findById(newCharacter.getId()).getCastingLevel());
        assertEquals(1, characterDao.findById(newCharacter.getId()).getClassId());
    }

    @Test
    public void deleteById() {
        Character newCharacter = setupCharacter();
        Character newCharacter2 = setupCharacter();
        Character newCharacter3 = setupCharacter();
        characterDao.add(newCharacter);
        characterDao.add(newCharacter2);
        characterDao.add(newCharacter3);
        characterDao.deleteById(newCharacter2.getId());
        assertEquals(2, characterDao.getAll().size());
    }

    //Helper
    private Character setupCharacter() {
        return new Character("name", "race", 3, 4, 3, "");
    }
}