package pulsepvp_.dispenserslime.managers;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.PluginManager;

import pulsepvp_.dispenserslime.DispenserSlime;
import pulsepvp_.dispenserslime.listeners.BlockBreak;

public class EventsManagers implements Listener {
	public void registers() {

		PluginManager pm = Bukkit.getPluginManager();
		pm.registerEvents(new BlockBreak(), DispenserSlime.getInstance());
	}
}
