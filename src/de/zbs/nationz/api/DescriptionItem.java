package de.zbs.nationz.api;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

public class DescriptionItem {
	
	private String title;
	private ItemStack item;
	private List<String> lore;
	
	public DescriptionItem(String title, OfflinePlayer head) {
		ItemStack playerinfo = new ItemStack(Material.SKULL_ITEM, 1, (byte) 3);
        SkullMeta meta = (SkullMeta) playerinfo.getItemMeta();
        meta.setOwner(head.getName());
        playerinfo.setItemMeta(meta);
		item = playerinfo;
		this.title = title;
	}
	
	public DescriptionItem(String title, Player head) {
		new DescriptionItem(title, (OfflinePlayer) head);
	}
	
	public DescriptionItem(String title, ItemStack base) {
		item = base;
		this.title = title;
	}
	
	public void appendString(String string) {
		lore.add(ChatColor.GRAY + string);
	}
	
	public void appendListItem(ListItemType lit, String string) {
		lore.add(lit.getString() + string);
	}
	
	public void appendSpacer() {
		lore.add(" ");
	}
	
	public void appendInfo(String opener, String info) {
		lore.add(ChatColor.GRAY + opener + ": " + ChatColor.YELLOW + info);
	}
	
	public ItemStack build() {
		ItemStack is = this.item;
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.DARK_AQUA + title);
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}
	
	public static enum ListItemType {
		GOOD(ChatColor.GREEN + "+ "),
		UNSET(ChatColor.YELLOW + "> "),
		SPACER(ChatColor.YELLOW + "   "),
		BAD(ChatColor.DARK_RED + "- ");
		
		private String string;
		
		ListItemType(String string) {
			this.string = string;
		}
		
		public String getString() {
			return this.string;
		}
	}
}