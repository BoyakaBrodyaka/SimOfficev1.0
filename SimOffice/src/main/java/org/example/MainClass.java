package org.example;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.example.building.*;
import org.example.menu.MenuItemHandler;
import org.example.menu.MenuListener;
import org.example.menu.rebirth.RebirthHandler;
import org.example.menu.rebirth.RebirthListener;
import org.example.menu.rebirth.RebirthMenu;
import org.example.menu.rebirth.UpgradesMenu;
import org.example.menu.settings.FlightSetting;
import org.example.menu.settings.FlightSpeedSetting;
import org.example.menu.settings.SettingsMenu;
import org.example.menu.shop.ShopMenu;
import org.example.menu.shop.equipment.EquipmentInventory;
import org.example.menu.shop.furniture.FurnitureInventory;
import org.example.menu.shop.furniture.FurnitureManager;
import org.example.menu.shop.furniture.building.chairOne.ChairOneInventory;
import org.example.menu.shop.furniture.building.chairOne.ChairOne;
import org.example.menu.shop.furniture.building.tableOne.TableOne;
import org.example.menu.shop.office.OfficeInventory;
import org.example.start.*;
import org.example.stats.IncomeManager;
import org.example.stats.PlayerStatsManager;
import org.example.stats.commands.ResetStatsCommand;
import org.example.stats.commands.SetStatCommand;
import org.example.stats.commands.SetStatusCommand;
import org.example.stats.scoreboard.ScoreboardManager;
import org.example.stats.PlayerStats;

import java.util.UUID;

public class MainClass extends JavaPlugin implements Listener {
    private PlayerStats playerStats;
    private ScoreboardManager scoreboardManager;
    private static final String REALM_NAME = "PrisonServer";
    private IncomeManager incomeManager;
    private PlayerStatsManager playerStatsManager;
    private ArenaManager arenaManager;
    private OfficeManager officeManager;
    private OfficeDataManager officeDataManager;
    private SettingsMenu settingsMenu;
    private ShopMenu shopMenu;
    private FurnitureInventory furnitureInventory;
    private UpgradesMenu upgradesMenu;

    public void onEnable() {

        playerStatsManager = new PlayerStatsManager(this);
        incomeManager = new IncomeManager(playerStatsManager);
        officeDataManager = new OfficeDataManager(this);
        officeManager = new OfficeManager(this, officeDataManager);
        arenaManager = new ArenaManager(this, officeDataManager);
        shopMenu = new ShopMenu(playerStatsManager);
        FurnitureManager furnitureManager = new FurnitureManager(furnitureInventory, playerStatsManager);
        furnitureInventory = new FurnitureInventory(shopMenu, furnitureManager);
        upgradesMenu = new UpgradesMenu();

        settingsMenu = new SettingsMenu();
        FlightSetting flightSetting = new FlightSetting(settingsMenu);
        FlightSpeedSetting flightSpeedSetting = new FlightSpeedSetting(settingsMenu);
        settingsMenu.setFlightSetting(flightSetting);
        settingsMenu.setFlightSpeedSetting(flightSpeedSetting);

        scoreboardManager = new ScoreboardManager(playerStatsManager, REALM_NAME);
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : getServer().getOnlinePlayers()) {
                    UUID uuid = player.getUniqueId();
                    scoreboardManager.updateScoreboard(player);
                }
            }
        }.runTaskTimer(this, 0L, 20L);

        getServer().getPluginCommand("setstat").setExecutor(new SetStatCommand(playerStatsManager));
        getServer().getPluginCommand("setstatus").setExecutor(new SetStatusCommand(playerStatsManager));
        getServer().getPluginCommand("givebuilder").setExecutor(new GiveBuilderCommand());
        getServer().getPluginCommand("givetablebuilder").setExecutor(new GiveTableBuilderCommand());
        getServer().getPluginCommand("resetstats").setExecutor(new ResetStatsCommand(getDataFolder(), playerStatsManager));
        getServer().getPluginCommand("home").setExecutor(new HomeCommand(officeManager));
        getServer().getPluginCommand("resetoffice").setExecutor(new ResetOfficeCommand(officeManager));

        getServer().getPluginManager().registerEvents(settingsMenu, this);
        getServer().getPluginManager().registerEvents(new BuildingListener(), this);

        getServer().getPluginManager().registerEvents(new TableOne(this), this);
        getServer().getPluginManager().registerEvents(new ChairOne(this), this);
        getServer().getPluginManager().registerEvents(new ChairOneInventory(this, playerStatsManager), this);

        Bukkit.getPluginManager().registerEvents(new MenuListener(settingsMenu, officeManager, playerStatsManager, this), this);
        Bukkit.getPluginManager().registerEvents(new MenuItemHandler(), this);
        getServer().getPluginManager().registerEvents(playerStatsManager, this);
        getServer().getPluginManager().registerEvents(new PlayerJoinListener(officeManager, arenaManager), this);
        getServer().getPluginManager().registerEvents(new CreateOfficeListener(officeManager), this);
        getServer().getPluginManager().registerEvents(new OfficeInventory(shopMenu), this);
        getServer().getPluginManager().registerEvents(new FurnitureInventory(shopMenu, furnitureManager), this);
        getServer().getPluginManager().registerEvents(new EquipmentInventory(shopMenu), this);

        for (Player player : Bukkit.getOnlinePlayers()) {
            playerStatsManager.loadPlayerData(player.getName());
        }
    }
    public void onDisable() {
        playerStatsManager.saveAllPlayerData();
        if (playerStats != null) {
            playerStats.save();
        }
    }

    public PlayerStats getPlayerStats() {
        return playerStats;
    }

    public OfficeManager getOfficeManager() {
        return officeManager;
    }

    public ArenaManager getArenaManager() {
        return arenaManager;
    }

    public OfficeDataManager getOfficeDataManager() {
        return officeDataManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        MenuItemHandler.giveMenuItem(player);
    }
}