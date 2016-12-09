package de.zbs.nationz.craftitem;

import org.bukkit.Material;

public class CrafItem_WindEssence extends CraftItem {

	@Override
	public String getDisplayName() {
		return "Wind Essence";
	}

	@Override
	public String[] getLore() {
		return null;
	}

	@Override
	public Material getIcon() {
		return Material.SUGAR;
	}

	@Override
	public boolean isGlowing() {
		return false;
	}
}