package dao;

import java.util.HashMap;
import java.util.List;
import models.Character;

public interface CharacterDAO {

    //create
    void add(Character newCharacter);


    //read
    List<Character> getAll();
    Character findById(int characterId);


    //update
    void update(HashMap<String, Object> propertiesToUpdate, int characterUpdatedId);


    //delete
    void deleteById(int characterId);

}