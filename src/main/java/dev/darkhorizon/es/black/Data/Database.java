package dev.darkhorizon.es.black.Data;

import dev.darkhorizon.es.black.Main;

import java.sql.Connection;


public abstract class Database {
    Main plugin;
    Connection connection;
    public String table = "data";

    public Database(Main instance) {
        plugin = instance;
    }

    public abstract void load();

    public abstract Connection getSQLConnection();

}