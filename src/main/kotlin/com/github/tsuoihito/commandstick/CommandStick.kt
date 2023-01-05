package com.github.tsuoihito.commandstick

import org.bukkit.plugin.java.JavaPlugin

class CommandStick : JavaPlugin() {
    override fun onEnable() {
        getCommand("commandstick")?.setExecutor(CStickCommand())
        server.pluginManager.registerEvents(StickUseListener(), this)
    }

    override fun onDisable() {
    }
}
