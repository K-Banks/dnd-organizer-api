package dao;

import models.Spell;
import org.junit.*;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import models.Class;
import dao.Sql2oClassDao;

import java.util.HashMap;
import java.util.List;

import static org.junit.Assert.*;

public class Sql2oSpellDaoTest {

    private static Sql2oSpellDao spellDao;
    private static Connection con;

    @BeforeClass
    public static void setUp() throws Exception {
        String connectionString = "jdbc:postgresql://localhost:5432/dndspells_test";
        Sql2o sql2o = new Sql2o(connectionString, null, null);
        spellDao = new Sql2oSpellDao(sql2o);
        con = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        System.out.println("clearing database");
        List<Spell> getAll = spellDao.getAll();
        for (Spell one:getAll) {
            spellDao.deleteById(one.getId());
        }
    }

    @AfterClass
    public static void shutDown() throws Exception {
        System.out.println("connection closed");
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
        Spell newSpell = setupNewSpell();
        Spell newSpell2 = setupNewSpell();
        Spell newSpell3 = setupNewSpell();
        spellDao.add(newSpell);
        spellDao.add(newSpell2);
        spellDao.add(newSpell3);
        assertEquals(3, spellDao.getAll().size());
    }

    @Test
    public void findById() {
        Spell newSpell = setupNewSpell();
        Spell newSpell2 = setupNewSpell();
        Spell newSpell3 = setupNewSpell();
        spellDao.add(newSpell);
        spellDao.add(newSpell2);
        spellDao.add(newSpell3);
        assertEquals(newSpell2.getId(), spellDao.findById(newSpell2.getId()).getId());
    }

    @Test
    public void update() {
        Spell newSpell=setupNewSpell();
        spellDao.add(newSpell);
        HashMap<String, Object> propertiesToUpdate = new HashMap<>();
        propertiesToUpdate.put("name", "name");
        propertiesToUpdate.put("description", "description");
        propertiesToUpdate.put("school", "school");
        propertiesToUpdate.put("level", 1);
        propertiesToUpdate.put("castTime", "castTime");
        propertiesToUpdate.put("concentration", false);
        propertiesToUpdate.put("duration", "duration");
        propertiesToUpdate.put("range", "range");
        propertiesToUpdate.put("verbal", false);
        propertiesToUpdate.put("somatic", false);
        propertiesToUpdate.put("material", "material");
        spellDao.update(propertiesToUpdate, newSpell.getId());
        assertEquals("name", spellDao.findById(newSpell.getId()).getName());
        assertEquals("description", spellDao.findById(newSpell.getId()).getDescription());
        assertEquals("duration", spellDao.findById(newSpell.getId()).getDuration());
        assertEquals("school", spellDao.findById(newSpell.getId()).getSchool());
        assertEquals(1, spellDao.findById(newSpell.getId()).getLevel());
        assertEquals("castTime", spellDao.findById(newSpell.getId()).getCastTime());
        assertFalse(spellDao.findById(newSpell.getId()).isConcentration());
        assertEquals("range", spellDao.findById(newSpell.getId()).getRange());
        assertFalse(spellDao.findById(newSpell.getId()).isVerbal());
        assertFalse(spellDao.findById(newSpell.getId()).isSomatic());
        assertEquals("material", spellDao.findById(newSpell.getId()).getMaterial());
    }

    @Test
    public void deleteById() {
        Spell newSpell = setupNewSpell();
        spellDao.add(newSpell);
        Spell newSpell2 = setupNewSpell();
        spellDao.add(newSpell2);
        int classId = 2;
        spellDao.assignSpellToClass(newSpell.getId(), classId);
        spellDao.assignSpellToClass(newSpell2.getId(), classId);
        assertEquals(2, spellDao.getAll().size());
        assertEquals(2, spellDao.getAllSpellsOfAClass(classId).size());
        spellDao.deleteById(newSpell.getId());
        assertEquals(1, spellDao.getAll().size());
        assertEquals(1, spellDao.getAllSpellsOfAClass(classId).size());
    }

    @Test
    public void getAllSpellsOfAClass() {
    }

    //helper
    private Spell setupNewSpell(){
        return new Spell("Clairvoyance", "testDescription", "Divination", 3, "10 minutes", false, true, "10 minutes", "1 mile", true, true, "a focus worth at least 100gp, either a jeweled horn for hearing or a glass eye for seeing");
    }
}