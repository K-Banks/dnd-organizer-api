package models;

import java.util.Objects;

public class Character {
    private String name;
    private int id;
    private String race;
    private int classId;
    private int castingLevel;
    private int spellCount;
    private String preparedSpells;

    public Character(String name, String race, int classId, int level, int spellCount, String preparedSpells) {
        this.name = name;
        this.race = race;
        this.classId = classId;
        this.castingLevel = castingLevel;
        this.spellCount = spellCount;
        this.preparedSpells = preparedSpells;
    }

    public String getPreparedSpells() {
        return preparedSpells;
    }

    public void setPreparedSpells(String preparedSpells) {
        this.preparedSpells = preparedSpells;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getClassId() {
        return classId;
    }

    public void setClassId(int classId) {
        this.classId = classId;
    }

    public int getLevel() {
        return castingLevel;
    }

    public void setLevel(int castingLevel) {
        this.castingLevel = castingLevel;
    }

    public int getSpellCount() {
        return spellCount;
    }

    public void setSpellCount(int spellCount) {
        this.spellCount = spellCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return classId == character.classId &&
                castingLevel == character.castingLevel &&
                spellCount == character.spellCount &&
                Objects.equals(name, character.name) &&
                Objects.equals(race, character.race) &&
                Objects.equals(preparedSpells, character.preparedSpells);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, race, classId, castingLevel, spellCount, preparedSpells);
    }
}
