package org.cubicdev.sqlTest.SQL;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnection {
    String host;
    String databaseName;
    String user;
    String password;
    Connection connection;

    String url;
    public SQLConnection(String host, String databaseName, String user, String password){
        this.host = host;
        this.databaseName = databaseName;
        this.user = user;
        this.password = password;

        this.url = "jdbc:mysql://"+host+":3306/"+databaseName;
    }

    public void createConnection(){
        try {
             connection = DriverManager.getConnection(url, user, password);
             Bukkit.getLogger().info("Conexion SQL establecida.");
        } catch (SQLException e) {
            Bukkit.getLogger().info("SQL error. Create connection");
            e.printStackTrace();
        }
    }

    public Connection getConnection(){
        return connection;
    }
}
