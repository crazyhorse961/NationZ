package de.zbs.nationz.permission;

public enum Rank {
	MEMBER(0, "Member", "M"),
	PREMIUM(5, "Premium", "P"),
	SUPPORTER(100, "Supporter", "S"),
	MODERATOR(250, "Moderator", "M"),
	ADMIN(500, "Admin", "A"),
	OWNER(1000, "Owner", "O"),
	DEVELOPER(2500, "Developer", "D");
	
	private final int value;
	private final String title;
	private final String prefix;
	
	private Rank(int val, String title, String prefix) {
		this.value = val;
		this.title = title;
		this.prefix = prefix;
	}
	
	public String toString() {
		return title;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getPrefix() {
		return prefix;
	}
	
	public int getLevel() {
		return value;
	}
	
	public static Rank getByLevel(int lvl) {
		for (Rank r : values()) {
			if (r.getLevel() == lvl)
				return r;
		}
		return MEMBER;
	}
}