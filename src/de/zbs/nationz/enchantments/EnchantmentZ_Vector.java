package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_Vector extends EnchantmentZ {
	
	public Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (e.getDamage() > 0) {
				if (e.getEntity() instanceof Player) {
					if (e.getDamager() instanceof Player) {
						Player killer = (Player) e.getDamager();
						Player p = (Player) e.getEntity();
						Item es  = Item.get(killer.getEquipment().getItemInMainHand());
						if (es.hasEnchantment(VECTOR)) {
							p.setVelocity(new Vector(0, es.getEnchantmentLevel(VECTOR), 0));
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
		return "Vector";
	}

	@Override
	public Material getIcon() {
		return Material.ARROW;
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
				new ItemStack(Material.FISHING_ROD, 4),
				
		};
	}

}
