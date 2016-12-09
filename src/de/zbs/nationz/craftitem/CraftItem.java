package de.zbs.nationz.craftitem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public abstract class CraftItem {
	
	public static final CraftItem SHARD_OF_REPULSION = new CrafItem_ShardOfRepulsion();
	public static final CraftItem GOOD_BREWING_ESSENCE = new CrafItem_GoodBrewingEssence();
	public static final CraftItem BAD_BREWING_ESSENCE = new CrafItem_BadBrewingEssence();
	public static final CraftItem WIND_ESSENCE = new CrafItem_WindEssence();

	public static final CraftItem[] CRAFITEMS = new CraftItem[] {
			SHARD_OF_REPULSION, GOOD_BREWING_ESSENCE, BAD_BREWING_ESSENCE, WIND_ESSENCE
	};
	
	public abstract String getDisplayName();

	public abstract String[] getLore();
	
	public abstract Material getIcon();

	public abstract boolean isGlowing();
	
//	public abstract whatEver getCorrespondingTexture();
	
	public ItemStack getItem() {
		return getItem(1);
	}
	
	public ItemStack getItem(int amount) {
		ItemStack is = new ItemStack(getIcon(), amount);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + getDisplayName());
		List<String> lore = new ArrayList<String>();
		for (int i = 0; i < getLore().length; i++) {
			lore.add(ChatColor.GRAY + getLore()[i]);
		}
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
}