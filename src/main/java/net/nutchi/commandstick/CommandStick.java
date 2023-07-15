package net.nutchi.commandstick;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class CommandStick extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        getCommand("commandstick").setExecutor(this);
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length >= 1 && sender instanceof Player) {
            ((Player) sender).getInventory().addItem(getCommandStick(Arrays.asList(args)));
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        return new ArrayList<>();
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            getCommand(event.getPlayer().getInventory().getItemInMainHand()).ifPresent(c -> event.getPlayer().performCommand(c));
        }
    }

    private ItemStack getCommandStick(List<String> cmd) {
        ItemStack stick = new ItemStack(Material.STICK);
        ItemMeta meta = stick.getItemMeta();
        if (meta != null) {
            String cmdStr = String.join(" ", cmd);
            meta.setDisplayName("Command stick [ /" + cmdStr + " ]");
            meta.setLore(Collections.singletonList(cmdStr));
            meta.addEnchant(Enchantment.MENDING, 1, true);
            meta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        stick.setItemMeta(meta);
        return stick;
    }

    private Optional<String> getCommand(ItemStack stick) {
        ItemMeta meta = stick.getItemMeta();
        if (meta != null && isCommandStickMeta(meta)) {
            List<String> lore = meta.getLore();
            if (lore != null && lore.size() != 0) {
                return Optional.of(lore.get(0));
            }
        }
        return Optional.empty();
    }

    private boolean isCommandStickMeta(ItemMeta meta) {
        return meta.getDisplayName().startsWith("Command stick");
    }
}
