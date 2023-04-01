package models;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public abstract class Model implements Serializable {
    public abstract List<Model> all(ResultSet rs) throws SQLException;

//    public abstract Model get(ResultSet rs) throws SQLException;

    public abstract String table();

//    public abstract Model find(int id);

    public abstract String getWhere();

    public abstract void setWhere(String where);

    public abstract String getJoin();

    public abstract void setJoin(String join);

    public abstract String getValues();

    public abstract void setValues(String values);
}
