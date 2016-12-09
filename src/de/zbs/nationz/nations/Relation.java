package de.zbs.nationz.nations;

import java.util.Arrays;
import java.util.List;

import org.bukkit.ChatColor;

public enum Relation {

	WAR(ChatColor.DARK_RED),
	CEASEFIRE(ChatColor.BLUE),
	NEUTRAL(ChatColor.DARK_GRAY),
	FRIENDLY(ChatColor.GREEN),
	ALLIANCE(ChatColor.DARK_GREEN);
	
	private ChatColor chatcolor;
	
	Relation(ChatColor chatcolor) {
		this.chatcolor = chatcolor;
	}
	
	public ChatColor getColor() {
		return this.chatcolor;
	}
	
	public String getName() {
		return name().toLowerCase().replace(name().toLowerCase().charAt(0), name().charAt(0));
	}

	public static List<Relation> getAll() {
		return Arrays.asList(WAR, CEASEFIRE, NEUTRAL, FRIENDLY, ALLIANCE);
	}
}