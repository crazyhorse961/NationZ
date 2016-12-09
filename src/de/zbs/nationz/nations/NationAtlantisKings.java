package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationAtlantisKings extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}
		
	@Override
	public String getName() {
		return "Kings Of Atlantis";
	}
	
	@Override
	public String getPrefix() {
		return "KoA";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "The one and only Kings of Atlantis. Living");
		d.add(ChatColor.GRAY + "underwater.");
		d.add("  ");
		d.add(Nation.good + "Infinite Water Breathing");
		d.add(Nation.good + "Swim faster");
		d.add(Nation.good + "Build faster underwater");
		d.add(Nation.bad + "Limited Time on Land");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Ocean");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Underwater");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "North");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.PRISMARINE_SHARD;
	}

	@Override
	public Biome getHome() {
		return Biome.OCEAN;
	}
	
	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.NIGHT_VISION, Integer.MAX_VALUE, 0));
		p.add(new PotionEffect(PotionEffectType.WATER_BREATHING, Integer.MAX_VALUE, 0));
		return p;
	}

	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.DARK_BLUE;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTMwODUzMDQsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzY1MmFhMTJmMzNkMDUyY2U1ODU5MmIxZDJhOWE1MzVlNDAyN2E0YmFmZGExMmI1YzZjMWI3ZWVjNWMyMmM5YyJ9fX0=";
	}
}
