package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EnchantmentZ_Knockback extends EnchantmentZ {

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
		return 2;
	}

	@Override
	public String getName() {
		return "Knockback";
	}

	@Override
	public Material getIcon() {
		return Material.RABBIT_FOOT;
	}

	@Override
	public Listener getListener() {
		return null;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return Enchantment.KNOCKBACK;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.FEATHER, 64),
				new ItemStack(Material.ANVIL, 8)
		};
	}
}