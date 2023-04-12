package database;

import config.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author admin
 */
public class DB implements QueryBuilder {

    private static DB instance;
    private static QueryBuilder queryBuilder;
    private Connection connection;

    /**
     * fields from query builder interface
     */
    private String query;
    private String table;
    private List columns = new ArrayList<>();
    private Map<String, Object> set;

    private String join;
    private String where;
    private String orderBy;
    private String values;

    public static DB getInstance() throws IOException, ClassNotFoundException, SQLException {
        if (instance == null) {
            instance = new DB();
        }

        instance.reset();

        return instance;
    }

    private DB() throws IOException, ClassNotFoundException, SQLException {
        // connection to db
        Util util = Util.getInstance();

        Class.forName(util.get("DRIVER"));
        System.out.println("Driver is loaded.");

        connection = DriverManager
                .getConnection(
                        util.get("URL"),
                        util.get("USER"),
                        util.get("PASSWORD")
                );
        System.out.println("Connection is established.");

        connection.setAutoCommit(false);
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException ex) {
            Logger.getLogger(DB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    /*
     * QueryBuilder methods
     */
    @Override
    public QueryBuilder table(String table) {
        this.table = table;
        return this;
    }

    @Override
    public QueryBuilder select(String... columns) {
        this.columns.addAll(Arrays.asList(columns));
        return this;
    }

    @Override
    public QueryBuilder where(String condition) {
        this.where = condition;
        return this;
    }

    @Override
    public QueryBuilder join(String join) {
        this.join = join;
        return this;
    }

    @Override
    public QueryBuilder values(String values) {
        this.values = values;
        return this;
    }

    @Override
    public QueryBuilder orderBy(String orderBy) {
        this.orderBy = orderBy;
        return this;
    }

    @Override
    public String get() {

        StringBuilder sb = new StringBuilder();


        // select
        sb.append("SELECT ");
        if (columns.isEmpty())
            sb.append("*");
        else
            sb.append(String.join(", ", columns));

        // from
        sb.append(" FROM ").append(this.table);

        // joins
        if (join != null)
            sb.append(" ").append(this.join);

        // conditions
        if (where != null)
            sb.append(" WHERE ").append(this.where);

        // sort
        if (orderBy != null)
            sb.append(" ").append(this.orderBy);

        return sb.toString();

    }

    @Override
    public String insert() {

        StringBuilder sb = new StringBuilder();

        sb.append("INSERT INTO ").append(this.table);
        sb.append(" (").append(String.join(", ", columns)).append(") ");
        sb.append("VALUES (").append(this.values).append(")");

        return sb.toString();
    }

    public String delete() {

        StringBuilder sb = new StringBuilder();

        sb.append("DELETE FROM ");
        sb.append(this.table);
        sb.append(" WHERE ").append(this.where);

        return sb.toString();
    }

    public String save() {

        StringBuilder sb = new StringBuilder();

        sb.append("UPDATE ").append(this.table);
        sb.append(" SET ").append(mapToString(this.set));
        sb.append(" WHERE ").append(this.where);

        return sb.toString();
    }

    private String mapToString(Map<String, Object> set) {

        String string;
        for (Map.Entry<String, Object> entry : set.entrySet()) {

        }

        return null;
    }

    private void reset() {
        this.query = null;
        this.table = null;
        this.columns.clear();
        this.set = null;
        this.join = null;
        this.where = null;
        this.orderBy = null;
        this.values = null;
    }


}
