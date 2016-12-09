package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_FeatherFalling extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageEvent e) {
			if (e.getCause() == DamageCause.FALL) {
				if (e.getEntity() instanceof Player) {
					Player p = (Player) e.getEntity();
					Item es  = Item.get(p.getInventory().getBoots());
					if (es.hasEnchantment(FEATHER_FALLING)) {
						e.setDamage(e.getDamage() / 100 * (100 - es.getEnchantmentLevel(FEATHER_FALLING)) * 20);
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
		return new ItemTarget().BOOTS;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Feather Falling";
	}

	@Override
	public Material getIcon() {
		return Material.FEATHER;
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
				new ItemStack(Material.FEATHER, 64),
				new ItemStack(Material.SLIME_BALL, 1)
		};
	}
}
