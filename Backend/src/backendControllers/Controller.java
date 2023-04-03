package backendControllers;

import database.DB;
import models.Model;
import models.Role;
import models.User;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Controller {
    public static Controller instance;

    public Controller() {
    }

    public static Controller getInstance() {
        if (instance == null)
            instance = new Controller();

        return instance;
    }

    public void start() {
        Communication communication = new Communication();
        communication.start();
    }


    /*
     | -----------------------------------
     | DATABASE SQL METHODS
     | -----------------------------------
     */
    public User getUser(User user) throws Exception {

        DB db = DB.getInstance();

        String query = db
                .table(user.getTable())
                .where(user.getWhere())
                .get();

        Statement s = DB.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(query);
        db.commit();

        User retrievedUser = new User();
        if (rs.isBeforeFirst()) {
            rs.next();

            // setting user instance properties with values retrieved from db
            retrievedUser.setId((int) rs.getInt("id"));
            retrievedUser.setFirstname(rs.getString("firstname"));
            retrievedUser.setLastname(rs.getString("lastname"));
            retrievedUser.setEmail(rs.getString("email"));
            retrievedUser.setUsername(rs.getString("username"));
            retrievedUser.setPassword(rs.getString("password"));
            retrievedUser.setRoleId(rs.getInt("role_id"));
            retrievedUser.setImage(rs.getString("image"));
            retrievedUser.setCreatedAt(rs.getDate("created_at"));
            retrievedUser.setUpdatedAt(rs.getDate("updated_at"));
        }

        return retrievedUser;
    }

    public Role getUserRole(Role role) throws Exception {

        DB db = DB.getInstance();

        String query = db
                .table(role.getTable())
                .where(role.getWhere())
                .get();

        Statement s = DB.getInstance().getConnection().createStatement();
        ResultSet rs = s.executeQuery(query);
        db.commit();

        Role retrievedRole = new Role();
        if (rs.isBeforeFirst()) {
            rs.next();

            retrievedRole.setId((int) rs.getInt("id"));
            retrievedRole.setName(rs.getString("name"));
        }

        return retrievedRole;
    }


    public boolean addUser(User user) throws Exception {

        String columns = this.getFields(user).get("names");
        String values = this.getFields(user).get("values");

        DB db = DB.getInstance();

        String query = db
                .table(user.getTable())
                .columns(columns)
                .values(values)
                .insert();

        Statement s = DB.getInstance().getConnection().createStatement();
        int rowsAffected = s.executeUpdate(query);

        if (rowsAffected == 0) {
            db.rollback();
            return false;
        }

        db.commit();
        return true;
    }

    public User addAndReturnUser(User user) throws Exception {

        String columns = this.getFields(user).get("names");
        String values = this.getFields(user).get("values");
        System.out.println(columns);
        System.out.println(values);
        DB db = DB.getInstance();

        String query = db
                .table(user.getTable())
                .columns(columns)
                .values(values)
                .insert();

        Statement s = DB.getInstance().getConnection().createStatement();
        int rowsAffected = s.executeUpdate(query);

        if (rowsAffected == 0) {
            db.rollback();
            return null;
        }

        db.commit();
        user.setWhere("username = '" + user.getUsername() + "'");
        return this.getUser(user);
    }


    /*
     | --------------------------------
     | HELPERS
     | --------------------------------
     */

    /**
     * @param model Method returns field names - part of sql query
     */
    private HashMap<String, String> getFields(Model model) {

        Field[] fields = model.getClass().getDeclaredFields();

        StringBuilder names = new StringBuilder();
        StringBuilder values = new StringBuilder();

        int counter = 0;

        for (Field field : fields) {

            Object value = getFieldValue(model, field);

            if (!field.getName().equals("table"))
                if (value != null && value != (Object) 0) {
                    names.append(field.getName() + ", ");
                    values.append("'" + value + "', ");
                }

            counter++;
        }

        String namesFinal = names.substring(0, names.length() - 2);
        String valuesFinal = values.substring(0, values.length() - 2);

        HashMap<String, String> result = new HashMap<>(2);
        result.put("names", namesFinal);
        result.put("values", valuesFinal);

        return result;

    }

    /**
     * @param field Method returns dynamically field value
     */
    private static Object getFieldValue(Model model, Field field) {

        Method method = null;
        String fieldName = field.getName();
        fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        if (fieldName.contains("_")) {

            String[] words = fieldName.split("_");

            StringBuilder sb = new StringBuilder();
            for (String word : words) {
                sb.append(word.substring(0, 1).toUpperCase());
                sb.append(word.substring(1));
            }
            fieldName = sb.toString();
        }

        try {
            method = model
                    .getClass()
                    .getMethod("get" + fieldName);

            return method.invoke(model);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}
