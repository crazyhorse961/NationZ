package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EnchantmentZ_DepthStrider extends EnchantmentZ {

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.FROST_WALKER) {
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
		return 3;
	}

	@Override
	public String getName() {
		return "Depth Strider";
	}

	@Override
	public Material getIcon() {
		return Material.WATER_LILY;
	}

	@Override
	public Listener getListener() {
		return null;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return Enchantment.DEPTH_STRIDER;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.WATER_LILY, 64)
		};
	}
}