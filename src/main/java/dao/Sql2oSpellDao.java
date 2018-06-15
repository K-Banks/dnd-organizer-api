package dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import models.Spell;

import java.util.HashMap;
import java.util.List;

public class Sql2oSpellDao implements SpellDAO {
    private final Sql2o sql2o;

    public Sql2oSpellDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Spell newSpell) {

    }

    @Override
    public void assignSpellToClass(int spellId, int classId) {

    }

    @Override
    public List<Spell> getAll() {
        return null;
    }

    @Override
    public Spell findById(int spellId) {
        return null;
    }

    @Override
    public void update(HashMap<String, Object> propertiesToUpdate, int spellUpdatedId) {

    }

    @Override
    public void deleteById(int spellId) {

    }
}
