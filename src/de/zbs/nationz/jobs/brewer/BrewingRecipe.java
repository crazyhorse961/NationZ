package de.zbs.nationz.jobs.brewer;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class BrewingRecipe {
	
	private static List<BrewingRecipe> r = new ArrayList<BrewingRecipe>();
	
	/* TODO Make Brewing Recipes
x ABSORPTION
x BLINDNESS
CONFUSION
DAMAGE_RESISTANCE
FAST_DIGGING
FIRE_RESISTANCE
GLOWING
HARM
HEAL
HEALTH_BOOST
HUNGER
INCREASE_DAMAGE
INVISIBILITY
JUMP
LEVITATION
LUCK
NIGHT_VISION
POISON
REGENERATION
SATURATION
SLOW
SLOW_DIGGING
SPEED
UNLUCK
WATER_BREATHING
WEAKNESS
WITHER
	 */
	
	public static void registerRecipes() {
		new BrewingRecipe(PotionEffectType.ABSORPTION, 6, 4,
				new ItemStack(Material.WATER_BUCKET, 1),
				new ItemStack(Material.GOLD_INGOT, 8),
				new ItemStack(Material.GOLDEN_APPLE, 16),
				new ItemStack(Material.GOLDEN_CARROT, 16)
		);
		new BrewingRecipe(PotionEffectType.BLINDNESS, 3, 1,
				new ItemStack(Material.INK_SACK, 32),
				new ItemStack(Material.OBSIDIAN, 4),
				new ItemStack(Material.INK_SACK, 32),
				new ItemStack(Material.INK_SACK, 32)
		);
	}
	
	private ItemStack[] ingredients;
	private PotionEffectType potionEffectType;
	private int maxDuration;
	private int maxAmplifier;
	
	public BrewingRecipe(PotionEffectType potionEffectType, int duration, int amplifier, ItemStack... ingredients) {
		this.ingredients = ingredients;
		this.potionEffectType = potionEffectType;
		this.maxDuration = duration * 20 * 60;
		this.maxAmplifier = amplifier - 1;
		r.add(this);
	}

	public ItemStack getResult() {
		ItemStack is = new ItemStack(Material.POTION);
		PotionMeta pm = (PotionMeta) is.getItemMeta();
		pm.addCustomEffect(new PotionEffect(potionEffectType, maxDuration, maxAmplifier), true);
		pm.setMainEffect(potionEffectType);
		is.setItemMeta(pm);
		return is;
	}

	public ItemStack[] getIngredients() {
		return ingredients;
	}

	public PotionEffectType getPotionEffectType() {
		return potionEffectType;
	}

	public int getMaxDuration() {
		return maxDuration;
	}

	public int getMaxAmplifier() {
		return maxAmplifier;
	}
}