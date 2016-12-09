package de.zbs.nationz.jobs.architect;

import java.util.List;

public class Region {
	
	private double corner1_x;
	private double corner1_z;
	private double corner2_x;
	private double corner2_z;
	private String access; //UUID, UUID, .../public/inherit
	private RegionType type;
	private List<RegionAction> flags;
	
	private Region(double corner1_x, double corner1_z, double corner2_x, double corner2_z, String access, RegionType type) {
		this.corner1_x = corner1_x;
		this.corner1_z = corner1_z;
		this.corner2_x = corner2_x;
		this.corner2_z = corner2_z;
		this.access = access;
		this.type = type;
	}
	
	public void newRegion(double corner1_x, double corner1_z, double corner2_x, double corner2_z, String access, RegionType type) {
		this.corner1_x = corner1_x;
		this.corner1_z = corner1_z;
		this.corner2_x = corner2_x;
		this.corner2_z = corner2_z;
		this.access = access;
		this.type = type;
	}
	
	public Region getRegion(double x, double z) {
		return null;
	}

	public double getCorner1_x() {
		return corner1_x;
	}

	public void setCorner1_x(double corner1_x) {
		this.corner1_x = corner1_x;
	}

	public double getCorner1_z() {
		return corner1_z;
	}

	public void setCorner1_z(double corner1_z) {
		this.corner1_z = corner1_z;
	}

	public double getCorner2_x() {
		return corner2_x;
	}

	public void setCorner2_x(double corner2_x) {
		this.corner2_x = corner2_x;
	}

	public double getCorner2_z() {
		return corner2_z;
	}

	public void setCorner2_z(double corner2_z) {
		this.corner2_z = corner2_z;
	}

	public String getAccess() {
		return access;
	}

	public void setAccess(String access) {
		this.access = access;
	}

	public RegionType getType() {
		return type;
	}

	public void setType(RegionType type) {
		this.type = type;
	}

	public List<RegionAction> getFlags() {
		return flags;
	}

	public void addFlag(RegionAction flag) {
		flags.add(flag);
	}

	public void removeFlag(RegionAction flag) {
		flags.remove(flag);
	}
	
	public void setFlags(List<RegionAction> flags) {
		this.flags = flags;
	}
}