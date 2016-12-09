package de.zbs.nationz.jobs.alchemist;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

import de.zbs.nationz.craftitem.CraftItem;

public class AlchemistRecipe {
	
	public static void registerRecipes() {
		new AlchemistRecipe(new ItemStack[] {
				new ItemStack(Material.GOLD_BLOCK), null, null, new ItemStack(Material.GOLD_BLOCK),
				new ItemStack(Material.GLASS), null, null, new ItemStack(Material.GLASS),
				new ItemStack(Material.GLASS), null, null, new ItemStack(Material.GLASS),
				new ItemStack(Material.GOLD_BLOCK), null, null, new ItemStack(Material.GOLD_BLOCK)
		}, CraftItem.SHARD_OF_REPULSION.getItem(), 50, new PlayerRunnable() {
			@Override
			public void run(Player player) {
				player.setVelocity(new Vector(0, 30, 0));
			}
		});
	}
	
	private ItemStack[] ingredients;
	private ItemStack result;
	private int chance;
	private static PlayerRunnable failed;
	
	static List<AlchemistRecipe> recipes = new ArrayList<AlchemistRecipe>();

	public AlchemistRecipe(ItemStack[] ingredients, ItemStack result, int chance, PlayerRunnable failed) {
		recipes.add(this);
		this.ingredients = ingredients;
		this.result = result;
		this.chance = chance;
		AlchemistRecipe.failed = failed;
	}
	
	public static AlchemistRecipe get(ItemStack[] ingredients) {
		for (AlchemistRecipe ar : recipes) {
			if (ar.getIngredients() == ingredients) {
				int rand = (int) Math.random() * 100;
				if (rand <= ar.getChance()) {
					return ar;
				} else {
					return null;
				}
			}
		}
		return null;
	}
	
	public static void punish(Player player) {
		failed.run(player);
	}
	
	public ItemStack[] getIngredients() {
		return ingredients;
	}

	public ItemStack getResult() {
		return result;
	}

	public int getChance() {
		return chance;
	}

	public PlayerRunnable getFailed() {
		return failed;
	}

	public static List<AlchemistRecipe> getRecipes() {
		return recipes;
	}

	public static ItemStack[] makeArray(Inventory inv) {
		ItemStack[] in = new ItemStack[16];
		in[0] = inv.getItem(10);
		in[1] = inv.getItem(11);
		in[2] = inv.getItem(19);
		in[3] = inv.getItem(20);
		in[4] = inv.getItem(28);
		in[5] = inv.getItem(29);
		in[6] = inv.getItem(37);
		in[7] = inv.getItem(38);
		
		in[8] = inv.getItem(15);
		in[9] = inv.getItem(16);
		in[10] = inv.getItem(24);
		in[11] = inv.getItem(25);
		in[12] = inv.getItem(33);
		in[13] = inv.getItem(34);
		in[14] = inv.getItem(42);
		in[15] = inv.getItem(43);
		return in;
	}
	
	public static abstract class PlayerRunnable {
		public abstract void run(Player player);
	}
}