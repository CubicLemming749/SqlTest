package org.cubicdev.sqlTest;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.cubicdev.sqlTest.Data.PlayerData;
import org.cubicdev.sqlTest.Listener.PlayerListener;
import org.cubicdev.sqlTest.SQL.SQLConnection;
import org.cubicdev.sqlTest.SQL.SQLManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;

public final class SqlTest extends JavaPlugin {
    PlayerData playerData;
    SQLConnection sqlConnection;
    SQLManager sqlManager;
    Connection connection;

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();

        playerData = new PlayerData();

        sqlConnection = new SQLConnection("localhost", "test", "root", "root");
        sqlConnection.createConnection();

        connection = sqlConnection.getConnection();

        sqlManager = new SQLManager(sqlConnection);
        sqlManager.createTables();

        pm.registerEvents(new PlayerListener(this), this);
        Objects.requireNonNull(this.getCommand("login")).setExecutor(new LoginCommand(this));
        Objects.requireNonNull(this.getCommand("register")).setExecutor(new RegisterCommand(this));
    }

    @Override
    public void onDisable() {
        try {
            connection.close();
        } catch (SQLException e) {
            getLogger().warning("SQL error.");
        }
    }

    public PlayerData getPlayerData(){
        return playerData;
    }

    public SQLManager getSqlManager() {
        return sqlManager;
    }

    public Connection getSqlConnection(){
        return connection;
    }
}
