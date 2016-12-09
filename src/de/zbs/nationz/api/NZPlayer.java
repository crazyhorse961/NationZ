package de.zbs.nationz.api;

import java.lang.reflect.InvocationTargetException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.Sound;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.EnumWrappers.TitleAction;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import de.zbs.nationz.jobs.Job;
import de.zbs.nationz.jobs.Job.EXP;
import de.zbs.nationz.jobs.Job.LVL;
import de.zbs.nationz.main.NationZ;
import de.zbs.nationz.nations.Nation;
import de.zbs.nationz.permission.Rank;

public class NZPlayer {
	
	public void updateString(String toUpdate, String string) {
		try {
			String statement = "UPDATE users SET " + toUpdate + " = ? WHERE unique_id=?";
			PreparedStatement ps = NationZ.getConnection().prepareStatement(statement);
			ps.setString(1, string);
			ps.setString(2, this.getUniqueId().toString());
			ps.executeUpdate();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	private static final String insertStatement = "INSERT INTO users (unique_id, last_name, names, job, nation, rank, kills, lastKilled, deaths, lastDeath, deathMessageConfig) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String selectStatement = "SELECT * FROM users WHERE unique_id = ?";
		
	private static HashMap<String, NZPlayer> players = new HashMap<>();
	
	private UUID uuid;
	private String lastName;
	private List<String> names;
	private Job job;
	private Nation nation;
	private int rank;
	private List<String> kills;
	private String lastKilled;
	private List<String> deaths;
	private String lastDeath;
	private String deathMessageConfig; //(pvp/none/all)::(chat/acitonbar)
	
	public static NZPlayer getNZPlayer(Player p) {
		if (!players.containsKey(p.getUniqueId().toString())) {
			players.put(p.getUniqueId().toString(), new NZPlayer(p));
		}
		return players.get(p.getUniqueId().toString());
	}

	public static NZPlayer getNZPlayer(OfflinePlayer op) {
		if (!players.containsKey(op.getUniqueId().toString())) {
			players.put(op.getUniqueId().toString(), new NZPlayer((Player) op));
		}
		return players.get(op.getUniqueId().toString());
	}
	
	private NZPlayer(Player p) {
		try {
			PreparedStatement stmt = NationZ.getConnection().prepareStatement(selectStatement);
			stmt.setString(1, p.getUniqueId().toString());
			ResultSet rs = stmt.executeQuery();
			this.uuid = p.getUniqueId();
			if (!rs.first()) {
				PreparedStatement sstmt = NationZ.getConnection().prepareStatement(insertStatement);
				sstmt.setString(1, p.getUniqueId().toString());
				sstmt.setString(2, p.getName());
				List<String> tmp = new ArrayList<String>();
				tmp.add(p.getName());
				sstmt.setString(3, tmp.toString());
				sstmt.setString(4, "not-chosen");
				sstmt.setString(5, "not-chosen");
				sstmt.setInt(6, 0);
				sstmt.setString(7, new ArrayList<String>().toString());
				sstmt.setString(8, "none");
				sstmt.setString(9, new ArrayList<String>().toString());
				sstmt.setString(10, "none");
				sstmt.setString(11, "all::chat");
				sstmt.executeUpdate();
				this.names = Arrays.asList(p.getName());
				this.job = null;
				this.nation = null;
			}
			this.names = Arrays.asList(rs.getString("names").split(";"));
			this.job = Job.getJob(rs.getString("job"));
			this.nation = Nation.getNation(rs.getString("nation"));
			this.lastName = rs.getString("last_name");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void addName(String name) {
		this.names.add(name);
	}
	
	public void removeNames(String name) {
		this.names.remove(name);
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void getNation(Nation nation) {
		this.nation = nation;
	}
	
	public void removeNZPlayer() {
		players.remove(this.uuid.toString());
	}

	public void setRank(int rank) {
		this.rank = rank;
	}

	public void addKill(Player player) {
		this.kills.add(player.getUniqueId().toString());
	}
	
	public void removeKill(Player player) {
		this.kills.remove(player.getUniqueId().toString());
	}
	
	public void setKills(List<String> kills) {
		this.kills = kills;
	}

	public void setLastKilled(String lastKilled) {
		this.lastKilled = lastKilled;
	}

	public void addDeath(Player player) {
		this.deaths.add(player.getUniqueId().toString());
	}
	
	public void removeDeaths(Player player) {
		this.deaths.remove(player.getUniqueId().toString());
	}
	
	public void setDeaths(List<String> deaths) {
		this.deaths = deaths;
	}

	public void setLastDeath(String lastDeath) {
		this.lastDeath = lastDeath;
	}

	public void setDeathMessageConfig(DMC_Types type, DMC_Sends send) {
		this.deathMessageConfig = type.name().toLowerCase() + "::" + send.name().toLowerCase();
	}
	
	public enum DMC_Types {
		PVP,
		NONE,
		ALL
	}
	
	public enum DMC_Sends {
		CHAT,
		ACTIONBAR
	}

	public int getRank() {
		return rank;
	}

	public boolean hasRank(Rank should) {
		return NZPlayer.getNZPlayer(getPlayer()).getRank() >= should.getLevel();
	}

	public List<String> getKills() {
		return kills;
	}

	public String getLastKilled() {
		return lastKilled;
	}

	public List<String> getDeaths() {
		return deaths;
	}

	public String getLastDeath() {
		return lastDeath;
	}

	public String getDeathMessageConfig() {
		return deathMessageConfig;
	}

	public UUID getUniqueId() {
		return uuid;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public List<String> getNames() {
		return names;
	}

	public Job getJob() {
		return job;
	}
		
	public EXP getJobExp() {
		Job j = getJob();
		return j.getExp(uuid);
	}
	
	public int getJobLevel() {
		Job j = getJob();
		EXP e = j.getExp(uuid);
		LVL x = e.getXPLVL();
		Integer l = x.lvl;
		return l;
	}

	public Nation getNation() {
		return nation;
	}

	public boolean isMayor() {
		return this.nation.getMayorUUID().equals(this.uuid);
	}

	private Player getPlayer() {
		return Bukkit.getPlayer(uuid);
	}
	
	public void sendTitle(Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		sendTitle(fadeIn, stay, fadeOut, title, TitleAction.TITLE);
	}

	public void sendSubTitle(Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		sendTitle(fadeIn, stay, fadeOut, title, TitleAction.SUBTITLE);
	}
	
	private void sendTitle(Integer fadeIn, Integer stay, Integer fadeOut, String title, TitleAction action) {
		PacketContainer tp = NationZ.protocolManager.createPacket(PacketType.Play.Server.TITLE);
		
		tp.getModifier().writeDefaults();
		tp.getTitleActions().write(0, action);
		tp.getChatComponents().write(0, WrappedChatComponent.fromText(title));
		tp.getIntegers().write(0, fadeIn);
		tp.getIntegers().write(1, stay);
		tp.getIntegers().write(2, fadeOut);
		
		try {
			NationZ.protocolManager.sendServerPacket(getPlayer(), tp);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void sendActionBar(String message){
		PacketContainer titlePacket = NationZ.protocolManager.createPacket(PacketType.Play.Server.CHAT);
		
		//TODO Send ActionBar
		
		try {
			NationZ.protocolManager.sendServerPacket(getPlayer(), titlePacket);
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String message) {
		getPlayer().sendMessage(ChatColor.RED + "          " + message);
		getPlayer().playSound(getPlayer().getLocation(), Sound.BLOCK_ANVIL_LAND, 1, 1);
	}
	
	public void updateBossBar() {
		BossBar b = Bukkit.createBossBar("error", BarColor.GREEN, BarStyle.SOLID);
		b.addPlayer(getPlayer());
		int begin = getJobExp().getXPLVL().begin;
		int current = getJobExp().getEXP();
		int end = getJobExp().getXPLVL().end;
		b.setTitle(getJob().getDisplayname() + ChatColor.DARK_GRAY + " - " + Job.getColor() + current + ChatColor.DARK_GRAY + "/" + Job.getColor() + end);
		b.setProgress(100 / (end - begin) * (current - begin));
		
	}
}