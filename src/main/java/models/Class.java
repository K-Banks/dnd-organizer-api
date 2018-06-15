package models;

import java.util.Objects;

public class Class {
    private int id;
    private String name;
    private String description;

    public Class(String name, String description) {
        this.name = name;
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Class aClass = (Class) o;
        return Objects.equals(name, aClass.name) &&
                Objects.equals(description, aClass.description);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, description);
    }
}
