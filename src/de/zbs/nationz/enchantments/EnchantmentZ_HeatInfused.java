package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_HeatInfused extends EnchantmentZ {

	private Listener ls = new Listener() {
		@SuppressWarnings("deprecation")
		@EventHandler
		public void onBreak(BlockBreakEvent e) {
			Item es  = Item.get(e.getPlayer().getEquipment().getItemInMainHand());
			if (es.hasEnchantment(HEAT_INFUSED)) {
				e.setCancelled(true);
				switch (e.getBlock().getType()) {
					case COAL_ORE:
						e.getBlock().breakNaturally(new ItemStack(Material.COAL, 4));
						break;
					case GLOWING_REDSTONE_ORE:
					case REDSTONE_ORE:
						e.getBlock().breakNaturally(new ItemStack(Material.REDSTONE, 7));
						break;
					case QUARTZ_ORE:
						e.getBlock().breakNaturally(new ItemStack(Material.QUARTZ, 3));
						break;
					case LAPIS_ORE:
						e.getBlock().breakNaturally(new ItemStack(Material.INK_SACK, 5, DyeColor.BLUE.getData()));
						break;
					case IRON_ORE:
						e.getBlock().breakNaturally(new ItemStack(Material.IRON_ORE, 1));
						break;
					case GOLD_ORE:
						e.getBlock().breakNaturally(new ItemStack(Material.GOLD_ORE, 1));
						break;
					default:
						break;
				}
			}
		}
	};
	
	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.FORTUNE) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().PICKAXE;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public String getName() {
		return "Heat Infused";
	}

	@Override
	public Material getIcon() {
		return Material.FURNACE;
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
				new ItemStack(Material.FURNACE, 4),
				new ItemStack(Material.NETHERRACK, 32)
		};
	}
}