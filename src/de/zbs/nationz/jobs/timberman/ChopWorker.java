package de.zbs.nationz.jobs.timberman;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.scheduler.BukkitTask;

import de.zbs.nationz.main.NationZ;

public class ChopWorker {
	
	public static BlockFace[][] logfaces = {{BlockFace.UP}, 
			{BlockFace.UP, BlockFace.NORTH}, {BlockFace.UP, BlockFace.EAST}, {BlockFace.UP, BlockFace.SOUTH}, {BlockFace.UP, BlockFace.WEST},
			{BlockFace.UP, BlockFace.NORTH_EAST}, {BlockFace.UP, BlockFace.SOUTH_EAST}, {BlockFace.UP, BlockFace.NORTH_WEST}, {BlockFace.UP, BlockFace.SOUTH_WEST},
			{BlockFace.NORTH}, {BlockFace.EAST}, {BlockFace.WEST}, {BlockFace.SOUTH}, 
			{BlockFace.NORTH_EAST}, {BlockFace.SOUTH_EAST}, {BlockFace.NORTH_WEST}, {BlockFace.SOUTH_WEST}};
	public static BlockFace[] leaffaces = {BlockFace.UP, BlockFace.NORTH, BlockFace.EAST, BlockFace.WEST, BlockFace.SOUTH};
	
	public static boolean isTree(Block block){
		if (!isLog(block)) return false;
		Block nextblock = block.getRelative(BlockFace.SELF);
		int searchlimit = 50;
		List<Block> searched = new ArrayList<Block>();
		while (searchlimit > 0){
			searchlimit --;
			if (!searched.contains(nextblock)){
				searched.add(nextblock);
				boolean found = false;
				for (BlockFace[] blockfacess : logfaces){
					Block newblock = nextblock;
					for (BlockFace blockfacesss : blockfacess){
						newblock = newblock.getRelative(blockfacesss);
					}
					if (isLog(newblock)){
						nextblock = newblock;
						found = true;
						break;
					}
				}
				if (found) continue;
			}
			
			int leavescount = 0;
			for (BlockFace leafface : leaffaces){
				if (isLeaves(nextblock.getRelative(leafface))){
					leavescount ++;
				}
			}
			if (leavescount >= 2){
				return true;
			} else {
				for (BlockFace leafface : leaffaces){
					if (isLeaves(nextblock.getRelative(BlockFace.UP).getRelative(leafface))){
						leavescount ++;
					}
				}
				if (leavescount >= 4){
					return true;
				} else {
					return false;	
				}
			}
		}
		return false;
	}
	
	public static List<Block> getLogsToPop(Block block){
		Set<Block> logs = new HashSet<Block>();
		Set<Block> logsundone = new HashSet<Block>();
		logsundone.add(block);
		while (logsundone.size() > 0){
			Block log = logsundone.iterator().next();
			logsundone.remove(log);
			logs.add(log);
			
			for (BlockFace[] blockfacess : logfaces){
				Block newblock = log;
				for (BlockFace blockfacesss : blockfacess){
					newblock = newblock.getRelative(blockfacesss);
				}
				if (isLog(newblock)){
					if (!logs.contains(newblock) && !logsundone.contains(newblock)){
						logsundone.add(newblock);
					}
				}
			}
			if (logsundone.size() + logs.size() > 50){
				return new ArrayList<Block>();
			}
		}
		List<Block> logsl = new ArrayList<Block>(logs);
		return logsl;
	}
	
	public static void pop(List<Block> logsl, Block block){
		if (logsl.isEmpty()) return;
		Collections.sort(logsl, new LogSorter(block));
		ChopTask choptask = new ChopTask(logsl);
		
		long speedo = 2L;
		if (logsl.size() > 50) speedo = 1L;
		
		BukkitTask task = Bukkit.getScheduler().runTaskTimer(NationZ.plugin, choptask, 0L, speedo);
		
		choptask.feedId(task.getTaskId());
	}
	
	public static boolean isLog(Block block){
		switch (block.getType()){
		case LOG:
		case LOG_2:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isLeaves(Block block){
		switch (block.getType()){
		case LEAVES:
		case LEAVES_2:
			return true;
		default:
			return false;
		}
	}
	
	public static boolean isLeavesOrVines(Block block){
		switch (block.getType()){
		case LEAVES:
		case LEAVES_2:
		case VINE:
			return true;
		default:
			return false;
		}
	}
}