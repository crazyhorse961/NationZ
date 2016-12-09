package de.zbs.nationz.ability;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public abstract class Ability {
	
	public static final Ability FORCE_A_NATURE = new Ability_ForceANature();

	public static final Ability[] ABILITITES = new Ability[] {
			FORCE_A_NATURE
	};

	public abstract String getName();
	
	public abstract String[] getDescription();
	
	public abstract RightClickRunnable getRun();
	
	public abstract List<Material> applicableTo();
	
	public Listener interactListener() {
		return new Listener() {
			@EventHandler
			public void onInteract(PlayerInteractEvent e) {
				if (!(e.getPlayer().getEquipment().getItemInMainHand() == null)) { return; }
				if (!(e.getItem() == null)) { return; }
				if (!(e.getItem().getType() == null)) { return; }
				if (!(e.getItem().getType() == Material.AIR)) { return; }
				
				if (!e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().getLore().contains(ChatColor.RED + getName())) { return; }
				if (!applicableTo().contains(e.getItem().getType())) { return; }
				
				if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
					getRun().run(e.getPlayer(), e.getClickedBlock());
				} else if (e.getAction() == Action.RIGHT_CLICK_AIR) {
					getRun().run(e.getPlayer(), null);
				}
			}
		};
	}
	
	public static abstract class RightClickRunnable {
		public abstract void run(Player player, Block clickedBlock);
	}
	
	public static Ability getAbility(String string) {
		for (int i = 0; i < ABILITITES.length; i++) {
			if (string.equalsIgnoreCase(ABILITITES[i].getName())) {
				return ABILITITES[i];
			}
		}
		return null;
	}
}