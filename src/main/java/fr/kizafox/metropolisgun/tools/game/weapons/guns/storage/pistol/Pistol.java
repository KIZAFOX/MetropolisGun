package fr.kizafox.metropolisgun.tools.game.weapons.guns.storage.pistol;

import fr.kizafox.metropolisgun.MetropolisGun;
import fr.kizafox.metropolisgun.tools.game.weapons.guns.storage.Guns;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class Pistol implements Listener {

    protected final MetropolisGun instance;

    public Pistol(final MetropolisGun instance) {
        this.instance = instance;
        this.instance.getServer().getPluginManager().registerEvents(this, this.instance);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerInteract(final PlayerInteractEvent event) {
        final Player player = event.getPlayer();
        final Guns pistol = Guns.PISTOL;

        if(player.getItemInHand().getType().equals(Material.SHEARS)){
            if(player.getLevel() == 0){
                player.getInventory().removeItem(player.getInventory().getItemInHand());
                player.playSound(player.getLocation(), Sound.BLOCK_PISTON_EXTEND, 100.0F, 100.0F);
                player.setLevel(pistol.getMaxBullet());

                for(int i = 0; i <= 100; i++){
                    player.sendMessage("");
                }

                player.sendMessage(ChatColor.RED + "          [ " + ChatColor.AQUA + pistol.getDefaultName() + ChatColor.RED + " ]");
                player.sendMessage(ChatColor.GREEN + "        Pistolet rechargé");
            }else{
                player.sendMessage(MetropolisGun.PREFIX + ChatColor.RED + "Encore " + player.getLevel() + " munition(s) !");
            }
        }

        if(player.getItemInHand().getType().equals(Guns.PISTOL.getMaterial())) {
            if (player.getLevel() == 0) {
                player.playSound(player.getLocation(), Sound.BLOCK_PISTON_CONTRACT, 100.0F, 100.0F);
                player.sendMessage(MetropolisGun.PREFIX + ChatColor.RED + "Besoin de recharger !");
            } else {
                final Snowball snowball = player.getWorld().spawn(player.getEyeLocation(), Snowball.class);

                snowball.setVelocity(player.getLocation().getDirection().multiply(1.5D));
                snowball.setShooter(player);

                player.setLevel(player.getLevel() - 1);
                player.playSound(player.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 100.0F, 100.0F);

                snowball.setVelocity(snowball.getVelocity().multiply(3));
            }
        }
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onEntityDamageByEntity(final EntityDamageEvent event){
        if(event instanceof EntityDamageByEntityEvent){
            final Entity attacker = ((EntityDamageByEntityEvent) event).getDamager();

            if (attacker instanceof Snowball) {
                event.setDamage(2.5D);
                //Bukkit.broadcastMessage(event.getDamage() + " : " + event.getEntity().getName() + " : " + ((EntityDamageByEntityEvent) event).getDamager().getName());
            }
        }
    }
}
