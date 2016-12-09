package de.zbs.nationz.listener;

import java.lang.reflect.InvocationTargetException;

import org.bukkit.ChatColor;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.server.PluginDisableEvent;
import org.bukkit.event.server.ServerListPingEvent;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedChatComponent;

import de.zbs.nationz.api.FC;
import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.inventory.MainMenuInventory;
import de.zbs.nationz.main.NationZ;
import de.zbs.nationz.permission.Rank;

public class ConnectionListener implements Listener {
	
	boolean notWhitelisted = false;
	
	@EventHandler
	public void onPing(ServerListPingEvent e) {
		String first = "       " + NationZ.title + ChatColor.DARK_GRAY + " - " + ChatColor.GRAY + "A unique Real-Time Clan-MMORPG";
		String second = ChatColor.DARK_GRAY + " » " + ChatColor.RED + "Next Season: " + ChatColor.YELLOW + FC.getMainCfg().getString("season.next");
		e.setMotd(first + "\n" + second);
		e.setMaxPlayers(2400);
	}
	
	@EventHandler
	public void onJoin(final PlayerJoinEvent e) {
		if (e.getPlayer().isBanned()) {
			e.getPlayer().kickPlayer(NationZ.title + 
					"\n" + NationZ.line(ChatColor.AQUA) + 
					"\n" + ChatColor.RED + " " + 
					"\n" + ChatColor.DARK_RED + "You were banned!" + 
					"\n" + ChatColor.RED + " " + 
					"\n" + ChatColor.RED + "You didn't stick to the Server-Rules and now received your punishment!" + 
					"\n" + ChatColor.RED + " " + 
					"\n" + ChatColor.RED + "There is no chance for unbanning since Players who don't stick to the rules," + 
					"\n" + ChatColor.RED + "ruin the game, the fun and the Server." + 
					"\n" + ChatColor.RED + " " + 
					"\n" + NationZ.line(ChatColor.AQUA));
			notWhitelisted = true;
			return;
		} else if (!e.getPlayer().isWhitelisted()) {
			e.getPlayer().kickPlayer(NationZ.title + 
					"\n" + NationZ.line(ChatColor.AQUA) + 
					"\n" + ChatColor.RED + " " + 
					"\n" + ChatColor.DARK_RED + "You are not whitelisted!" + 
					"\n" + ChatColor.RED + " " + 
					"\n" + ChatColor.RED + "Please buy a " + NationZ.title + ChatColor.RED + "-Pass to join the Server." + 
					"\n" + ChatColor.RED + "You can buy passes here: " + ChatColor.YELLOW + FC.getMainCfg().getString("link.pass") + 
					"\n" + ChatColor.RED + " " + 
					"\n" + ChatColor.DARK_RED + "You" + ChatColor.WHITE + "Tuber" + ChatColor.RED + " and more than 10.000 Subscribers?" + 
					"\n" + ChatColor.DARK_GRAY + "" + ChatColor.STRIKETHROUGH + "-->" + ChatColor.YELLOW + "   " + FC.getMainCfg().getString("link.pass") + 
					"\n" + ChatColor.RED + " " + 
					"\n" + NationZ.line(ChatColor.AQUA));
			notWhitelisted = true;
			return;
		}
		
        AttributeInstance instance = e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED);
        if (instance != null) {
            instance.setBaseValue(16);
        }
		
		NZPlayer nz = NZPlayer.getNZPlayer(e.getPlayer());
		if (nz.getNation() != null) {
			nz.getNation().reAddToNation(e.getPlayer());
		}
		if (nz.getJob() != null) {
			nz.getJob().reAddToJob(e.getPlayer(), nz.getJobLevel());
		}
		
		if (nz.getLastName() == null) {
			nz.addName(e.getPlayer().getName());
		} else {
			if (!(nz.getLastName().equals(e.getPlayer().getName()))) {
				nz.addName(e.getPlayer().getName());
			}
		}
		
		AsyncPlayerChatListener.checkForChat(e.getPlayer(), false);
		e.setJoinMessage(null);
		e.getPlayer().getInventory().setItem(8, MainMenuInventory.item());
		e.getPlayer().setOp(nz.hasRank(Rank.DEVELOPER));
		
		PacketContainer tablist = NationZ.protocolManager.createPacket(PacketType.Play.Server.PLAYER_LIST_HEADER_FOOTER);
		tablist.getChatComponents().write(0, WrappedChatComponent.fromText(NationZ.title + ChatColor.DARK_GRAY + " - " + ChatColor.GOLD + "A unique Hardcore-Clan-Survival Experience."));
		tablist.getChatComponents().write(1, WrappedChatComponent.fromText(nz.getJob().getDisplayname() + ChatColor.DARK_GRAY + " (" + ChatColor.DARK_GREEN + nz.getJobLevel() + ChatColor.DARK_GRAY + ") - " + nz.getNation().getDisplayname()));
		
		try {
			NationZ.protocolManager.sendServerPacket(e.getPlayer(), tablist);
		} catch (InvocationTargetException e1) {
			e1.printStackTrace();
		}
	}
	
	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		if (e.getPlayer().isOnline()) {
			e.setQuitMessage(null);
		}
	}
	
	@EventHandler
	public void onDisable(PluginDisableEvent e) {
		if (e.getPlugin() == NationZ.plugin) {
			NationZ.restart(false);
		}
	}
}