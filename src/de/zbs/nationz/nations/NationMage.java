package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationMage extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}
	
	@Override
	public String getName() {
		return "Mage";
	}
	
	@Override
	public String getPrefix() {
		return "Mag";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "Wise and Intelligent mages. Summoning Minions");
		d.add(ChatColor.GRAY + "to help them in combat.");
		d.add("  ");
		d.add(Nation.good + "Massive Mining Spells");
		d.add(Nation.good + "Massive Combat Spells");
		d.add(Nation.bad + "Take slightly more Damage");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Jungle");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Towers scraping the Sky");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "North-West");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.BLAZE_ROD;
	}

	@Override
	public Biome getHome() {
		return Biome.JUNGLE;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 1));
		return p;
	}

	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.AQUA;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTMwMzUxOTUsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzJkOWU3ZGVkNzM5MzBjMzk3YTY1MTcyMmIyYTliOTNjNmQ4ZDFhZjI5YTBhOTdkNDk0NmVlMTNlNWQyZGRmIn19fQ==";
	}
}