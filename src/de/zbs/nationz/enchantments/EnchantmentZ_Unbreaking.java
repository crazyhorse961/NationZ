package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemDamageEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_Unbreaking extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onDamage(PlayerItemDamageEvent e) {
			Item es  = Item.get(e.getItem());
			if (es.hasEnchantment(UNBREAKING)) {
				int r = (int) Math.random() * 100;
				switch (es.getEnchantmentLevel(UNBREAKING)) {
					case 1:
						if (r <= 12) {
							e.setDamage(0);
						}
						break;
					case 2:
						if (r <= 25) {
							e.setDamage(0);
						}
						break;
					case 3:
						if (r <= 37) {
							e.setDamage(0);
						}
						break;
					case 4:
						if (r <= 50) {
							e.setDamage(0);
						}
						break;
					case 5:
						if (r <= 63) {
							e.setDamage(0);
						}
						break;
					case 6:
						if (r <= 75) {
							e.setDamage(0);
						}
						break;
					case 7:
						if (r <= 87) {
							e.setDamage(0);
						}
						break;
					case 8:
						e.setDamage(0);
						break;
				}
			}
		}
	};

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		return false;
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().ALL;
	}

	@Override
	public int getMaxLevel() {
		return 8;
	}

	@Override
	public String getName() {
		return "Unbreaking";
	}

	@Override
	public Material getIcon() {
		return Material.IRON_FENCE;
	}

	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return null;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.OBSIDIAN, 8),
				new ItemStack(Material.LEATHER, 4),
		};
	}
}