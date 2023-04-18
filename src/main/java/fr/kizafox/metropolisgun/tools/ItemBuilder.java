package fr.kizafox.metropolisgun.tools;

import java.util.*;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.inventory.meta.SkullMeta;

/**
 * Create an item without use hands.
 *
 * @author : KIZAFOX
 * @date : 1/04/2023
 * @project : KZXCore
 */
public class ItemBuilder {

    protected final ItemStack itemStack;
    /**
     * Create a new ItemBuilder from scratch.
     * @param material The material to create the ItemBuilder with.
     */
    public ItemBuilder(final Material material){
        this(material, 1);
    }

    /**
     * Create a new ItemBuilder over an existing itemstack.
     * @param itemStack The itemstack to create the ItemBuilder over.
     */
    public ItemBuilder(final ItemStack itemStack){
        this.itemStack=itemStack;
    }

    /**
     * Create a new ItemBuilder from scratch.
     * @param material The material of the item.
     * @param amount The amount of the item.
     */
    public ItemBuilder(final Material material, final int amount){
        this.itemStack = new ItemStack(material, amount);
    }

    /**
     * Clone the ItemBuilder into a new one.
     * @return The cloned instance.
     */
    public ItemBuilder clone() throws CloneNotSupportedException {
        ItemBuilder clone = (ItemBuilder) super.clone();
        return new ItemBuilder(this.itemStack);
    }

    /**
     * Change the durability of the item.
     * @param durability The durability to set it to.
     */
    public ItemBuilder setDurability(final short durability){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        final Damageable damageable = (Damageable) itemMeta;

        damageable.setDamage(durability);
        itemStack.setItemMeta(damageable);
        return this;
    }

    /**
     * Set the name of the item.
     * @param name The name to change it to.
     */
    public ItemBuilder setName(final String name){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();

        Objects.requireNonNull(itemMeta).setDisplayName(name);
        itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Add an unsafe enchantment.
     * @param enchantment The enchantment to add.
     * @param level The level to put the enchant on.
     */
    public ItemBuilder addUnsafeEnchantment(final Enchantment enchantment, final int level){
        this.itemStack.addUnsafeEnchantment(enchantment, level);
        return this;
    }

    /**
     * Remove a certain enchant from the item.
     * @param enchantment The enchantment to remove
     */
    public ItemBuilder removeEnchantment(final Enchantment enchantment){
        this.itemStack.removeEnchantment(enchantment);
        return this;
    }

    /**
     * Set the skull owner for the item. Works on skulls only.
     * @param player The name of the skull's owner.
     */
    public ItemBuilder setSkullOwner(final Player player){
        try{
            SkullMeta skullMeta = (SkullMeta) this.itemStack.getItemMeta();

            skullMeta.setOwningPlayer(player);
            this.itemStack.setItemMeta(skullMeta);
        }catch(ClassCastException e) {
            throw new RuntimeException(e);
        }
        return this;
    }

    /**
     * Add an enchant to the item.
     * @param enchantment The enchant to add
     * @param level The level
     */
    public ItemBuilder addEnchant(final Enchantment enchantment, final int level){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();

        itemMeta.addEnchant(enchantment, level, true);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Add multiple enchants at once.
     * @param enchantments The enchants to add.
     */
    public ItemBuilder addEnchantments(final Map<Enchantment, Integer> enchantments){
        this.itemStack.addEnchantments(enchantments);
        return this;
    }

    /**
     * Sets infinity durability on the item by setting the durability to Short.MAX_VALUE.
     */
    public ItemBuilder setInfinityDurability(){
        this.setDurability(Short.MAX_VALUE);
        return this;
    }

    /**
     * Re-sets the lore.
     * @param lore The lore to set it to.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setLore(final String... lore){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(lore));
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Remove a lore line.
     * @param line The lore to remove.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder removeLoreLine(final String line){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        final List<String> lore = new ArrayList<>(Objects.requireNonNull(itemMeta.getLore()));

        if(!lore.contains(line)){
            return this;
        }else{
            lore.remove(line);
            itemMeta.setLore(lore);
            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    /**
     * Remove a lore line.
     * @param index The index of the lore line to remove.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder removeLoreLine(int index){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        final List<String> lore = new ArrayList<>(Objects.requireNonNull(itemMeta.getLore()));

        if(index < 0 || index > lore.size()){
            return this;
        }else{
            lore.remove(index);
            itemMeta.setLore(lore);
            this.itemStack.setItemMeta(itemMeta);
        }
        return this;
    }

    /**
     * Add a lore line.
     * @param line The lore line to add.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder addLoreLine(final String line){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        List<String> lore = new ArrayList<>();

        if(itemMeta.hasLore()){
            lore = new ArrayList<>(Objects.requireNonNull(itemMeta.getLore()));
        }

        lore.add(line);
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Add a lore line.
     * @param line The lore line to add.
     * @param pos The index of where to put it.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder addLoreLine(final String line, final int pos){
        final ItemMeta itemMeta = this.itemStack.getItemMeta();
        final List<String> lore = new ArrayList<>(Objects.requireNonNull(itemMeta.getLore()));

        lore.set(pos, line);
        itemMeta.setLore(lore);
        this.itemStack.setItemMeta(itemMeta);
        return this;
    }

    /**
     * Sets the dye color on an item.
     * <b>* Notice that this doesn't check for item type, sets the literal data of the dyecolor as durability.</b>
     * @param color The color to put.
     */
    @SuppressWarnings("deprecation")
    public ItemBuilder setDyeColor(DyeColor color){
        this.setDurability(color.getDyeData());
        return this;
    }

    /**
     * Sets the armor color of a leather armor piece. Works only on leather armor pieces.
     * @param color The color to set it to.
     */
    public ItemBuilder setLeatherArmorColor(Color color){
        try{
            final LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) this.itemStack.getItemMeta();

            leatherArmorMeta.setColor(color);
            this.itemStack.setItemMeta(leatherArmorMeta);
        }catch(ClassCastException expected) {
            throw new RuntimeException(expected);
        }
        return this;
    }

    /**
     * Retrieves the itemstack from the ItemBuilder.
     * @return The itemstack created/modified by the ItemBuilder instance.
     */
    public ItemStack toItemStack(){
        return this.itemStack;
    }
}