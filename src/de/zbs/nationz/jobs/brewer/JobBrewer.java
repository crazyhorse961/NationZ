package de.zbs.nationz.jobs.brewer;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import de.zbs.advinventory.api.AdvInventory;
import de.zbs.advinventory.api.AdvInventory.ClickRunnable;
import de.zbs.nationz.api.DescriptionItem;
import de.zbs.nationz.api.DescriptionItem.ListItemType;
import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.jobs.Job;
import de.zbs.nationz.util.ItemUtils;

public class JobBrewer extends Job {
	
	private Listener ls = new Listener() {
		@EventHandler
		public void onInteract(PlayerInteractEvent e) {
			if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
			if (!(e.getClickedBlock().getType().equals(Material.BREWING_STAND))) return;
			NZPlayer n = NZPlayer.getNZPlayer(e.getPlayer());
			if (n.getJob() != Job.BREWER) return;
			
			AdvInventory a = new AdvInventory("Brewer", 54, ItemUtils.placeholder());
			
			DescriptionItem di = new DescriptionItem("Extend Time", new ItemStack(Material.BLAZE_ROD));
			di.appendString("Extend Time by one Minute");
			di.appendSpacer();
			di.appendInfo("Time:", ChatColor.GOLD + "0 " + ChatColor.YELLOW + "Minutes");
			di.appendSpacer();
			di.appendString("Required Items:");
			di.appendListItem(ListItemType.SPACER, "32x Redstone Dust");
			di.appendListItem(ListItemType.SPACER, "1x  Diamond");
			a.setItem(di.build(), 10, new ClickRunnable() {
				@Override
				public void run(InventoryClickEvent e) {
					Player p = (Player) e.getWhoClicked();
					boolean redstone = true;
					boolean diamond = true;
					if (p.getInventory().contains(new ItemStack(Material.REDSTONE), 32)) {
						redstone = false;
					}
					if (p.getInventory().contains(new ItemStack(Material.DIAMOND), 1)) {
						diamond = false;
					}
					if (redstone || diamond) {
						p.sendMessage(ChatColor.GOLD + "One or more required Ingredients are missing:");
						if (redstone) {
							p.sendMessage(ChatColor.YELLOW + "     32x Redstone Dust");
						}
						if (diamond) {
							p.sendMessage(ChatColor.YELLOW + "     1x Diamond");
						}
					} else {
						//TODO Finish
					}
				}
			});
			
			a.openInventory(e.getPlayer());
		}
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}
	
	@Override
	public String getName() {
		return "Brewer";
	}

	@Override
	public String getPrefix() {
		return "Bre";
	}

	@Override
	public Material getIcon() {
		return Material.GHAST_TEAR;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "Brewers can make potions beyond the limits of the");
		desc.add(ChatColor.GREEN + "normal Humans, they can make them faster, better and");
		desc.add(ChatColor.GREEN + "more efficient using less materials.");
		return desc;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		return p;
	}
	
	@Override
	public Perk[] getPerks() {
		return null;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MzM2NjQ4OTYsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2M1NThmZGJhMGYyZTYzMmQ0MzcxMTUyZjQxOTE4ODgwNmI5ZjQ3Y2QzZTEyZjFkM2EzMGI3NTNlY2NmIn19fQ==";
	}
	
}
