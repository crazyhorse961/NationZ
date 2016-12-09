package de.zbs.nationz.main;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;

import de.slikey.effectlib.EffectManager;
import de.zbs.advinventory.api.AdvInventory;
import de.zbs.nationz.api.Config;
import de.zbs.nationz.api.FC;
import de.zbs.nationz.inventory.MainMenuInventory;
import de.zbs.nationz.jobs.Job;
import de.zbs.nationz.jobs.alchemist.AlchemistRecipe;
import de.zbs.nationz.jobs.brewer.BrewingRecipe;
import de.zbs.nationz.listener.AsyncPlayerChatListener;
import de.zbs.nationz.listener.ConnectionListener;
import de.zbs.nationz.listener.PlayerDeathListener;
import de.zbs.nationz.listener.PlayerDrinkListener;
import de.zbs.nationz.listener.PlayerPickupItemListener;
import de.zbs.nationz.nations.Nation;
import de.zbs.nationz.permission.UserInventory;

public class NationZ extends JavaPlugin {
	
	//TODO Backpacks
	//TODO PowerItems
	//TODO WorldEvents
	//TODO Welt bauen
	
	private String url = "";

	private static Connection conn;
	
	public static Connection getConnection() {
		return conn;
	}
	
	public static String title = ChatColor.GOLD + "" + ChatColor.BOLD + "Nation" + ChatColor.RED + ChatColor.BOLD + "Z";

	public static String rawLine = "------>---------------------------------------<------";
	public static String line(ChatColor c) {
		return c + "" + ChatColor.STRIKETHROUGH + rawLine;	
	}
	
	public static NationZ plugin;
	public static EffectManager effectManager;
	public static int usercurrentversion = 0; // Current user config version
	public static ProtocolManager protocolManager;
		
