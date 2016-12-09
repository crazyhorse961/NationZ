package de.zbs.nationz.listener;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class PlayerPickupItemListener implements Listener {

	@EventHandler
	public void onPickup(PlayerPickupItemEvent e) {
		if (e.getItem().getCustomName() != null) {
			if (!e.getItem().getCustomName().equalsIgnoreCase(ChatColor.GRAY + "Only " + ChatColor.RED + e.getPlayer().getDisplayName() + ChatColor.GRAY + " can pick this Item up")) {
				e.setCancelled(true);
			}
		}
	}
}
