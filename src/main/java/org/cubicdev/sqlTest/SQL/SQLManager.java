package org.cubicdev.sqlTest.SQL;

import org.bukkit.Bukkit;
import org.cubicdev.sqlTest.SqlTest;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class SQLManager {
    private SQLConnection sqlConnection;
    Connection connection;

    public SQLManager(SQLConnection sqlConnection){
        this.sqlConnection = sqlConnection;
        connection = sqlConnection.getConnection();
    }


    public void createTables(){
        String query = "CREATE TABLE IF NOT EXISTS logins(uuid varchar(36) primary key, password varchar(255));";
        try {
            connection.createStatement().execute(query);
            Bukkit.getLogger().info("Creando tablas.");
        } catch (SQLException e) {
            Bukkit.getLogger().warning("SQL error. Create table");
            e.printStackTrace();
        }
    }

    public String getPassword(String uuid){
        String password = "";
        String query = "SELECT password FROM logins WHERE uuid='"+uuid+"';";

        try{
            ResultSet resultSet = connection.createStatement().executeQuery(query);
            while(resultSet.next()){
                password = resultSet.getString("password");
            }
        }catch (SQLException e){
            Bukkit.getLogger().warning("SQL error. GetPassword");
            e.printStackTrace();
        }

        if(password == null){
            return "error";
        }

        return password;
    }

    public boolean isPasswordCorrect(UUID uuid, String pass){
        return pass.equals(getPassword(uuid.toString()));
    }
}
