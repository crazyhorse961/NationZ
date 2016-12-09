package de.zbs.nationz.craftitem;

import org.bukkit.Material;

public class CrafItem_GoodBrewingEssence extends CraftItem {

	@Override
	public String getDisplayName() {
		return "Good Brewing Essence";
	}

	@Override
	public String[] getLore() {
		return null;
	}

	@Override
	public Material getIcon() {
		return Material.GLOWSTONE_DUST;
	}

	@Override
	public boolean isGlowing() {
		return false;
	}
}