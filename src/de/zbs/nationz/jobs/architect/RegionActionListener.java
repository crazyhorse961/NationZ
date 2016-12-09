package de.zbs.nationz.jobs.architect;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class RegionActionListener implements Listener {
	
	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {
		
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getAction() == Action.RIGHT_CLICK_AIR) {
			
		} else if(e.getAction() == Action.RIGHT_CLICK_BLOCK) {
			

		} else if(e.getAction() == Action.LEFT_CLICK_AIR) {
			

		} else if(e.getAction() == Action.LEFT_CLICK_BLOCK) {
			

		} else if(e.getAction() == Action.PHYSICAL) {
			
		}
	}
}