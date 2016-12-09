package de.zbs.nationz.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.api.NZPlayer;

public class PlayerDrinkListener implements Listener {
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onConsume(PlayerItemConsumeEvent pice) {
		if (pice.getItem().getType().equals(Material.MILK_BUCKET)) {
			pice.setCancelled(true);
			for (PotionEffect pe : pice.getPlayer().getActivePotionEffects()) { // clear effects
				pice.getPlayer().removePotionEffect(pe.getType());
			}
			pice.getPlayer().addPotionEffects(NZPlayer.getNZPlayer(pice.getPlayer()).getNation().getEffects());  // re-add potion effects
		}
	}
}
