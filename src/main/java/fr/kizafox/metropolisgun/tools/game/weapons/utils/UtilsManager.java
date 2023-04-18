package fr.kizafox.metropolisgun.tools.game.weapons.utils;

import fr.kizafox.metropolisgun.tools.ItemBuilder;
import fr.kizafox.metropolisgun.tools.game.weapons.utils.storage.Utils;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class UtilsManager {

    public void give(final Player player, final Utils utils){
        player.getInventory().addItem(new ItemBuilder(utils.getMaterial()).setName(utils.getUtilName()).setLore(String.valueOf(utils.getUtilDescription())).toItemStack());
    }

    public Utils getUtils(){
        return Arrays.stream(Utils.values()).findFirst().orElse(null);
    }
}
