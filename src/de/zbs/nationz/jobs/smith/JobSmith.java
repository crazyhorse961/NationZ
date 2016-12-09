package de.zbs.nationz.jobs.smith;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.jobs.Job;

public class JobSmith extends Job {
	
	private static List<Material> disallowedItems() {
		List<Material> disallowedItems = new ArrayList<Material>();
		disallowedItems.add(Material.LEATHER_HELMET);
		disallowedItems.add(Material.LEATHER_CHESTPLATE);
		disallowedItems.add(Material.LEATHER_LEGGINGS);
		disallowedItems.add(Material.LEATHER_BOOTS);
		disallowedItems.add(Material.GOLD_HELMET);
		disallowedItems.add(Material.GOLD_CHESTPLATE);
		disallowedItems.add(Material.GOLD_LEGGINGS);
		disallowedItems.add(Material.GOLD_BOOTS);
		disallowedItems.add(Material.CHAINMAIL_HELMET);
		disallowedItems.add(Material.CHAINMAIL_CHESTPLATE);
		disallowedItems.add(Material.CHAINMAIL_LEGGINGS);
		disallowedItems.add(Material.CHAINMAIL_BOOTS);
		disallowedItems.add(Material.IRON_HELMET);
		disallowedItems.add(Material.IRON_CHESTPLATE);
		disallowedItems.add(Material.IRON_LEGGINGS);
		disallowedItems.add(Material.IRON_BOOTS);
		disallowedItems.add(Material.DIAMOND_HELMET);
		disallowedItems.add(Material.DIAMOND_CHESTPLATE);
		disallowedItems.add(Material.DIAMOND_LEGGINGS);
		disallowedItems.add(Material.DIAMOND_BOOTS);
		disallowedItems.add(Material.WOOD_SWORD);
		disallowedItems.add(Material.GOLD_SWORD);
		disallowedItems.add(Material.STONE_SWORD);
		disallowedItems.add(Material.IRON_SWORD);
		disallowedItems.add(Material.DIAMOND_SWORD);
		return disallowedItems;
	}
	
	private Listener ls = new Listener() {
		@EventHandler
		public void onCraft(CraftItemEvent e) {
			if (disallowedItems().contains(e.getRecipe().getResult().getType())) {
				e.setCancelled(true);
				e.getWhoClicked().sendMessage(ChatColor.RED + "Only Smiths can craft this Item!");
			}
		}
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Smith";
	}

	@Override
	public String getPrefix() {
		return "Smi";
	}

	@Override
	public Material getIcon() {
		return Material.ANVIL;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "The Smith crafts Armor and Weapons as well as");
		desc.add(ChatColor.GREEN + "sometimes Tools for the whole Nation. If Smiths");
		desc.add(ChatColor.GREEN + "craft something, it has a chance to be Inforced.");
		desc.add(ChatColor.GREEN + "Inforced Items are stronger, have a higher durability,");
		desc.add(ChatColor.GREEN + "and deal more damage.");
		return desc;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		return p;
	}
	
	@Override
	public Perk[] getPerks() {
		return new Perk[] {
				new Perk("Inforce Chance", "Chance of Inforcing", "inforceChance", Job.asHashMap(new String[] {
						"0:0",
						"12:10",
						"25:17",
						"30:25"
				}))
		};
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTc1OTE3ODA4OTIsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJaZXJvX0RheV9FeHBsb2l0IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU2NDA0YzFhNGM4N2M1MTlhYjhmOTE3YzIwMWUyMzRjZmFlYjYzNmUzM2ZhNWIzMGNkOTZlZDNjODM3MTNkIn19fQ==";
	}
}
