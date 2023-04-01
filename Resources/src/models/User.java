package models;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class User extends Model {

    /*
     *-------------------------------------
     * FIELDS
     *-------------------------------------
     */
    private int id;
    private String firstname;
    private String lastname;
    private String email;
    private String username;
    private String password;
    private int role_id;
    private String image;
    private Date created_at;
    private Date updated_at;

    private String where;
    private String values;


    private final String table = "users";
    private Role role;

    public User() {

    }

    public User(String firstname, String lastname, String email, String username, String password, int role_id) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.email = email;
        this.username = username;
        this.password = password;
        this.role_id = role_id;
        this.created_at = new Date(System.currentTimeMillis());
        this.updated_at = new Date(System.currentTimeMillis());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String surname) {
        this.lastname = surname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRoleId() {
        return role_id;
    }

    public void setRoleId(int role_id) {
        this.role_id = role_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Date getCreatedAt() {
        return created_at;
    }

    public void setCreatedAt(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdatedAt() {
        return updated_at;
    }

    public void setUpdatedAt(Date updated_at) {
        this.updated_at = updated_at;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTable() {
        return table;
    }


    /*
     * MODEL METHODS IMPLEMENTATION
     */
    @Override
    public List<Model> all(ResultSet rs) throws SQLException {
        return null;
    }

    @Override
    public String table() {
        return null;
    }


    @Override
    public String getJoin() {
        return null;
    }

    @Override
    public void setJoin(String join) {

    }

    @Override
    public String getWhere() {
        return where;
    }

    @Override
    public void setWhere(String where) {
        this.where = where;
    }

    public String getValues() {
        return values;
    }

    @Override
    public void setValues(String set) {
        this.values = values;
    }

}