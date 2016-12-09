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

import de.zbs.nationz.api.Item;
import de.zbs.nationz.craftitem.CraftItem;

public class EnchantmentZ_Venom extends EnchantmentZ {
	
	public Listener ls = new Listener() {
		@EventHandler
		public void onDamage(EntityDamageByEntityEvent e) {
			if (e.getDamage() > 0) {
				if (e.getEntity() instanceof Player) {
					if (e.getDamager() instanceof Player) {
						Player killer = (Player) e.getDamager();
						Player p = (Player) e.getEntity();
						if (new ItemTarget().SWORD.contains(killer.getEquipment().getItemInMainHand().getType())) {
							Item es  = Item.get(killer.getEquipment().getItemInMainHand());
							if (es.hasEnchantment(VENOM)) {
								int duration = es.getEnchantmentLevel(VENOM) * 2 * 20;
								p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, duration, 0));
							}
						}
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
		return new ItemTarget().SWORD;
	}

	@Override
	public int getMaxLevel() {
		return 6;
	}

	@Override
	public String getName() {
		return "Venom";
	}

	@Override
	public Material getIcon() {
		return Material.EMERALD;
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
				new ItemStack(Material.EMERALD, 8),
				CraftItem.BAD_BREWING_ESSENCE.getItem()
		};
	}

}
