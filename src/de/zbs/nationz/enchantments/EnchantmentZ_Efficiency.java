package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EnchantmentZ_Efficiency extends EnchantmentZ {

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		return false;
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().TOOLS;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Efficiency";
	}

	@Override
	public Material getIcon() {
		return Material.BEACON;
	}

	@Override
	public Listener getListener() {
		return null;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return Enchantment.DIG_SPEED;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.DIAMOND, 16),
				new ItemStack(Material.IRON_INGOT, 32),
				new ItemStack(Material.INK_SACK, 32, (short) 4),
				new ItemStack(Material.GOLD_INGOT, 32)
		};
	}
}