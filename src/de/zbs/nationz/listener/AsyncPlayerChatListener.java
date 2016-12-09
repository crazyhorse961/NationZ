package de.zbs.nationz.listener;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;

import javax.imageio.ImageIO;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import com.bobacadodl.imgmessage.ImageChar;
import com.bobacadodl.imgmessage.ImageMessage;

import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.main.NationZ;
import de.zbs.nationz.permission.Rank;
import mkremins.fanciful.FancyMessage;

public class AsyncPlayerChatListener implements Listener {
	
	static String path = "plugins/NationZ/Heads";
	
	static void checkForChat(final Player p, boolean bbbb) {
		Runnable r = new Runnable() {
			@SuppressWarnings("resource")
			@Override
			public void run() {
				if (!(new File(path, p.getUniqueId().toString() + ".png").exists())) {
					String imageUrl = "https://crafatar.com/avatars/" + p.getName() + "?helm&size=8";
					URL url = null;
					try {
						url = new URL(imageUrl);
					} catch (MalformedURLException e2) {
						failed(e2);
						return;
					}
					InputStream is = null;
					try {
						is = url.openStream();
					} catch (IOException e2) {
						failed(e2);
						return;
					}
					OutputStream os = null;
					try {
						os = new FileOutputStream(new File(path, p.getUniqueId() + ".png"));
					} catch (FileNotFoundException e2) {
						failed(e2);
						return;
					}

					byte[] b = new byte[2048];
					boolean got = false;
					int length;
					try {
						while ((length = is.read(b)) != -1) {
							got = true;
							os.write(b, 0, length);
						}
					} catch (IOException e2) {
						failed(e2);
						return;
					}
					
					try {
						is.close();
						os.close();
						if (got) {
							success();
						} else {
							failed(null);
						}
					} catch (IOException e2) {
						failed(e2);
						return;
					}
				}
			}
			
			private void success() {
				NationZ.plugin.getLogger().info("Successfully downloaded Head-Image for: " + p.getName());
			}
			
			private void failed(Exception ex) {
				NationZ.plugin.getLogger().warning("Download failed!");
				try {
					new File(path, p.getUniqueId() + ".png").delete();
				} catch (Exception e10) {}
				if (ex != null) {
					NationZ.plugin.getLogger().log(Level.SEVERE, "Exception thrown: ", ex);
				}
			}
		};
		if (bbbb) {
			r.run();
		} else {
			Thread t = new Thread(r);
			t.start();
		}
	}
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onChat(final AsyncPlayerChatEvent e) {
		e.setCancelled(true);
		NZPlayer n = NZPlayer.getNZPlayer(e.getPlayer());
		FancyMessage msg;
		ChatColor nationcolor = n.getNation().getNationChatColor();
		try {
			File file;
			if (!(new File(path, e.getPlayer().getUniqueId() + ".png").exists())) {
				checkForChat(e.getPlayer(), true);
			}
			if (new File(path, e.getPlayer().getUniqueId() + ".png").exists()) {
		        file = new File(path, e.getPlayer().getUniqueId() + ".png");
		    } else {
		    	file = new File(path, "steve.png");
		    }
			BufferedImage imageToSend = null;
			try {
				imageToSend = ImageIO.read(file);
			} catch (IOException e1) {
				NationZ.plugin.getLogger().log(Level.SEVERE, "Exception thrown: ", e1);
			}
			String[] info = new ImageMessage(imageToSend, 8, ImageChar.BLOCK.getChar()).appendText(
					NationZ.line(ChatColor.GOLD),
		        	" ",
		        	ChatColor.GRAY + "Name" + ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + e.getPlayer().getDisplayName(),
		        	" ",
		        	ChatColor.GRAY + "Nation" + ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + n.getNation().getName(),
		        	ChatColor.GRAY + "Job" + ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + ChatColor.stripColor(n.getJob().getDisplayname()) + ChatColor.RESET + "" + ChatColor.DARK_GRAY + " [" + ChatColor.GRAY + Integer.toString(n.getJobLevel()) + ChatColor.DARK_GRAY + "]",
		        	(n.isMayor() ? ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + ChatColor.BOLD + "Mayor" : " "),
		        	NationZ.line(ChatColor.GOLD)
		        	).getLines();
			msg = new FancyMessage(
					ChatColor.DARK_GRAY + "["
					+ nationcolor + n.getNation().getPrefix()
					+ ChatColor.DARK_GRAY + "/"
					+ nationcolor + n.getJob().getPrefix()
					+ ChatColor.DARK_GRAY + "/"
					+ nationcolor + Rank.getByLevel(n.getRank()).getPrefix()
					+ ChatColor.DARK_GRAY + "] "
					+ nationcolor + e.getPlayer().getDisplayName())
		        	.tooltip(info)
		        	.command("/user " + e.getPlayer().getName())
		        	.then(ChatColor.DARK_GRAY + " » ")
		        	.then(e.getMessage()).color(nationcolor);
			for (Player p : Bukkit.getOnlinePlayers()) {
				msg.send(p);
			}
		} catch (Exception ex) {
			NationZ.plugin.getLogger().info("Failed retrieving playerhead for: " + e.getPlayer().getName() + ", activating fallback.");
			String[] info = new String[8];
			info[0] = NationZ.line(ChatColor.GOLD);
		    info[1] = " ";
		    info[2] = ChatColor.GRAY + "Name" + ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + e.getPlayer().getDisplayName();
		    info[3] = " ";
		    info[4] = ChatColor.GRAY + "Nation" + ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + n.getNation().getName();
		    info[5] = ChatColor.GRAY + "Job" + ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + ChatColor.stripColor(n.getJob().getDisplayname()) + ChatColor.RESET + "" + ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + Integer.toString(n.getJobLevel()) + ChatColor.DARK_GRAY + "]";
		    info[6] = (n.isMayor() ? ChatColor.RESET + "" + ChatColor.DARK_BLUE + " » " + ChatColor.BOLD + "" + nationcolor + ChatColor.BOLD + "Mayor" : " ");
		    info[7] = NationZ.line(ChatColor.GOLD);
		    msg = new FancyMessage(
		    		ChatColor.DARK_GRAY + "["
		    + nationcolor + n.getNation().getPrefix()
			+ ChatColor.DARK_GRAY + "/"
			+ nationcolor + n.getJob().getPrefix()
			+ ChatColor.DARK_GRAY + "] "
			+ nationcolor + e.getPlayer().getDisplayName())
		    .tooltip(info)
		    .then(ChatColor.DARK_GRAY + " » ")
		    .then(e.getMessage()).color(nationcolor);
			for (Player p : Bukkit.getOnlinePlayers()) {
				msg.send(p);
			}
		}
	}
}