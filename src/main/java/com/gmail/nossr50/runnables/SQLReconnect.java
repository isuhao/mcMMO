package com.gmail.nossr50.runnables;

import org.bukkit.entity.Player;

import com.gmail.nossr50.McMMO;
import com.gmail.nossr50.datatypes.PlayerProfile;
import com.gmail.nossr50.util.Database;
import com.gmail.nossr50.util.Users;

public class SQLReconnect implements Runnable {
    private final McMMO plugin;

    public SQLReconnect(McMMO plugin) {
        this.plugin = plugin;
    }

    @Override
    public void run() {
        if (!Database.isConnected()) {
            Database.connect();
            if (Database.isConnected()) {
                for (PlayerProfile x : Users.getProfiles().values()) {
                    x.save(); //Save all profiles
                }

                Users.getProfiles().clear(); //Clear the profiles
                for (Player x : plugin.getServer().getOnlinePlayers()) {
                    Users.addUser(x); //Add in new profiles, forcing them to 'load' again from MySQL
                }
            }
        }
    }
}
