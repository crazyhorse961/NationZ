package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_LifeSteal extends EnchantmentZ {

	public Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (e.getDamage() > 0) {
				if (e.getEntity() instanceof Player) {
					if (e.getDamager() instanceof Player) {
						Player p = (Player) e.getDamager();
						Item es  = Item.get(p.getEquipment().getItemInMainHand());
						if (es.hasEnchantment(LIFESTEAL)) {
							p.setHealth(p.getHealth() + es.getEnchantmentLevel(LIFESTEAL));
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
		return "Lifesteal";
	}

	@Override
	public Material getIcon() {
		return Material.REDSTONE;
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
				new ItemStack(Material.SKULL, 4),
				new ItemStack(Material.REDSTONE, 64)
		};
	}

}
