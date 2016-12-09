package de.zbs.nationz.jobs.hunter;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.jobs.Job;

public class JobHunter extends Job {
	
	private Listener ls = new Listener() {
		
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Hunter";
	}

	@Override
	public String getPrefix() {
		return "Hun";
	}

	@Override
	public Material getIcon() {
		return Material.BONE;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "Hunters deal a lot more Damage to Monsters");
		desc.add(ChatColor.GREEN + "and are able to get Meat and other usables from");
		desc.add(ChatColor.GREEN + "Animals");
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
				new Perk("More Drops", "Increases Drops", "dropRate", Job.asHashMap(new String[] { //Multiplier
						"0:1",
						"8:1.2",
						"11:1.5",
						"18:2",
						"25:2.3",
						"29:2.8"
				}))
		};
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTc1OTE1NDM3NDIsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJaZXJvX0RheV9FeHBsb2l0IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzhjY2ExOWJjZTUwMjg1YmQ4ZTJlYWVmMmFlMDc0Yzk5ZjE2Y2UxMWNkZWE1NjQyZDU2YTc3ODJhOGQ5NWFiIiwibWV0YWRhdGEiOnsibW9kZWwiOiJzbGltIn19fX0=";
	}
}
