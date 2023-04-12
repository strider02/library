package database;

public interface QueryBuilder {
    /**
     * @param table
     */
    database.QueryBuilder table(String table);

    /**
     * @param columns
     */
    database.QueryBuilder select(String... columns);


    /**
     * @param condition
     */
    database.QueryBuilder where(String condition);

    /**
     * @param joins inner join, left join, right join, crossjoin
     */
    database.QueryBuilder join(String joins);


    database.QueryBuilder values(String values);

    /**
     * @param orderBy
     */
    database.QueryBuilder orderBy(String orderBy);

    /**
     * @return
     */
    String get();
    String insert();
}
