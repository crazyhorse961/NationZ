package de.zbs.nationz.jobs.tamer;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.jobs.Job;

public class JobTamer extends Job {
	
	private Listener ls = new Listener() {
		
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Tamer";
	}

	@Override
	public String getPrefix() {
		return "Tam";
	}

	@Override
	public Material getIcon() {
		return Material.COOKED_BEEF;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "When a Tamer kills a neutral Mob, he automatically");
		desc.add(ChatColor.GREEN + "gets the cooked meat. He is the only one to create");
		desc.add(ChatColor.GREEN + "Animal-Farms without any risks.");
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
				new Perk("Faster Tame", "Chance of instantly taming an animal", "tameChance", Job.asHashMap(new String[] {
						"0:0",
						"1:10",
						"5:20",
						"9:30",
						"12:40",
						"15:50",
						"19:60",
						"22:70",
						"24:80",
						"28:90"
				}))
		};
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTc1OTE5ODA1NjIsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJaZXJvX0RheV9FeHBsb2l0IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzQxY2RiNDEyOTJlOTcwNWI5OWU1OGNiMGY3NTNhZjczMTZkODI2MDIxZThhZGRiZmU5OGZjYTMyOTg2In19fQ==";
	}
}
