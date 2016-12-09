package de.zbs.nationz.craftitem;

import org.bukkit.Material;

public class CrafItem_ShardOfRepulsion extends CraftItem {

	@Override
	public String getDisplayName() {
		return "Shard of Repulsion";
	}

	@Override
	public String[] getLore() {
		return new String[] {
				"Used for Enchantments like Thorns, Shockwave,",
				"Revulsion, Hardened and other Armor-",
				"Enchantments that harm attackers"
		};
	}

	@Override
	public Material getIcon() {
		return Material.PRISMARINE_SHARD;
	}

	@Override
	public boolean isGlowing() {
		return false;
	}
}