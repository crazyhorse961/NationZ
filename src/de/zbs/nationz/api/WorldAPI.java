package de.zbs.nationz.api;

import org.bukkit.block.Biome;

import de.zbs.nationz.nations.Nation;

public class WorldAPI {

	private WorldAPI() {}

	public static Biome getGenerationBiome(int x, int y) {
		return getGenerationBiome((double)x, (double)y);
	}
	
	public static Biome getGenerationBiome(double x, double y) {
		return Biome.COLD_BEACH;
//		double dist = getDistance(x, y);
//		if (dist < 200)
//			return Biome.pl;
//		else if (dist > 4096)
//			return null;
//		Nation n = getResidingNation(x, y);
//		if (n == null)
//			return Biome.BEACH;
//		return n.getHome();
	}
	
	public static Nation getResidingNation(int x, int y) {
		return getResidingNation((double)x, (double)y);
	}
	
	public static Nation getResidingNation(double x, double y) {
		double degree = getDegree(x, y);
		if (degree >= 339.5 || degree <= 20.5)
			return Nation.ATLANTISKINGS;              // N
		else if (degree >= 24.5  && degree <= 65.5)
			return Nation.DWARFS;                     // NE
		else if (degree >= 69.5 && degree <= 110.5)
			return Nation.HUMANS;                     // E
		else if (degree >= 114.5 && degree <= 155.5)
			return Nation.CYCLOPS;                    // SE
		else if (degree >= 159.5 && degree <= 200.5)
			return Nation.ELVES;                      // S
		else if (degree >= 204.5 && degree <= 245.5)
			return Nation.HALFGODS;                   // SW
		else if (degree >= 249.5 && degree <= 290.5)
			return Nation.PHARAOS;                    // W
		else if (degree >= 294.5 && degree <= 335.5)
			return Nation.MAGE;                       // NW
		return null;
	}
	
	private static double normalize(double d, double x) {
		if (x < 0) {
			return 360d - d;
		}
		return d;
	}
	
	private static double round(double d) {
		return ((double)Math.round(d * 10000000000d)) / 10000000000d;
	}
	
	private static double toDegrees(double d) {
		return d * (180 / Math.PI);
	}
		
	public static double getDegree(int x, int y) {
		return getDegree((double)x, (double)y);
	}
	
	public static double getDegree(double x, double y) {
		return normalize(round(toDegrees(Math.acos(y / getDistance(x, y)))), x);
	}
	
	public static double getDistance(double x, double y) {
		return getDistance(x, y, 0d, 0d);
	}
	
	public static double getDistance(double x1, double y1, double x2, double y2) {
		return Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2));
	}
}
