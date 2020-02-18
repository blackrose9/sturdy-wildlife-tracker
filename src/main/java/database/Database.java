package database;

import org.sql2o.Sql2o;

public class Database {
    public static Sql2o sql2o = new Sql2o("jdbc:postgresql://localhost:4567/tracker", "postgres", "pg");
}
