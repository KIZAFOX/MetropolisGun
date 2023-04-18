package fr.kizafox.metropolisgun.tools.game.weapons.knifes.storage.defaultknifes;

import fr.kizafox.metropolisgun.MetropolisGun;
import fr.kizafox.metropolisgun.tools.game.weapons.knifes.storage.Knifes;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class DefaultKnifes implements Listener {

    protected final MetropolisGun instance;

    public DefaultKnifes(final MetropolisGun instance) {
        this.instance = instance;
        this.instance.getServer().getPluginManager().registerEvents(this, this.instance);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerInteract(final PlayerInteractEvent event){
        final Player player = event.getPlayer();
        final ItemStack item = event.getItem();

        if(item == null)return;

        if(Objects.requireNonNull(item).getType().equals(Material.WOODEN_SWORD) && Objects.requireNonNull(item.getItemMeta()).getDisplayName().equalsIgnoreCase(Knifes.DEFAULT.getKnifeName())){
            Objects.requireNonNull(player.getAttribute(Attribute.GENERIC_ATTACK_DAMAGE)).setBaseValue(1);
        }
    }
}
