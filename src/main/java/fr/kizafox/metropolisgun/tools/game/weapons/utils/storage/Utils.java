package fr.kizafox.metropolisgun.tools.game.weapons.utils.storage;

import fr.kizafox.metropolisgun.tools.game.weapons.guns.storage.Guns;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum Utils {

    PISTOL_AMMUNITION(Material.SHEARS, "Munitions de " + Guns.PISTOL.getDefaultName(), ChatColor.GRAY + "Munitions de " + Guns.PISTOL.getDefaultName(), Arrays.asList("", ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Quick description of the gun."), 20);

    private final Material material;
    private final String defaultName, utilName;
    private final List<String> utilDescription;
    private final int ammunitionCount;

    Utils(Material material, String defaultName, String utilName, List<String> utilDescription, int ammunitionCount) {
        this.material = material;
        this.defaultName = defaultName;
        this.utilName = utilName;
        this.utilDescription = utilDescription;
        this.ammunitionCount = ammunitionCount;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getUtilName() {
        return utilName;
    }

    public List<String> getUtilDescription() {
        return utilDescription;
    }

    public int getAmmunitionCount() {
        return ammunitionCount;
    }
}
