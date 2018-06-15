package models;

public class Spell {
    private int id;
    private String name;
    private String description;
    private String school;
    private int level;
    private String castTime;
    private boolean ritual;
    private boolean concentration;
    private String duration;
    private String range;
    private boolean verbal;
    private boolean somatic;
    private String material;

    public Spell(String name, String description, String school, int level, String castTime, boolean ritual, boolean concentration, String duration, String range, boolean verbal, boolean somatic, String material) {
        this.name = name;
        this.description = description;
        this.school = school;
        this.level = level;
        this.castTime = castTime;
        this.ritual = ritual;
        this.concentration = concentration;
        this.duration = duration;
        this.range = range;
        this.verbal = verbal;
        this.somatic = somatic;
        this.material = material;
    }
}
