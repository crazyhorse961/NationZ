package de.zbs.nationz.ability;

import java.util.List;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

import de.zbs.nationz.enchantments.ItemTarget;

public class Ability_ForceANature extends Ability {

	@Override
	public String getName() {
		return "Force A Nature";
	}

	@Override
	public String[] getDescription() {
		return new String[] {
				"Throw nearby Players into the air."
		};
	}

	@Override
	public RightClickRunnable getRun() {
		return new RightClickRunnable() {
			@Override
			public void run(Player player, Block clickedBlock) {
				for (Entity e : player.getNearbyEntities(6, 6, 6)) {
					if (e instanceof Player) {
						e.setVelocity(new Vector(0, 6, 0));
						e.getLocation().getWorld().playEffect(e.getLocation(), Effect.STEP_SOUND, Material.WOOD);
						e.getLocation().getWorld().playSound(e.getLocation(), Sound.ENTITY_BLAZE_SHOOT, 1, 1);
					}
				}
			}
		};
	}

	@Override
	public List<Material> applicableTo() {
		return new ItemTarget().SWORD;
	}
}