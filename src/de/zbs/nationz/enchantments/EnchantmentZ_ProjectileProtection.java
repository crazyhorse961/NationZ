package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

public class EnchantmentZ_ProjectileProtection extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageEvent e) {
			if (e.getEntity() instanceof Player) {
				if (e.getCause() == DamageCause.PROJECTILE) {
					Player p = (Player) e.getEntity();
					e.setDamage(e.getDamage() * (1 - EnchantmentZ.calcInventoryPieceAmount(PROJECTILE_PROTECTION, p.getInventory()) * 0.0625));
				}
			}
		}
	};
	
	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		if (arg0 == EnchantmentZ.FIRE_PROTECTION ||
				arg0 == EnchantmentZ.PROTECTION ||
				arg0 == EnchantmentZ.BLAST_PROTECTION) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().ARMOR;
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public String getName() {
		return "Projectile Protection";
	}

	@Override
	public Material getIcon() {
		return Material.ARROW;
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
				new ItemStack(Material.ARROW, 8)
		};
	}
}