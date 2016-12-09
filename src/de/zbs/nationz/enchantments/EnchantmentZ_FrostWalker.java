package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;
import de.zbs.nationz.main.NationZ;

public class EnchantmentZ_FrostWalker extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onMove(PlayerMoveEvent e) {
			if (e.getPlayer().getLocation().add(0, -1, 0).getBlock().getType() == Material.WATER || e.getPlayer().getLocation().add(0, 1, 0).getBlock().getType() == Material.STATIONARY_WATER) {
				Item es  = Item.get(e.getPlayer().getInventory().getBoots());
				Location l = e.getPlayer().getLocation();
				buildIce(l.add(0, -1, 0), es);
				buildIce(l.add(1, 0, 0), es);
				buildIce(l.add(0, 0, 1), es);
				buildIce(l.add(-1, 0, 0), es);
				buildIce(l.add(-1, 0, 0), es);
				buildIce(l.add(0, 0, -1), es);
				buildIce(l.add(0, 0, -1), es);
				buildIce(l.add(1, 0, 0), es);
				buildIce(l.add(1, 0, 0), es);
			}
		}
	};
	
	private void buildIce(Location loc, Item es) {
		if (loc.add(0, -1, 0).getBlock().getType() == Material.WATER || loc.add(0, -1, 0).getBlock().getType() == Material.STATIONARY_WATER) {
			boolean stationary = loc.add(0, -1, 0).getBlock().getType() == Material.STATIONARY_WATER;
			if (loc.add(0, 1, 0).getBlock().getType() == Material.AIR) {
				loc.add(0, -1, 0).getBlock().setType(Material.ICE);
				Bukkit.getScheduler().runTaskLater(NationZ.plugin, new Runnable() {
					@Override
					public void run() {
						if (stationary) {
							loc.getBlock().setType(Material.STATIONARY_WATER);
						} else {
							loc.getBlock().setType(Material.WATER);
						}
					}
				}, 40 + es.getEnchantmentLevel(FROST_WALKER) * 20);
			}
		}
	}

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.DEPTH_STRIDER) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().BOOTS;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Frost Walker";
	}

	@Override
	public Material getIcon() {
		return Material.ICE;
	}

	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return null;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.ICE, 64),
				new ItemStack(Material.WATER_BUCKET, 4)
		};
	}
}