package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationCyclops extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Cyclops";
	}
	
	@Override
	public String getPrefix() {
		return "Cyc";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "Limited in eyesight but very strong with a lot health.");
		d.add("  ");
		d.add(Nation.good + "Constant Haste");
		d.add(Nation.good + "Constant Damage Resistance");
		d.add(Nation.bad + "Constant Blindness");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Ice Plains Spikes");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Bunkers");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "South-east");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.EYE_OF_ENDER;
	}

	@Override
	public Biome getHome() {
		return Biome.MESA;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
		p.add(new PotionEffect(PotionEffectType.DAMAGE_RESISTANCE, Integer.MAX_VALUE, 1));
		p.add(new PotionEffect(PotionEffectType.BLINDNESS, Integer.MAX_VALUE, 0));
		return p;
	}

	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.LIGHT_PURPLE;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTMzMzQ2MzAsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2Y2OWYxYWZmYWY5NTMzMWQzMWUzMzY5YTg5Nzg5NjJlZWYzNGVhMGM2MzVmNTE4M2YzZWE1NjdhZmY0ZGFhIn19fQ==";
	}
}
