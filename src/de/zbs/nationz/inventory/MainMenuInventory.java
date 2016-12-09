package de.zbs.nationz.inventory;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import de.zbs.nationz.api.FC;
import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.util.ItemUtils;

public class MainMenuInventory implements Listener {
	
	public static ItemStack item() {
		ItemStack item = new ItemStack(Material.NETHER_STAR);
		ItemMeta meta = item.getItemMeta();
		meta.setDisplayName(ChatColor.GREEN + "Main Menu" + ChatColor.DARK_GRAY + " || " + ChatColor.GRAY + "Right-Click");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(ChatColor.GRAY + "Right-Click this to open the Main Inventory");
		meta.setLore(lore);
		item.setItemMeta(meta);
		return item;
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
		if (e.getItem() == null)
			return;
		if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && (e.getItem().equals(item()))) {
			e.setCancelled(true);
			e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.BLOCK_CHEST_OPEN, 1, 1);
			open(e.getPlayer());
		}
	}
	
	@EventHandler
	public void onDrop(final PlayerDropItemEvent e) {
		if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName() == null) {
			e.getItemDrop().setCustomName(ChatColor.GRAY + "Only " + ChatColor.RED + e.getPlayer().getDisplayName() + ChatColor.GRAY + " can pick this Item up");
			e.getItemDrop().setCustomNameVisible(true);
		} else if (e.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(item().getItemMeta().getDisplayName())) {
			e.setCancelled(true);
		} else {
			e.getItemDrop().setCustomName(ChatColor.GRAY + "Only " + ChatColor.RED + e.getPlayer().getDisplayName() + ChatColor.GRAY + " can pick this Item up");
			e.getItemDrop().setCustomNameVisible(true);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent e) {
		try {
			if (e.getCurrentItem() == null || e.getCurrentItem().getType().equals(Material.AIR) || e.getCurrentItem().getItemMeta() == null || e.getCurrentItem().getItemMeta().getDisplayName().isEmpty()) {
				return;
			}
		} catch (NullPointerException ex){return;}
		Player p = (Player) e.getWhoClicked();
		if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(item().getItemMeta().getDisplayName())) {
			e.setCancelled(true);
			p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
		} else {
			if (e.getInventory().getName().equals("Main Menu")) {
				e.setCancelled(true);
				if (e.getSlot() == 16) {
					if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types").equalsIgnoreCase("all")) {
						FC.getCfg(p.getUniqueId().toString()).set("deathmessages.types", "pvp");
					} else if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types").equalsIgnoreCase("pvp")) {
						FC.getCfg(p.getUniqueId().toString()).set("deathmessages.types", "none");
					} else if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types").equalsIgnoreCase("none")) {
						FC.getCfg(p.getUniqueId().toString()).set("deathmessages.types", "all");
					}
					p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
					open(p);
				} else if (e.getSlot() == 25) {
					if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.notification").equalsIgnoreCase("chat")) {
						FC.getCfg(p.getUniqueId().toString()).set("deathmessages.notification", "actionbar");
					} else if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.notification").equalsIgnoreCase("actionbar")) {
						FC.getCfg(p.getUniqueId().toString()).set("deathmessages.notification", "chat");
					}
					p.playSound(p.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
					open(p);
				} else {
					p.playSound(p.getLocation(), Sound.ENTITY_ITEM_BREAK, 1, 1);
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public static void open(Player p) {
		Inventory inv = Bukkit.createInventory(p, 54, "Main Menu");
		
		for (int i = 0; i < 54; i++) {
			inv.setItem(i, ItemUtils.placeholder(DyeColor.GRAY, ChatColor.GRAY + ""));
		}
		
		ItemStack deathmessagetypes = new ItemStack(Material.STICK);
		ItemMeta deathmessagetypesmeta = deathmessagetypes.getItemMeta();
		ArrayList<String> deathmessagetypeslore = new ArrayList<String>();
		deathmessagetypesmeta.setDisplayName(ChatColor.BLUE + "Death Messages: Types");
		if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types").equalsIgnoreCase("all")) {
			deathmessagetypes = new ItemStack(Material.GOLDEN_APPLE);
			deathmessagetypeslore.add(ChatColor.GRAY + "PvP: ON");
			deathmessagetypeslore.add(ChatColor.GRAY + "Other: ON");
		} else if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types").equalsIgnoreCase("pvp")) {
			deathmessagetypes = new ItemStack(Material.IRON_SWORD);
			deathmessagetypeslore.add(ChatColor.GRAY + "PvP: ON");
			deathmessagetypeslore.add(ChatColor.GRAY + "Other: OFF");
		} else if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types").equalsIgnoreCase("none")) {
			deathmessagetypes = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
			deathmessagetypeslore.add(ChatColor.GRAY + "PvP: OFF");
			deathmessagetypeslore.add(ChatColor.GRAY + "Other: OFF");
		} else {
			deathmessagetypes = new ItemStack(Material.BARRIER);
			deathmessagetypeslore.add(ChatColor.GRAY + "ERROR! Invalid Argument: " + FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types"));
		}
		deathmessagetypeslore.add(" ");
		deathmessagetypeslore.add(ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + "Click to change");
		deathmessagetypesmeta.setLore(deathmessagetypeslore);
		deathmessagetypes.setItemMeta(deathmessagetypesmeta);
		
		ItemStack deathmessagenotification;
		if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.types").equalsIgnoreCase("none")) {
			deathmessagenotification = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
			ItemMeta deathmessagenotificationmeta = deathmessagenotification.getItemMeta();
			ArrayList<String> deathmessagenotificationlore = new ArrayList<String>();
			deathmessagenotificationmeta.setDisplayName(ChatColor.BLUE + "Death Messages: Notification");
			deathmessagenotificationlore.add(ChatColor.GRAY + "Death Messages are disabled, this setting is useless.");
			deathmessagenotificationmeta.setLore(deathmessagenotificationlore);
			deathmessagenotification.setItemMeta(deathmessagenotificationmeta);
		} else {
			deathmessagenotification = new ItemStack(Material.PAPER);
			ItemMeta deathmessagenotificationmeta = deathmessagenotification.getItemMeta();
			ArrayList<String> deathmessagenotificationlore = new ArrayList<String>();
			deathmessagenotificationmeta.setDisplayName(ChatColor.GREEN + "Death Messages: Notification");
			if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.notification").equalsIgnoreCase("chat")) {
				deathmessagenotificationlore.add(ChatColor.GRAY + "Chat: ON");
				deathmessagenotificationlore.add(ChatColor.GRAY + "Actionbar: OFF");
			} else if (FC.getCfg(p.getUniqueId().toString()).getString("deathmessages.notification").equalsIgnoreCase("actionbar")) {
				deathmessagenotificationlore.add(ChatColor.GRAY + "Chat: OFF");
				deathmessagenotificationlore.add(ChatColor.GRAY + "Actionbar: ON");
			}
			deathmessagenotificationlore.add(" ");
			deathmessagenotificationlore.add(ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + "Click to change");
			deathmessagenotificationmeta.setLore(deathmessagenotificationlore);
			deathmessagenotification.setItemMeta(deathmessagenotificationmeta);
		}
		
		NZPlayer nz = NZPlayer.getNZPlayer(p);
		
		ItemStack job = nz.getJob().getItem();
		ItemMeta jobmeta = job.getItemMeta();
		ArrayList<String> joblore = new ArrayList<String>();
		jobmeta.setDisplayName(ChatColor.BLUE + "Job");
		joblore.add(ChatColor.GRAY + "Your Job: " + nz.getJob().getName());
		joblore.add(ChatColor.GRAY + "Your Level: " + nz.getJobLevel());
		joblore.add(" ");
		joblore.add(ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + "Click to change");
		jobmeta.setLore(joblore);
		job.setItemMeta(jobmeta);
				
		ItemStack nation = nz.getNation().getItem();
		ItemMeta nationmeta = nation.getItemMeta();
		ArrayList<String> nationlore = new ArrayList<String>();
		nationlore.add(ChatColor.GRAY + "Your Nation: " + ChatColor.YELLOW + nationmeta.getDisplayName());
		nationlore.add(" ");
		nationlore.add(ChatColor.DARK_GRAY + "> " + ChatColor.RED + "You can't change this");
		nationmeta.setDisplayName(ChatColor.BLUE + "Nation");
		nationmeta.setLore(nationlore);
		nation.setItemMeta(nationmeta);
		
		if (nz.isMayor()) {
			ItemStack mayor = new ItemStack(Material.GOLD_INGOT);
			ItemMeta mayormeta = mayor.getItemMeta();
			ArrayList<String> mayorlore = new ArrayList<String>();
			mayormeta.setDisplayName(ChatColor.BLUE + "Mayor-Inventory");
			mayorlore.add(ChatColor.GRAY + "Manage your Nation and lead them to victory!");
			mayorlore.add(" ");
			deathmessagetypeslore.add(ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + "Click to open the Mayor-Inventory");
			mayormeta.setLore(mayorlore);
			mayor.setItemMeta(mayormeta);
			
			inv.setItem(43, mayor);
		} else {
			ItemStack mayor = new ItemStack(Material.GOLD_INGOT);
			ItemMeta mayormeta = mayor.getItemMeta();
			ArrayList<String> mayorlore = new ArrayList<String>();
			mayormeta.setDisplayName(ChatColor.BLUE + "Mayor-Inventory");
			mayorlore.add(ChatColor.GRAY + "Manage your Nation and lead them to victory!");
			mayorlore.add(" ");
			deathmessagetypeslore.add(ChatColor.DARK_GRAY + "> " + ChatColor.GRAY + "Click to open the Mayor-Inventory");
			mayormeta.setLore(mayorlore);
			mayor.setItemMeta(mayormeta);
		}
		
		inv.setItem(10, job);
		inv.setItem(13, nation);
		inv.setItem(16, deathmessagetypes);
		inv.setItem(25, deathmessagenotification);

		p.openInventory(inv);
	}

}
