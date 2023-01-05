package com.github.tsuoihito.commandstick

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerInteractEvent
import org.bukkit.inventory.EquipmentSlot

class StickUseListener : Listener {
    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.hand == EquipmentSlot.OFF_HAND) return
        val meta = event.player.inventory.itemInMainHand.itemMeta ?: return
        if (!isCommandStick(meta)) return

        getCommand(meta)?.let { event.player.performCommand(it) }
    }
}
