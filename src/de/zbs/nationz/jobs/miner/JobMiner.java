package de.zbs.nationz.jobs.miner;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import de.zbs.nationz.jobs.Job;

public class JobMiner extends Job {
	
	private Listener ls = new Listener() {
//		@EventHandler
//		public void onInteract(PlayerInteractEvent e) {
//			if (NationAPI.getJob(e.getPlayer().getUniqueId()) == Job.MINER) {
//				if (e.getPlayer().getEquipment().getItemInMainHand().getType().equals(Material.WOOD_PICKAXE) || e.getPlayer().getEquipment().getItemInMainHand().getType().equals(Material.GOLD_PICKAXE) || e.getPlayer().getEquipment().getItemInMainHand().getType().equals(Material.STONE_PICKAXE) || e.getPlayer().getEquipment().getItemInMainHand().getType().equals(Material.IRON_PICKAXE) || e.getPlayer().getEquipment().getItemInMainHand().getType().equals(Material.DIAMOND_PICKAXE)) {
//					if (e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().getDisplayName() == null) {
//						if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//							if (e.getPlayer().getInventory().contains(Material.SULPHUR)) {
//								e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FUSE, 1, 1);
//								ItemMeta meta = e.getItem().getItemMeta();
//								meta.setDisplayName(e.getItem().getType().name().replace("_", " ") + ChatColor.DARK_RED + " FUSED!");
//								e.getItem().setItemMeta(meta);
//							}
//						}
//					} else {
//						if (e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().getDisplayName().contains(ChatColor.DARK_RED + " FUSED!")) {
//							if (e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//								e.getClickedBlock().getLocation().getWorld().createExplosion(e.getClickedBlock().getLocation(), Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("explodeRadius")));
//								e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().setDisplayName(e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().getDisplayName().replace(ChatColor.DARK_RED + " FUSED!", ""));
//							}
//						} else {
//							if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
//								if (e.getPlayer().getInventory().contains(new ItemStack(Material.SULPHUR))) {
//									e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.FUSE, 1, 1);
//									ItemMeta meta = e.getItem().getItemMeta();
//									meta.setDisplayName(e.getItem().getItemMeta().getDisplayName() + ChatColor.DARK_RED + " FUSED!");
//									e.getItem().setItemMeta(meta);
//									e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().first(Material.SULPHUR)).setAmount(e.getPlayer().getInventory().getItem(e.getPlayer().getInventory().first(Material.SULPHUR)).getAmount() - 1);;
//								}
//							}
//						}
//					}
//				}
//			}
//		}
		
//		@EventHandler
//		public void onBlockBreak(BlockBreakEvent e) {
//			if (NationAPI.getJob(e.getPlayer().getUniqueId()) == Job.MINER) {
//				if (e.getBlock().getType() == Material.COAL_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				} else if (e.getBlock().getType() == Material.GOLD_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				} else if (e.getBlock().getType() == Material.IRON_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				} else if (e.getBlock().getType() == Material.EMERALD_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				} else if (e.getBlock().getType() == Material.LAPIS_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				} else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				} else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				} else if (e.getBlock().getType() == Material.QUARTZ_ORE) {
//					e.getBlock().getLocation().getWorld().dropItem(e.getBlock().getLocation(), new ItemStack(Material.COAL, Integer.parseInt(getLevelPerks().get(NationAPI.getJobLevel(e.getPlayer().getUniqueId())).get("oreMultiplier"))));
//				}
//			}
//		}
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Miner";
	}

	@Override
	public String getPrefix() {
		return "Min";
	}

	@Override
	public Material getIcon() {
		return Material.IRON_PICKAXE;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "Miners can dig faster and are more fortunate when");
		desc.add(ChatColor.GREEN + "mining Ores.");
		return desc;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2));
		return p;
	}
	
	@Override
	public Perk[] getPerks() {
		return new Perk[] {
				new Perk("Ore Multiploer", "Multiplies Ores", "oreMultiplier", Job.asHashMap(new String[] {
						"0:1",
						"12:2",
						"18:3",
						"27:4"
				})),
				new Perk("Instant Smelting", "Chance of Instantly-Smelting Ores", "instaSmelt", Job.asHashMap(new String[] {
						"0:0",
						"6:14",
						"7:16",
						"8:18",
						"9:20",
						"10:22",
						"11:24",
						"12:26",
						"13:28",
						"14:30",
						"15:32",
						"16:34",
						"17:36",
						"18:38",
						"19:40"
				}))
		};
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTc1OTE2MTEyNTUsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJaZXJvX0RheV9FeHBsb2l0IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzNmNDU2N2VlZTc1NjNmNGVkZjcwZjQ5ZThmNjVlNmIwOTBjYjE2OWQ4YmNkNzhjM2U5NjdhMjc5YTI0ODFhNyJ9fX0=";
	}
}
