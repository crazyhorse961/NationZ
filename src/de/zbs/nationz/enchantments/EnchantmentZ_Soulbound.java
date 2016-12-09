package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

public class EnchantmentZ_Soulbound extends EnchantmentZ {

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
		return 4;
	}

	@Override
	public String getName() {
		return "Soulbound";
	}

	@Override
	public Material getIcon() {
		return Material.BLAZE_POWDER;
	}

	@Override
	public Listener getListener() {
		return null; //See PlayerDeathListener:64-77
	}

	@Override
	public Enchantment vanillaEnchant() {
		return null;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.NETHER_STAR, 16)
		};
	}

}
