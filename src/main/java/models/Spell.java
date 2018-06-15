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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCastTime() {
        return castTime;
    }

    public void setCastTime(String castTime) {
        this.castTime = castTime;
    }

    public boolean isRitual() {
        return ritual;
    }

    public void setRitual(boolean ritual) {
        this.ritual = ritual;
    }

    public boolean isConcentration() {
        return concentration;
    }

    public void setConcentration(boolean concentration) {
        this.concentration = concentration;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public boolean isVerbal() {
        return verbal;
    }

    public void setVerbal(boolean verbal) {
        this.verbal = verbal;
    }

    public boolean isSomatic() {
        return somatic;
    }

    public void setSomatic(boolean somatic) {
        this.somatic = somatic;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
