package fr.kizafox.metropolisgun.tools.game.weapons.guns.storage;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum Guns {

    //Material, GunName, GunDescription, minBullet, maxBullet
    PISTOL(Material.WOODEN_HOE, "Pistolet", ChatColor.GRAY+"Pistolet", Arrays.asList("", ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Quick description of the gun."), 20, 20);

    private final Material material;
    private final String defaultName, gunName;
    private final List<String> gunDescription;
    private final int minBullet, maxBullet;

    Guns(Material material, String defaultName, String gunName, List<String> gunDescription, int minBullet, int maxBullet) {
        this.material = material;
        this.defaultName = defaultName;
        this.gunName = gunName;
        this.gunDescription = gunDescription;
        this.minBullet = minBullet;
        this.maxBullet = maxBullet;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getGunName() {
        return gunName;
    }

    public List<String> getGunDescription() {
        return gunDescription;
    }

    public int getMinBullet() {
        return minBullet;
    }

    public int getMaxBullet() {
        return maxBullet;
    }
}
