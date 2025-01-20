package org.cubicdev.sqlTest;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class RegisterCommand implements CommandExecutor {
    private SqlTest main;
    public RegisterCommand(SqlTest main){
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        return false;
    }
}
