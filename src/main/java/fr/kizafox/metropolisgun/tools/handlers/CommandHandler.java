package fr.kizafox.metropolisgun.tools.handlers;

import fr.kizafox.metropolisgun.MetropolisGun;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.*;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;


/**
 * Change this line to a short description of the class
 *
 * @author : KIZAFOX
 * @date : 18/04/2023
 * @project : MetropolisGun
 */
public abstract class CommandHandler implements CommandExecutor{

    protected String command, permission, usage;
    protected List<String> aliases;

    public static final HashSet<String> registeredCommands = new HashSet<>();
    protected static final MetropolisGun instance = MetropolisGun.get();

    public CommandHandler(String command) {
        this(command, null, null, null);
    }

    public CommandHandler(String command, String permission, String usage, List<String> aliases) {
        this.command = command;
        this.permission = permission;
        this.usage = usage;
        this.aliases = aliases;
    }

    public void register() {
        CommandHandler.ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.aliases != null) {
            cmd.setAliases(this.aliases);
        }

        if (this.usage != null) {
            cmd.setUsage(this.usage);
        }

        if (this.permission != null) {
            cmd.setPermission(this.permission);
        }

        Objects.requireNonNull(getCommandMap()).register(cmd.getName(), cmd);
        registeredCommands.add(cmd.getName());
        cmd.setExecutor(this);
    }

    public static CommandMap getCommandMap() {
        try {
            Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
            f.setAccessible(true);
            return (CommandMap) f.get(Bukkit.getServer());
        } catch (Exception var2) {
            var2.printStackTrace();
            return null;
        }
    }

    public abstract void perform(CommandSender sender, Command cmd, String label, String[] args);

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (this.permission != null && !Objects.requireNonNull(getCommandMap()).getCommand(this.command).testPermissionSilent(sender)) sender.sendMessage(MetropolisGun.PREFIX + ChatColor.RED + "Vous n'avez pas la permission d'éxécuter cette commande!");
        else this.perform(sender, cmd, label, args);
        return false;
    }

    public void sendUsage(CommandSender sender) {
        if (this.usage != null) sender.sendMessage(ChatColor.RED + this.usage);
        else sender.sendMessage(ChatColor.RED + "&cUsage not set.");
    }

    private static Object getPrivateField(Object object, String field) throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        Field objectField = clazz.getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }
    private static void unRegisterBukkitCommand(Command cmd) {
        try {
            Object result = getPrivateField(Bukkit.getServer().getPluginManager(), "commandMap");
            SimpleCommandMap commandMap = (SimpleCommandMap) result;
            Object map = getPrivateField(commandMap, "knownCommands");
            @SuppressWarnings("unchecked")
            HashMap<String, Command> knownCommands = (HashMap<String, Command>) map;
            knownCommands.remove(cmd.getName());
            for (String alias : cmd.getAliases()){
                knownCommands.remove(alias);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void unregisterCommands() {
        for (String s : registeredCommands) {
            unRegisterBukkitCommand(Objects.requireNonNull(getCommandMap()).getCommand(s));
        }
    }

    private static final class ReflectCommand extends Command {
        private CommandHandler exe = null;

        private ReflectCommand(String command) {
            super(command);
        }

        public void setExecutor(CommandHandler exe) {
            this.exe = exe;
        }

        public boolean execute(CommandSender sender, String commandLabel, String[] args) {
            return this.exe != null && !this.exe.onCommand(sender, this, commandLabel, args);
        }
    }
}
