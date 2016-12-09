package de.zbs.nationz.api;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class FileCacher {

	private static Map<String, File> filecache = new HashMap<>();
	
	private static Map<String, FileConfiguration> cfgcache = new HashMap<>();
	
	protected FileCacher() {}
	
	public static void load(String uuid) {
		filecache.put(uuid, new File(Config.path + "/PlayerDB", uuid + ".yml"));
		cfgcache.put(uuid, YamlConfiguration.loadConfiguration(filecache.get(uuid)));
	}
		
	public static File getFile(String uuid) {
		return filecache.get(uuid);
	}
	
	public static File getFile(Player p) {
		return filecache.get(p.getUniqueId().toString());
	}
	
	public static FileConfiguration getCfg(Player p) {
		return cfgcache.get(p.getUniqueId().toString());
	}
	
	
	public static FileConfiguration getCfg(String uuid) {
		return cfgcache.get(uuid);
	}
	
	public static void left(String uuid) {
		try {
			cfgcache.get(uuid).save(filecache.get(uuid));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cfgcache.remove(uuid);
		filecache.remove(uuid);
	}
	
	public static void start() {
		filecache.put("cfg", new File(Config.path, Config.name));
		cfgcache.put("cfg", YamlConfiguration.loadConfiguration(filecache.get("cfg")));
	}
	
	public static File getMainFile() {
		return filecache.get("cfg");
	}
	
	public static FileConfiguration getMainCfg() {
		return cfgcache.get("cfg");
	}
	
	public static void stop() {
		try {
			cfgcache.get("cfg").save(filecache.get("cfg"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		cfgcache.remove("cfg");
		filecache.remove("cfg");
	}
}