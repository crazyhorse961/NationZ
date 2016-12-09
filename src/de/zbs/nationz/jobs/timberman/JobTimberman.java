package de.zbs.nationz.jobs.timberman;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.potion.PotionEffect;

import de.zbs.nationz.api.NZPlayer;
import de.zbs.nationz.jobs.Job;

public class JobTimberman extends Job {
	
	private Listener ls = new Listener() {
		@EventHandler
		public void onBreak(BlockBreakEvent e) {
			if (isAxe(e.getPlayer().getEquipment().getItemInMainHand().getType())) {
				if (NZPlayer.getNZPlayer(e.getPlayer()).getJob() == Job.TIMBERMAN) {
					e.getPlayer().getEquipment().getItemInMainHand().setDurability((short) (e.getPlayer().getEquipment().getItemInMainHand().getDurability() + 1));
					if (ChopWorker.isTree(e.getBlock())) {
						Block block = e.getBlock();
						List<Block> logsl = ChopWorker.getLogsToPop(block);
						if (logsl.size() == 0) return;
						logsl.add(0, e.getBlock());
						ChopWorker.pop(logsl, block);
						if (e.getPlayer().getEquipment().getItemInMainHand().getDurability() > e.getPlayer().getEquipment().getItemInMainHand().getType().getMaxDurability()){
							e.getPlayer().setItemOnCursor(null);
						}
					}
				}
			}
		}
	};
	
	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public String getName() {
		return "Timberman";
	}

	@Override
	public String getPrefix() {
		return "Tim";
	}

	@Override
	public Material getIcon() {
		return Material.IRON_AXE;
	}

	@Override
	public ArrayList<String> getDescription() {
		ArrayList<String> desc = new ArrayList<String>();
		desc.add(ChatColor.GREEN + "The only People who are able to chop down a whole");
		desc.add(ChatColor.GREEN + "tree at once are the Timbermans, they also automatically");
		desc.add(ChatColor.GREEN + "replant all Saplings.");
		return desc;
	}
	
	@Override
	public ArrayList<PotionEffect> getEffects() {
		ArrayList<PotionEffect> p = new ArrayList<PotionEffect>();
		return p;
	}
	
	private boolean isAxe(Material material) {
		return material == Material.WOOD_AXE ||
				material == Material.GOLD_AXE ||
				material == Material.STONE_AXE ||
				material == Material.IRON_AXE ||
				material == Material.DIAMOND_AXE;
	}
	
	@Override
	public Perk[] getPerks() {
		return null;
	}

	@Override
	public String getBase64Texture() {
		return "eyJ0aW1lc3RhbXAiOjE0NTc1OTIwMzU5NzYsInByb2ZpbGVJZCI6IjljMWRhMDUyYzk5YzRlYTI5NmY1NWRhODlkZTY5NTk2IiwicHJvZmlsZU5hbWUiOiJaZXJvX0RheV9FeHBsb2l0IiwidGV4dHVyZXMiOnsiU0tJTiI6eyJ1cmwiOiJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2YxM2UxN2Q1Mzg3ZTk3NmNlYTlkOWE2ZDIyMjFlZDc4NDYyMjY3N2FiYWQ0NzE1ZTM3YzdkMTM3YzliN2UwMzAiLCJtZXRhZGF0YSI6eyJtb2RlbCI6InNsaW0ifX19fQ==";
	}
}
