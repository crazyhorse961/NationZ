package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationDwarfs extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}
	
	@Override
	public String getName() {
		return "Dwarfs";
	}
	
	@Override
	public String getPrefix() {
		return "Dwa";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "Dwarfs, addicted to Gold and Diamonds");
		d.add(ChatColor.GRAY + "living in the Underground-World.");
		d.add("  ");
		d.add(Nation.good + "Very fast Mining");
		d.add(Nation.good + "Deal 60% more damage with Axes");
		d.add(Nation.bad + "Constant Slowness");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Extreme Hills");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Caves and underground-tunnels");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "North-East");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.GOLD_NUGGET;
	}

	@Override
	public Biome getHome() {
		return Biome.EXTREME_HILLS;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 2));
		p.add(new PotionEffect(PotionEffectType.SLOW, Integer.MAX_VALUE, 0));
		return p;
	}

	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.DARK_PURPLE;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTMxNTIxNzIsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2VlNDFhNWNkZTgyODNhZjc1ODkzNTdmMmNkMTYwYzNiMmM5M2JhMGU3ZjE4MDNlZWIzYjI0ZDMzMmVhZmJlIn19fQ==";
	}
}
