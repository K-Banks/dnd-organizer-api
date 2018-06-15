package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import models.Character;

import org.sql2o.Connection;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class Sql2oCharacterDaoTest {

    private Sql2oCharacterDao characterDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        characterDao = new Sql2oCharacterDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
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
        assertEquals(newCharacter.getLevel(), characterDao.findById(newCharacter.getId()).getLevel());
        assertEquals(newCharacter.getClassId(), characterDao.findById(newCharacter.getId()).getClassId());
    }

    @Test
    public void update() {
        Character newCharacter = setupCharacter();
        characterDao.add(newCharacter);
        HashMap<String, Object> propertiesToUpdate = new HashMap<>();
        propertiesToUpdate.put("name", "Kayl");
        propertiesToUpdate.put("level", 3);
        propertiesToUpdate.put("classId", 1);
        propertiesToUpdate.put("race", "Elf");
        characterDao.update(propertiesToUpdate, newCharacter.getId());
        assertEquals("Kayl", characterDao.findById(newCharacter.getId()).getName());
        assertEquals("Elf", characterDao.findById(newCharacter.getId()).getRace());
        assertEquals(3, characterDao.findById(newCharacter.getId()).getLevel());
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
        return new Character("name", "race", 3, 4);
    }
}