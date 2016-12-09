package de.zbs.nationz.jobs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import de.slikey.effectlib.EffectManager;
import de.slikey.effectlib.effect.ImageEffect;
import de.slikey.effectlib.util.DynamicLocation;
import de.slikey.effectlib.util.ParticleEffect;
import de.zbs.nationz.api.FC;
import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.jobs.alchemist.JobAlchemist;
import de.zbs.nationz.jobs.architect.JobArchitect;
import de.zbs.nationz.jobs.brewer.JobBrewer;
import de.zbs.nationz.jobs.enchanter.JobEnchanter;
import de.zbs.nationz.jobs.engineer.JobEngineer;
import de.zbs.nationz.jobs.hunter.JobHunter;
import de.zbs.nationz.jobs.miner.JobMiner;
import de.zbs.nationz.jobs.smith.JobSmith;
import de.zbs.nationz.jobs.tamer.JobTamer;
import de.zbs.nationz.jobs.timberman.JobTimberman;
import de.zbs.nationz.main.NationZ;

public abstract class Job {

	public static final class EXP {
		
		private int EXP;
		private LVL XPLVL;
		private UUID uuid;
		
		public EXP(UUID id, int xp) {
			uuid = id;
			EXP = xp;
			XPLVL = Job.getLvl(EXP);
			FC.getCfg(uuid.toString()).set("exp", EXP);
			FC.getCfg(uuid.toString()).set("jobs.level", XPLVL.lvl);
		}


		public void addXP(Integer xp) {
			addXP(xp, null);
		}
		
		public void addXP(int xp, String text) {
			EXP+=xp;
			if (Bukkit.getOfflinePlayer(uuid).isOnline()) {
				Player p = Bukkit.getPlayer(uuid);
				NZPlayer nz = NZPlayer.getNZPlayer(p);
				if (text == null) {
					nz.sendActionBar(ChatColor.BLUE + "+ " + xp + " Experience");
				} else {
					nz.sendActionBar(ChatColor.BLUE + "+ " + xp + " Experience");
				}
				if (Job.getLvl(EXP).lvl > XPLVL.lvl) {
					nz.getJob().levelUp(Bukkit.getPlayer(uuid));
				}
				p.playSound(p.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
			}
			XPLVL=Job.getLvl(EXP);
			FC.getCfg(uuid.toString()).set("jobs.exp", EXP);
			FC.getCfg(uuid.toString()).set("jobs.level", XPLVL.lvl);
		}
		
		public int getEXP() {
			return EXP;
		}
		
		public LVL getXPLVL() {
			return XPLVL;
		}
	}
	
	public static final class LVL {
		
		public final int begin;
		public final int end;
		public final int lvl;
		
		LVL(int begin,int end,int lvl) {
			this.begin = begin;
			this.end = end;
			this.lvl = lvl;
		}
		
		public boolean in(int xp) {
			return begin >= xp && end <= end;
		}
	}
	
	public static final LVL[] LVLs = new LVL[] {
			new LVL(0, 99, 1), 
			new LVL(100, 499, 2), 
			new LVL(500, 1499, 3), 
			new LVL(1500, 3699, 4), 
			new LVL(3700, 7099, 5), 
			new LVL(7100, 12299, 6), 
			new LVL(12300, 19999, 7), 
			new LVL(20000, 28999, 8), 
			new LVL(29000, 40999, 9), 
			new LVL(41000, 56999, 10), 
			new LVL(57000, 75999, 11), 
			new LVL(76000, 97999, 12), 
			new LVL(98000, 124999, 13), 
			new LVL(125000, 155999, 14), 
			new LVL(156000, 191999, 15), 
			new LVL(192000, 232999, 16), 
			new LVL(233000, 279999, 17), 
			new LVL(280000, 331999, 18), 
			new LVL(332000, 389999, 19), 
			new LVL(390000, 454999, 20), 
			new LVL(455000, 526999, 21), 
			new LVL(527000, 605999, 22), 
			new LVL(606000, 691999, 23), 
			new LVL(692000, 786999, 24), 
			new LVL(787000, 888999, 25), 
			new LVL(889000, 999999, 26), 
			new LVL(1000000, 1121999, 27), 
			new LVL(1122000, 1254999, 28), 
			new LVL(1255000, 1399999, 29), 
			new LVL(1400000, Integer.MAX_VALUE, 30)
	};
	
	public static final Job ALCHEMIST = new JobAlchemist();
	public static final Job ARCHITECT = new JobArchitect();
	public static final Job BREWER = new JobBrewer();
	public static final Job ENCHANTER = new JobEnchanter();
	public static final Job ENGINEER = new JobEngineer();
	public static final Job HUNTER = new JobHunter();
	public static final Job MINER = new JobMiner();
	public static final Job SMITH = new JobSmith();
	public static final Job TAMER = new JobTamer();
	public static final Job TIMBERMAN = new JobTimberman();
	
	public static final Job[] JOBS = new Job[] {
			ALCHEMIST,
			ARCHITECT,
			BREWER,
			ENCHANTER,
			ENGINEER,
			HUNTER,
			MINER,
			SMITH,
			TAMER,
			TIMBERMAN
	};
	
	public static List<Job> getAll() {
		return Arrays.asList(JOBS);
	}
	
	private static ChatColor color = ChatColor.DARK_GREEN;
	
	public static ChatColor getColor() {
		return color;
	}
	
	public String getDisplayname() {
		return getColor() + getName();
	}

	public abstract String getName();
	
	public abstract String getPrefix();
	
	public abstract Material getIcon();
	
	public abstract ArrayList<String> getDescription();
	
