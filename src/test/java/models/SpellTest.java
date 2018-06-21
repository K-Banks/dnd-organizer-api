package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpellTest {

    @Test
    public void createSpell() {
        Spell newSpell = new Spell("test", "test", "test", 3, "test", true, true, "test", "test", true, true, "test");
        assertTrue(newSpell instanceof Spell);
    }

    @Test
    public void testGetterAndSetterForName() {
        Spell spell = setupSpell();
        spell.setName("Cantrip");
        assertNotEquals("test", spell.getName());
    }

    @Test
    public void testGetterAndSetterForDescription() {
        Spell spell = setupSpell();
        spell.setDescription("Cantrip");
        assertNotEquals("test", spell.getDescription());
    }

    @Test
    public void testGetterAndSetterForSchool() {
        Spell spell = setupSpell();
        spell.setSchool("Cantrip");
        assertNotEquals("test", spell.getSchool());
    }

    @Test
    public void testGetterAndSetterForLevel() {
        Spell spell = setupSpell();
        spell.setLevel(4);
        assertNotEquals(3, spell.getLevel());
    }

    @Test
    public void testGetterAndSetterForCastTime() {
        Spell spell = setupSpell();
        spell.setCastTime("Cantrip");
        assertNotEquals("test", spell.getCastTime());
    }

    @Test
    public void testGetterAndSetterForRitual() {
        Spell spell = setupSpell();
        spell.setRitual(false);
        assertNotEquals("test", spell.isRitual());
    }

    @Test
    public void testGetterAndSetterForConcentration() {
        Spell spell = setupSpell();
        spell.setConcentration(false);
        assertNotEquals("test", spell.isConcentration());
    }

    @Test
    public void testGetterAndSetterForDuration() {
        Spell spell = setupSpell();
        spell.setDuration("Cantrip");
        assertNotEquals("test", spell.getDuration());
    }

    @Test
    public void testGetterAndSetterForRange() {
        Spell spell = setupSpell();
        spell.setRange("Cantrip");
        assertNotEquals("test", spell.getRange());
    }

    @Test
    public void testGetterAndSetterForVerbal() {
        Spell spell = setupSpell();
        spell.setVerbal(false);
        assertNotEquals("test", spell.isVerbal());
    }

    @Test
    public void testGetterAndSetterForSomatic() {
        Spell spell = setupSpell();
        spell.setSomatic(false);
        assertNotEquals("test", spell.isSomatic());
    }

    @Test
    public void testGetterAndSetterForMaterial() {
        Spell spell = setupSpell();
        spell.setMaterial("Cantrip");
        assertNotEquals("test", spell.getMaterial());
    }

    @Test
    public void testGetterAndSetterForId() {
        Spell spell = setupSpell();
        spell.setId(3);
        assertNotEquals(null, spell.getId());
    }

    //Helper
    private Spell setupSpell() {
        return new Spell("test", "test", "test", 3, "test", true, true, "test", "test", true, true, "test");
    }
}