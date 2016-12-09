package de.zbs.nationz.api;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.StructureModifier;

import de.zbs.nationz.ability.Ability;
import de.zbs.nationz.enchantments.EnchantmentZ;
import de.zbs.nationz.main.NationZ;

public class Item {
	
	public enum Quality {
		NORMAL(ChatColor.GRAY, "Normal"),
		COMMON(ChatColor.GREEN, "Common"),
		UNIQUE(ChatColor.YELLOW, "Unique"),
		RARE(ChatColor.DARK_PURPLE, "Rare"),
		EPIC(ChatColor.RED, "Epic"),
		LEGENDARY(ChatColor.GOLD, "Legendary");
		
		private ChatColor color;
		private String name;
		
		Quality(ChatColor c, String n) {
			this.color = c;
			this.name = n;
		}
		
		public String getName() {
			return this.color + this.name;
		}
		
		public ChatColor getColor() {
			return color;
		}
	}
	
	private String name;
	private ItemStack base;
	private List<String> lore;
	private HashMap<EnchantmentZ, Integer> enchantments;
	private Quality quality;
	private int durability;
	private int maxDurability;
	private Ability ability;
	private boolean glow;
	private int textureInt;
		
	public Item() {}
	
	public Item(String name, ItemStack base, List<String> lore, HashMap<EnchantmentZ, Integer> enchantments, Quality quality, int durability, int maxDurability, Ability ability, boolean glow) {
		this.name = name;
		this.base = base;
		this.lore = lore;
		this.enchantments = enchantments;
		this.quality = quality;
		this.durability = durability;
		this.maxDurability = maxDurability;
		this.ability = ability;
		this.glow = glow;
	}

	public static Item get(ItemStack item) {
		if (!(item.hasItemMeta())) return null;
		ItemMeta meta = item.getItemMeta();
		if (!(meta.hasLore())) return null;
	
		String name = null;
		List<String> lore = new ArrayList<String>();
		HashMap<EnchantmentZ, Integer> enchs = new HashMap<EnchantmentZ, Integer>();
		Quality quality = null;
		int durability = 0;
		int maxDurability = 0;
		Ability ability = null;
		boolean glow = false;
		
		for (String l : meta.getLore()) {
			if (l.startsWith("H: ")) {
				l.replace("H: ", "");
				if (l.startsWith("Name: ")) {
					l.replace("Name: ", "");
				} else if (l.startsWith("Lore: ")) {
					lore.add(l.replace("Lore: ", ""));
				} else if (l.startsWith("Ench: ")) {
					EnchantmentZ e;
					l.replace("Ench: ", "");
					String ench = l.split("::")[0];
					Integer level = Integer.parseInt(l.split("::")[1]);
					e = EnchantmentZ.getEnchantment(ench);
					enchs.put(e, level);
				} else if (l.startsWith("Qual: ")) {
					String s = l.replace("Qual: ", "");
					if (s.equalsIgnoreCase("COMMON")) {
						quality = Quality.COMMON;
					} else if (s.equalsIgnoreCase("UNIQUE")) {
						quality = Quality.UNIQUE;
					} else if (s.equalsIgnoreCase("RARE")) {
						quality = Quality.RARE;
					} else if (s.equalsIgnoreCase("EPIC")) {
						quality = Quality.EPIC;
					} else if (s.equalsIgnoreCase("LEGENDARY")) {
						quality = Quality.LEGENDARY;
					} else {
						quality = Quality.NORMAL;
					}
				} else if (l.startsWith("Dura: ")) {
					durability = Integer.parseInt(l.replace("Dura:", ""));
				} else if (l.startsWith("MaxD: ")) {
					maxDurability = Integer.parseInt(l.replace("MaxD:", ""));
				} else if (l.startsWith("Abil: ")) {
					ability = Ability.getAbility(l.replace("Abil: ", ""));
				} else if (l.startsWith("Glow: ")) {
					glow = Boolean.parseBoolean(l.replace("Glow: ", ""));
				}
			}
		}
		
		
		return new Item(
				name,
				item,
				lore,
				enchs,
				quality,
				durability,
				maxDurability,
				ability,
				glow
				);
	}
	
