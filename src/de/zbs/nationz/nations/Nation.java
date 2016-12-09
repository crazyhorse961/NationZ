package de.zbs.nationz.nations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.api.DescriptionItem;
import de.zbs.nationz.api.FC;

public abstract class Nation {
	
	/* STATIC FIELDS
	 *  - NATIONS INHERITS */
	
	public static final Nation MAGE = new NationMage();
	public static final Nation ATLANTISKINGS = new NationAtlantisKings();
	public static final Nation DWARFS = new NationDwarfs();
	public static final Nation HUMANS = new NationHumans();
	public static final Nation ELVES = new NationElves();
	public static final Nation HALFGODS = new NationHalfGods();
	public static final Nation CYCLOPS = new NationCyclops();
	public static final Nation PHARAOS = new NationPharaos();
	
	public static String good = DescriptionItem.ListItemType.GOOD.getString();
	public static String bad = DescriptionItem.ListItemType.BAD.getString();
	
	/*  - NATION  LIST */	

	public static final Nation[] NATIONS = new Nation[] { MAGE, ATLANTISKINGS, DWARFS, HUMANS, ELVES, HALFGODS, CYCLOPS, PHARAOS };
	
	public String getDisplayname() {
		return ChatColor.BLUE + getName();
	}
	
	/* ABSTRACT METHODS */
	
	public abstract String getName();
	
	public abstract String getPrefix();
	
	public abstract ArrayList<String> getDescription();
	
	public abstract Material getIcon();
	
	public abstract Biome getHome();
		
	public abstract ArrayList<PotionEffect> getEffects();

	public abstract ChatColor getNationChatColor();
		
	public abstract Listener getListener();
	
	public abstract String getBase64Texture();
	
	/* PRE-DEFINED METHODS */
	
	public static Nation getNation(String string) {
		for (int i = 0; i < NATIONS.length; i++) {
			if (NATIONS[i].getName().equalsIgnoreCase(string) || NATIONS[i].getPrefix().equalsIgnoreCase(string)) {
				return NATIONS[i];
			}
		}
		return null;
	}
	
	public boolean hasRelation(Nation n, Relation nr) {
		String toCheck = FC.getMainCfg().getString("nation." + getName().toLowerCase() + "." + n.getName().toLowerCase());
		return toCheck.equalsIgnoreCase(nr.name());
	}
	
	public void setRelation(Nation n, Relation nr) {
		FC.getMainCfg().set("nation." + getName().toLowerCase() + "." + n.getName().toLowerCase(), nr.name());
	}

	public Relation getRelation(Nation n) {
		String toCheck = FC.getMainCfg().getString("nation." + getName().toLowerCase() + "." + n.getName().toLowerCase());
		for (Relation rel : Relation.getAll()) {
			if (rel.name().equalsIgnoreCase(toCheck)) {
				return rel;
			}
		}
		return null;
	}

	public ArrayList<UUID> getPlayerlist() {
		return playerlist;
	}
	
	public ItemStack getItem() {
		ItemStack s = new ItemStack(this.getIcon());
		ItemMeta m = s.getItemMeta();
		m.setDisplayName(this.getDisplayname());
		m.setLore(this.getDescription());
		s.setItemMeta(m);
		return s;
	}
	
	public void addToNation(Player p) {
		playerlist.add(p.getUniqueId());
		p.addPotionEffects(getEffects());
	}
	
	public void reAddToNation(Player p) {
		playerlist.add(p.getUniqueId());
		p.addPotionEffects(getEffects());
	}
	
	public UUID getMayorUUID() {
		return mayoruuid;
	}
	
	public static List<Nation> getAll() {
		return Arrays.asList(NATIONS);
	}
	
	/* FIELDS */
	
	protected UUID mayoruuid = UUID.fromString("00000000-0000-0000-0000-000000000000");
	
	protected ArrayList<UUID> playerlist = new ArrayList<UUID>();
}
