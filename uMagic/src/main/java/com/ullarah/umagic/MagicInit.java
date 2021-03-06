package com.ullarah.umagic;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public class MagicInit extends JavaPlugin {

    private static WorldGuardPlugin worldGuard;

    public static WorldGuardPlugin getWorldGuard() {
        return worldGuard;
    }
    private static void setWorldGuard(WorldGuardPlugin worldGuard) {
        MagicInit.worldGuard = worldGuard;
    }

    public void onEnable() {

        PluginManager pluginManager = getServer().getPluginManager();
        Plugin pluginWorldGuard = pluginManager.getPlugin("WorldGuard");

        if (pluginWorldGuard != null) {

            setWorldGuard((WorldGuardPlugin) pluginWorldGuard);

            getServer().addRecipe(new MagicRecipe().hoeRecipe());
            getServer().getPluginManager().registerEvents(new MagicEvents(), this);

        } else {

            Bukkit.getLogger().log(Level.SEVERE, "WorldGuard plugin not found. Disabling uMagic.");
            pluginManager.disablePlugin(this);

        }

    }

    public void onDisable() {


    }

}
