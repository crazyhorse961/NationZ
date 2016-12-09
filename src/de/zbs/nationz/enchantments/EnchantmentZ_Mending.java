package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EnchantmentZ_Mending extends EnchantmentZ {

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		return false;
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().ALL;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Mending";
	}

	@Override
	public Material getIcon() {
		return Material.EXP_BOTTLE;
	}

	@Override
	public Listener getListener() {
		return null;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return Enchantment.MENDING;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.DIAMOND, 8),
				new ItemStack(Material.BOOK, 32)
		};
	}
}