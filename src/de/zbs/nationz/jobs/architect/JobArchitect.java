package de.zbs.nationz.jobs.architect;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.jobs.Job;
import de.zbs.nationz.nations.Nation;

public class JobArchitect extends Job {
	
	private Listener ls = new Listener() {		
		@EventHandler
		public void onInteract(PlayerInteractEvent e) {
			if (e.getAction() != Action.RIGHT_CLICK_BLOCK) return;
			if (e.getClickedBlock().getType() != Material.REDSTONE_TORCH_ON) return;
			if (e.getPlayer().getEquipment().getItemInMainHand() == null) return;
			if (e.getPlayer().getEquipment().getItemInMainHand().getType() != Material.BOOK) return;
			if (!(e.getPlayer().getEquipment().getItemInMainHand().hasItemMeta())) return;
			if (!(e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().hasDisplayName())) return;
			if (!(e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().getDisplayName().startsWith("Region: "))) return;
			
			Location l = e.getClickedBlock().getLocation();
			ArmorStand as = (ArmorStand) l.getWorld().spawnEntity(l.add(0.5, 0, 0.5), EntityType.ARMOR_STAND);
			as.setGravity(false);
			as.setMarker(true);
			as.setVisible(false);
			as.setSmall(true); 
			as.setHelmet(new ItemStack(Material.LAPIS_BLOCK));
			as.setCustomNameVisible(true);
			String region = e.getPlayer().getEquipment().getItemInMainHand().getItemMeta().getDisplayName().replace("Region: ", "");
			as.setCustomName(ChatColor.RED + "" + ChatColor.BOLD + "Flare" + ChatColor.DARK_GRAY + " / " + ChatColor.GRAY + "Region: " + ChatColor.RED + region);
			
			for (Entity nearbyEntity : as.getNearbyEntities(50, 0, 50)) {
				if (nearbyEntity.getType() == EntityType.ARMOR_STAND) {
					ArmorStand nbas = (ArmorStand) nearbyEntity;
					if (nbas.getCustomName() != null) {
						if (nbas.getCustomName().startsWith(ChatColor.RED + "" + ChatColor.BOLD + "Flare" + ChatColor.DARK_GRAY + " / ")) {
							
						}
					}
				}
			}
		}
		
		@EventHandler
		public void onChange(SignChangeEvent e) {
			if (!e.getLine(0).equalsIgnoreCase("[stat]")) return;
			NZPlayer nz = NZPlayer.getNZPlayer(e.getPlayer());
			if (nz.getJob() != Job.ARCHITECT) {
				nz.sendMessage("You need to be an Architect!");
				return;
			}
			if (e.getLine(2).isEmpty() && e.getLine(3).isEmpty()) {
				nz.sendMessage("The third and fourth line need to be empty!");
				return;
			}
			Nation select = null;
			for (Nation n : Nation.getAll()) {
				if (n.getName().equalsIgnoreCase(e.getLine(1)) || n.getPrefix().equalsIgnoreCase(e.getLine(1))) {
					select = n;
					break;
				}
			}
			if (select == null) {
				nz.sendMessage("The second line needs to be the Name or Prefix of a Nation!");
				return;
			}
			Nation self = nz.getNation();
			if (self == select) {
				nz.sendMessage(select.getName() + ChatColor.RED + " is not a possible option!");
				return;
			}
			
			e.setLine(0, "");
			e.setLine(1, select.getName());
			e.setLine(2, select.getRelation(self).getName());
		}
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Architect";
	}

	@Override
	public String getPrefix() {
		return "Arc";
	}

	@Override
	public Material getIcon() {
		return Material.BRICK;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "Archcitects are able to build Houses and homes for");
		desc.add(ChatColor.GREEN + "their Nations. They are the only ones who are able");
		desc.add(ChatColor.GREEN + "to build functional houses.");
		return desc;
	}

	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		return p;
	}
	
	@Override
	public Perk[] getPerks() {
		return null;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTY0MzMwMzc3NDYsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJHXzNfTl9JX1NfWV9TIiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzU1MWM3M2VmMzlhOGRmZWRmZDJkNTY2ZTYxY2E4OGZlYTBlODJkOGE1ZjRiZmIyNDc4MTFiMTdiNzk5ZTAiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ==";
	}
}
