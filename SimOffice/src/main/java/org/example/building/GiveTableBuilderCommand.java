package org.example.building;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class GiveTableBuilderCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Эту команду может использовать только игрок.");
            return true;
        }
        Player player = (Player) sender;
        ItemStack builderItem = new ItemStack(Material.BLAZE_ROD);
        ItemMeta meta = builderItem.getItemMeta();
        meta.setDisplayName("Table Builder Rod");
        builderItem.setItemMeta(meta);
        player.getInventory().addItem(builderItem);
        return true;
    }
}
