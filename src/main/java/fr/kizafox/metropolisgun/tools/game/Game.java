package fr.kizafox.metropolisgun.tools.game;

import fr.kizafox.metropolisgun.MetropolisGun;
import fr.kizafox.metropolisgun.tools.ItemBuilder;
import fr.kizafox.metropolisgun.tools.game.weapons.guns.GunsManager;
import fr.kizafox.metropolisgun.tools.game.weapons.guns.storage.Guns;
import fr.kizafox.metropolisgun.tools.game.weapons.knifes.KnifesManager;
import fr.kizafox.metropolisgun.tools.game.weapons.utils.UtilsManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class Game {

    public static final GunsManager GUNS_MANAGER = new GunsManager();
    public static final KnifesManager KNIFES_MANAGER = new KnifesManager();

    public static final UtilsManager UTILS_MANAGER = new UtilsManager();

    public static void join(final Player player){
        player.getInventory().clear();
        player.setLevel(20);

        player.sendMessage(MetropolisGun.PREFIX + ChatColor.GREEN + "Vous avez rejoint le combat !");

        KNIFES_MANAGER.give(player, KNIFES_MANAGER.getKnifes());
        GUNS_MANAGER.give(player, GunsManager.getGuns());

        for(int i = 0; i <= 6; i++){
            UTILS_MANAGER.give(player, UTILS_MANAGER.getUtils());
        }
    }
}
