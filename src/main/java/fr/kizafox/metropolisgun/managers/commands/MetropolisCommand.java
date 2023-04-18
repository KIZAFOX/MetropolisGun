package fr.kizafox.metropolisgun.managers.commands;

import fr.kizafox.metropolisgun.MetropolisGun;
import fr.kizafox.metropolisgun.tools.handlers.CommandHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public class MetropolisCommand extends CommandHandler {

    public MetropolisCommand(String command) {
        super(command);
        this.permission = "metropolisgun.permissions.admin";
        this.usage = "/metropolisgun";
        this.aliases = Arrays.asList("metropolis", "mg");
    }

    @Override
    public void perform(CommandSender sender, Command cmd, String label, String[] args) {
        if(!(sender instanceof Player)){
            sender.sendMessage(MetropolisGun.PREFIX + ChatColor.RED + "Seulement un joueur peut utiliser cette commande !");
            return;
        }

        final Player player = (Player) sender;

        if(args.length != 0){
            this.sendUsage(player);
            return;
        }

        final Inventory inventory = Bukkit.createInventory(null, 9);

        player.openInventory(inventory);
    }
}
