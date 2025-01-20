package org.cubicdev.sqlTest.Listener;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.TabCompleteEvent;
import org.cubicdev.sqlTest.Data.PlayerData;
import org.cubicdev.sqlTest.SqlTest;

import java.util.List;


public class PlayerListener implements Listener {
    private SqlTest main;
    private PlayerData playerData;
    public PlayerListener(SqlTest plugin){
        main = plugin;
        playerData = plugin.getPlayerData();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent ev){
        Player player = ev.getPlayer();
        player.sendMessage(ChatColor.translateAlternateColorCodes('&', "&cLogeate porfavor."));
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent ev){
        Location fromLoc = ev.getFrom();
        Location toLoc = ev.getTo();
        if(!playerData.isLogged(ev.getPlayer().getUniqueId())){
            if (fromLoc != toLoc) {
                ev.setCancelled(true);
                ev.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo puedes moverte antes de logearte."));
            }
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent ev){
        if(playerData.isLogged(ev.getPlayer().getUniqueId())){
            playerData.removeLoggedPlayer(ev.getPlayer().getUniqueId());
        }
    }

    @EventHandler
    public void onCommandPreProcess(PlayerCommandPreprocessEvent ev){
        if(ev.getMessage().equals("/login")) return;
        if(!playerData.isLogged(ev.getPlayer().getUniqueId())){
            ev.setCancelled(true);
            ev.getPlayer().sendMessage(ChatColor.translateAlternateColorCodes('&', "&cNo puedes enviar comandos antes de logearte."));
        }
    }
}
