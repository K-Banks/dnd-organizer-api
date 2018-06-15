package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class CharacterTest {

    @Test
    public void createCharacter() {
        Character newCharacter = new Character("Jo", "elf", 4, 3);
        assertTrue(newCharacter instanceof Character);
    }

    @Test
    public void testGetterAndSetterForName() {
        Character newCharacter = setupNewCharacter();
        newCharacter.setName("Kyle");
        assertFalse(newCharacter.getName().equals("Jo"));
        assertTrue(newCharacter.getName().equals("Kyle"));
    }

    @Test
    public void testGetterAndSetterForId() {
        Character newCharacter = setupNewCharacter();
        newCharacter.setId(1);
        assertNotEquals(null, newCharacter.getId());
        assertTrue(newCharacter.getId()==1);
    }

    @Test
    public void testGetterAndSetterForRace() {
        Character newCharacter = setupNewCharacter();
        newCharacter.setRace("Orc");
        assertEquals("Orc", newCharacter.getRace());
    }

    @Test
    public void testGetterAndSetterForClassId() {
        Character newCharacter = setupNewCharacter();
        newCharacter.setClassId(2);
        assertEquals(2, newCharacter.getClassId());
    }

    @Test
    public void testGetterAndSetterForLevel() {
        Character newCharacter = setupNewCharacter();
        newCharacter.setLevel(5);
        assertEquals(5, newCharacter.getLevel());
    }

    //Helper function
    private Character setupNewCharacter() {
        return new Character("Jo", "Elf", 4, 3);
    }
}