	public abstract Perk[] getPerks();

	public abstract String getBase64Texture();
	
	public ArrayList<UUID> getPlayerlist() {
		return playerlist;
	}
	
	public static Job getJob(String string) {
		for (int i = 0; i < JOBS.length; i++) {
			if (JOBS[i].getName().equalsIgnoreCase(string) || JOBS[i].getPrefix().equalsIgnoreCase(string)) {
				return JOBS[i];
			}
		}
		return null;
	}
	
	public void addToJob(Player p) {
		playerlist.add(p.getUniqueId());
		p.addPotionEffects(getEffects());
		playerexp.put(p.getUniqueId().toString(), new EXP(p.getUniqueId(), 0));
		FC.getCfg(p.getUniqueId().toString()).set("jobs.lastset", System.currentTimeMillis());
	}

	public void reAddToJob(Player p, int xp) {
		playerlist.add(p.getUniqueId());
		p.addPotionEffects(getEffects());
		playerexp.put(p.getUniqueId().toString(), new EXP(p.getUniqueId(), xp));
	}
	
	public abstract ArrayList<PotionEffect> getEffects();
	
	public EXP getExp(UUID d) {
		return playerexp.get(d.toString());
	}
	
	public static LVL getLvl(int xp) {
		for(LVL l : LVLs)
			if (l.in(xp))
				return l;
		return LVLs[0];
	}
	
	public ItemStack getItem() {
		ItemStack s = new ItemStack(this.getIcon());
		ItemMeta m = s.getItemMeta();
		m.setDisplayName(this.getDisplayname());
		m.setLore(this.getDescription());
		s.setItemMeta(m);
		return s;
	}
	
	protected ArrayList<UUID> playerlist = new ArrayList<UUID>();
	protected HashMap<String, EXP> playerexp = new HashMap<>();
	
	public abstract Listener getListener();
	
	public void levelUp(Player p) {
		p.playSound(p.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 10, 10);
		 try {
			 EffectManager manager = new EffectManager(NationZ.plugin);
			 final ImageEffect imageEffect = new ImageEffect(manager);
			 imageEffect.setDynamicOrigin(new DynamicLocation(p.getLocation().add(0, 5, 0)));
			 imageEffect.loadFile(new File("plugins/NationZ", "levelup.png"));
			 imageEffect.enableRotation = true;
			 imageEffect.particle = ParticleEffect.SLIME;
			 imageEffect.start();
			 Bukkit.getScheduler().runTaskLater(NationZ.plugin, new Runnable() {
				 @Override
				 public void run() {
					 imageEffect.cancel();
				 }
			 }, 60);
		 } catch (IOException e) {
			 NationZ.plugin.getLogger().warning("LevelUp-Image not found!");
		 }
		p.sendMessage(" ");
		p.sendMessage(NationZ.line(ChatColor.GOLD));
		p.sendMessage(" ");
		p.sendMessage(ChatColor.AQUA + "" + ChatColor.MAGIC + "XXX" + ChatColor.RESET + ChatColor.AQUA + "                                        LEVEL  UP!                                      " + ChatColor.MAGIC + "XXX");
		p.sendMessage(" ");
		for (int i = 0; i < getPerks().length; i++) {
			
		}
		p.sendMessage(" ");
		p.sendMessage(NationZ.line(ChatColor.GOLD));
		p.sendMessage(" ");
	}
	
	public Perk getPerk(String name) {
		for (int i = 0; i < getPerks().length; i++) {
			if (getPerks()[i].getKey().equalsIgnoreCase(name) || getPerks()[i].getName().equalsIgnoreCase(name)) {
				return getPerks()[i];
			}
		}
		return null;
	}
	
	public static HashMap<Integer, Integer> asHashMap(String[] string) {
		HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();
		for (int i = 0; i < string.length; i++) {
			hm.put(Integer.parseInt(string[i].split(":")[0]), Integer.parseInt(string[i].split(":")[1]));
		}
		return hm;
	}
	
	public class Perk {
		
		private String name;
		private String description;
		private String key;
		private HashMap<Integer, Integer> perkMap;
		
		public Perk(String name, String description, String key, HashMap<Integer, Integer> perkMap) {
			this.name = name;
			this.description = description;
			this.key = key;
			this.perkMap = perkMap;
		}
		
		public String getName() {
			return name;
		}
		
		public String getDescription() {
			return description;
		}
		
		public String getKey() {
			return key;
		}
		
		public HashMap<Integer, Integer> getPerkMap() {
			return perkMap;
		}
	}
	
	public static void downloadLevelUpImage() {
		Bukkit.getScheduler().runTaskAsynchronously(NationZ.plugin, new Runnable() {
			@Override
			public void run() {
				NationZ.plugin.getLogger().info("Downloading default LevelUp-Image...");
				try {
					@SuppressWarnings("resource")
					FileOutputStream fos = new FileOutputStream("plugins/NationZ/levelup.png");
					fos.getChannel().transferFrom(Channels.newChannel(new URL("http://i.imgur.com/0yGT3Ul.png").openStream()), 0, Long.MAX_VALUE);
					NationZ.plugin.getLogger().info("Successfully downloaded LevelUp-Image");
				} catch (MalformedURLException e) {
					NationZ.plugin.getLogger().log(Level.SEVERE, "Exception thrown: ", e);
				} catch (FileNotFoundException e) {
					NationZ.plugin.getLogger().log(Level.SEVERE, "Exception thrown: ", e);
				} catch (IOException e) {
					NationZ.plugin.getLogger().log(Level.SEVERE, "Exception thrown: ", e);
				}
			}
		});
	}
}