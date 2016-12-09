package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationPharaos extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}
	
	@Override
	public String getName() {
		return "Pharaos";
	}
	
	@Override
	public String getPrefix() {
		return "Pha";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "Dead, undead and living Pharaos united");
		d.add("  ");
		d.add(Nation.good + "Faster digging");
		d.add(Nation.bad + "Instant Hunger");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Desert");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Pyramids");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "West");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.BLAZE_POWDER;
	}

	@Override
	public Biome getHome() {
		return Biome.DESERT;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.HUNGER, Integer.MAX_VALUE, 0));
		p.add(new PotionEffect(PotionEffectType.FAST_DIGGING, Integer.MAX_VALUE, 0));
		return p;
	}
	
	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.GOLD;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTI5Nzk5NjcsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2I5ODA3M2Q1MjFhYWYwZTU0MGFjNWRjNDFhNDdhYjY1MWU4YTZiY2JlMjdmZjRhNWNkNmEwNDhiOTMzM2I0NyIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19";
	}
}