	@Override
	public void onEnable() {
		Logger logger = Logger.getLogger("");
		logger.addHandler(new Handler() {
			@Override
			public void publish(LogRecord paramLogRecord) {
				if (paramLogRecord.getThrown() != null || paramLogRecord.getLevel().equals(Level.SEVERE)) {
					File f = new File("plugins/NationZ", "errors.yml");
					FileConfiguration cfg = YamlConfiguration.loadConfiguration(f);
					long time = System.currentTimeMillis();
					cfg.set(time + ".getLevel", paramLogRecord.getLevel().toString());
					StackTraceElement[] e = paramLogRecord.getThrown().getStackTrace();
					List<List<String>> stack = new ArrayList<List<String>>();
					for (int i = 0; i < e.length - 1; i++) {
						List<String> elem = new ArrayList<String>();
						elem.add("error: " + e[i].getClassName() + "." + e[i].getFileName() + "#" + e[i].getMethodName() + ":" + Integer.toString(e[i].getLineNumber()));
						stack.add(elem);
					}
					cfg.set(time + ".getThrown.getStackTrace", stack);
					
					Throwable ex = paramLogRecord.getThrown().getCause();
					if (ex != null) {
						StackTraceElement[] c = ex.getStackTrace();
						List<List<String>> stack_cause = new ArrayList<List<String>>();
						for (int i = 0; i < e.length - 1; i++) {
							List<String> elem = new ArrayList<String>();
							elem.add("error: " + c[i].getClassName() + "." + c[i].getFileName() + "#" + c[i].getMethodName() + ":" + Integer.toString(e[i].getLineNumber()));
							stack_cause.add(elem);
						}
						cfg.set(time + ".getThrown.getCause", stack_cause);
					}
					cfg.set(time + ".getThrown.getMessage", (paramLogRecord.getThrown().getMessage() != null ? paramLogRecord.getThrown().getMessage().toString() : null));
					cfg.set(time + ".getLoggerName", paramLogRecord.getLoggerName());
					try {
						cfg.save(f);
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}

			@Override
			public void flush() {}

			@Override
			public void close() throws SecurityException {}
		});
		
		List<String> dependencies = new ArrayList<String>();

		dependencies.add("EffectLib");
		dependencies.add("ProtocolLib");
		
		int enable = 0;
		
		for (String s : dependencies) {
			if (Bukkit.getPluginManager().getPlugin(s) == null || !Bukkit.getPluginManager().getPlugin(s).isEnabled()) {
				getLogger().warning("Install " + s + " first!");
				enable++;
			}
		}
		
		if (enable > 0) {
			getLogger().warning(enable + " dependencies missing! NationZ will stop now!");
			setEnabled(false);
		} else {
			plugin = this;
			effectManager = new EffectManager(plugin);
			protocolManager = ProtocolLibrary.getProtocolManager();
			
			FC.start();
			new File("plugins/NationZ").mkdirs();
			Config.load();
			
			url = "jdbc:mysql://" + Config.getSQLServer() + "/" + Config.getSQLDatabase();
			try {
				Class.forName("com.mysql.jdbc.Driver");
				if (Config.getSQLPassword().equalsIgnoreCase("null") || Config.getSQLPassword() == null) {
					conn = DriverManager.getConnection(url, Config.getSQLUsername(), null);
				} else {
					conn = DriverManager.getConnection(url, Config.getSQLUsername(), Config.getSQLPassword());
				}
			} catch (SQLException | ClassNotFoundException e) {
				this.getLogger().warning("SQL is not Set Up yet. Disabling...");
				e.printStackTrace();
				this.setEnabled(false);
				return;
			}
			
			if (!(new File("plugins/NationZ", "levelup.png").exists())) {
				Job.downloadLevelUpImage();
			}
			
			Listener[] ls = new Listener[] {
					new ConnectionListener(),
					new PlayerDrinkListener(),
					new AsyncPlayerChatListener(),
					new PlayerDeathListener(),
					new PlayerPickupItemListener(),
					AdvInventory.getListener(),
					new MainMenuInventory(),
					new UserInventory()
			};
			
			PluginManager pm = Bukkit.getPluginManager();
			for (int i = 0; i < ls.length - 1; i++) {
				pm.registerEvents(ls[i], plugin);
			}
			for (Nation n : Nation.NATIONS) {
				if (n.getListener() != null) {
					pm.registerEvents(n.getListener(), plugin);
				}
			}
			for (Job j : Job.JOBS) {
				if (j.getListener() != null) {
					pm.registerEvents(j.getListener(), plugin);
				}
			}
			
			AlchemistRecipe.registerRecipes();
			BrewingRecipe.registerRecipes();
			
			this.getCommand("nationz").setExecutor(new NationZCommand());
			
	        ZonedDateTime zonedNow = ZonedDateTime.of(LocalDateTime.now(), ZoneId.of("Europe/Berlin"));
	        ZonedDateTime zonedNext5 = zonedNow.withHour(5).withMinute(0).withSecond(0);
	        if(zonedNow.compareTo(zonedNext5) > 0) {
	            zonedNext5 = zonedNext5.plusDays(1);
	        }
	        Duration duration = Duration.between(zonedNow, zonedNext5);
	        long initalDelay = duration.getSeconds();

	        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);            
	        scheduler.scheduleAtFixedRate(new Runnable() {
	        	@Override
	        	public void run() {
					NationZ.restart(true);
	        	}
	        }, initalDelay, 24*60*60, TimeUnit.SECONDS);
			
			this.getLogger().info("Successfully enabled!");
		}
	}
	
	@Override
	public void onDisable() {
		NationZ.effectManager.dispose();
		if (FC.getMainCfg() != null) {
			FC.stop();
		}
		this.getLogger().info("Successfully disabled!");
	}
	
	public static void restart(boolean b) {
		for (Player onlineplayer : Bukkit.getOnlinePlayers()) {
			onlineplayer.kickPlayer(NationZ.title + 
					"\n" + NationZ.line(ChatColor.AQUA) + 
					"\n" + ChatColor.RED + " " + 
					"\n" + ChatColor.DARK_RED + "Server is restarting!" + 
					"\n" + ChatColor.RED + "Please try again later." + 
					"\n" + ChatColor.RED + " " + 
					"\n" + NationZ.line(ChatColor.AQUA));
		}
		if (b) {
			Bukkit.getServer().spigot().restart();
		}
	}

}
