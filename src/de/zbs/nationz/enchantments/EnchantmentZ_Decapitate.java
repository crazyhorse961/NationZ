package de.zbs.nationz.enchantments;

import java.lang.reflect.Field;
import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Skeleton.SkeletonType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.comphenix.protocol.wrappers.WrappedGameProfile;
import com.comphenix.protocol.wrappers.WrappedSignedProperty;

import de.zbs.nationz.api.Item;

public class EnchantmentZ_Decapitate extends EnchantmentZ {

	private Listener ls = new Listener() {
		@EventHandler
		public void onKill(EntityDeathEvent e) {
			EntityDamageEvent ede = e.getEntity().getLastDamageCause();
			if (ede instanceof EntityDamageByEntityEvent) {
				EntityDamageByEntityEvent edbee = (EntityDamageByEntityEvent) ede;
				if (edbee.getDamager() instanceof Player) {
					Player p = (Player) edbee.getDamager();
					if (getItemTarget().contains(p.getEquipment().getItemInMainHand().getType())) {
						Item es = Item.get(p.getEquipment().getItemInMainHand());
						if (es.hasEnchantment(DECAPITATE)) {
							int r = (int) Math.random() * 100;
							switch (es.getEnchantmentLevel(DECAPITATE)) {
								case 1:
									if (r <= 33) {
										dropHead(e.getEntity());
									}
									break;
								case 2:
									if (r <= 6) {
										dropHead(e.getEntity());
									}
									break;
								case 3:
									dropHead(e.getEntity());
									break;
							}
						}
					}
				}
			}
		}
	};
	
