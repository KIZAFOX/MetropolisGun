package fr.kizafox.metropolisgun.tools.game.weapons.knifes.storage;

import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.List;

public enum Knifes {

    DEFAULT(Material.WOODEN_SWORD, "Couteau de combat", ChatColor.GRAY+"Couteau de combat", Arrays.asList("", ChatColor.DARK_GRAY + "" + ChatColor.ITALIC + "Quick description of the gun."));

    private final Material material;
    private final String defaultName, knifeName;
    private final List<String> knifeDescription;

    Knifes(Material material, String defaultName, String knifeName, List<String> knifeDescription) {
        this.material = material;
        this.defaultName = defaultName;
        this.knifeName = knifeName;
        this.knifeDescription = knifeDescription;
    }

    public Material getMaterial() {
        return material;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public String getKnifeName() {
        return knifeName;
    }

    public List<String> getKnifeDescription() {
        return knifeDescription;
    }
}
