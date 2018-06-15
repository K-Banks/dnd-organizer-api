package dao;

import java.util.HashMap;
import java.util.List;
import models.Class;

public interface ClassDAO {
    //create
    void add(Class newClass);

    //read
    List<Class> getAll();
    Class findById(int classId);

    //update
    void update(HashMap<String, Object> propertiesToUpdate, int classUpdatedId);

    //delete
    void deleteById(int classId);
}
