package org.example.stats;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.example.stats.status.IncomeModifier;
import org.example.stats.status.IncomeModifierFactory;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class PlayerStats {
    private String status = "Хороший";
    private double balance = 0.0;
    private double income = 0.0;
    private double baseIncome = 0.0; // Изначальный доход
    private int level = 1;
    private int rebirth = 0;
    private int tokens = 0;
    private PlayerStatsManager playerStatsManager;

    private String playerName;
    private transient File dataFolder; // Сериализация поля будет пропущена

    public PlayerStats(String playerName, File dataFolder, PlayerStatsManager playerStatsManager) {
        this.playerName = playerName;
        this.dataFolder = dataFolder;
        this.playerStatsManager = playerStatsManager;
        load();
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; updateIncome(); save(); }
    public double getBalance() { return balance; }
    public void setBalance(double balance) { this.balance = balance; save(); }
    public double getIncome() { return income; }
    public void setIncome(double income) {
        this.baseIncome = income; // Обновляем изначальный доход
        updateIncome();
        save();
    }
    public int getLevel() { return level; }
    public void setLevel(int level) { this.level = level; save(); }
    public int getRebirth() { return rebirth; }
    public void setRebirth(int rebirth) { this.rebirth = rebirth; save(); }
    public int getTokens() { return tokens; }
    public void setTokens(int tokens) { this.tokens = tokens; save(); }

    public void save() {
        File file = new File(dataFolder, playerName + ".yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
                return;
            }
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        config.set("status", status);
        config.set("balance", balance);
        config.set("income", income);
        config.set("baseIncome", baseIncome); // Сохраняем изначальный доход
        config.set("level", level);
        config.set("rebirth", rebirth);
        try {
            config.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        File file = new File(dataFolder, playerName + ".yml");
        if (!file.exists()) {
            return;
        }
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        status = config.getString("status", "Хороший");
        balance = config.getDouble("balance", 0.0);
        income = config.getDouble("income", 0.0);
        baseIncome = config.getDouble("baseIncome", 0.0);
        level = config.getInt("level", 1);
        rebirth = config.getInt("rebirth", 0);
    }

    public void reset() {
        status = "Хороший";
        balance = 0.0;
        income = 0.0;
        baseIncome = 0.0;
        level = 1;
        rebirth = 0;

        playerStatsManager.setPlayerBalance(playerName, balance);
        playerStatsManager.setPlayerIncome(playerName, baseIncome);
        playerStatsManager.setPlayerLevel(playerName, level);
        playerStatsManager.setPlayerRebirth(playerName, rebirth);
        save();
    }

    public double getModifiedIncome() {
        IncomeModifier incomeModifier = IncomeModifierFactory.getIncomeModifier(status);
        double modifiedIncome = incomeModifier.modifyIncome(baseIncome);
        return modifiedIncome;
    }

    public void updateIncome() {
        income = getModifiedIncome();
    }
}
