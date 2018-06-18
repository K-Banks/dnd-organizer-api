package models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class Class {
    private int id;
    private String name;
    private String description;
//    private String spellSlots;

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

//    public HashMap parseSpellSlots(String spellSlots) {
//        HashMap<Integer, Integer> returnSpellSlots = new HashMap<Integer, Integer>();
//        String[] parsed = spellSlots.split(",");
//        int levelKey=0;
//        int i=0;
//        while (i < parsed.length) {
//            int spellSlotValue;
//            if (i%2 == 0) {
//                levelKey = Integer.parseInt(parsed[i]);
//            } else if (i%2==1 && i!=0) {
//                spellSlotValue = Integer.parseInt(parsed[i]);
//                returnSpellSlots.put(levelKey, spellSlotValue);
//            }
//            i+=1;
//        }
//        return returnSpellSlots;
//    }
//
//    public String convertSlotsToStrings

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
