package dao;

import models.Character;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sql2oCharacterDao implements CharacterDAO {

    private final Sql2o sql2o;

    public Sql2oCharacterDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Character newCharacter) {
        String sql = "INSERT INTO characters (name, race, classId, level) VALUES (:name, :race, :classId, :level)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(newCharacter)
                    .executeUpdate()
                    .getKey();
            newCharacter.setId(id);
        } catch(Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Character> getAll() {
        String sql = "SELECT * FROM characters";
        try (Connection con = sql2o.open()) {
           return con.createQuery(sql)
                    .executeAndFetch(Character.class);
        }
    }

    @Override
    public Character findById(int characterId) {
        String sql = "SELECT * FROM characters WHERE id = :id";
        try (Connection con = sql2o.open()) {
            return con.createQuery(sql)
                    .addParameter("id", characterId)
                    .executeAndFetchFirst(Character.class);
        }
    }

    @Override
    public void update(HashMap<String, Object> propertiesToUpdate, int characterUpdatedId) {
        int updates = propertiesToUpdate.size();
        ArrayList<String> properties = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        for (String key: propertiesToUpdate.keySet()) {
            properties.add(key);
            values.add(propertiesToUpdate.get(key));
        }
        try (Connection con = sql2o.open()) {
            int i = 0;
            while (i < updates) {
                con.createQuery("UPDATE characters SET (" + properties.get(i) + ") = (:" + properties.get(i) + ") WHERE id=:id")
                        .addParameter(properties.get(i), values.get(i))
                        .addParameter("id", characterUpdatedId)
                        .executeUpdate();
                i = i+1;
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int characterId) {
        String sql = "DELETE FROM characters WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", characterId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }


}
