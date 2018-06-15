package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SpellTest {

    @Test
    public void createSpell() {
        Spell newSpell = new Spell("test", "test", "test", 3, "test", true, true, "test", "test", true, true, "test");
        assertTrue(newSpell instanceof Spell);
    }
}