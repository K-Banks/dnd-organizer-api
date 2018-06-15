package dao;

import models.Class;
import org.sql2o.Sql2o;

import java.util.HashMap;
import java.util.List;

public class Sql2oClassDao implements ClassDAO{

    private final Sql2o sql2o;

    public Sql2oClassDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Class newClass) {

    }

    @Override
    public List<Class> getAll() {
        return null;
    }

    @Override
    public Class findById(int classId) {
        return null;
    }

    @Override
    public void update(HashMap<String, Object> propertiesToUpdate, int classUpdatedId) {

    }

    @Override
    public void deleteById(int classId) {

    }
}
