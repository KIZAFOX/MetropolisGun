package fr.kizafox.metropolisgun.tools.game.listeners;

import fr.kizafox.metropolisgun.MetropolisGun;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Objects;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class PlayerDeathListener implements Listener {

    protected final MetropolisGun instance;

    public PlayerDeathListener(final MetropolisGun instance) {
        this.instance = instance;
        this.instance.getServer().getPluginManager().registerEvents(this, this.instance);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onDeath(final PlayerDeathEvent event){
        final Player player = event.getEntity().getPlayer();
        final Player killer = event.getEntity().getKiller();

        if(killer != null){
            killer.setHealth(killer.getMaxHealth());

            killer.sendMessage(MetropolisGun.PREFIX + ChatColor.GRAY + ChatColor.ITALIC + "Vie restauré...");
            event.setDeathMessage(Objects.requireNonNull(killer).getName() + " a tué " + Objects.requireNonNull(player).getName());
        }
    }

}
