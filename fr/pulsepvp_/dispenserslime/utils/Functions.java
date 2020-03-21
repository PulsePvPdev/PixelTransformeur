package pulsepvp_.dispenserslime.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.BlockIterator;

import net.md_5.bungee.api.ChatColor;
import pulsepvp_.dispenserslime.DispenserSlime;

public class Functions {
	public static final Block getTargetedBlock(Player player, Integer range) {
		BlockIterator bi = new BlockIterator(player, range);
		Block lastBlock = bi.next();
		while (bi.hasNext()) {
			lastBlock = bi.next();
			if (lastBlock.getType() == Material.AIR)
				continue;
			break;
		}
		return lastBlock;
	}

	public static final int getDelay(Player player) {
		int delay = 20;
		if (player.hasPermission("pixeltransformeur.17"))
			delay = 17;
		if (player.hasPermission("pixeltransformeur.15"))
			delay = 15;
		if (player.hasPermission("pixeltransformeur.10"))
			delay = 10;
		if (player.hasPermission("pixeltransformeur.5"))
			delay = 5;
		if (player.hasPermission("pixeltransformeur.3"))
			delay = 3;
		if (player.hasPermission("pixeltransformeur.1"))
			delay = 1;
		return delay;

	}

	public static final boolean matchPermission(Player player, String perm) {
		for (PermissionAttachmentInfo permission : player.getEffectivePermissions()) {
			if (permission.getPermission().startsWith(perm) && permission.getValue())
				return true;
		}

		return false;
	}

	public static final boolean dispenserHasItems(Dispenser block) {
		Inventory inv = block.getSnapshotInventory();
		int magma = 0;
		int green_dye = 0;
		for (ItemStack item : inv) {
			if (item != null) {
				Material material = item.getType();
				if (material.equals(Material.MAGMA_CREAM))
					magma += item.getAmount();
				if (material.equals(Material.GREEN_DYE))
					green_dye += item.getAmount();
				if (!material.equals(Material.GREEN_DYE) && !material.equals(Material.MAGMA_CREAM)) {
					return false;
				}
			}
		}
		return (magma == 1 && green_dye == 1) ? true : false;
	}

	public static void runProcess(Block block, Player player) {
		int delay = getDelay(player);
		Bukkit.broadcastMessage("delay:" + delay);
		Location loc = block.getLocation();
		Dispenser dispenser = (Dispenser) block.getState();
		player.sendMessage("" + DispenserSlime.prefix + ChatColor.GREEN + "Transformation en cours...");
		new BukkitRunnable() {

			int count = 0;

			@Override
			public void run() {
				if (count >= delay * 2) {
					player.sendMessage(
							"" + DispenserSlime.prefix + ChatColor.GREEN + "Transformation effectuée avec succés !");
					dispenser.getInventory().clear();
					dispenser.getInventory().addItem(new ItemStack(Material.SLIME_BALL));
					cancel();
				}
				block.getWorld().spawnParticle(Particle.SMOKE_LARGE, loc, 12);
				count++;
			}

		}.runTaskTimer(DispenserSlime.getInstance(), 0L, 10L);

	}

	public static void spawnParticle() {

	}
}
