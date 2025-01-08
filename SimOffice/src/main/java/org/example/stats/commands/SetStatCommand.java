package org.example.stats.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.example.stats.FormatNumber;
import org.example.stats.PlayerStats;
import org.example.stats.PlayerStatsManager;

import java.text.Format;

public class SetStatCommand implements CommandExecutor {
    private final PlayerStatsManager playerStatsManager;

    public SetStatCommand(PlayerStatsManager playerStatsManager) {
        this.playerStatsManager = playerStatsManager;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эту команду может использовать только игрок.");
            return true;
        }

        if (args.length < 3) {
            sender.sendMessage("Использование: /setstat <игрок> <баланс|доход|уровень|реборн> <значение>");
            return true;
        }

        String playerName = args[0];
        PlayerStats playerStats = playerStatsManager.getPlayerStats(playerName);

        if (playerStats == null) {
            sender.sendMessage("Не удалось загрузить данные игрока.");
            return true;
        }

        String statType = args[1].toLowerCase();
        String value = args[2];

        try {
            double numericValue = FormatNumber.parseValue(value);
            switch (statType) {
                case "баланс":
                    playerStatsManager.setPlayerBalance(playerName, numericValue);
                    sender.sendMessage("Баланс установлен на: " + FormatNumber.formatNumber(numericValue));
                    break;
                case "доход":
                    playerStatsManager.setPlayerIncome(playerName, numericValue);
                    sender.sendMessage("Доход установлен на: " + FormatNumber.formatNumber(numericValue));
                    break;
                case "уровень":
                    int level = (int) numericValue;
                    playerStatsManager.setPlayerLevel(playerName, level);
                    sender.sendMessage("Уровень установлен на: " + FormatNumber.formatNumber(level));
                    break;
                case "реборн":
                    int rebirth = (int) numericValue;
                    playerStatsManager.setPlayerRebirth(playerName, rebirth);
                    sender.sendMessage("Реборн установлен на: " + FormatNumber.formatNumber(rebirth));
                    break;
                default:
                    sender.sendMessage("Неизвестный тип статистики: " + statType);
                    break;
            }
        } catch (NumberFormatException e) {
            sender.sendMessage("Неверный формат значения для " + statType + ": " + value);
        }

        return true;
    }
}