	public ItemStack build() {
		ItemStack is = base;
		is.setDurability((short) this.textureInt);
		ItemMeta im = base.getItemMeta();
		im.setDisplayName(this.quality.getColor() + this.name);
		im.spigot().setUnbreakable(true);
		List<String> lore = new ArrayList<String>();
		lore.add(this.quality.getColor() + this.getQuality().toString());
		lore.add(ChatColor.BLUE + "Durability: " + this.durability + ChatColor.DARK_GRAY + "/" + ChatColor.BLUE + this.maxDurability);
		
		if (this.enchantments != null) {
			lore.add(" ");
			for (EnchantmentZ ez : this.enchantments.keySet()) {
				lore.add(ChatColor.GRAY + ez.getName() + " " + ez.IntegerToRomanNumeral(this.enchantments.get(ez)));
				if (ez.vanillaEnchant() != null) {
					im.addEnchant(ez.vanillaEnchant(), this.enchantments.get(ez), true);
				}
			}
		}
		
		if (this.lore != null) {
			lore.add(" ");
			for (String s : lore) {
				lore.add(ChatColor.WHITE + s);
			}
		}
		
		if (ability != null) {
			lore.add(" ");
			lore.add(ChatColor.RED + "Ability: " + ability.getName());
			for (int i = 0; i < ability.getDescription().length; i++) {
				lore.add(ChatColor.RED + "      " + ability.getDescription()[i]);
			}
		}
		
		if (glow != false) {
			if (base.getType() == Material.BOW) {
				im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
			} else {
				im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
			}
		}

		lore.add("H: Name: " + this.name);
		for (String s : this.lore) {
			lore.add("H: Lore: " + s);
		}
		for (EnchantmentZ s : this.enchantments.keySet()) {
			lore.add("H: Ench: " + s.getName() + "::" + this.enchantments.get(s));
		}
		lore.add("H: Qual: " + this.quality.getName());
		lore.add("H: Dura: " + this.durability);
		lore.add("H: MaxD: " + this.maxDurability);
		lore.add("H: Abil: " + this.ability.toString());
		lore.add("H: Glow: " + this.glow);
		
		im.addItemFlags(
				ItemFlag.HIDE_ATTRIBUTES,
				ItemFlag.HIDE_DESTROYS,
				ItemFlag.HIDE_ENCHANTS,
				ItemFlag.HIDE_PLACED_ON,
				ItemFlag.HIDE_POTION_EFFECTS,
				ItemFlag.HIDE_UNBREAKABLE);
		
		im.setLore(lore);
		
		is.setItemMeta(im);
		
		NationZ.protocolManager.addPacketListener(new PacketAdapter(NationZ.plugin, PacketType.Play.Server.SET_SLOT) {
			@Override
			public void onPacketSending(PacketEvent event) {
				PacketContainer packet = event.getPacket().deepClone();
				StructureModifier<ItemStack> sm = packet.getItemModifier();
				for (int j = 0; j < sm.size(); j++) {
					if (sm.getValues().get(j) != null) {
						ItemStack item = sm.getValues().get(j);
						ItemMeta itemMeta = item.getItemMeta();
						if (itemMeta.hasLore()) {
							List<String> lore = itemMeta.getLore();
							for (String s : lore) {
								if (s.startsWith("H:")) {
									lore.remove(s);
								}
							}
							itemMeta.setLore(lore);
							item.setItemMeta(itemMeta);
						}
					}
				}
				event.setPacket(packet);
			}
		});
		
		NationZ.protocolManager.addPacketListener(new PacketAdapter(NationZ.plugin, ListenerPriority.NORMAL, PacketType.Play.Server.WINDOW_ITEMS) {
			@Override
			public void onPacketSending(PacketEvent event) {
    	    	PacketContainer packet = event.getPacket().deepClone();
    	    	StructureModifier<ItemStack[]> sm = packet.getItemArrayModifier();
    	    	for (int j = 0; j < sm.size(); j++) {
    	    		for (int i = 0; i < sm.getValues().size(); i++) {
    	    			if (sm.getValues().get(j) != null) {
    	    				ItemStack item = sm.getValues().get(j)[0];
    	    				ItemMeta itemMeta = item.getItemMeta();
    	    				if (itemMeta.hasLore()) {
    	    					List<String> lore = itemMeta.getLore();
    	    					for (String s : lore) {
    	    						if (s.startsWith("H:")) {
    	    							lore.remove(s);
    	    						}
    	    					}
    	    					itemMeta.setLore(lore);
    	    					item.setItemMeta(itemMeta);
    	    				}
    	    			}
    	    		}
    	    	}
    	    	event.setPacket(packet);
			}
		});
		
		return is;
	}

