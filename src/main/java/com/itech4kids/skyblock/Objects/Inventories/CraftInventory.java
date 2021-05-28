package com.itech4kids.skyblock.Objects.Inventories;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import net.minecraft.server.v1_8_R3.CraftingManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CraftInventory extends CraftInventoryCustom {

    private List<Integer> craftingSlots;
    private Integer resultSlot;

    public CraftInventory() {
        super(null, 54, "Craft Item");

        Inventory menu = this;

        resultSlot = 23;
        craftingSlots = new ArrayList<>();

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        ItemStack recipeRequired = new ItemStack(Material.BARRIER);
        ItemMeta recipeReqMeta = recipeRequired.getItemMeta();
        List<String> recipeReqLore = new ArrayList<>();
        recipeReqLore.add(ChatColor.GRAY + "Add the items for a valid");
        recipeReqLore.add(ChatColor.GRAY + "recipe in the crafting grid");
        recipeReqLore.add(ChatColor.GRAY + "to the left!");
        recipeReqMeta.setDisplayName(ChatColor.RED + "Recipe Required");
        recipeReqMeta.setLore(recipeReqLore);
        recipeRequired.setItemMeta(recipeReqMeta);

        ItemStack quickCrafting = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        ItemMeta quickCraftingMeta = quickCrafting.getItemMeta();
        List<String> quickCraftingLore = new ArrayList<>();
        quickCraftingLore.add(ChatColor.GRAY + "Quick Crafting allows you to");
        quickCraftingLore.add(ChatColor.GRAY + "craft items without assembling");
        quickCraftingLore.add(ChatColor.GRAY + "the recipe.");
        quickCraftingMeta.setDisplayName(ChatColor.RED + "Recipe Required");
        quickCraftingMeta.setLore(quickCraftingLore);
        quickCrafting.setItemMeta(quickCraftingMeta);

        for (int i = 0; i < 54; ++i){
            menu.setItem(i, ItemHandler.createEmptySpace());
        }

        menu.setItem(10, null);
        craftingSlots.add(10);
        menu.setItem(11, null);
        craftingSlots.add(11);
        menu.setItem(12, null);
        craftingSlots.add(12);

        menu.setItem(19, null);
        craftingSlots.add(19);
        menu.setItem(20, null);
        craftingSlots.add(20);
        menu.setItem(21, null);
        craftingSlots.add(21);

        menu.setItem(28, null);
        craftingSlots.add(28);
        menu.setItem(29, null);
        craftingSlots.add(29);
        menu.setItem(30, null);
        craftingSlots.add(30);

        menu.setItem(48, glass);
        menu.setItem(47, glass);
        menu.setItem(46, glass);
        menu.setItem(45, glass);

        menu.setItem(50, glass);
        menu.setItem(51, glass);
        menu.setItem(52, glass);
        menu.setItem(53, glass);

        menu.setItem(16, quickCrafting);
        menu.setItem(25, quickCrafting);
        menu.setItem(34, quickCrafting);

        menu.setItem(23, recipeRequired);

        menu.setItem(49, ItemHandler.createPageBackArrow("Skyblock Menu"));
    }
//
//    @EventHandler
//    public void onClick(InventoryClickEvent e){
//        if (e.getClickedInventory().equals(this)){
//            if (e.getCurrentItem() != null){
//                e.setCancelled(true);
//            }else{
//                if (craftingSlots.contains(e.getSlot())){
//                    Main.getMain().recipe.getResult(this, Main.getMain().recipe.getGrid(this, craftingSlots));
//                }
//            }
//        }
//    }
//
//    @EventHandler
//    public void onInventoryClose(InventoryCloseEvent e){
//        if (e.getInventory().equals(this)){
//            HandlerList.unregisterAll(this);
//
//            Inventory inv = e.getInventory();
//
//            for (int i = 0; i < inv.getSize(); ++i) {
//                if (inv.getItem(i) == null) continue;
//
//                e.getPlayer().getInventory().addItem(inv.getItem(i));
//            }
//        }
//    }

}
