package fr.kizafox.metropolisgun.tools.game.listeners;

import fr.kizafox.metropolisgun.MetropolisGun;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 17/04/2023
 * @project : MetropolisGun
 */
public class FoodLevelChangeListener implements Listener {

    protected final MetropolisGun instance;

    public FoodLevelChangeListener(final MetropolisGun instance) {
        this.instance = instance;
        this.instance.getServer().getPluginManager().registerEvents(this, this.instance);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onFoodLevelChange(final FoodLevelChangeEvent event){
        event.setCancelled(true);
    }

}
