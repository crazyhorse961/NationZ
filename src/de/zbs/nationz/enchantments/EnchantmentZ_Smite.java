package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_Smite extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (e.getDamager() instanceof Player) {
				if (e.getEntityType() == EntityType.SKELETON ||
						e.getEntityType() == EntityType.ZOMBIE ||
						e.getEntityType() == EntityType.GIANT) {
					Item es  = Item.get(((Player) e.getEntity()).getEquipment().getItemInMainHand());
					if (es.hasEnchantment(SMITE)) {
						e.setDamage(e.getDamage() + (es.getEnchantmentLevel(SMITE) * 0.5 + 1));
					}
				}
			}
		}
	};

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.BANE_OF_ARTHROPODS ||
				arg0 == EnchantmentZ.SHARPNESS) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().SWORD;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Smite";
	}

	@Override
	public Material getIcon() {
		return Material.SKULL;
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
				new ItemStack(Material.ROTTEN_FLESH, 64),
				new ItemStack(Material.BONE, 64)
		};
	}
}