package dao;

import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;
import models.Spell;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sql2oSpellDao implements SpellDAO {
    private final Sql2o sql2o;

    public Sql2oSpellDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Spell newSpell) {
        String sql = "INSERT INTO spells (name, description, school, level, castTime, ritual, concentration, duration, range, verbal, somatic, material) VALUES (:name, :description, :school, :level, :castTime, :ritual, :concentration, :duration, :range, :verbal, :somatic, :material)";
        try (Connection con = sql2o.open()) {
            int id = (int)con.createQuery(sql)
                    .bind(newSpell)
                    .executeUpdate()
                    .getKey();
            newSpell.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void assignSpellToClass(int spellId, int classId) {
        String sql = "INSERT INTO classes_spells (spellId, classId) VALUES (:spellId, :classId)";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("spellId", spellId)
                    .addParameter("classId", classId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Spell> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM spells")
                    .executeAndFetch(Spell.class);
        }
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

    @Override
    public List<Spell> getAllSpellsOfAClass(int classId) {
        try (Connection con = sql2o.open()) {
            List<Integer> spellIds = con.createQuery("SELECT spellId FROM classes_spells WHERE classId ="+classId)
                    .executeAndFetch(Integer.class);
            List<Spell> spellsOfClass = new ArrayList<>();
            for (int spellId:spellIds) {
                Spell listSpell = con.createQuery("SELECT * FROM spells WHERE id="+spellId)
                        .executeAndFetchFirst(Spell.class);
                spellsOfClass.add(listSpell);
            }
            return spellsOfClass;
        }
    }
}
