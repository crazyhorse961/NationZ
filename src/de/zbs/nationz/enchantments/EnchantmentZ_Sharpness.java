package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EnchantmentZ_Sharpness extends EnchantmentZ {

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.BANE_OF_ARTHROPODS ||
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
		return "Sharpness";
	}

	@Override
	public Material getIcon() {
		return Material.QUARTZ;
	}

	@Override
	public Listener getListener() {
		return null;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return Enchantment.DAMAGE_ALL;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.QUARTZ, 64),
				new ItemStack(Material.DIAMOND, 16)
		};
	}
}