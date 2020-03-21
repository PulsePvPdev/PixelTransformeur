package pulsepvp_.dispenserslime.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.md_5.bungee.api.ChatColor;
import pulsepvp_.dispenserslime.DispenserSlime;

public class BlockBreak implements Listener {
	@EventHandler
	public void onBreak(BlockBreakEvent event) {
		if (DispenserSlime.process_running_dispenser.contains(event.getBlock().getLocation())) {
			event.setCancelled(true);
			event.getPlayer().sendMessage("" + DispenserSlime.prefix + ChatColor.RED
					+ "Ce dispenser est entrain d'effectuer une transformation !");
		}

	}
}
