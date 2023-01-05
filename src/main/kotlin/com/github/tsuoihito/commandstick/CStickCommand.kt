package com.github.tsuoihito.commandstick

import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class CStickCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) return false
        if (sender !is Player) return true

        sender.inventory.addItem(getCommandStick(args.toList()))
        return true
    }
}
