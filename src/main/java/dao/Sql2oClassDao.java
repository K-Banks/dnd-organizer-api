package dao;

import org.sql2o.Sql2o;

public class Sql2oClassDao implements ClassDAO{

    private final Sql2o sql2o;

    public Sql2oClassDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


}
