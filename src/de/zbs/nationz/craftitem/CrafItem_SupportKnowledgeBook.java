package de.zbs.nationz.craftitem;

import org.bukkit.Material;

public class CrafItem_SupportKnowledgeBook extends CraftItem {

	@Override
	public String getDisplayName() {
		return "Supporting Knowledge Book";
	}

	@Override
	public String[] getLore() {
		return null;
	}

	@Override
	public Material getIcon() {
		return Material.ENCHANTED_BOOK;
	}

	@Override
	public boolean isGlowing() {
		return false;
	}
}