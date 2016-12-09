package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;
import de.zbs.nationz.main.NationZ;

public class EnchantmentZ_DeepWounds extends EnchantmentZ {

	public Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (e.getDamage() > 0) {
				if (e.getEntity() instanceof Player) {
					if (e.getDamager() instanceof Player) {
						Player killer = (Player) e.getDamager();
						Item es  = Item.get(killer.getEquipment().getItemInMainHand());
						if (es.hasEnchantment(DEEP_WOUNDS)) {
							int id = Bukkit.getScheduler().scheduleSyncRepeatingTask(NationZ.plugin, new Runnable() {
								@Override
								public void run() {
									
								}
							}, 0, 20);
							Bukkit.getScheduler().runTaskLater(NationZ.plugin, new Runnable() {
								@Override
								public void run() {
									Bukkit.getScheduler().cancelTask(id);
								}
							}, es.getEnchantmentLevel(DEEP_WOUNDS) * 20);
						}
					}
				}
			}
		}
	};
	
	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		return false;
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().SWORD;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public String getName() {
		return "Deep Wounds";
	}

	@Override
	public Material getIcon() {
		return Material.REDSTONE_BLOCK;
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
				new ItemStack(Material.REDSTONE, 64),
				new ItemStack(Material.BONE, 64),
		};
	}

}
