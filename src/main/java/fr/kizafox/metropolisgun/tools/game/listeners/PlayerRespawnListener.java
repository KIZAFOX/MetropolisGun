package fr.kizafox.metropolisgun.tools.game.listeners;

import fr.kizafox.metropolisgun.MetropolisGun;
import fr.kizafox.metropolisgun.tools.game.Game;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class PlayerRespawnListener implements Listener {

    protected final MetropolisGun instance;

    public PlayerRespawnListener(final MetropolisGun instance) {
        this.instance = instance;
        this.instance.getServer().getPluginManager().registerEvents(this, this.instance);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onRespawn(final PlayerRespawnEvent event){
        Game.join(event.getPlayer());
    }
}
