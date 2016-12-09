package de.zbs.nationz.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class DurabilityListener implements Listener {
	
	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		if (e.getPlayer().getEquipment().getItemInMainHand() == null || e.getPlayer().getEquipment().getItemInMainHand().getType() == Material.AIR) return;
		ItemStack i = e.getPlayer().getEquipment().getItemInMainHand();
		if (!i.hasItemMeta()) return;
		if (!i.getItemMeta().hasDisplayName()) return;
		Item t = Item.get(i);
		t.editDurability(false, 1);
	}
}