package pulsepvp_.dispenserslime;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.plugin.java.JavaPlugin;

import pulsepvp_.dispenserslime.commands.MainCommand;
import pulsepvp_.dispenserslime.managers.EventsManagers;

public class DispenserSlime extends JavaPlugin {
	public static ConsoleCommandSender console = Bukkit.getConsoleSender();
	private static DispenserSlime instance;
	public static String prefix = ("" + ChatColor.GOLD + ChatColor.BOLD + "Pixelion " + ChatColor.RESET
			+ ChatColor.DARK_GRAY + "» " + ChatColor.RESET);
	public static String console_prefix = "" + ChatColor.GOLD + ChatColor.BOLD + "[" + ChatColor.RESET
			+ ChatColor.YELLOW + "DispenserSlime" + ChatColor.BOLD + ChatColor.GOLD + "]" + " ";
	public static List<Location> process_running_dispenser = new ArrayList<Location>();

	@Override
	public void onEnable() {
		instance = this;
		console.sendMessage("" + console_prefix + ChatColor.GREEN + "Loading...");
		new EventsManagers().registers();
		getCommand("pt").setExecutor(new MainCommand());
	}

	@Override
	public void onDisable() {
		this.saveConfig();
		console.sendMessage("" + console_prefix + ChatColor.RED + "Disabling...");
	}

	public static ConsoleCommandSender getConsole() {
		return console;
	}

	public static DispenserSlime getInstance() {
		return instance;
	}
}
