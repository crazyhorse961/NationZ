package de.zbs.nationz.enchantments;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import de.zbs.nationz.api.Item;

public abstract class EnchantmentZ {
	/* === Vanilla Enchantments === */
	public static final EnchantmentZ PROTECTION = new EnchantmentZ_Protection();
	public static final EnchantmentZ FIRE_PROTECTION = new EnchantmentZ_FireProtection();
	public static final EnchantmentZ BLAST_PROTECTION = new EnchantmentZ_BlastProtection();
	public static final EnchantmentZ PROJECTILE_PROTECTION = new EnchantmentZ_ProjectileProtection();
	public static final EnchantmentZ FEATHER_FALLING = new EnchantmentZ_FeatherFalling();
//	Reserved for AtlantisKings: AQUA_AFFINITY
//	Reserved for AtlantisKings: RESPIRATION
	public static final EnchantmentZ THORNS = new EnchantmentZ_Thorns();
	public static final EnchantmentZ DEPTH_STRIDER = new EnchantmentZ_DepthStrider();
	public static final EnchantmentZ FROST_WALKER = new EnchantmentZ_FrostWalker();
	
	public static final EnchantmentZ SHARPNESS = new EnchantmentZ_Sharpness();
	public static final EnchantmentZ SMITE = new EnchantmentZ_Smite();
	public static final EnchantmentZ BANE_OF_ARTHROPODS = new EnchantmentZ_BaneOfArthropods();
	public static final EnchantmentZ KNOCKBACK = new EnchantmentZ_Knockback();
	public static final EnchantmentZ FIRE_ASPECT = new EnchantmentZ_FireAspect();
	public static final EnchantmentZ LOOTING = new EnchantmentZ_Looting();
	public static final EnchantmentZ EFFICIENCY = new EnchantmentZ_Efficiency();
	public static final EnchantmentZ FORTUNE = new EnchantmentZ_Fortune();

	public static final EnchantmentZ POWER = new EnchantmentZ_Power();
	public static final EnchantmentZ PUNCH = new EnchantmentZ_Punch();
	public static final EnchantmentZ FLAME = new EnchantmentZ_Flame();
	public static final EnchantmentZ INFINITY = new EnchantmentZ_Infinity();

	public static final EnchantmentZ LUCK_OF_THE_SEA = new EnchantmentZ_LuckOfTheSea();
	public static final EnchantmentZ LURE = new EnchantmentZ_Lure();
	
	public static final EnchantmentZ MENDING = new EnchantmentZ_Mending();
	public static final EnchantmentZ UNBREAKING = new EnchantmentZ_Unbreaking();

	/* === Custom Enchantments === */
	public static final EnchantmentZ DECAPITATE = new EnchantmentZ_Decapitate();
	public static final EnchantmentZ HEAT_INFUSED = new EnchantmentZ_HeatInfused();
	public static final EnchantmentZ LIFESTEAL = new EnchantmentZ_LifeSteal();
	public static final EnchantmentZ CONFUSION = new EnchantmentZ_Confusion();
	public static final EnchantmentZ VENOM = new EnchantmentZ_Venom();
	public static final EnchantmentZ VECTOR = new EnchantmentZ_Vector();
	public static final EnchantmentZ HARDENED = new EnchantmentZ_Hardened();
	public static final EnchantmentZ DEEP_WOUNDS = new EnchantmentZ_DeepWounds();
	public static final EnchantmentZ DECAY = new EnchantmentZ_Decay();
	public static final EnchantmentZ SOULBOUND = new EnchantmentZ_Soulbound();

	public static final EnchantmentZ[] ENCHANTMENTS = new EnchantmentZ[] {
			PROTECTION, FIRE_PROTECTION, BLAST_PROTECTION, PROJECTILE_PROTECTION, FEATHER_FALLING, THORNS, DEPTH_STRIDER, FROST_WALKER,
			SHARPNESS, SMITE, BANE_OF_ARTHROPODS, KNOCKBACK, FIRE_ASPECT, LOOTING,
			EFFICIENCY, FORTUNE,
			POWER, PUNCH, FLAME, INFINITY,
			LUCK_OF_THE_SEA, LURE,
			MENDING, UNBREAKING,
			
			HEAT_INFUSED, LIFESTEAL, CONFUSION, VENOM, VECTOR, HARDENED, DEEP_WOUNDS, DECAY,
			DECAPITATE, SOULBOUND
	};
	
	public abstract boolean conflictsWith(EnchantmentZ arg0);

	public abstract List<Material> getItemTarget();

	public abstract int getMaxLevel();

	public abstract String getName();
	
	public abstract Material getIcon();
	
	public abstract Listener getListener();

	public abstract Enchantment vanillaEnchant();
	
	public abstract ItemStack[] craftingIngredients();
	
	public boolean canEnchant(ItemStack item) {
		return getItemTarget().contains(item.getType());
	}
	
	public static EnchantmentZ getEnchantment(String string) {
		for (int i = 0; i < ENCHANTMENTS.length; i++) {
			if (string.equalsIgnoreCase(ENCHANTMENTS[i].getName())) {
				return ENCHANTMENTS[i];
			}
		}
		return null;
	}
	
	public String IntegerToRomanNumeral(int input) {
	    if (input < 1 || input > 3999)
	        return "Invalid Roman Number Value";
	    String s = "";
	    while (input >= 1000) {
	        s += "M";
	        input -= 1000;        }
	    while (input >= 900) {
	        s += "CM";
	        input -= 900;
	    }
	    while (input >= 500) {
	        s += "D";
	        input -= 500;
	    }
	    while (input >= 400) {
	        s += "CD";
	        input -= 400;
	    }
	    while (input >= 100) {
	        s += "C";
	        input -= 100;
	    }
	    while (input >= 90) {
	        s += "XC";
	        input -= 90;
	    }
	    while (input >= 50) {
	        s += "L";
	        input -= 50;
	    }
	    while (input >= 40) {
	        s += "XL";
	        input -= 40;
	    }
	    while (input >= 10) {
	        s += "X";
	        input -= 10;
	    }
	    while (input >= 9) {
	        s += "IX";
	        input -= 9;
	    }
	    while (input >= 5) {
	        s += "V";
	        input -= 5;
	    }
	    while (input >= 4) {
	        s += "IV";
	        input -= 4;
	    }
	    while (input >= 1) {
	        s += "I";
	        input -= 1;
	    }    
	    return s;
	}
	
	public static int calcInventoryPieceAmount(EnchantmentZ enchantment, PlayerInventory inv) {
		int amount = 0;
		if (Item.get(inv.getHelmet()).hasEnchantment(enchantment)) {
			amount ++;
		}
		if (Item.get(inv.getChestplate()).hasEnchantment(enchantment)) {
			amount ++;
		}
		if (Item.get(inv.getLeggings()).hasEnchantment(enchantment)) {
			amount ++;
		}
		if (Item.get(inv.getBoots()).hasEnchantment(enchantment)) {
			amount ++;
		}
		return amount;
	}
	
}