package dao;

import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;

public class Sql2oCharacterDao implements CharacterDAO {

    private final Sql2o sql2o;

    public Sql2oCharacterDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Character newCharacter) {

    }

    @Override
    public List<Character> getAll() {
        return null;
    }

    @Override
    public Character findById(int characterId) {
        return null;
    }

    @Override
    public void update(HashMap<String, String> propertiesToUpdate, int characterUpdatedId) {

    }

    @Override
    public void deleteById(int characterId) {

    }


}
