package dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Sql2o;
import models.Character;

import org.sql2o.Connection;

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
        Character newCharacter = setupCharcter();
        int currentId = newCharacter.getId();
        characterDao.add(newCharacter);
        assertNotEquals(currentId, newCharacter.getId());
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

    //Helper
    private Character setupCharcter() {
        return new Character("name", "race", 3, 4);
    }
}