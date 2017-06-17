package com.patrick;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Patrick on 5/24/2017.
 */
public class WorldBlocker extends JavaPlugin implements Listener{

    public FileConfiguration plugin = getConfig();
    public String path = "WorldBlocker.World-Name";
    public String world_name = plugin.getString(path);

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(this, this);
        loadConfiguration();
    }


    public void loadConfiguration() {
        plugin.addDefault(path, "");
        plugin.options().copyDefaults(true);
        saveConfig();
    }

    @EventHandler
    public void onWorldChange(PlayerChangedWorldEvent event)
    {
        Player player = event.getPlayer();

        if(player.hasPermission("WorldBlocker.eventPlot"))
        {
            return;
        }
        if(player.getWorld().getName().equals(world_name))
        {
            Bukkit.dispatchCommand(getServer().getConsoleSender(), "spawn " + event.getPlayer().getName());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if(event.getPlayer().hasPermission("WorldBlocker.eventPlot"))
        {
            return;
        }
        if(event.getPlayer().getWorld().getName().equals(world_name))
        {
            Bukkit.dispatchCommand(getServer().getConsoleSender(), "spawn " + event.getPlayer().getName());
        }
    }
}
