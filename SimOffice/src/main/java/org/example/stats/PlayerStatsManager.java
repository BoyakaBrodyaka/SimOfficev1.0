package org.example.stats;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.example.stats.PlayerStats;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class PlayerStatsManager implements Listener {

    private JavaPlugin plugin;
    private Map<String, PlayerStats> playerDataMap = new HashMap<>();

    public PlayerStatsManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        loadPlayerData(player.getName());
        PlayerStats playerStats = playerDataMap.get(player.getName());
        if (playerStats != null) {
            player.sendMessage("Добро пожаловать, ваши данные загружены.");
        } else {
            player.sendMessage("Не удалось загрузить ваши данные, создаем новые...");
        }
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        savePlayerData(player.getName());
    }

    public void saveAllPlayerData() {
        for (String playerName : playerDataMap.keySet()) {
            savePlayerData(playerName);
        }
    }

    public void loadAllPlayerData() {
        File folder = plugin.getDataFolder();
        if (!folder.exists()) {
            folder.mkdir();
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".yml"));
        if (files != null) {
            for (File file : files) {
                String playerName = file.getName().replace(".yml", "");
                loadPlayerData(playerName);
            }
        }
    }

    public void loadPlayerData(String playerName) {
        File file = new File(plugin.getDataFolder(), playerName + ".yml");
        if (!file.exists()) {
            plugin.getLogger().info("Создан новый файл для игрока: " + playerName);
            playerDataMap.put(playerName, new PlayerStats(playerName, plugin.getDataFolder(), this));
            return;
        }

        PlayerStats playerStats = new PlayerStats(playerName, plugin.getDataFolder(), this);
        playerDataMap.put(playerName, playerStats);
        plugin.getLogger().info("Данные загружены для игрока: " + playerName);
    }

    public void savePlayerData(String playerName) {
        PlayerStats playerStats = playerDataMap.get(playerName);
        if (playerStats == null) {
            plugin.getLogger().warning("PlayerStats is null for player with name: " + playerName);
            return;
        }

        playerStats.save();
        plugin.getLogger().info("Данные сохранены для игрока: " + playerName);
    }

    public PlayerStats getPlayerStats(String playerName) {
        return playerDataMap.get(playerName);
    }

    public void setPlayerRebirth(String playerName, int rebirth) {
        PlayerStats playerStats = getPlayerStats(playerName);
        if (playerStats != null) {
            playerStats.setRebirth(rebirth);
        }
    }

    public void setPlayerLevel(String playerName, int level) {
        PlayerStats playerStats = getPlayerStats(playerName);
        if (playerStats != null) {
            playerStats.setLevel(level);
        }
    }

    public void setPlayerIncome(String playerName, double income) {
        PlayerStats playerStats = getPlayerStats(playerName);
        if (playerStats != null) {
            playerStats.setIncome(income);
        }
    }

    public void setPlayerBalance(String playerName, double balance) {
        PlayerStats playerStats = getPlayerStats(playerName);
        if (playerStats != null) {
            playerStats.setBalance(balance);
        }
    }

    public void setPlayerTokens(String playerName, int tokens) {
        PlayerStats playerStats = getPlayerStats(playerName);
        if (playerStats != null) {
            playerStats.setTokens(tokens);
        }
    }
}
