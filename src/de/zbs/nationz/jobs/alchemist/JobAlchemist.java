package de.zbs.nationz.jobs.alchemist;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.jobs.Job;

public class JobAlchemist extends Job {
	
	private Listener ls = new Listener() {
		@EventHandler
		public void onInteract(PlayerInteractEvent e) {
			if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if (e.getClickedBlock().getType() == Material.ENCHANTMENT_TABLE) {
					if (NZPlayer.getNZPlayer(e.getPlayer()).getJob() == Job.ALCHEMIST) {
						if (e.getClickedBlock().getLocation().add(2, 1, 2).getBlock().getType() == Material.REDSTONE_TORCH_ON &&
								e.getClickedBlock().getLocation().add(2, 1, 2).getBlock().getType() == Material.REDSTONE_TORCH_ON &&
								e.getClickedBlock().getLocation().add(2, 1, -2).getBlock().getType() == Material.REDSTONE_TORCH_ON &&
								e.getClickedBlock().getLocation().add(-2, 1, 2).getBlock().getType() == Material.REDSTONE_TORCH_ON &&
								e.getClickedBlock().getLocation().add(-2, 1, -2).getBlock().getType() == Material.REDSTONE_TORCH_ON &&
		
								e.getClickedBlock().getLocation().add(2, 0, 1).getBlock().getType() == Material.IRON_FENCE &&
								e.getClickedBlock().getLocation().add(2, 0, -1).getBlock().getType() == Material.IRON_FENCE &&
		
								e.getClickedBlock().getLocation().add(-2, 0, 1).getBlock().getType() == Material.IRON_FENCE &&
								e.getClickedBlock().getLocation().add(-2, 0, -1).getBlock().getType() == Material.IRON_FENCE &&
		
								e.getClickedBlock().getLocation().add(1, 0, 2).getBlock().getType() == Material.IRON_FENCE &&
								e.getClickedBlock().getLocation().add(1, 0, -2).getBlock().getType() == Material.IRON_FENCE &&
		
								e.getClickedBlock().getLocation().add(-1, 0, 2).getBlock().getType() == Material.IRON_FENCE &&
								e.getClickedBlock().getLocation().add(-1, 0, -2).getBlock().getType() == Material.IRON_FENCE &&
		
								e.getClickedBlock().getLocation().add(2, 0, 2).getBlock().getType() == Material.MOSSY_COBBLESTONE &&
								e.getClickedBlock().getLocation().add(2, 0, -2).getBlock().getType() == Material.MOSSY_COBBLESTONE &&
								e.getClickedBlock().getLocation().add(-2, 0, 2).getBlock().getType() == Material.MOSSY_COBBLESTONE &&
								e.getClickedBlock().getLocation().add(-2, 0, -2).getBlock().getType() == Material.MOSSY_COBBLESTONE &&
		
								e.getClickedBlock().getLocation().add(0, -1, 0).getBlock().getType() == Material.GOLD_BLOCK &&
		
								e.getClickedBlock().getLocation().add(1, -1, 1).getBlock().getType() == Material.WOOD_STEP &&
								e.getClickedBlock().getLocation().add(1, -1, -1).getBlock().getType() == Material.WOOD_STEP &&
								e.getClickedBlock().getLocation().add(-1, -1, 1).getBlock().getType() == Material.WOOD_STEP &&
								e.getClickedBlock().getLocation().add(-1, -1, -1).getBlock().getType() == Material.WOOD_STEP &&
		
								e.getClickedBlock().getLocation().add(0, -1, 1).getBlock().getType() == Material.WOOD_STEP &&
								e.getClickedBlock().getLocation().add(1, -1, 0).getBlock().getType() == Material.WOOD_STEP &&
								e.getClickedBlock().getLocation().add(0, -1, -1).getBlock().getType() == Material.WOOD_STEP &&
								e.getClickedBlock().getLocation().add(-1, -1, 0).getBlock().getType() == Material.WOOD_STEP &&
		
								e.getClickedBlock().getLocation().add(0, -1, 2).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(2, -1, 0).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(-2, -1, 0).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(0, -1, -2).getBlock().getType() == Material.NETHER_BRICK &&
		
								e.getClickedBlock().getLocation().add(1, -1, 2).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(1, -1, -2).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(-1, -1, 2).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(-1, -1, -2).getBlock().getType() == Material.NETHER_BRICK &&
		
								e.getClickedBlock().getLocation().add(2, -1, 1).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(2, -1, -1).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(-2, -1, 1).getBlock().getType() == Material.NETHER_BRICK &&
								e.getClickedBlock().getLocation().add(-2, -1, -1).getBlock().getType() == Material.NETHER_BRICK &&
		
								e.getClickedBlock().getLocation().add(2, -1, 2).getBlock().getType() == Material.COBBLESTONE &&
								e.getClickedBlock().getLocation().add(2, -1, -2).getBlock().getType() == Material.COBBLESTONE &&
								e.getClickedBlock().getLocation().add(-2, -1, 2).getBlock().getType() == Material.COBBLESTONE &&
								e.getClickedBlock().getLocation().add(-2, -1, -2).getBlock().getType() == Material.COBBLESTONE) {
							e.setCancelled(true);
							AlchemistInventory.open(e.getPlayer(), e.getClickedBlock().getLocation());
						}
					}
				}
			}
		}
	};
	
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Alchemist";
	}

	@Override
	public String getPrefix() {
		return "Alc";
	}

	@Override
	public Material getIcon() {
		return Material.GOLD_INGOT;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "Alchemists are the only ones who are able to use");
		desc.add(ChatColor.GREEN + "the Altar of Alchemists.");
		desc.add(ChatColor.GREEN + "In this Altar, they can combine different ingredients,");
		desc.add(ChatColor.GREEN + "and summon powerful Items but can also heavily damage");
		desc.add(ChatColor.GREEN + "your Nation if you are not vigilant.");
		return desc;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		return p;
	}
	
	public static HashMap<Integer, ItemStack> createMap(Inventory inv) {
		HashMap<Integer, ItemStack> returnmap = new HashMap<Integer, ItemStack>();
		if (inv.getItem(10).getType() != Material.STAINED_GLASS_PANE || inv.getItem(10).getType() != Material.AIR) {
			returnmap.put(0, inv.getItem(10));
		}
		if (inv.getItem(11).getType() != Material.STAINED_GLASS_PANE || inv.getItem(11).getType() != Material.AIR) {
			returnmap.put(1, inv.getItem(11));
		}
		if (inv.getItem(15).getType() != Material.STAINED_GLASS_PANE || inv.getItem(15).getType() != Material.AIR) {
			returnmap.put(2, inv.getItem(15));
		}
		if (inv.getItem(16).getType() != Material.STAINED_GLASS_PANE || inv.getItem(16).getType() != Material.AIR) {
			returnmap.put(3, inv.getItem(16));
		}
		if (inv.getItem(19).getType() != Material.STAINED_GLASS_PANE || inv.getItem(19).getType() != Material.AIR) {
			returnmap.put(4, inv.getItem(19));
		}
		if (inv.getItem(20).getType() != Material.STAINED_GLASS_PANE || inv.getItem(20).getType() != Material.AIR) {
			returnmap.put(5, inv.getItem(20));
		}
		if (inv.getItem(24).getType() != Material.STAINED_GLASS_PANE || inv.getItem(24).getType() != Material.AIR) {
			returnmap.put(6, inv.getItem(24));
		}
		if (inv.getItem(25).getType() != Material.STAINED_GLASS_PANE || inv.getItem(25).getType() != Material.AIR) {
			returnmap.put(7, inv.getItem(25));
		}
		if (inv.getItem(28).getType() != Material.STAINED_GLASS_PANE || inv.getItem(28).getType() != Material.AIR) {
			returnmap.put(8, inv.getItem(28));
		}
		if (inv.getItem(29).getType() != Material.STAINED_GLASS_PANE || inv.getItem(29).getType() != Material.AIR) {
			returnmap.put(9, inv.getItem(29));
		}
		if (inv.getItem(33).getType() != Material.STAINED_GLASS_PANE || inv.getItem(33).getType() != Material.AIR) {
			returnmap.put(10, inv.getItem(33));
		}
		if (inv.getItem(34).getType() != Material.STAINED_GLASS_PANE || inv.getItem(34).getType() != Material.AIR) {
			returnmap.put(11, inv.getItem(34));
		}
		if (inv.getItem(37).getType() != Material.STAINED_GLASS_PANE || inv.getItem(37).getType() != Material.AIR) {
			returnmap.put(12, inv.getItem(37));
		}
		if (inv.getItem(38).getType() != Material.STAINED_GLASS_PANE || inv.getItem(38).getType() != Material.AIR) {
			returnmap.put(13, inv.getItem(38));
		}
		if (inv.getItem(42).getType() != Material.STAINED_GLASS_PANE || inv.getItem(42).getType() != Material.AIR) {
			returnmap.put(14, inv.getItem(42));
		}
		if (inv.getItem(43).getType() != Material.STAINED_GLASS_PANE || inv.getItem(43).getType() != Material.AIR) {
			returnmap.put(15, inv.getItem(43));
		}
		return returnmap;
	}

	@Override
	public Perk[] getPerks() {
		return new Perk[] {
				new Perk("Faster Alchimestry", "Gives Results faster", "fastAlchimestry", Job.asHashMap(new String[] { //Seconds
						"0:9",
						"4:8",
						"7:7",
						"12:6",
						"17:5",
						"23:4",
						"28:3"
				}))
		};
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MzM1NzIwOTMsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzIyMzhjMzI0MmZiNzAyYjJlODIzNDhjNmViYTdjZjBkYWE1ZTRhZTQyMTFlZDBiODFiZTRmOTRjYmUyZCJ9fX0=";
	}
}
