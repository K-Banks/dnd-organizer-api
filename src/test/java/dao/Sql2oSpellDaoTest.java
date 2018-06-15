package dao;

import models.Spell;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import models.Class;
import dao.Sql2oClassDao;

import static org.junit.Assert.*;

public class Sql2oSpellDaoTest {

    private Sql2oSpellDao spellDao;
    private Connection con;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        spellDao = new Sql2oSpellDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        con.close();
    }

    @Test
    public void addingNewSpellAssignsId() {
        Spell newSpell = setupNewSpell();
        assertEquals(0, newSpell.getId());
        spellDao.add(newSpell);
        assertNotEquals(0, newSpell.getId());
    }

    @Test
    public void assignSpellToClass() {
        Spell newSpell = setupNewSpell();
        int classId = 2;
        spellDao.add(newSpell);
        spellDao.assignSpellToClass(newSpell.getId(), classId);
        assertTrue(spellDao.getAllSpellsOfAClass(classId).contains(newSpell));
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

    @Test
    public void getAllSpellsOfAClass() {
    }

    //helper
    private Spell setupNewSpell(){
        return new Spell("Clairvoyance", "testDescription", "Divination", 3, "10 minutes", false, true, "10 minutes", "1 mile", true, true, "a focus worth at least 100gp, either a jeweled horn for hearing or a glass eye for seeing");
    }
}