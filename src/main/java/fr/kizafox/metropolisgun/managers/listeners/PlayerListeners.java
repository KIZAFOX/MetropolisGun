package fr.kizafox.metropolisgun.managers.listeners;

import fr.kizafox.metropolisgun.MetropolisGun;
import fr.kizafox.metropolisgun.tools.game.Game;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.*;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 17/04/2023
 * @project : MetropolisGun
 */
public class PlayerListeners implements Listener {

    protected final MetropolisGun instance;

    public PlayerListeners(final MetropolisGun instance) {
        this.instance = instance;
        this.instance.getServer().getPluginManager().registerEvents(this, this.instance);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onLogin(final PlayerJoinEvent event){
        final Player player = event.getPlayer();

        event.setJoinMessage(ChatColor.GRAY + "[" + ChatColor.GREEN + "+" + ChatColor.GRAY + "] " + ChatColor.WHITE + player.getName());

        player.setResourcePack("https://download.mc-packs.net/pack/e4308b8c147712c7bea1081cb2c807e67920f70b.zip");

        player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        player.getActivePotionEffects().forEach(potionEffects -> player.removePotionEffect(potionEffects.getType()));

        player.setGameMode(GameMode.ADVENTURE);
        player.setFoodLevel(20);
        player.setWalkSpeed(0.20F);
        player.setFlySpeed(0.15F);
        player.setAllowFlight(false);
        player.setExp(0);
        player.setLevel(20);
        player.setMaxHealth(20.0D);
        player.setHealth(player.getMaxHealth());

        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        player.getInventory().clear();

        Game.join(player);
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onLogout(final PlayerQuitEvent event){
        event.setQuitMessage(ChatColor.GRAY + "[" + ChatColor.RED + "-" + ChatColor.GRAY + "] " + ChatColor.WHITE + event.getPlayer().getName());
    }

    @EventHandler (priority = EventPriority.MONITOR)
    public void onPlayerResourcePackStatus(final PlayerResourcePackStatusEvent event){
        final Player player = event.getPlayer();
        final PlayerResourcePackStatusEvent.Status status = event.getStatus();

        if(status == PlayerResourcePackStatusEvent.Status.DECLINED){
            player.sendMessage(MetropolisGun.PREFIX + ChatColor.RED + "You must enable the texture-pack!");
        }else if(status == PlayerResourcePackStatusEvent.Status.FAILED_DOWNLOAD){
            player.sendMessage(MetropolisGun.PREFIX + ChatColor.RED + "Download failed! Restarting...");

            player.setResourcePack("https://download.mc-packs.net/pack/e4308b8c147712c7bea1081cb2c807e67920f70b.zip");
        }else{
            player.sendMessage(MetropolisGun.PREFIX + ChatColor.GREEN + ChatColor.ITALIC + "Texture pack successfully loaded !");
        }
    }

}
