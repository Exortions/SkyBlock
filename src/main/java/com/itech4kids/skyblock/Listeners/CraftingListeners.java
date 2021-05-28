package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Enums.SkyblockStats;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import net.minecraft.server.v1_8_R3.CraftingManager;
import net.minecraft.server.v1_8_R3.IRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.*;
import java.util.function.Supplier;

public class CraftingListeners implements Listener {

    public CraftingListeners(){
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory menu = e.getInventory();
        if (e.getView().getTitle().equalsIgnoreCase("Craft Item")){
            List<Integer> craftingSlots = new ArrayList<>();
            craftingSlots.add(10);
            craftingSlots.add(11);
            craftingSlots.add(12);

            craftingSlots.add(19);
            craftingSlots.add(20);
            craftingSlots.add(21);

            craftingSlots.add(28);
            craftingSlots.add(29);
            craftingSlots.add(30);

            ItemStack recipeRequired = new ItemStack(Material.BARRIER);
            ItemMeta recipeReqMeta = recipeRequired.getItemMeta();
            List<String> recipeReqLore = new ArrayList<>();
            recipeReqLore.add(ChatColor.GRAY + "Add the items for a valid");
            recipeReqLore.add(ChatColor.GRAY + "recipe in the crafting grid");
            recipeReqLore.add(ChatColor.GRAY + "to the left!");
            recipeReqMeta.setDisplayName(ChatColor.RED + "Recipe Required");
            recipeReqMeta.setLore(recipeReqLore);
            recipeRequired.setItemMeta(recipeReqMeta);

            if (!e.getInventory().equals(player.getInventory())) {
                if (craftingSlots.containsAll(e.getInventorySlots())) {
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            List<String> list = new ArrayList<>();
                            for (int i : craftingSlots) {
                                String s = "";
                                if (menu.getItem(i) != null) {
                                    s = s + menu.getItem(i).getType().name() + ":" + menu.getItem(i).getDurability();
                                } else {
                                    s = s + "AIR";
                                }
                                list.add(s);
                            }
                            for (Map.Entry<ShapedRecipe, String> entry : getShapedRecipeStrings().entrySet()) {
                                if (entry.getValue().contains("AIR")) {
                                    if (entry.getValue().toUpperCase().contains(list + "")) {
                                        menu.setItem(23, ItemHandler.toSkyblockItem(entry.getKey().getResult()));
                                        menu.getItem(53).setDurability((short) 5);
                                        menu.getItem(52).setDurability((short) 5);
                                        menu.getItem(51).setDurability((short) 5);
                                        menu.getItem(50).setDurability((short) 5);
                                        menu.getItem(48).setDurability((short) 5);
                                        menu.getItem(47).setDurability((short) 5);
                                        menu.getItem(46).setDurability((short) 5);
                                        menu.getItem(45).setDurability((short) 5);
                                        break;
                                    } else {
                                        menu.setItem(23, recipeRequired);
                                        menu.getItem(53).setDurability((short) 14);
                                        menu.getItem(52).setDurability((short) 14);
                                        menu.getItem(51).setDurability((short) 14);
                                        menu.getItem(50).setDurability((short) 14);
                                        menu.getItem(48).setDurability((short) 14);
                                        menu.getItem(47).setDurability((short) 14);
                                        menu.getItem(46).setDurability((short) 14);
                                        menu.getItem(45).setDurability((short) 14);
                                    }

                                } else {
                                    List<String> list2 = new ArrayList<>();
                                    for (int i : craftingSlots) {
                                        String s = "";
                                        if (menu.getItem(i) != null) {
                                            s = s + menu.getItem(i).getType().name() + ":" + menu.getItem(i).getDurability();
                                            list2.add(s);
                                        } else {
                                        }
                                    }

                                    if (entry.getValue().toUpperCase().contains((list2 + ""))) {
                                        menu.setItem(23, ItemHandler.toSkyblockItem(entry.getKey().getResult()));
                                        menu.getItem(53).setDurability((short) 5);
                                        menu.getItem(52).setDurability((short) 5);
                                        menu.getItem(51).setDurability((short) 5);
                                        menu.getItem(50).setDurability((short) 5);
                                        menu.getItem(48).setDurability((short) 5);
                                        menu.getItem(47).setDurability((short) 5);
                                        menu.getItem(46).setDurability((short) 5);
                                        menu.getItem(45).setDurability((short) 5);
                                        break;

                                    } else {
                                        menu.setItem(23, recipeRequired);
                                    }
                                }
                            }

                        }
                    }.runTaskLater(Main.getMain(), 1);
                }
            }else{
                e.setCancelled(false);
            }
        }
    }

    @EventHandler
    public void onClose(InventoryCloseEvent e){
        if (e.getInventory().getTitle().equalsIgnoreCase("Craft Item")){
            List<Integer> craftingSlots = new ArrayList<>();
            craftingSlots.add(10);
            craftingSlots.add(11);
            craftingSlots.add(12);

            craftingSlots.add(19);
            craftingSlots.add(20);
            craftingSlots.add(21);

            craftingSlots.add(28);
            craftingSlots.add(29);
            craftingSlots.add(30);

            for (int i : craftingSlots) {
                ItemStack item = e.getInventory().getItem(i);
                if (item != null){
                    Main.getMain().getPlayer(e.getPlayer().getName()).addItem(item);
                }
            }

        }
    }

    @EventHandler
    public void onCraft(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        Inventory menu = e.getClickedInventory();
        if (e.getView().getTitle().equalsIgnoreCase("Craft Item")){
            List<Integer> craftingSlots = new ArrayList<>();
            craftingSlots.add(10);
            craftingSlots.add(11);
            craftingSlots.add(12);

            craftingSlots.add(19);
            craftingSlots.add(20);
            craftingSlots.add(21);

            craftingSlots.add(28);
            craftingSlots.add(29);
            craftingSlots.add(30);

            ItemStack recipeRequired = new ItemStack(Material.BARRIER);
            ItemMeta recipeReqMeta = recipeRequired.getItemMeta();
            List<String> recipeReqLore = new ArrayList<>();
            recipeReqLore.add(ChatColor.GRAY + "Add the items for a valid");
            recipeReqLore.add(ChatColor.GRAY + "recipe in the crafting grid");
            recipeReqLore.add(ChatColor.GRAY + "to the left!");
            recipeReqMeta.setDisplayName(ChatColor.RED + "Recipe Required");
            recipeReqMeta.setLore(recipeReqLore);
            recipeRequired.setItemMeta(recipeReqMeta);

            if (!e.getClickedInventory().equals(player.getInventory())) {
                if (craftingSlots.contains(e.getSlot())) {

                    craftItem(menu, craftingSlots, recipeRequired);
                } else {
                    if (e.getSlot() == 23){
                        if (e.getCurrentItem() != null) {
                            if (!e.getCurrentItem().getType().equals(Material.BARRIER)) {
                                if (!e.getClick().isShiftClick()) {
                                    e.setCancelled(true);
                                    Main.getMain().getPlayer(player.getName()).addItem(e.getCurrentItem());
                                    e.setCurrentItem(recipeRequired);
                                    menu.getItem(53).setDurability((short) 14);
                                    menu.getItem(52).setDurability((short) 14);
                                    menu.getItem(51).setDurability((short) 14);
                                    menu.getItem(50).setDurability((short) 14);
                                    menu.getItem(48).setDurability((short) 14);
                                    menu.getItem(47).setDurability((short) 14);
                                    menu.getItem(46).setDurability((short) 14);
                                    menu.getItem(45).setDurability((short) 14);

                                    for (int i : craftingSlots) {
                                        if (e.getClickedInventory().getItem(i) != null) {
                                            ItemStack item = e.getClickedInventory().getItem(i);
                                            if (item.getAmount() > 1) {
                                                item.setAmount(item.getAmount() - 1);
                                            } else {
                                                e.getClickedInventory().setItem(i, null);
                                            }
                                        }
                                    }

                                    craftItem(menu, craftingSlots, recipeRequired);
                                }else{
                                    e.setCancelled(true);
                                    for (int i = 0; i < 64; i++) {
                                        Main.getMain().getPlayer(player.getName()).addItem(e.getCurrentItem());
                                        e.setCurrentItem(recipeRequired);
                                        menu.getItem(53).setDurability((short) 14);
                                        menu.getItem(52).setDurability((short) 14);
                                        menu.getItem(51).setDurability((short) 14);
                                        menu.getItem(50).setDurability((short) 14);
                                        menu.getItem(48).setDurability((short) 14);
                                        menu.getItem(47).setDurability((short) 14);
                                        menu.getItem(46).setDurability((short) 14);
                                        menu.getItem(45).setDurability((short) 14);

                                        for (int integer : craftingSlots) {
                                            if (e.getClickedInventory().getItem(integer) != null) {
                                                ItemStack item = e.getClickedInventory().getItem(integer);
                                                if (item.getAmount() > 1) {
                                                    item.setAmount(item.getAmount() - 1);
                                                } else {
                                                    e.getClickedInventory().setItem(integer, null);
                                                }
                                            }
                                        }

                                        List<String> list = new ArrayList<>();
                                        for (int i2 : craftingSlots) {
                                            String s = "";
                                            if (menu.getItem(i2) != null) {
                                                s = s + menu.getItem(i2).getType().name() + ":" + menu.getItem(i).getDurability();
                                            } else {
                                                s = s + "AIR";
                                            }
                                            list.add(s);
                                        }

                                        for (Map.Entry<ShapedRecipe, String> entry : getShapedRecipeStrings().entrySet()) {
                                            if (entry.getValue().contains("AIR")) {
                                                if (entry.getValue().toUpperCase().contains(list + "")) {
                                                    menu.setItem(23, ItemHandler.toSkyblockItem(entry.getKey().getResult()));
                                                    menu.getItem(53).setDurability((short) 5);
                                                    menu.getItem(52).setDurability((short) 5);
                                                    menu.getItem(51).setDurability((short) 5);
                                                    menu.getItem(50).setDurability((short) 5);
                                                    menu.getItem(48).setDurability((short) 5);
                                                    menu.getItem(47).setDurability((short) 5);
                                                    menu.getItem(46).setDurability((short) 5);
                                                    menu.getItem(45).setDurability((short) 5);
                                                    break;
                                                } else {
                                                    menu.setItem(23, recipeRequired);
                                                    menu.getItem(53).setDurability((short) 14);
                                                    menu.getItem(52).setDurability((short) 14);
                                                    menu.getItem(51).setDurability((short) 14);
                                                    menu.getItem(50).setDurability((short) 14);
                                                    menu.getItem(48).setDurability((short) 14);
                                                    menu.getItem(47).setDurability((short) 14);
                                                    menu.getItem(46).setDurability((short) 14);
                                                    menu.getItem(45).setDurability((short) 14);
                                                }

                                            } else {
                                                List<String> list2 = new ArrayList<>();
                                                for (int i2 : craftingSlots) {
                                                    String s = "";
                                                    if (menu.getItem(i) != null) {
                                                        s = s + menu.getItem(i2).getType().name() + ":" + menu.getItem(i).getDurability();
                                                        list2.add(s);
                                                    } else {
                                                    }
                                                }

                                                if (entry.getValue().toUpperCase().contains((list2 + ""))) {
                                                    menu.setItem(23, ItemHandler.toSkyblockItem(entry.getKey().getResult()));
                                                    menu.getItem(53).setDurability((short) 5);
                                                    menu.getItem(52).setDurability((short) 5);
                                                    menu.getItem(51).setDurability((short) 5);
                                                    menu.getItem(50).setDurability((short) 5);
                                                    menu.getItem(48).setDurability((short) 5);
                                                    menu.getItem(47).setDurability((short) 5);
                                                    menu.getItem(46).setDurability((short) 5);
                                                    menu.getItem(45).setDurability((short) 5);
                                                    break;

                                                } else {
                                                    menu.setItem(23, recipeRequired);
                                                }
                                            }
                                        }
                                    }
                                }
                            }else{
                                e.setCancelled(true);
                            }
                        }
                    }else {
                        e.setCancelled(true);
                    }
                }
            }else{
                e.setCancelled(false);
            }
        }
    }

    public void craftItem(Inventory menu, List<Integer> craftingSlots, ItemStack recipeRequired){
        new BukkitRunnable() {
            @Override
            public void run() {
                List<String> list = new ArrayList<>();
                for (int i : craftingSlots) {
                    String s = "";
                    if (menu.getItem(i) != null) {
                        s = s + menu.getItem(i).getType().name() + ":" + menu.getItem(i).getDurability();
                    } else {
                        s = s + "AIR";
                    }
                    list.add(s);
                }
                for (Map.Entry<ShapedRecipe, String> entry : getShapedRecipeStrings().entrySet()) {
                    if (entry.getValue().contains("AIR")) {
                        if (entry.getValue().toUpperCase().contains(list + "")) {
                            menu.setItem(23, ItemHandler.toSkyblockItem(entry.getKey().getResult()));
                            menu.getItem(53).setDurability((short) 5);
                            menu.getItem(52).setDurability((short) 5);
                            menu.getItem(51).setDurability((short) 5);
                            menu.getItem(50).setDurability((short) 5);
                            menu.getItem(48).setDurability((short) 5);
                            menu.getItem(47).setDurability((short) 5);
                            menu.getItem(46).setDurability((short) 5);
                            menu.getItem(45).setDurability((short) 5);
                            break;
                        } else {
                            menu.setItem(23, recipeRequired);
                            menu.getItem(53).setDurability((short) 14);
                            menu.getItem(52).setDurability((short) 14);
                            menu.getItem(51).setDurability((short) 14);
                            menu.getItem(50).setDurability((short) 14);
                            menu.getItem(48).setDurability((short) 14);
                            menu.getItem(47).setDurability((short) 14);
                            menu.getItem(46).setDurability((short) 14);
                            menu.getItem(45).setDurability((short) 14);
                        }

                    } else {
                        List<String> list2 = new ArrayList<>();
                        for (int i : craftingSlots) {
                            String s = "";
                            if (menu.getItem(i) != null) {
                                s = s + menu.getItem(i).getType().name() + ":" + menu.getItem(i).getDurability();
                                list2.add(s);
                            } else {
                            }
                        }

                        if (entry.getValue().toUpperCase().contains((list2 + ""))) {
                            menu.setItem(23, ItemHandler.toSkyblockItem(entry.getKey().getResult()));
                            menu.getItem(53).setDurability((short) 5);
                            menu.getItem(52).setDurability((short) 5);
                            menu.getItem(51).setDurability((short) 5);
                            menu.getItem(50).setDurability((short) 5);
                            menu.getItem(48).setDurability((short) 5);
                            menu.getItem(47).setDurability((short) 5);
                            menu.getItem(46).setDurability((short) 5);
                            menu.getItem(45).setDurability((short) 5);
                            break;

                        } else {
                            menu.setItem(23, recipeRequired);
                        }
                    }
                }
            }
        }.runTaskLater(Main.getMain(), 1);
    }

    public HashMap<ShapelessRecipe, List<String >> getShapelessRecipeStrings(){
        HashMap<ShapelessRecipe, List<String>> map = new HashMap<>();
        for (IRecipe iRecipe : CraftingManager.getInstance().getRecipes()){
            Recipe recipe = iRecipe.toBukkitRecipe();
            if (recipe instanceof ShapelessRecipe){
                ShapelessRecipe shapelessRecipe = (ShapelessRecipe) recipe;
                List<String> list = new ArrayList<>();
                for (ItemStack item : shapelessRecipe.getIngredientList()){
                    list.add(item.getType().name());
                }
                map.put(shapelessRecipe, list);
            }
        }
        return map;
    }

    public HashMap<ShapedRecipe, String> getShapedRecipeStrings(){
        HashMap<ShapedRecipe, String> map = new HashMap<>();
        for (IRecipe iRecipe : CraftingManager.getInstance().getRecipes()){
            Recipe recipe = iRecipe.toBukkitRecipe();
            if (recipe instanceof ShapedRecipe){
                List<String> list = new ArrayList<>();
                ShapedRecipe shapedRecipe = (ShapedRecipe) recipe;
                for (Map.Entry<Character, ItemStack> entry : shapedRecipe.getIngredientMap().entrySet()){
                    for (String s : shapedRecipe.getShape()){
                        String string = "";
                        for (int i = 0; i < s.length(); i++) {
                            if (String.valueOf(s.charAt(i)).equalsIgnoreCase(String.valueOf(entry.getKey()))){
                                if (entry.getValue() != null) {
                                    string = string + entry.getValue().getType().name() + ":" + entry.getValue().getDurability();

                                    if (string.contains("32767")){
                                        string = string.replaceAll("32767", "0");
                                    }
                                }else {
                                    string = string + "AIR";
                                }
                                list.add(string);
                                Bukkit.getLogger().info(list + "");
                            }
                        }
                    }
                }
                map.put(shapedRecipe, list + "");
            }
        }
        return map;
    }
}