	private void dropHead(Entity ent) {
		ItemStack head = null;
		if (ent instanceof Player) {
			Player p = (Player) ent;
			head = new ItemStack(Material.SKULL, 1, (short) 3);
			SkullMeta sm = (SkullMeta) head.getItemMeta();
			sm.setOwner(p.getName());
		} else {
			String texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2FhYjI3Mjg0MGQ3OTBjMmVkMmJlNWM4NjAyODlmOTVkODhlMzE2YjY1YzQ1NmZmNmEzNTE4MGQyZTViZmY2In19fQ==";
			switch (ent.getType()) {
				case BLAZE:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjc4ZWYyZTRjZjJjNDFhMmQxNGJmZGU5Y2FmZjEwMjE5ZjViMWJmNWIzNWE0OWViNTFjNjQ2Nzg4MmNiNWYwIn19fQ==";
					break;
				case CAVE_SPIDER:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDE2NDVkZmQ3N2QwOTkyMzEwN2IzNDk2ZTk0ZWViNWMzMDMyOWY5N2VmYzk2ZWQ3NmUyMjZlOTgyMjQifX19";
					break;
				case CHICKEN:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTYzODQ2OWE1OTljZWVmNzIwNzUzNzYwMzI0OGE5YWIxMWZmNTkxZmQzNzhiZWE0NzM1YjM0NmE3ZmFlODkzIn19fQ==";
					break;
				case COW:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWQ2YzZlZGE5NDJmN2Y1ZjcxYzMxNjFjNzMwNmY0YWVkMzA3ZDgyODk1ZjlkMmIwN2FiNDUyNTcxOGVkYzUifX19";
					break;
				case CREEPER:
					texture = "invalid_creeper";
					break;
				case ENDER_DRAGON:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzRlY2MwNDA3ODVlNTQ2NjNlODU1ZWYwNDg2ZGE3MjE1NGQ2OWJiNGI3NDI0YjczODFjY2Y5NWIwOTVhIn19fQ==";
					break;
				case ENDERMAN:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvN2E1OWJiMGE3YTMyOTY1YjNkOTBkOGVhZmE4OTlkMTgzNWY0MjQ1MDllYWRkNGU2YjcwOWFkYTUwYjljZiJ9fX0=";
					break;
				case GHAST:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOGI2YTcyMTM4ZDY5ZmJiZDJmZWEzZmEyNTFjYWJkODcxNTJlNGYxYzk3ZTVmOTg2YmY2ODU1NzFkYjNjYzAifX19";
					break;
				case GUARDIAN:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTMyYzI0NTI0YzgyYWIzYjNlNTdjMjA1MmM1MzNmMTNkZDhjMGJlYjhiZGQwNjM2OWJiMjU1NGRhODZjMTIzIn19fQ==";
					break;
				case HORSE:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjE5MDI4OTgzMDg3MzBjNDc0NzI5OWNiNWE1ZGE5YzI1ODM4YjFkMDU5ZmU0NmZjMzY4OTZmZWU2NjI3MjkifX19";
					break;
				case IRON_GOLEM:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODkwOTFkNzllYTBmNTllZjdlZjk0ZDdiYmE2ZTVmMTdmMmY3ZDQ1NzJjNDRmOTBmNzZjNDgxOWE3MTQifX19";
					break;
				case MAGMA_CUBE:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzg5NTdkNTAyM2M5MzdjNGM0MWFhMjQxMmQ0MzQxMGJkYTIzY2Y3OWE5ZjZhYjM2Yjc2ZmVmMmQ3YzQyOSJ9fX0=";
					break;
				case MUSHROOM_COW:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDBiYzYxYjk3NTdhN2I4M2UwM2NkMjUwN2EyMTU3OTEzYzJjZjAxNmU3YzA5NmE0ZDZjZjFmZTFiOGRiIn19fQ==";
					break;
				case OCELOT:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTY1N2NkNWMyOTg5ZmY5NzU3MGZlYzRkZGNkYzY5MjZhNjhhMzM5MzI1MGMxYmUxZjBiMTE0YTFkYjEifX19";
					break;
				case PIG:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjIxNjY4ZWY3Y2I3OWRkOWMyMmNlM2QxZjNmNGNiNmUyNTU5ODkzYjZkZjRhNDY5NTE0ZTY2N2MxNmFhNCJ9fX0=";
					break;
				case PIG_ZOMBIE:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzRlOWM2ZTk4NTgyZmZkOGZmOGZlYjMzMjJjZDE4NDljNDNmYjE2YjE1OGFiYjExY2E3YjQyZWRhNzc0M2ViIn19fQ==";
					break;
				case RABBIT:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGM3YTMxN2VjNWMxZWQ3Nzg4Zjg5ZTdmMWE2YWYzZDJlZWI5MmQxZTk4NzljMDUzNDNjNTdmOWQ4NjNkZTEzMCJ9fX0=";
					break;
				case SHEEP:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZjMxZjljY2M2YjNlMzJlY2YxM2I4YTExYWMyOWNkMzNkMThjOTVmYzczZGI4YTY2YzVkNjU3Y2NiOGJlNzAifX19";
					break;
				case SKELETON:
					Skeleton sk = (Skeleton) ent;
					if (sk.getSkeletonType() == SkeletonType.NORMAL) {
						texture = "invalid_skeleton";
					} else {
						texture = "invalid_witherskeleton";
					}
					break;
				case SLIME:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTZhZDIwZmMyZDU3OWJlMjUwZDNkYjY1OWM4MzJkYTJiNDc4YTczYTY5OGI3ZWExMGQxOGM5MTYyZTRkOWI1In19fQ==";
					break;
				case SNOWMAN:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWZkZmQxZjc1MzhjMDQwMjU4YmU3YTkxNDQ2ZGE4OWVkODQ1Y2M1ZWY3MjhlYjVlNjkwNTQzMzc4ZmNmNCJ9fX0=";
					break;
				case SPIDER:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2Q1NDE1NDFkYWFmZjUwODk2Y2QyNThiZGJkZDRjZjgwYzNiYTgxNjczNTcyNjA3OGJmZTM5MzkyN2U1N2YxIn19fQ==";
					break;
				case VILLAGER:
					int i = Math.round((float) Math.random())* 10;
					if (i > 5) {
						texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvODIyZDhlNzUxYzhmMmZkNGM4OTQyYzQ0YmRiMmY1Y2E0ZDhhZThlNTc1ZWQzZWIzNGMxOGE4NmU5M2IifX19";
					} else {
						texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzdlNTc1Y2ZmMTEwMTRhNWFjYWRmMzNlZTQ4ODU2OGNjODllNDMxOTM1MTFjYTc0ZWZjODNlZWJiNzZmNCJ9fX0=";
					}
					break;
				case WITCH:
					texture = "eyJ0aW1lc3RhbXAiOjE0NTg5ODExNTg4MzYsInByb2ZpbGVJZCI6IjI0MDNhYzE5Yjg1NDQwZDRhNDA3ZGZlNDE2MzUzNDgxIiwicHJvZmlsZU5hbWUiOiJzY3JhZmJyb3RoZXJzNCIsInRleHR1cmVzIjp7IlNLSU4iOnsidXJsIjoiaHR0cDovL3RleHR1cmVzLm1pbmVjcmFmdC5uZXQvdGV4dHVyZS9hZWM1OGI1ZWZkMmJiNzI4MTNhNGVhYzBkNjZhODIxNzJmZDQ2NjRhMTQzM2RiNTE5NTkzNGI3NjRiMzk3In19fQ==";
					break;
				case WITHER:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2RmNzRlMzIzZWQ0MTQzNjk2NWY1YzU3ZGRmMjgxNWQ1MzMyZmU5OTllNjhmYmI5ZDZjZjVjOGJkNDEzOWYifX19";
					break;
				case WOLF:
					texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZTk1Y2JiNGY3NWVhODc2MTdmMmY3MTNjNmQ0OWRhYzMyMDliYTFiZDRiOTM2OTY1NGIxNDU5ZWExNTMxNyJ9fX0=";
					break;
				case ZOMBIE:
					texture = "invalid_zombie";
					break;
				default:
					break;
			}

	        if (texture.startsWith("invalid_")) {
	        	if (texture.replace("invalid_", "").equalsIgnoreCase("skeleton")) {
			        head = new ItemStack(Material.SKULL_ITEM, 1, (short) 0);
	        	} else if (texture.replace("invalid_", "").equalsIgnoreCase("witherskeleton")) {
			        head = new ItemStack(Material.SKULL_ITEM, 1, (short) 1);
	        	} else if (texture.replace("invalid_", "").equalsIgnoreCase("zombie")) {
			        head = new ItemStack(Material.SKULL_ITEM, 1, (short) 2);
	        	} else if (texture.replace("invalid_", "").equalsIgnoreCase("creeper")) {
			        head = new ItemStack(Material.SKULL_ITEM, 1, (short) 4);
	        	}
	        } else {
	        	WrappedGameProfile prof = new WrappedGameProfile(UUID.randomUUID(), null); // XXX: Test
	        	prof.getProperties().clear();
	        	prof.getProperties().put("textures", new WrappedSignedProperty("texture", texture, null));
	        	
		        head = new ItemStack(Material.SKULL_ITEM, 1, (short) 3);
		        ItemMeta headMeta = head.getItemMeta();
		        
		        try {
		            Field profileField = headMeta.getClass().getDeclaredField("profile");
		            profileField.setAccessible(true);
		            profileField.set(headMeta, prof);
		        } catch (NoSuchFieldException | SecurityException e) {
		           	e.printStackTrace();
		        } catch (IllegalArgumentException | IllegalAccessException e) {
		        	e.printStackTrace();
		        }
		        head.setItemMeta(headMeta);
	        }
		}
		ent.getLocation().getWorld().dropItemNaturally(ent.getLocation(), head);
	}

	@Override
	public boolean conflictsWith(EnchantmentZ arg0) {
		return false;
	}

	@Override
	public List<Material> getItemTarget() {
		return new ItemTarget().AXE;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public String getName() {
		return "Decapitate";
	}

	@Override
	public Material getIcon() {
		return Material.SKULL;
	}

	@Override
	public Listener getListener() {
		return ls;
	}

	@Override
	public Enchantment vanillaEnchant() {
		return null;
	}

	@Override
	public ItemStack[] craftingIngredients() {
		return new ItemStack[] {
				new ItemStack(Material.SKULL_ITEM, 8),
				new ItemStack(Material.ROTTEN_FLESH, 64),
				new ItemStack(Material.BONE, 64)
		};
	}
}