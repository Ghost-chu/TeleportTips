package com.mcsunnyside.TeleportTips;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin implements Listener {
	@Override
	public void onEnable() {
		saveDefaultConfig();
		Bukkit.getPluginManager().registerEvents(this, this);
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void PlayerTeleported(PlayerTeleportEvent e) {
		TeleportCause Cause = e.getCause();
		if (Cause.equals(TeleportCause.COMMAND) || Cause.equals(TeleportCause.END_GATEWAY)
				|| Cause.equals(TeleportCause.END_PORTAL) || Cause.equals(TeleportCause.NETHER_PORTAL)
				|| Cause.equals(TeleportCause.PLUGIN)) {
			List<String> Tips = new ArrayList<>();
			Tips = getConfig().getStringList("Tips");
			Random rand = new Random();
			int randomNum = rand.nextInt(getConfig().getStringList("Tips").size());
			e.getPlayer().sendTitle(getConfig().getString("Strings.Teleport").replaceAll("&", "¡ì"),
					getConfig().getString("Strings.TipsPrefix").replaceAll("&", "¡ì")
							+ Tips.get(randomNum).replaceAll("&", "¡ì"),
					0, 80, 0);
		}
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equalsIgnoreCase("tips")) {
			reloadConfig();
			sender.sendMessage("Reloaded");
			return true;
		}
		return false;
	}
}
