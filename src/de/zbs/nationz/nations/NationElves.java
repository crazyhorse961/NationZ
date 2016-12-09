package de.zbs.nationz.nations;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

class NationElves extends Nation {
	
	private Listener ls = new Listener() {
		
	};

	@Override
	public Listener getListener() {
		return ls;
	}
	
	@Override
	public String getName() {
		return "Elves";
	}
	
	@Override
	public String getPrefix() {
		return "Elv";
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> d = new ArrayList<String>();
		d.add(ChatColor.GRAY + "Smart and fast elves jumping from tree to tree.");
		d.add("  ");
		d.add(Nation.good + "Slightly fater");
		d.add(Nation.good + "Constant Jump Boost");
		d.add(Nation.good + "Deal a lot more Damage with their bow");
		d.add(Nation.bad + "Get slightly more Damage");
		d.add(" ");
		d.add(ChatColor.GRAY + "Biome: " + ChatColor.YELLOW + "Forest");
		d.add(ChatColor.GRAY + "Style: " + ChatColor.YELLOW + "Treehouses");
		d.add(ChatColor.GRAY + "Direction: " + ChatColor.YELLOW + "South");
		return d;
	}

	@Override
	public Material getIcon() {
		return Material.FEATHER;
	}

	@Override
	public Biome getHome() {
		return Biome.FOREST;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		p.add(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 0));
		p.add(new PotionEffect(PotionEffectType.JUMP, Integer.MAX_VALUE, 1));
		p.add(new PotionEffect(PotionEffectType.WEAKNESS, Integer.MAX_VALUE, 0));
		return p;
	}

	@Override
	public ChatColor getNationChatColor() {
		return ChatColor.GREEN;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MTI4OTQ1MjEsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2FjZWE0M2RiZjRmZTI5OWU3ZTc2MDU5NDQxMTE5YzI4ZmY3NjE3NDI3MGM1ZmI2YTgzM2E1YWE4OWUzIiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=";
	}
}