	public String getName() {
		return name;
	}

	public ItemStack setName(String name) {
		this.name = name;
		ItemMeta im = this.base.getItemMeta();
		im.setDisplayName(this.name);
		this.base.setItemMeta(im);
		return this.base;
	}

	public ItemStack getBase() {
		return base;
	}

	public ItemStack setBase(ItemStack base) {
		this.base = base;
		return this.base;
	}

	public List<String> getLore() {
		return lore;
	}

	public ItemStack setLore(List<String> lore) {
		this.lore = lore;
		ItemMeta im = this.base.getItemMeta();
		im.setLore(lore);
		this.base.setItemMeta(im);
		return this.base;
	}

	public ItemStack addToLore(String lore) {
		this.lore.add(lore);
		ItemMeta im = this.base.getItemMeta();
		im.setLore(this.lore);
		this.base.setItemMeta(im);
		return this.base;
	}

	public ItemStack removeFromLore(String lore) {
		this.lore.remove(lore);
		ItemMeta im = this.base.getItemMeta();
		im.setLore(this.lore);
		this.base.setItemMeta(im);
		return this.base;
	}

	public HashMap<EnchantmentZ, Integer> getEnchantments() {
		return enchantments;
	}

	public ItemStack setEnchantments(HashMap<EnchantmentZ, Integer> enchantments) {
		this.enchantments = enchantments;
		return build();
	}

	public ItemStack addEnchantment(EnchantmentZ echantment, Integer power) {
		this.enchantments.put(echantment, power);
		return build();
	}

	public ItemStack removeEnchantment(EnchantmentZ echantment) {
		this.enchantments.remove(echantment);
		return build();
	}

	public boolean hasEnchantment(EnchantmentZ echantment) {
		return this.enchantments.containsKey(echantment);
	}
	
	public int getEnchantmentLevel(EnchantmentZ echantment) {
		return this.enchantments.get(echantment);
	}

	public Quality getQuality() {
		return quality;
	}

	public ItemStack setQuality(Quality quality) {
		this.quality = quality;
		ItemMeta im = this.base.getItemMeta();
		im.getLore().set(0, this.quality.getName());
		this.base.setItemMeta(im);
		return this.base;
	}
	
	public int getDurability() {
		return durability;
	}

	public ItemStack setDurability(int durability) {
		this.durability = durability;
		ItemMeta im = this.base.getItemMeta();
		this.lore.set(1, ChatColor.BLUE + "Durability: " + this.durability + ChatColor.DARK_GRAY + "/" + ChatColor.BLUE + this.maxDurability);
		im.setLore(this.lore);
		this.base.setItemMeta(im);
		return this.base;
	}
	
	public ItemStack editDurability(boolean positive, int amount) {
		if (positive) {
			this.durability = durability + amount;
		} else {
			this.durability = durability - amount;
		}
		ItemMeta im = this.base.getItemMeta();
		this.lore.set(1, ChatColor.BLUE + "Durability: " + this.durability + ChatColor.DARK_GRAY + "/" + ChatColor.BLUE + this.maxDurability);
		im.setLore(this.lore);
		this.base.setItemMeta(im);
		return this.base;
	}

	public int getMaxDurability() {
		return maxDurability;
	}

	public ItemStack setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
		ItemMeta im = this.base.getItemMeta();
		this.lore.set(1, ChatColor.BLUE + "Durability: " + this.durability + ChatColor.DARK_GRAY + "/" + ChatColor.BLUE + this.maxDurability);
		im.setLore(this.lore);
		this.base.setItemMeta(im);
		return this.base;
	}

	public Ability getAbility() {
		return ability;
	}

	public ItemStack setAbility(Ability ability) {
		this.ability = ability;
		return build();
	}

	public boolean isGlowing() {
		return glow;
	}

	public ItemStack setGlow(boolean glow) {
		this.glow = glow;
		ItemMeta im = this.base.getItemMeta();
		if (this.base.getType() == Material.BOW) {
			im.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
		} else {
			im.addEnchant(Enchantment.ARROW_DAMAGE, 1, true);
		}
		this.base.setItemMeta(im);
		return this.base;
	}

	public int getTextureInt() {
		return textureInt;
	}

	public void setTextureInt(int textureInt) {
		this.textureInt = textureInt;
	}
}