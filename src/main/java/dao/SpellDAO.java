package dao;

import java.util.HashMap;
import java.util.List;
import models.Spell;

public interface SpellDAO {
    //create
    void add(Spell newSpell);
    void assignSpellToClass(int spellId, int classId);

    //read
    List<Spell> getAll();
    List<Spell> getAllSpellsOfAClass(int classId);
    Spell findById(int spellId);

    //update
    void update(HashMap<String, Object> propertiesToUpdate, int spellUpdatedId);

    //delete
    void deleteById(int spellId);
}