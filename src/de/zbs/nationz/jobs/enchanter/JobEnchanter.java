package de.zbs.nationz.jobs.enchanter;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.util.Vector;

import de.zbs.nationz.jobs.Job;
import de.zbs.nationz.main.NationZ;

public class JobEnchanter extends Job {
	
	private Listener ls = new Listener() {
		@EventHandler
		public void onInteract(PlayerInteractEvent e) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getClickedBlock().getType() == Material.CAULDRON) {
					if (e.getClickedBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.DIAMOND_BLOCK) {
						Item item = e.getPlayer().getWorld().dropItem(e.getClickedBlock().getLocation().add(0.5, 2, 0.5), e.getPlayer().getEquipment().getItemInMainHand());
						item.setMetadata("item-owner", new FixedMetadataValue(NationZ.plugin, e.getPlayer().getUniqueId().toString()));
						item.setVelocity(new Vector(0, 0, 0));
						item.setCustomName(item.getName());
						item.setCustomNameVisible(true);
						e.getPlayer().getEquipment().setItemInMainHand(null);
						e.setCancelled(true);
					}
				}
			}
		}
		
		@EventHandler
		public void onPickUp(PlayerPickupItemEvent e) {
			if (e.getItem().hasMetadata("item-owner")) {
				e.setCancelled(true);
				if (e.getItem().getMetadata("item-owner").get(0).asString().equalsIgnoreCase(e.getPlayer().getUniqueId().toString())) {
					e.setCancelled(false);
				}
			}
		}
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Enchanter";
	}

	@Override
	public String getPrefix() {
		return "Enc";
	}

	@Override
	public Material getIcon() {
		return Material.ENCHANTED_BOOK;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "Enchanter will have more fortune when enchanting");
		desc.add(ChatColor.GREEN + "and will be able to choose Enchantments manually");
		desc.add(ChatColor.GREEN + "in higher Levels.");
		return desc;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		return p;
	}
	
	@Override
	public Perk[] getPerks() {
		return new Perk[] {
				new Perk("Cheaper Recipes", "Makes Enchanting-Recipes cheaper", "cheaperEnchant", Job.asHashMap(new String[] { //Percent
						"0:100",
						"12:90",
						"25:80",
						"30:70"
				}))
		};
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MzM3NDg0MTEsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2NlZDEyMTJmZTczMTRkZWNlNDU3YzU5NmYwM2M3ZTMzOGI2NzlmZGMyZjhhMWRkOGJkYzFlODYxOTU4ZSJ9fX0=";
	}
}
