package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchantmentZ_Hardened extends EnchantmentZ {

	public Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (e.getDamage() > 0) {
				if (e.getEntity() instanceof Player) {
					if (e.getDamager() instanceof Player) {
						Player killer = (Player) e.getDamager();
						Player p = (Player) e.getEntity();
						killer.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, EnchantmentZ.calcInventoryPieceAmount(HARDENED, p.getInventory()), 0));
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
		return new ItemTarget().ARMOR;
	}

	@Override
	public int getMaxLevel() {
		return 6;
	}

	@Override
	public String getName() {
		return "Hardened";
	}

	@Override
	public Material getIcon() {
		return Material.ANVIL;
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
				new ItemStack(Material.OBSIDIAN, 16),
				new ItemStack(Material.EMERALD, 2),
				new ItemStack(Material.SLIME_BALL, 4)
		};
	}

}
