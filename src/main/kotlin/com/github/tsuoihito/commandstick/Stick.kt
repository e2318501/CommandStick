package com.github.tsuoihito.commandstick

import org.bukkit.Material
import org.bukkit.enchantments.Enchantment
import org.bukkit.inventory.ItemFlag
import org.bukkit.inventory.ItemStack
import org.bukkit.inventory.meta.ItemMeta

fun getCommandStick(command: List<String>): ItemStack {
    return ItemStack(Material.STICK).apply {
        itemMeta = itemMeta?.apply {
            val commandString = command.joinToString(" ")
            setDisplayName("Command Stick [ /${commandString} ]")
            lore = listOf(commandString, "Command Stick")
            addEnchant(Enchantment.MENDING, 1, true)
            addItemFlags(ItemFlag.HIDE_ENCHANTS)
        }
    }
}

fun isCommandStick(meta: ItemMeta): Boolean {
    return meta.displayName.startsWith("Command Stick") || meta.lore?.contains("Command Stick")?: false
}

fun getCommand(meta: ItemMeta): String? {
    val lore = meta.lore
    if (lore == null || lore.size == 0) return null
    return lore[0]
}
