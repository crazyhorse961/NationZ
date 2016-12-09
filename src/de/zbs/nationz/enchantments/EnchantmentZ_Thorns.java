package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.craftitem.CraftItem;

public class EnchantmentZ_Thorns extends EnchantmentZ {

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		return false;
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().ARMOR;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public String getName() {
		return "Thorns";
	}

	@Override
	public Material getIcon() {
		return Material.CACTUS;
	}

	@Override
	public Listener getListener() {
		return null;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return Enchantment.THORNS;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				CraftItem.SHARD_OF_REPULSION.getItem(),
				new ItemStack(Material.IRON_FENCE, 4),
				new ItemStack(Material.CACTUS, 16)
		};
	}
}