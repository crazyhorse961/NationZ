package de.zbs.nationz.listener;


import java.util.Iterator;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.api.FC;
import de.zbs.nationz.api.Item;
import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.enchantments.EnchantmentZ;
import de.zbs.nationz.inventory.MainMenuInventory;
import de.zbs.nationz.main.NationZ;

public class PlayerDeathListener implements Listener {
	
	@EventHandler
	public void onDeath(final PlayerDeathEvent e) {
		Location oldloc = e.getEntity().getLocation();
		Bukkit.getScheduler().runTask(NationZ.plugin, new Runnable() {
			@Override
			public void run() {
				e.getEntity().spigot().respawn();
			}
		});
		
		e.getEntity().getWorld().strikeLightningEffect(oldloc);
		e.getEntity().playSound(e.getEntity().getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 1, 1);
		
        ItemStack stack = MainMenuInventory.item();
        Iterator<ItemStack> iterator = e.getDrops().iterator();
        while(iterator.hasNext()){
            if (stack.isSimilar(iterator.next())) {
                iterator.remove();
            }
        }
		
		for (ItemStack i : e.getDrops()) {
			if (i.isSimilar(MainMenuInventory.item())) {
				e.getDrops().remove(i);
			} else {
				if (i.equals(null)) {
					e.getEntity().getWorld().dropItemNaturally(oldloc, i);
					e.getEntity().getInventory().remove(i);
				}
			}
		}
		
		for (ItemStack is : e.getEntity().getInventory().getArmorContents()) {
			Item i = Item.get(is);
			if (i.hasEnchantment(EnchantmentZ.SOULBOUND)) {
				e.getDrops().remove(is);
			}
			e.getEntity().getInventory().addItem(is);
		}
		for (ItemStack is : e.getEntity().getInventory().getContents()) {
			Item i = Item.get(is);
			if (i.hasEnchantment(EnchantmentZ.SOULBOUND)) {
				e.getDrops().remove(is);
			}
			e.getEntity().getInventory().addItem(is);
		}
		
		e.setDeathMessage(null);
		
		EntityDamageEvent ede = e.getEntity().getLastDamageCause();
		if (ede instanceof EntityDamageByEntityEvent) {
			EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent) ede;
			Entity damager = edbee.getDamager();
			Entity victim = edbee.getEntity();
			if (victim instanceof Player) {
				Player victimPlayer = (Player) victim;
				if (damager instanceof Player) {
					Player damagerPlayer = (Player) damager;
					
					if (FC.getCfg(damagerPlayer.getUniqueId().toString()).getString("pvp.lastkilled").equalsIgnoreCase(victimPlayer.getUniqueId().toString())) {
						String[] connector  = new String[] {
								"{killer} killed {player} in revenge",
								"{killer} revenged {player}",
								"{killer} raged on {player} for revenge",
								};
						Random random = new Random();
						int index = random.nextInt(connector.length);
						sendMessage(connector[index].replace("{player}", victimPlayer.getDisplayName()).replace("{killer}", damagerPlayer.getDisplayName()), true);
						
						NZPlayer.getNZPlayer(damagerPlayer).getJob().getExp(damager.getUniqueId()).addXP(80, ChatColor.BLUE + "+40 " + ChatColor.AQUA + "x2" + ChatColor.BLUE + "Experience for revenge on " + victimPlayer.getDisplayName());
					} else {
						String[] connector  = new String[] {
								"{player} was slain by {killer}",
								"{killer} killed {player}",
								"{killer} eliminated {player}",
								"{killer} wrecked {player}",
								"{killer} slayed {player}",
								};
						Random random = new Random();
						int index = random.nextInt(connector.length);
						sendMessage(connector[index].replace("{player}", victimPlayer.getDisplayName()).replace("{killer}", damagerPlayer.getDisplayName()), true);

						NZPlayer.getNZPlayer(damagerPlayer).getJob().getExp(damager.getUniqueId()).addXP(80, ChatColor.BLUE + "+40 Experience for killing " + victimPlayer.getDisplayName());
					}
					FC.getCfg(victimPlayer).set("pvp.lastkilled", damagerPlayer.getUniqueId().toString());

					List<String> killed = FC.getCfg(damagerPlayer).getStringList("pvp.kills.players");
					killed.add(victimPlayer.getUniqueId().toString());
					FC.getCfg(damagerPlayer).set("pvp.kills.players", killed);
					
					List<String> died = FC.getCfg(victimPlayer).getStringList("pvp.deaths.players");
					died.add(damagerPlayer.getUniqueId().toString());
					FC.getCfg(victimPlayer).set("pvp.deaths.players", died);
					
					NZPlayer.getNZPlayer(damagerPlayer).getJob().getExp(damager.getUniqueId()).addXP(80, ChatColor.BLUE + "+40 Experience for killing " + victimPlayer.getDisplayName());
				} else {
					switch (edbee.getDamager().getType()) {
						case BLAZE:
							String[] blazeConnector  = new String[] {
									"{player} tried to catch a fireball by a Blaze",
									"{player} was burnt by a Blaze",
									"A Blaze stopped {player}'s life",
									"Watch out for Blazes! {player} got killed by one...",
									};
							Random blazeRandom = new Random();
							int blazeIndex = blazeRandom.nextInt(blazeConnector.length);
							sendMessage(blazeConnector[blazeIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case CAVE_SPIDER:
							String[] caveSpiderConnector  = new String[] {
									"{player} tried to kill a Cave Spider",
									"A Cave Spider slayed {player}",
									"{player} was killed by a Cave Spider",
									"Watch out for Cave Spiders! {player} got killed by one...",
									};
							Random caveSpiderRandom = new Random();
							int caveSpiderIndex = caveSpiderRandom.nextInt(caveSpiderConnector.length);
							sendMessage(caveSpiderConnector[caveSpiderIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case COW:
							String[] cowConnector  = new String[] {
									"{player} got killed by a angry Bull",
									"{player} died trying to tame a Cow",
									"{player} was killed by a Cow",
									"Not all Cows are friendly. {player} found it out the hard way...",
									};
							Random cowRandom = new Random();
							int cowIndex = cowRandom.nextInt(cowConnector.length);
							sendMessage(cowConnector[cowIndex].replace("{player}", victimPlayer.getDisplayName()), false);
							break;
						case CREEPER:
							String[] creeperConnector  = new String[] {
									"{player} got blown up by a creeper",
									"{player} died whilst fighting a creeper",
									"{player} was slain by a creeper",
									"A creeper blew {player} up",
									};
							Random creeperRandom = new Random();
							int creeperIndex = creeperRandom.nextInt(creeperConnector.length);
							sendMessage(creeperConnector[creeperIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case ENDER_DRAGON:
							String[] enderDragonConnector  = new String[] {
									"{player} tried to ride the Ender Dragon",
									"{player} died whilst fighting the Ender Dragon",
									"{player} was doomed by the Ender Dragon",
									"The Ender Dragon stopped {player}'s life",
									};
							Random enderDragonRandom = new Random();
							int enderDragonIndex = enderDragonRandom.nextInt(enderDragonConnector.length);
							sendMessage(enderDragonConnector[enderDragonIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case ENDERMAN:
							String[] enderManConnector  = new String[] {
									"{player} was taken by an Enderman",
									"{player} endered away...",
									"{player} was slain by an Enderman",
									"{player} was shocked to an Enderman",
									};
							Random enderManRandom = new Random();
							int enderManIndex = enderManRandom.nextInt(enderManConnector.length);
							sendMessage(enderManConnector[enderManIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case ENDERMITE:
							String[] enderMiteConnector  = new String[] {
									"{player} was slain by an Endermite",
									};
							Random enderMiteRandom = new Random();
							int enderMiteIndex = enderMiteRandom.nextInt(enderMiteConnector.length);
							sendMessage(enderMiteConnector[enderMiteIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case GHAST:
							String[] ghastConnector  = new String[] {
									"{player} tried to catch a fireball by a Ghast",
									"{player} was burnt by a Ghast",
									"{player} was fireballed by a Ghast",
									"{player} ate a Ghast-Fireball",
									};
							Random ghastRandom = new Random();
							int ghastIndex = ghastRandom.nextInt(ghastConnector.length);
							sendMessage(ghastConnector[ghastIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case GIANT:
							String[] giantConnector  = new String[] {
									"{player} was stomped to death by a Giant",
									"{player} was killed by a rare Giant",
									};
							Random giantRandom = new Random();
							int giantIndex = giantRandom.nextInt(giantConnector.length);
							sendMessage(giantConnector[giantIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case GUARDIAN:
							String[] guardianConnector  = new String[] {
									"{player} was killed by a Guardian",
									"{player} was slain by Guardian",
									};
							Random guardianRandom = new Random();
							int guardianIndex = guardianRandom.nextInt(guardianConnector.length);
							sendMessage(guardianConnector[guardianIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case HORSE:
							String[] horseConnector  = new String[] {
									"{player} was punched to death by a Horse",
									"{player} was killed by an angry Horse",
									"{player} tried to tame a Horse and failed",
									};
							Random horseRandom = new Random();
							int horseIndex = horseRandom.nextInt(horseConnector.length);
							sendMessage(horseConnector[horseIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case IRON_GOLEM:
							String[] ironGolemConnector  = new String[] {
									"{player} was drilled into the ground by an Iron Golem",
									"{player} was killed by an angry Iron Golem",
									"{player} got a sidekick by an Iron Golem",
									"{player} was drilled by an Iron Golem",
									};
							Random ironGolemRandom = new Random();
							int ironGolemIndex = ironGolemRandom.nextInt(ironGolemConnector.length);
							sendMessage(ironGolemConnector[ironGolemIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case MAGMA_CUBE:
							String[] magmaCubeConnector  = new String[] {
									"{player} was slain by Magma Cube",
									"{player} was stomped into the ground by Magma Cube",
									"{player} burnt to death by Magma Cube",
									};
							Random magmaCubeRandom = new Random();
							int magmaCubeIndex = magmaCubeRandom.nextInt(magmaCubeConnector.length);
							sendMessage(magmaCubeConnector[magmaCubeIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case MUSHROOM_COW:
							String[] mushroomCowConnector  = new String[] {
									"{player} got killed by a angry Mooshroom-Bull",
									"{player} died trying to tame a Mooshroom-Cow",
									"{player} was killed by a Mooshroom-Cow",
									"Not all Mooshroom-Cows are friendly. {player} found it out the hard way...",
									};
							Random mushroomCowRandom = new Random();
							int mushroomCowIndex = mushroomCowRandom.nextInt(mushroomCowConnector.length);
							sendMessage(mushroomCowConnector[mushroomCowIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case OCELOT:
							String[] ocelotConnector  = new String[] {
									"{player} got killed by a angry Mooshroom-Bull",
									"{player} died trying to tame a Mooshroom-Cow",
									"{player} was killed by a Mooshroom-Cow",
									"Not all Mooshroom-Cows are friendly. {player} found it out the hard way...",
									};
							Random ocelotRandom = new Random();
							int ocelotIndex = ocelotRandom.nextInt(ocelotConnector.length);
							sendMessage(ocelotConnector[ocelotIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case PIG:
							String[] pigConnector  = new String[] {
									"{player} was slain by Pig",
									"{player} was killed by a wild Boar",
									"{player} tried to tame a wild Boar",
									};
							Random pigRandom = new Random();
							int pigIndex = pigRandom.nextInt(pigConnector.length);
							sendMessage(pigConnector[pigIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case PIG_ZOMBIE:
							String[] pigZombieConnector  = new String[] {
									"{player} got killed by a Pig Zombie",
									"{player} was slain by a Pig Zombie",
									"{player} was stabbed to death by Pig Zombie",
									};
							Random pigZombieRandom = new Random();
							int pigZombieIndex = pigZombieRandom.nextInt(pigZombieConnector.length);
							sendMessage(pigZombieConnector[pigZombieIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case SHEEP:
							String[] sheepConnector  = new String[] {
									"{player} was slain by a wild Goat",
									"A Goat didn't want to be tamed by {player}",
									"{player} tried to tame a wild Goat and failed",
									"Not all sheeps want to be tamed. {player} found it out the hard way...",
									};
							Random sheepRandom = new Random();
							int sheepIndex = sheepRandom.nextInt(sheepConnector.length);
							sendMessage(sheepConnector[sheepIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case SILVERFISH:
							String[] silverfishConnector  = new String[] {
									"{player} was slain by a Silverfish",
									};
							Random silverfishRandom = new Random();
							int silverfishIndex = silverfishRandom.nextInt(silverfishConnector.length);
							sendMessage(silverfishConnector[silverfishIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case SKELETON:
							String[] skeletonConnector  = new String[] {
									"{player} got a headshot by a Skeleton",
									"{player} got shot by a skeleton",
									"{player} was slain by a skeleton",
									"{player} was pierced by a skeleton",
									};
							Random skeletonRandom = new Random();
							int skeletonIndex = skeletonRandom.nextInt(skeletonConnector.length);
							sendMessage(skeletonConnector[skeletonIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case SLIME:
							String[] slimeConnector  = new String[] {
									"{player} was stomped to death by a Slime",
									"A slime jumped on {player}'s head",
									"A slime squaded {player}",
									};
							Random slimeRandom = new Random();
							int slimeIndex = slimeRandom.nextInt(slimeConnector.length);
							sendMessage(slimeConnector[slimeIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case SPIDER:
							String[] spiderConnector  = new String[] {
									"{player} tried to kill a Spider",
									"A Cave slayed {player}",
									"{player} was killed by a Spider",
									"Watch out for Spiders! {player} got killed by one...",
									};
							Random spiderRandom = new Random();
							int spiderIndex = spiderRandom.nextInt(spiderConnector.length);
							sendMessage(spiderConnector[spiderIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case WITCH:
							String[] witchConnector  = new String[] {
									"{player} was killed by a Witch",
									"A Witch used magic on {player}. He died.",
									"Magic killed {player}.",
									};
							Random witchRandom = new Random();
							int witchIndex = witchRandom.nextInt(witchConnector.length);
							sendMessage(witchConnector[witchIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case WITHER:
							String[] witherConnector  = new String[] {
									"{player} withered away",
									"{player} was slain by Wither",
									"{player} got a headshot by a Wither",
									"{player} tried to kill a Wither",
									};
							Random witherRandom = new Random();
							int witherIndex = witherRandom.nextInt(witherConnector.length);
							sendMessage(witherConnector[witherIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case WOLF:
							String[] wolfConnector  = new String[] {
									"Ouch. That wolf didn't want to be tamed by {player}",
									"{player} was slain by an Angry Wolf",
									"{player} was killed by a wolf",
									"{player} was a victim of the wolf-Apocalypse",
									};
							Random wolfRandom = new Random();
							int wolfIndex = wolfRandom.nextInt(wolfConnector.length);
							sendMessage(wolfConnector[wolfIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						case ZOMBIE:
							String[] zombieConnector  = new String[] {
									"{player} got eaten by a Zombie",
									"The dead have taken {player}",
									"{player} was killed by a Zombie",
									"{player} was a victim of the Zombie-Apocalypse",
									};
							Random zombieRandom = new Random();
							int zombieIndex = zombieRandom.nextInt(zombieConnector.length);
							sendMessage(zombieConnector[zombieIndex].replace("{player}", ChatColor.GOLD + victimPlayer.getDisplayName()).replace(" ", ChatColor.YELLOW + " "), false);
							break;
						default:
							break;
					}
				}
			}
		} else {
			
		}
	}
	
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		NZPlayer nz = NZPlayer.getNZPlayer(e.getPlayer());
		//TODO e.setRespawnLocation(...);
		
		e.getPlayer().getInventory().setItem(8, MainMenuInventory.item());

		for (PotionEffect pe : e.getPlayer().getActivePotionEffects()) {
			e.getPlayer().removePotionEffect(pe.getType());
		}
		e.getPlayer().addPotionEffects(nz.getNation().getEffects());
	}
	
	private void sendMessage(String finalMessage, boolean pvp) {
		for (Player receiver : Bukkit.getOnlinePlayers()) {
			NZPlayer nz = NZPlayer.getNZPlayer(receiver);
			
			if (nz.getDeathMessageConfig().split("::")[0].equalsIgnoreCase("none")) return;
			if (pvp) {
				if (nz.getDeathMessageConfig().split("::")[0].equalsIgnoreCase("pvp")) {
					receiver.sendMessage(finalMessage);
				} else {
					return;
				}
			} else {
				receiver.sendMessage(finalMessage);
			}
		}
	}
}
