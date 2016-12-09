package de.zbs.nationz.permission;

import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.zbs.advinventory.api.AdvInventory;
import de.zbs.advinventory.api.AdvInventory.ClickRunnable;
import de.zbs.nationz.api.DescriptionItem;
import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.util.ItemUtils;
import net.md_5.bungee.api.ChatColor;

public class UserInventory implements Listener {
	
	public static ItemStack playerinfo(OfflinePlayer op) {
		NZPlayer nz = NZPlayer.getNZPlayer(op);
		String o = op.isOnline() ? ChatColor.GREEN + "ONLINE" : ChatColor.DARK_RED + "OFFLINE";
		DescriptionItem di = new DescriptionItem(ChatColor.BLUE + op.getName() + ChatColor.GRAY + " - " + o, op);
		di.appendSpacer();
		di.appendInfo("UUID", op.getUniqueId().toString());
		di.appendInfo("Nation", nz.getNation().getName());
		di.appendInfo("Job", nz.getJob().getDisplayname() + ChatColor.DARK_GRAY + " [" + ChatColor.GRAY + Integer.toString(nz.getJobLevel()) + ChatColor.DARK_GRAY + "]");
		
		int c = 1;
		String n = "";
		boolean first = true;
		for (String s : nz.getNames()) {
			if (c == 4) break;
			if (first) {
				first = false;
				n += s;
			} else {
				n += ", " + s;
			}
			c++;
		}
		
		if (n.isEmpty()) {
			di.appendInfo("Names", "None");
		} else {
			di.appendInfo("Names", "None");
		}
		
		if (op.isOnline()) {
			Player opp = (Player) op;
			di.appendSpacer();
			di.appendInfo("World", opp.getWorld().getName());
			di.appendInfo("X", Double.toString(opp.getLocation().getX()));
			di.appendInfo("Y", Double.toString(opp.getLocation().getY()));
			di.appendInfo("Z", Double.toString(opp.getLocation().getZ()));
			di.appendSpacer();
			di.appendInfo("Gamemode", opp.getGameMode().toString());
			di.appendInfo("EntitiyID", Integer.toString(opp.getEntityId()));
		}
		return di.build();
	}
	
	public static void open(Player p, OfflinePlayer op) {
		AdvInventory a = new AdvInventory(op.getName() + " > Main", 54, ItemUtils.placeholder());
		NZPlayer n = NZPlayer.getNZPlayer(op);

		a.setItem(playerinfo(op), 10);
		
		int i = 28;
		for (Rank r : Rank.values()) {
			Material m;
			if (n.hasRank(r)) {
				m = Material.GLOWSTONE_DUST;
			} else {
				m = Material.SULPHUR;
			}
			
			DescriptionItem di = new DescriptionItem(ChatColor.AQUA + r.getTitle(), new ItemStack(m));
			di.appendInfo("Level", Integer.toString(r.getLevel()));
			di.appendInfo("Title", r.getTitle());
			di.appendInfo("Prefix", r.getPrefix());
			
			a.setItem(di.build(), i, new ClickRunnable() {
				@Override
				public void run(InventoryClickEvent e) {
					String level = ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(0).replace("Level: ", ""));
					int lvl = Integer.parseInt(level);
					NZPlayer nz = NZPlayer.getNZPlayer(p);
					if (lvl == -1) {
						nz.sendTitle(10, 20, 10, "This access level hasn't been found.");
						nz.sendSubTitle(10, 20, 10, "Try again.");
					} else {
						n.setRank(lvl);
					}
				}
			});
			
			i++;
		}
		
		a.openInventory(p);
	}
}