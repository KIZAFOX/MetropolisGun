package fr.kizafox.metropolisgun.tools.game.weapons.guns;

import fr.kizafox.metropolisgun.tools.ItemBuilder;
import fr.kizafox.metropolisgun.tools.game.weapons.guns.storage.Guns;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class GunsManager {

    public void give(final Player player, final Guns gun){
        player.getInventory().addItem(new ItemBuilder(gun.getMaterial()).setName(gun.getGunName()).setLore(String.valueOf(gun.getGunDescription())).toItemStack());
    }

    public static Guns getGuns(){
        return Arrays.stream(Guns.values()).findFirst().orElse(null);
    }
}
