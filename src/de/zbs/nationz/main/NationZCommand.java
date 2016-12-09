package de.zbs.nationz.main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import de.zbs.nationz.api.Config;
import de.zbs.nationz.api.FC;
import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.permission.Rank;
import de.zbs.nationz.permission.UserInventory;
import mkremins.fanciful.FancyMessage;

public class NationZCommand implements CommandExecutor {

	private final String devpw = "nZ.J4V4.zbs";
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean onCommand(CommandSender cs, Command cmd, String label, String[] args) {
		if (label.equalsIgnoreCase("nation") || label.equalsIgnoreCase("nationz")) {
			cs.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "-+|+--------------------------------------+|+-");
			cs.sendMessage(ChatColor.BLUE + NationZ.plugin.getName() + ChatColor.GRAY + " " + NationZ.plugin.getDescription().getName() + ChatColor.DARK_GRAY + " by " + ChatColor.GRAY + NationZ.plugin.getDescription().getAuthors());
			cs.sendMessage(ChatColor.GRAY + "Type " + ChatColor.BLUE + "/help" + ChatColor.GRAY + " for help");
			cs.sendMessage(ChatColor.BLUE + "" + ChatColor.STRIKETHROUGH + "-+|+--------------------------------------+|+-");
		} else if (label.equalsIgnoreCase("job") || label.equalsIgnoreCase("jobs")) {
			if (cs instanceof Player) {
				//TODO Give Player ability to switch Jobs
			} else {
				cs.sendMessage("This Command is only available for Players");
			}
		} else if (label.equalsIgnoreCase("dev")) {
			if (cs instanceof Player) {
				Player p = (Player) cs;
				NZPlayer n = NZPlayer.getNZPlayer(p);
				if (n.hasRank(Rank.DEVELOPER) ||
						p.getUniqueId().toString().equals("9c1da052-c99c-4ea2-96f5-5da89de69596") || 
						p.getUniqueId().toString().equals("624cb2b7-b0c8-4ec1-8ad9-b7b06607088a") || 
						makeString(args) == devpw) {
					if (args.length == 0) {
						p.sendMessage(ChatColor.RED + "Invalid Subcommand!");
					} else if (args.length == 1) {
						p.sendMessage(ChatColor.RED + "Invalid Subcommand!");
					} else if (args.length == 2) {
						if (args[0].equalsIgnoreCase("setspawn")) {
							if (args[1].equalsIgnoreCase("nexus")) {
								Config.saveLocation("spawn", p.getLocation());
								p.sendMessage(ChatColor.GREEN + "Spawn Location saved successfully!");
							} else if (args[1].equalsIgnoreCase("tutorial")) {
								Config.saveLocation("tutorial", p.getLocation());
								p.sendMessage(ChatColor.GREEN + "Tutorial Location saved successfully!");
							} else if (args[1].equalsIgnoreCase("jobs")) {
								Config.saveLocation("jobs", p.getLocation());
								p.sendMessage(ChatColor.GREEN + "Job-Chooser Location saved successfully!");
							} else if (args[1].equalsIgnoreCase("nation")) {
								Config.saveLocation("nation", p.getLocation());
								p.sendMessage(ChatColor.GREEN + "Nation-Chooser Location saved successfully!");
							} else {
								p.sendMessage(ChatColor.RED + "Invalid Subcommand! Available Subcommands:");
								p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "tutorial");
								p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "nexus");
								p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "jobs");
								p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "nation");
							}
						} else {
							p.sendMessage(ChatColor.RED + "Invalid Subcommand! Available Subcommands:");
							p.sendMessage(ChatColor.DARK_GRAY + "- " + ChatColor.RED + "setspawn");
						}
					}
				} else {
					p.sendMessage(ChatColor.RED + "You need to be a " + ChatColor.BLUE + "DEVELOPER" + ChatColor.RED + "!");
				}
			} else {
				cs.sendMessage("Currently no temporary feature here.");
			}
		} else if (label.equalsIgnoreCase("user")) {
			if (cs instanceof Player) {
				Player p = (Player) cs;
				NZPlayer n = NZPlayer.getNZPlayer(p);
				if (n.hasRank(Rank.DEVELOPER) ||
						p.getUniqueId().toString().equals("9c1da052-c99c-4ea2-96f5-5da89de69596") || 
						p.getUniqueId().toString().equals("624cb2b7-b0c8-4ec1-8ad9-b7b06607088a") ||
						makeString(args) == devpw) {
					if (args.length == 0 ||
							makeString(args) == devpw) {
						UserInventory.open(p, p);
					} else if (args.length == 1) {
						if (!FC.getFile(Bukkit.getOfflinePlayer(args[0]).getUniqueId().toString()).exists()) {
							p.sendMessage(ChatColor.RED + "The given player hasn't been found.");
							return true;
						}
						if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
							UserInventory.open(p, Bukkit.getPlayer(args[0]));
						} else {
							UserInventory.open(p, Bukkit.getOfflinePlayer(args[0]));
						}
					}
				} else {
					p.sendMessage(ChatColor.RED + "You need to be an OWNER or have the correct password.");
				}
			} else {
				cs.sendMessage("This Command is only available for Players");
			}
		} else if (label.equalsIgnoreCase("help")) {
			if (cs instanceof Player) {
				Player p = (Player) cs;
				p.sendMessage(NationZ.line(ChatColor.DARK_GRAY));
				FancyMessage msg = new FancyMessage("For help about NationZ: ").color(ChatColor.DARK_AQUA);
				msg.then("Forum").color(ChatColor.AQUA).tooltip(ChatColor.DARK_AQUA + "Click to open the Forum!").style(ChatColor.UNDERLINE).link(FC.getMainCfg().getString("link.forum"));
				msg.then(" / ").color(ChatColor.DARK_GRAY);
				msg.then("Wiki").color(ChatColor.AQUA).tooltip(ChatColor.DARK_AQUA + "Click to open the Wiki!").style(ChatColor.UNDERLINE).link(FC.getMainCfg().getString("link.wiki"));
				p.sendMessage(NationZ.line(ChatColor.DARK_GRAY));
			} else {
				cs.sendMessage("This Command is only available for Players");
			}
		} else if (label.equalsIgnoreCase("admin")) {
			if (cs instanceof Player) {
				Player p = (Player) cs;
				NZPlayer nz = NZPlayer.getNZPlayer(p);
				if (nz.hasRank(Rank.ADMIN)) {
					//TODO Open Admin Inventory
				} else {
					p.sendMessage(ChatColor.RED + "You need to be an ADMIN.");
				}
			} else {
				cs.sendMessage("This Command is only available for Players");
			}
		} else if (label.equalsIgnoreCase("msg") || label.equalsIgnoreCase("message")) {
			if (Bukkit.getPlayer(args[0]) != null) {
				Player receiver = Bukkit.getPlayer(args[0]);
				String message = ChatColor.translateAlternateColorCodes('&', args[1-(args.length-1)]);
				cs.sendMessage(ChatColor.AQUA + "YOU" + ChatColor.GOLD + " > " + ChatColor.AQUA + receiver.getName() + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + message);
				receiver.sendMessage(ChatColor.AQUA + cs.getName() + ChatColor.GOLD + " > " + ChatColor.AQUA + "YOU" + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY + message);
				receiver.playSound(receiver.getLocation(), Sound.ENTITY_ARROW_HIT_PLAYER, 1, 1);
			}
		} else if (label.equalsIgnoreCase("mayor")) {
			if (cs instanceof Player) {
				Player p = (Player) cs;
				NZPlayer nz = NZPlayer.getNZPlayer(p);
				if (nz.isMayor() || nz.hasRank(Rank.ADMIN)) {
					//TODO Open Mayor Inventory
				} else {
					p.sendMessage(ChatColor.RED + "You need to be a Mayor");
				}
			} else {
				cs.sendMessage("This Command is only available for Players");
			}
		}
		return true;
	}
	
	public static String makeString(String[] arr) {
		String ret = "";
		for (int i = 0; i < arr.length; i++) {
			if (i != 0)
				ret += " ";
			ret += arr[i];
		}
		return ret;
	}
}
