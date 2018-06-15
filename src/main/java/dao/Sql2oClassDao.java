package dao;

import models.Class;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Sql2oClassDao implements ClassDAO{

    private final Sql2o sql2o;

    public Sql2oClassDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Class newClass) {
        String sql = "INSERT INTO classes (name, description) VALUES (:name, :description)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(newClass)
                    .executeUpdate()
                    .getKey();
            newClass.setId(id);
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public List<Class> getAll() {
        String sql = "SELECT * FROM classes";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .executeAndFetch(Class.class);
        }
    }

    @Override
    public Class findById(int classId) {
        String sql = "SELECT * FROM classes WHERE id=:id";
        try (Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", classId)
                    .executeAndFetchFirst(Class.class);
        }
    }

    @Override
    public void update(HashMap<String, Object> propertiesToUpdate, int classUpdatedId) {
        int updates = propertiesToUpdate.size();
        ArrayList<String> properties = new ArrayList<>();
        ArrayList<Object> values = new ArrayList<>();
        for (String key: propertiesToUpdate.keySet()) {
            properties.add(key);
            values.add(propertiesToUpdate.get(key));
        }
        try (Connection con = sql2o.open()) {
            int i = 0;
            while (i < updates) {
                con.createQuery("UPDATE classes SET (" + properties.get(i) + ") = (:" + properties.get(i) + ") WHERE id=:id")
                        .addParameter(properties.get(i), values.get(i))
                        .addParameter("id", classUpdatedId)
                        .executeUpdate();
                i = i+1;
            }
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int classId) {
        String sql = "DELETE FROM classes WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", classId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}
