package dev.darkhorizon.es.black.data.sql;

import dev.darkhorizon.es.black.Main;
import org.bukkit.Bukkit;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.io.File;
import java.util.logging.Level;

public class SQLite extends DataHandler {

    private static final Main plugin = Main.getPlugin(Main.class);

    private final String STATICS_DATA_QUERY = "CREATE TABLE IF NOT EXISTS `" + super.statics_table + "` (`id` INTEGER PRIMARY KEY AUTOINCREMENT, `username` VARCHAR(20) NOT NULL, `votes` INT, `topg` LONG, `mcmp` LONG, `cuarenta_sv` LONG);";

    public SQLite() {
        load();
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private Connection getSQLConnection() {
        File dataFolder = new File(plugin.getDataFolder() + File.separator + "database" , database_name + ".db");
        if (!dataFolder.exists()){
            try {
                dataFolder.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Error al crear el archivo: " + database_name + ".db");
            }
        }
        try {
            if(connection != null && !connection.isClosed()){
                return connection;
            }
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:" + dataFolder);
            return connection;
        } catch (SQLException ex) {
            plugin.getLogger().log(Level.SEVERE,"Ocurrió un error: ", ex);
        } catch (ClassNotFoundException ex) {
            plugin.getLogger().log(Level.SEVERE, "Ocurrió un error: No se encontró la clase: " + ex);
        }
        return null;
    }

    @Override
    public Connection getConnection() {
        return this.connection;
    }

    @Override
    public void closeConnection(){
        try{
            if(connection != null) {
                connection.close();
                connection = null;
                Bukkit.getConsoleSender().sendMessage("§c§lLOG> §fConexión §aSQLite §fcerrada §acorrectamente");
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void refreshConnection() {
        try {
            if(getConnection().isClosed() || getConnection() == null) {
                getSQLConnection();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void load() {
        connection = getSQLConnection();
        try {
            Statement s = connection.createStatement();
            s.executeUpdate(STATICS_DATA_QUERY);
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
