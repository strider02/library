package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class Role extends Model {

    private int id;
    private String name;

    private String where;
    private String values;

    private final String table = "roles";

    private List<User> users;

    public Role() {
    }

    public Role(String name) {
        this.name = name;
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

    public String getTable() {
        return table;
    }

    @Override


    public List<Model> all(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String table() {
        return table;
    }

    @Override
    public String getWhere() {
        return where;
    }

    @Override
    public void setWhere(String where) {
        this.where = where;
    }

    @Override
    public String getJoin() {
        return null;
    }

    @Override
    public void setJoin(String join) {

    }

    @Override
    public String getValues() {
        return values;
    }

    @Override
    public void setValues(String values) {
        this.values = values;
    }
}
