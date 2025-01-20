package org.cubicdev.sqlTest.Data;

import java.util.HashMap;
import java.util.UUID;

public class PlayerData {
    HashMap<UUID, Boolean> loggedPlayers = new HashMap<>();

    public HashMap<UUID, Boolean> getLoggedPlayers(){
        return loggedPlayers;
    }

    public boolean isLogged(UUID uuid) {
        return loggedPlayers.getOrDefault(uuid, false);
    }

    public void addLoggedPlayer(UUID uuid){
        loggedPlayers.put(uuid, true);
    }

    public void removeLoggedPlayer(UUID uuid){
        loggedPlayers.remove(uuid);
    }
}
