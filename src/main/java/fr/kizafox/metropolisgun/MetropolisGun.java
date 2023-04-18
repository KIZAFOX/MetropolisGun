package fr.kizafox.metropolisgun;

import fr.kizafox.metropolisgun.managers.Managers;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Logger;

public final class MetropolisGun extends JavaPlugin {

    private static MetropolisGun INSTANCE;

    public static final String PREFIX = ChatColor.GRAY + "[" + ChatColor.AQUA + "MetropolisGun" + ChatColor.GRAY + "] " + ChatColor.RESET;
    public static final Logger LOGGER = Logger.getLogger("Minecraft");

    private Managers managers;

    @Override
    public void onEnable() {
        INSTANCE = this;

        this.managers = new Managers(this);

        LOGGER.info("Plugin enabled!");
    }

    @Override
    public void onDisable() {
        LOGGER.info("Plugin disabled!");
    }

    public static MetropolisGun get() {
        return INSTANCE;
    }

    public Managers getManagers() {
        return managers;
    }
}
