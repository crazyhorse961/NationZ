package de.zbs.nationz.enchantments;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_Fortune extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onBreak(BlockBreakEvent e) {
			Item es  = Item.get(e.getPlayer().getEquipment().getItemInMainHand());
			if (es.hasEnchantment(FORTUNE)) {
				List<Material> fb = new ArrayList<Material>();
				fb.add(Material.COAL_ORE);
				fb.add(Material.DIAMOND_ORE);
				fb.add(Material.EMERALD_ORE);
				fb.add(Material.GLOWING_REDSTONE_ORE);
				fb.add(Material.GOLD_ORE);
				fb.add(Material.IRON_ORE);
				fb.add(Material.LAPIS_ORE);
				fb.add(Material.QUARTZ_ORE);
				fb.add(Material.REDSTONE_ORE);
				if (fb.contains(e.getBlock().getType())) {
					e.setExpToDrop(es.getEnchantmentLevel(FORTUNE) * e.getExpToDrop());
					e.setCancelled(true);
					e.getBlock().breakNaturally(new ItemStack(e.getBlock().getType(), es.getEnchantmentLevel(FORTUNE)));
				}
			}
		}
	};

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.HEAT_INFUSED) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().TOOLS;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public String getName() {
		return "Fortune";
	}

	@Override
	public Material getIcon() {
		return Material.GOLD_NUGGET;
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
				new ItemStack(Material.DIAMOND, 16),
				new ItemStack(Material.INK_SACK, 32, (short) 4),
				new ItemStack(Material.BOOKSHELF, 8)
		};
	}
}