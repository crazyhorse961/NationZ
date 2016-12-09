package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationHumans extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}
	
	@Override
	public String getName() {
		return "Humans";
	}
	
	@Override
	public String getPrefix() {
		return "Hum";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "Clever Humans, great at pvp but bad at mining");
		d.add("  ");
		d.add(Nation.good + "Constant Strength");
		d.add(Nation.good + "Very good at taming horses");
		d.add(Nation.bad + "Constant Mining Fatigue");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Plains");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Houses");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "East");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.APPLE;
	}

	@Override
	public Biome getHome() {
		return Biome.PLAINS;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, Integer.MAX_VALUE, 1));
		p.add(new PotionEffect(PotionEffectType.SLOW_DIGGING, Integer.MAX_VALUE, 0));
		return p;
	}

	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.RED;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTMyNzE0MjUsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY4N2Q3NGNkYjQyNmE1NWI2NTA3OWU4MTJhYzRlZDFjNmE1ZDExY2Q1NDE2OTQyNGI0ZTc5ZmNmNTIxMTgifX19";
	}
}
