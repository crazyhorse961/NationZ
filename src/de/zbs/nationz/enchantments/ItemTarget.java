package de.zbs.nationz.enchantments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;

public class ItemTarget {
	
	public List<Material> ALL = new ArrayList<Material>();
	
	public List<Material> ARMOR = new ArrayList<Material>();
	public List<Material> TOOLS = new ArrayList<Material>();
	
	public List<Material> HELMET = new ArrayList<Material>();
	public List<Material> CHESTPLATE = new ArrayList<Material>();
	public List<Material> LEGGINGS = new ArrayList<Material>();
	public List<Material> BOOTS = new ArrayList<Material>();

	public List<Material> SWORD = new ArrayList<Material>();
	public List<Material> BOW = new ArrayList<Material>();
	public List<Material> FISHING_ROD = new ArrayList<Material>();

	public List<Material> PICKAXE = new ArrayList<Material>();
	public List<Material> SHOVEL = new ArrayList<Material>();
	public List<Material> AXE = new ArrayList<Material>();
	
	public ItemTarget() {
		/* === Armor === */
		HELMET.add(Material.LEATHER_HELMET);
		HELMET.add(Material.GOLD_HELMET);
		HELMET.add(Material.CHAINMAIL_HELMET);
		HELMET.add(Material.IRON_HELMET);
		HELMET.add(Material.DIAMOND_HELMET);
		
		CHESTPLATE.add(Material.LEATHER_CHESTPLATE);
		CHESTPLATE.add(Material.GOLD_CHESTPLATE);
		CHESTPLATE.add(Material.CHAINMAIL_CHESTPLATE);
		CHESTPLATE.add(Material.IRON_CHESTPLATE);
		CHESTPLATE.add(Material.DIAMOND_CHESTPLATE);
		
		LEGGINGS.add(Material.LEATHER_LEGGINGS);
		LEGGINGS.add(Material.GOLD_LEGGINGS);
		LEGGINGS.add(Material.CHAINMAIL_LEGGINGS);
		LEGGINGS.add(Material.IRON_LEGGINGS);
		LEGGINGS.add(Material.DIAMOND_LEGGINGS);
		
		BOOTS.add(Material.LEATHER_BOOTS);
		BOOTS.add(Material.GOLD_BOOTS);
		BOOTS.add(Material.CHAINMAIL_BOOTS);
		BOOTS.add(Material.IRON_BOOTS);
		BOOTS.add(Material.DIAMOND_BOOTS);
		
		ARMOR.addAll(HELMET);
		ARMOR.addAll(CHESTPLATE);
		ARMOR.addAll(LEGGINGS);
		ARMOR.addAll(BOOTS);

		/* === Tools === */
		PICKAXE.add(Material.WOOD_PICKAXE);
		PICKAXE.add(Material.GOLD_PICKAXE);
		PICKAXE.add(Material.STONE_PICKAXE);
		PICKAXE.add(Material.IRON_PICKAXE);
		PICKAXE.add(Material.DIAMOND_PICKAXE);
		
		SHOVEL.add(Material.WOOD_SPADE);
		SHOVEL.add(Material.GOLD_SPADE);
		SHOVEL.add(Material.STONE_SPADE);
		SHOVEL.add(Material.IRON_SPADE);
		SHOVEL.add(Material.DIAMOND_SPADE);
		
		AXE.add(Material.WOOD_AXE);
		AXE.add(Material.GOLD_AXE);
		AXE.add(Material.STONE_AXE);
		AXE.add(Material.IRON_AXE);
		AXE.add(Material.DIAMOND_AXE);
		
		TOOLS.addAll(PICKAXE);
		TOOLS.addAll(SHOVEL);
		TOOLS.addAll(AXE);

		/* === Other === */
		SWORD.add(Material.WOOD_SWORD);
		SWORD.add(Material.GOLD_SWORD);
		SWORD.add(Material.STONE_SWORD);
		SWORD.add(Material.IRON_SWORD);
		SWORD.add(Material.DIAMOND_SWORD);
		
		BOW.add(Material.BOW);

		FISHING_ROD.add(Material.FISHING_ROD);
		
		ALL.addAll(SWORD);
		ALL.addAll(BOW);
		ALL.addAll(ARMOR);
		ALL.addAll(TOOLS);
	}
}