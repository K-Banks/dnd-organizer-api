package models;

import java.util.Objects;

public class Character {
    private String name;
    private int id;
    private String race;
    private int classId;
    private int level;

    public Character(String name, String race, int classId, int level) {
        this.name = name;
        this.race = race;
        this.classId = classId;
        this.level = level;
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
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Character character = (Character) o;
        return classId == character.classId &&
                level == character.level &&
                Objects.equals(name, character.name) &&
                Objects.equals(race, character.race);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, race, classId, level);
    }
}
