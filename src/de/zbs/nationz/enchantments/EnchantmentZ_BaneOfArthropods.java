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

public class EnchantmentZ_BaneOfArthropods extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (e.getDamager() instanceof Player) {
				if (e.getEntityType() == EntityType.SILVERFISH ||
						e.getEntityType() == EntityType.CAVE_SPIDER ||
						e.getEntityType() == EntityType.SPIDER) {
					Item i = Item.get(((Player) e.getDamager()).getEquipment().getItemInMainHand());
					if (i.hasEnchantment(EnchantmentZ.BANE_OF_ARTHROPODS)) {
						e.setDamage(e.getDamage() + (i.getEnchantmentLevel(EnchantmentZ.BANE_OF_ARTHROPODS) * 0.5 + 1));
					}
				}
			}
		}
	};

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.SHARPNESS ||
				arg0 == EnchantmentZ.SMITE) {
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
		return "Bane Of Arthropods";
	}

	@Override
	public Material getIcon() {
		return Material.STRING;
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
				new ItemStack(Material.SPIDER_EYE, 16),
				new ItemStack(Material.STRING, 16),
				new ItemStack(Material.FERMENTED_SPIDER_EYE, 16),
				new ItemStack(Material.STONE, 16)
		};
	}
}