package com.itech4kids.skyblock.Objects.Crafting;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import net.minecraft.server.v1_8_R3.CraftingManager;
import net.minecraft.server.v1_8_R3.IRecipe;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CraftingRecipe {

    HashMap<String, ItemStack> recipes = new HashMap<String, ItemStack>();

    public CraftingRecipe(Main main) {
        for (IRecipe recipe : CraftingManager.getInstance().getRecipes()){
            recipes.put(recipe.toBukkitRecipe().getResult().getType().toString(), recipe.toBukkitRecipe().getResult());
        }
    }

    public String getResult(Inventory inv, String grid) {
        //LinkedHashMap<String, Gear> minSearch = recipes.get(length); //sorts by length to make search time faster. Linked because larger recipes take precedence
        //    if (minSearch == null) return;
        String rec = null;

        ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.ORANGE.getData());
        ItemMeta glassMeta = glass.getItemMeta();
        glassMeta.setDisplayName(" ");
        glass.setItemMeta(glassMeta);

        ItemStack glass2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GREEN.getData());
        ItemMeta glass2Meta = glass2.getItemMeta();
        glass2Meta.setDisplayName(" ");
        glass2.setItemMeta(glass2Meta);

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

        for (String recipe : recipes.keySet()) {
            if (grid.contains(recipe)) {
                rec = recipe;
                inv.setItem(24, recipes.get(recipe));
                inv.setItem(48, glass2);
                inv.setItem(47, glass2);
                inv.setItem(46, glass2);
                inv.setItem(45, glass2);

                inv.setItem(50, glass2);
                inv.setItem(51, glass2);
                inv.setItem(52, glass2);
                inv.setItem(53, glass2);
                break;
            }
        }
        if (rec == null) {
            inv.setItem(48, glass);
            inv.setItem(47, glass);
            inv.setItem(46, glass);
            inv.setItem(45, glass);

            inv.setItem(50, glass);
            inv.setItem(51, glass);
            inv.setItem(52, glass);
            inv.setItem(53, glass);
        }

        inv.setItem(49, ItemHandler.createExitBarrier());

        return rec;
    }

    public String getGrid(Inventory inv, List<Integer> slots) {
        StringBuilder recipe = new StringBuilder();
        int amount = 0;

        for (int i : slots) {
            if (inv.getItem(i) == null) {
                recipe.append("-");
                continue;
            }
            amount = amount + inv.getItem(i).getAmount();

            //TODO: Check if item is enchanted
            if (inv.getItem(i).getEnchantments().size() > 0) {
                recipe.append("E");
            }
            recipe.append(inv.getItem(i).getType().toString());
        }
        if (amount % 32 == 0) amount = 32;

        recipe = new StringBuilder(String.valueOf(amount) + recipe.toString());
        String str = recipe.toString();

        return (recipe != null) ? recipe.toString() : null;
    }

}
