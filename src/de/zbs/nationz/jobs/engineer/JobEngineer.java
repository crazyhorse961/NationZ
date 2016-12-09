package de.zbs.nationz.jobs.engineer;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.jobs.Job;

public class JobEngineer extends Job {
	
	private Listener ls = new Listener() {
		
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Engineer";
	}

	@Override
	public String getPrefix() {
		return "Eng";
	}

	@Override
	public Material getIcon() {
		return Material.IRON_INGOT;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "Engineers build functional infrastructure like");
		desc.add(ChatColor.GREEN + "moving gates, sentries or similar.");
		return desc;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		return p;
	}
	
	@Override
	public Perk[] getPerks() {
		return null;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTc1OTIxMDIzOTAsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJaZXJvX0RheV9FeHBsb2l0IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzZhMjdkNjY5ZTZkMTM2NjI2NzljY2I5YWRjN2E4MWZmYTQ0Y2JjMTI0ZjcxM2VhZmI0ZGQwY2ZmMDlmNDA4ZSIsIm1ldGFkYXRhIjp7Im1vZGVsIjoic2xpbSJ9fX19";
	}
}
