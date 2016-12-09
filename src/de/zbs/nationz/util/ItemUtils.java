package de.zbs.nationz.util;

import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ItemUtils {
	
	public static ItemStack placeholder() {
		return placeholder(DyeColor.GRAY, ChatColor.GRAY + "");
	}
	
	public static ItemStack placeholder(DyeColor d, String n) {
		@SuppressWarnings("deprecation")
		ItemStack placeholder = new ItemStack(Material.STAINED_GLASS_PANE, 1, d.getData());
		ItemMeta placeholdermeta = placeholder.getItemMeta();
		placeholdermeta.setDisplayName(n);
		placeholder.setItemMeta(placeholdermeta);
		return placeholder;
	}
	
	public static ItemStack backTo(String name) {
		ItemStack backto = new ItemStack(Material.ARROW);
		ItemMeta backtometa = backto.getItemMeta();
		backtometa.setDisplayName(ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "<--" + ChatColor.RESET + ChatColor.GRAY + " Back to " + name);
		backto.setItemMeta(backtometa);
		return backto;
	}
}
