package pulsepvp_.dispenserslime.commands;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import pulsepvp_.dispenserslime.DispenserSlime;
import pulsepvp_.dispenserslime.utils.Functions;

public class MainCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (Functions.matchPermission(player, "pixeltransformeur")) {
			Block block = Functions.getTargetedBlock(player, 5);
			if (block.getType().equals(Material.DISPENSER)) {
				Dispenser dispenser = (Dispenser) block.getState();
				if (Functions.dispenserHasItems(dispenser)) {
					DispenserSlime.process_running_dispenser.add(block.getLocation());
					Functions.runProcess(block, player);
				} else {
					player.sendMessage("" + DispenserSlime.prefix + ChatColor.RED
							+ "Le dispenser doit contenir uniquement " + ChatColor.GRAY + "1x Crème de Magma "
							+ ChatColor.RED + "et " + ChatColor.GRAY + "1x Colorant Vert");
				}
			} else {
				player.sendMessage(
						"" + DispenserSlime.prefix + ChatColor.RED + "Le bloc visé doit être un dispenser !");
			}
		} else {
			player.sendMessage("" + DispenserSlime.prefix + ChatColor.RED + "Vous n'avez pas la permission.");
		}
		return false;
	}

}
