package fr.kizafox.metropolisgun.managers;

import fr.kizafox.metropolisgun.MetropolisGun;
import fr.kizafox.metropolisgun.managers.commands.MetropolisCommand;
import fr.kizafox.metropolisgun.managers.listeners.PlayerListeners;
import fr.kizafox.metropolisgun.tools.game.listeners.FoodLevelChangeListener;
import fr.kizafox.metropolisgun.tools.game.listeners.PlayerDeathListener;
import fr.kizafox.metropolisgun.tools.game.listeners.PlayerRespawnListener;
import fr.kizafox.metropolisgun.tools.game.weapons.guns.storage.pistol.Pistol;
import fr.kizafox.metropolisgun.tools.game.weapons.knifes.storage.defaultknifes.DefaultKnifes;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class Managers {
    public Managers(final MetropolisGun instance){
        new PlayerListeners(instance);

        new Pistol(instance);

        new DefaultKnifes(instance);

        new FoodLevelChangeListener(instance);
        new PlayerDeathListener(instance);
        new PlayerRespawnListener(instance);

        this.registerCommands();
    }

    public void registerCommands(){
        new MetropolisCommand("metropolisgun").register();
    }
}
