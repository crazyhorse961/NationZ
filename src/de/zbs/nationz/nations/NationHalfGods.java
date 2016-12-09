package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationHalfGods extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}
	
	@Override
	public String getName() {
		return "Halfgods";
	}
	
	@Override
	public String getPrefix() {
		return "HaG";
	}
	
	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "Very smart sons and daughters of strong gods");
		d.add("  ");
		d.add(Nation.good + "Summon Lightnings and other spells");
		d.add(Nation.good + "Absorption");
		d.add(Nation.bad + "Take slightly more Damage");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Sky Islands");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Hangin' Houses");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "South-West");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.REDSTONE;
	}

	@Override
	public Biome getHome() {
		return Biome.PLAINS;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.ABSORPTION, Integer.MAX_VALUE, 0));
		return p;
	}

	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.BLUE;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTMyMjU3MzQsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzkzZjJmN2Q5MzllMjJiYjI4NjBjMjg3MjZkMjU2Y2M2MzU1MzRiNjg5OWIyNjQ3MTc5MjM5YzNmYjRkNTllIn19fQ==";
	}
}
