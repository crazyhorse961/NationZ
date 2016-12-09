package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_Infinity extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onShoot(EntityShootBowEvent e) {
			if (e.getEntity() instanceof Player) {
				Item es  = Item.get(e.getBow());
				if (es.hasEnchantment(INFINITY)) {
					Player p = (Player) e;
					int r = (int) Math.random() * 100;
					switch (es.getEnchantmentLevel(UNBREAKING)) {
						case 1:
							if (r <= 20) {
								p.getInventory().addItem(new ItemStack(Material.ARROW));
							}
							break;
						case 2:
							if (r <= 40) {
								p.getInventory().addItem(new ItemStack(Material.ARROW));
							}
							break;
						case 3:
							if (r <= 60) {
								p.getInventory().addItem(new ItemStack(Material.ARROW));
							}
							break;
						case 4:
							if (r <= 80) {
								p.getInventory().addItem(new ItemStack(Material.ARROW));
							}
							break;
						case 5:
							break;
					}
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
		return new ItemTarget().BOW;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Lucky Arrows";
	}

	@Override
	public Material getIcon() {
		return Material.BOOK_AND_QUILL;
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
				new ItemStack(Material.BOOK_AND_QUILL, 8),
				new ItemStack(Material.ARROW, 64)
		};
	}
}