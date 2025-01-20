package org.cubicdev.sqlTest;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.cubicdev.sqlTest.Data.PlayerData;
import org.cubicdev.sqlTest.SQL.SQLManager;

public class LoginCommand implements CommandExecutor {
    private SqlTest main;
    private PlayerData data;
    private SQLManager sqlManager;

    public LoginCommand(SqlTest main){
        this.main = main;
        this.data = main.getPlayerData();
        this.sqlManager = main.getSqlManager();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player) commandSender;
        if(args.length == 1){
            if (sqlManager.isPasswordCorrect(player.getUniqueId(), args[0])) {
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&aLogeado con exito."));
                data.addLoggedPlayer(player.getUniqueId());
                return true;
            }

            if(data.isLogged(player.getUniqueId())){
                player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cYa estas logeado."));
                return true;
            }

            player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cContraseña incorrecta."));
            return true;
        }

        return false;
    }
}
