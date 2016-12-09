package de.zbs.nationz.jobs.alchemist;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.slikey.effectlib.effect.LineEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.zbs.advinventory.api.AdvInventory;
import de.zbs.advinventory.api.AdvInventory.ClickRunnable;
import de.zbs.nationz.main.NationZ;
import de.zbs.nationz.util.ItemUtils;

public class AlchemistInventory {

	public static void open(Player p, Location enchanter) {
		AdvInventory adv = new AdvInventory("Alchemist...", 54, ItemUtils.placeholder());
		
		adv.removeItem(10);
		adv.removeItem(11);
		adv.removeItem(15);
		adv.removeItem(16);
		adv.removeItem(19);
		adv.removeItem(20);
		adv.removeItem(24);
		adv.removeItem(25);
		adv.removeItem(28);
		adv.removeItem(29);
		adv.removeItem(33);
		adv.removeItem(34);
		adv.removeItem(37);
		adv.removeItem(38);
		adv.removeItem(42);
		adv.removeItem(43);
		adv.setItem(new ItemStack(Material.MAGMA_CREAM), ChatColor.GOLD + "Click to process!", 22, new ClickRunnable() {
			@Override
			public void run(InventoryClickEvent e) {
				ItemStack[] in = new ItemStack[16];
				in[0] = adv.getSourceInventory().getItem(10);
				in[1] = adv.getSourceInventory().getItem(11);
				in[2] = adv.getSourceInventory().getItem(15);
				in[3] = adv.getSourceInventory().getItem(16);
				
				in[4] = adv.getSourceInventory().getItem(19);
				in[5] = adv.getSourceInventory().getItem(20);
				in[6] = adv.getSourceInventory().getItem(24);
				in[7] = adv.getSourceInventory().getItem(25);
				
				in[8] = adv.getSourceInventory().getItem(28);
				in[8] = adv.getSourceInventory().getItem(29);
				in[10] = adv.getSourceInventory().getItem(33);
				in[11] = adv.getSourceInventory().getItem(34);
				
				in[12] = adv.getSourceInventory().getItem(37);
				in[13] = adv.getSourceInventory().getItem(38);
				in[14] = adv.getSourceInventory().getItem(42);
				in[15] = adv.getSourceInventory().getItem(43);
				
				
				startLine(enchanter.add(0, 1, 0), 2, 2);
				startLine(enchanter, -4, 0);
				startLine(enchanter, 0, -4);
				startLine(enchanter, 4, 0);
				
				Bukkit.getScheduler().runTaskLater(NationZ.plugin, new Runnable() {
					@Override
					public void run() {
						AlchemistRecipe ar = AlchemistRecipe.get(in);
						if (ar == null) {
							AlchemistRecipe.punish(p);
						} else {
							enchanter.getWorld().dropItem(enchanter, ar.getResult());
						}
					}
				}, 80);
				
				e.getWhoClicked().closeInventory();
			}
		});
		
		adv.openInventory(p);
	}

	private static void startLine(Location enchanter, int offsetY, int offsetZ) {
		LineEffect le = new LineEffect(NationZ.effectManager);
		DynamicLocation ench = new DynamicLocation(enchanter);
		le.setDynamicOrigin(new DynamicLocation(enchanter.add(offsetY, 0.5, offsetZ)));
		le.setDynamicTarget(ench);
		le.start();
		le.iterations = 80;
	}
}