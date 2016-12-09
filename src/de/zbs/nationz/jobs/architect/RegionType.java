package de.zbs.nationz.jobs.architect;

public class RegionType {
	
	private String displayName;
	private int maxAmount;
	
	public RegionType(String displayName, int maxAmount) {
		this.displayName = displayName;
		this.maxAmount = maxAmount;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}
}