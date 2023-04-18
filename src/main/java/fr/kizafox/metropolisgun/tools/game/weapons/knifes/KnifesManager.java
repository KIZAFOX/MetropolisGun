package fr.kizafox.metropolisgun.tools.game.weapons.knifes;

import fr.kizafox.metropolisgun.tools.ItemBuilder;
import fr.kizafox.metropolisgun.tools.game.weapons.knifes.storage.Knifes;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class KnifesManager {

    public void give(final Player player, final Knifes knife){
        player.getInventory().addItem(new ItemBuilder(knife.getMaterial()).setName(knife.getKnifeName()).setLore(String.valueOf(knife.getKnifeDescription())).toItemStack());
    }

    public Knifes getKnifes(){
        return Arrays.stream(Knifes.values()).findFirst().orElse(null);
    }
}
