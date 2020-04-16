package dev.darkhorizon.es.virtualbosses.data.sql;

import dev.darkhorizon.es.virtualbosses.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public abstract class DataHandler {

    private static final Main plugin = Main.getPlugin(Main.class);

    /*
        Valores sacados de la config
        para el nombre de la database y tablas.
    */
    protected String database_name = "data";
    protected String statics_table = "statics";

    public String getStaticsTable() {
        return this.statics_table;
    }


    /*
        Conexión SQL.
     */

    protected Connection connection;
    protected abstract Connection getConnection();
    protected abstract void closeConnection();
    protected abstract void refreshConnection();

    /*
        ASYNC QUERIES
     */

    /*public void savePlayerData(String p, Warp pw) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO " + warps_table + " VALUES (?,?,?,?,?,?)");

            st.setString(1, p);
            st.setString(2, serializeLocation(pw.getLocation()));
            st.setString(3, pw.getName());
            st.setString(4, pw.getDesc());
            st.setString(5, serializeItem(pw.getItem()));
            st.setString(6, serializeState(pw.getState()));
            st.executeUpdate();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }*/



    protected void close(PreparedStatement ps, ResultSet rs){
        try {
            if (ps != null)
                ps.close();
            if (rs != null)
                rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
