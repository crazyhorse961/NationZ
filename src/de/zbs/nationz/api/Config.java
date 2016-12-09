package de.zbs.nationz.api;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import de.zbs.nationz.jobs.Job;
import de.zbs.nationz.nations.Nation;

public class Config {
	
	public static String path = "plugins/NationZ";
	public static String name = "config.yml";
	
	public static void load() {
		FileConfiguration cfg;
		if (new File(path, name).exists()) {
			cfg = FC.getMainCfg();
		} else {
			try {
				new File(path, name).createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			cfg = YamlConfiguration.loadConfiguration(new File(path, name));
		}
		cfg.addDefault("sql.username", "username");
		cfg.addDefault("sql.password", "password");
		cfg.addDefault("sql.server", "server");
		cfg.addDefault("sql.database", "NationZ");

		cfg.addDefault("link.home", "nationz.com");
		cfg.addDefault("link.forum", "forum.nationz.com");
		cfg.addDefault("link.wiki", "wiki.nationz.com");
		cfg.addDefault("link.shop", "store.nationz.com");
		cfg.addDefault("link.youtube", "youtube.nationz.com");
		cfg.addDefault("link.pass", "pass.nationz.com");

		cfg.addDefault("season.next", "Soon!");
		
		for (Nation n : Nation.getAll()) {
			String name = n.getName().toLowerCase().replace(" ", "_");
			cfg.addDefault("nation." + name + ".skin", n.getBase64Texture());
			cfg.addDefault("nation." + name + ".mayor", "00000000-0000-0000-0000-000000000000");
			for (Nation a : Nation.getAll()) {
				cfg.addDefault("nation." + name + ".relation." + a.getName().toLowerCase().replace(" ", "_"), new ArrayList<String>());
			}
		}
		
		for (Job j : Job.getAll()) {
			cfg.addDefault("job." + j.getName().toLowerCase() + ".skin", j.getBase64Texture());
		}

		cfg.options().copyDefaults(true);
		
		try {
			cfg.save(new File(path, name));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String getSQLUsername() {
		return FC.getMainCfg().getString("sql.username");
	}

	public static String getSQLPassword() {
		return FC.getMainCfg().getString("sql.password");
	}

	public static String getSQLServer() {
		return FC.getMainCfg().getString("sql.server");
	}
	
	public static String getSQLDatabase() {
		return FC.getMainCfg().getString("sql.database");
	}
	
	public static Location getLocation(String name) {
		return new Location(
				Bukkit.getWorld(UUID.fromString(FC.getMainCfg().getString("locations." + name + ".world"))),
				FC.getMainCfg().getDouble("locations." + name + ".x"),
				FC.getMainCfg().getDouble("locations." + name + ".y"),
				FC.getMainCfg().getDouble("locations." + name + ".z"),
				FC.getMainCfg().getInt("locations." + name + ".yaw"),
				FC.getMainCfg().getInt("locations." + name + ".pitch")
		);
	}
	
	public static void saveLocation(String name, Location loc) {
		FileConfiguration cfg = FC.getMainCfg();
		cfg.set("locations." + name + ".world", loc.getWorld());
		cfg.set("locations." + name + ".x", loc.getX());
		cfg.set("locations." + name + ".y", loc.getY());
		cfg.set("locations." + name + ".z", loc.getZ());
		cfg.set("locations." + name + ".yaw", loc.getYaw());
		cfg.set("locations." + name + ".pitch", loc.getPitch());
		try {
			cfg.save(FC.getMainFile());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
