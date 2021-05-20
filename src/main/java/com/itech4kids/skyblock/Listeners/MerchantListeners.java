package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Inventories.MerchantInventory;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Util.Config;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import org.apache.commons.lang.WordUtils;
import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

public class MerchantListeners implements Listener {

    @EventHandler
    public void onNpcClick(NPCRightClickEvent e){
        if (e.getNPC().getEntity().hasMetadata("minerMerchant")){
            HashMap<ItemStack, Integer> items = new HashMap<>();
            items.put(new ItemStack(Material.COAL, 2), 8);
            items.put(new ItemStack(Material.IRON_INGOT, 4), 22);
            items.put(new ItemStack(Material.GOLD_INGOT, 2), 12);
            ItemStack rookiePickaxe = new ItemStack(Material.STONE_PICKAXE);
            ItemMeta rookiePickaxeMeta = rookiePickaxe.getItemMeta();
            rookiePickaxeMeta.setDisplayName(ChatColor.WHITE + "Rookie Pickaxe");
            rookiePickaxeMeta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Breaking Power 2", " ", ChatColor.GRAY + "Damage: " + ChatColor.RED + "+15", " ", ChatColor.GRAY + "Mining Speed : " + ChatColor.GREEN + "+180", " ", ChatColor.BLUE + "Efficiency I", ChatColor.GRAY + "Grants " + ChatColor.GREEN + "+30 " + ChatColor.GOLD + "⸕ Mining Speed", " ", ChatColor.DARK_GRAY + "This item can be reforged!", ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON PICKAXE"));
            rookiePickaxe.addEnchantment(Enchantment.DURABILITY, 1);
            rookiePickaxeMeta.spigot().setUnbreakable(true);
            rookiePickaxeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            rookiePickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            rookiePickaxe.setItemMeta(rookiePickaxeMeta);
            items.put(rookiePickaxe, 12);
            ItemStack promisingPickaxe = new ItemStack(Material.IRON_PICKAXE);
            ItemMeta promisingPickaxeMeta = promisingPickaxe.getItemMeta();
            promisingPickaxeMeta.setDisplayName(ChatColor.GREEN + "Promising Pickaxe");
            promisingPickaxeMeta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Breaking Power 3", " ", ChatColor.GRAY + "Damage: " + ChatColor.RED + "+20", " ", ChatColor.GRAY + "Mining Speed : " + ChatColor.GREEN + "+190", " ", ChatColor.GRAY + "Gains a higher level of", ChatColor.GRAY + "Enchantment when breaking", ChatColor.GRAY + "blocks", " ", ChatColor.GRAY + "Will gain " + ChatColor.BLUE + "Efficiency I " + ChatColor.GRAY + "after", ChatColor.GRAY + "breaking 50 more blocks.", " ", ChatColor.DARK_GRAY + "This item can be reforged!", ChatColor.GREEN + "" + ChatColor.BOLD + "UNCOMMON PICKAXE"));
            promisingPickaxeMeta.spigot().setUnbreakable(true);
            promisingPickaxeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            promisingPickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            promisingPickaxe.setItemMeta(promisingPickaxeMeta);
            items.put(promisingPickaxe, 35);
            ItemStack goldenPickaxe = new ItemStack(Material.GOLD_PICKAXE);
            ItemMeta goldenPickaxeMeta = goldenPickaxe.getItemMeta();
            goldenPickaxeMeta.setDisplayName(ChatColor.WHITE + "Golden Pickaxe");
            goldenPickaxeMeta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Breaking Power 1", " ", ChatColor.GRAY + "Damage: " + ChatColor.RED + "+15", " ", ChatColor.GRAY + "Mining Speed : " + ChatColor.GREEN + "+250", " ", ChatColor.DARK_GRAY + "This item can be reforged!", ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON PICKAXE"));
            goldenPickaxeMeta.spigot().setUnbreakable(true);
            goldenPickaxeMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            goldenPickaxeMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
            goldenPickaxe.setItemMeta(goldenPickaxeMeta);
            items.put(goldenPickaxe, 18);
            items.put(new ItemStack(Material.TORCH, 32), 16);
            items.put(new ItemStack(Material.GRAVEL, 2), 12);
            items.put(new ItemStack(Material.COBBLESTONE, 1), 3);
            items.put(new ItemStack(Material.STONE, 2), 4);
            ItemStack onyx = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
            ItemMeta onyxMeta = onyx.getItemMeta();
            onyxMeta.setDisplayName(ChatColor.BLUE + "Onyx");
            onyxMeta.setLore(Arrays.asList(ChatColor.DARK_GRAY + "Reforge Stone", " ", ChatColor.GRAY + "Can be used in a Reforge Anvil", ChatColor.GRAY + "or with the Dungeon Blacksmith", ChatColor.GRAY + "to apply the Fruitful", ChatColor.GRAY + "reforge to a pickaxe", " ", ChatColor.GRAY + "Requires " + ChatColor.GREEN + "Mining Skill Lvl II", ChatColor.DARK_GRAY + " ", ChatColor.BLUE + "" + ChatColor.BOLD + "RARE REFORGE STONE"));
            onyx.setItemMeta(onyxMeta);
            items.put(onyx, 100);

            MerchantInventory minerMerchant = new MerchantInventory("Miner Merchant", items);
            e.getClicker().openInventory(minerMerchant);
        }
    }

    @EventHandler
    public void onMerchantInventoryClick(InventoryClickEvent e) throws IOException {
        Player player = (Player) e.getWhoClicked();
        if (e.getView().getTitle().endsWith("Merchant")){
            e.setCancelled(true);
            if (e.getClickedInventory().equals(player.getInventory())){
                if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                    player.sendMessage(ChatColor.GREEN + "You sold " + ChatColor.WHITE + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " x" + e.getCurrentItem().getAmount() + ChatColor.GREEN + " for " + ChatColor.GOLD + "0 Coins" + ChatColor.GREEN + "!");
                }else{
                    player.sendMessage(ChatColor.GREEN + "You sold " + ChatColor.WHITE + WordUtils.capitalize(e.getCurrentItem().getType().name().toLowerCase().replaceAll("_", " ")) + ChatColor.DARK_GRAY + " x" + e.getCurrentItem().getAmount() + ChatColor.GREEN + " for " + ChatColor.GOLD + "0 Coins" + ChatColor.GREEN + "!");
                }
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                e.setCurrentItem(null);
            }else {
                int cost = 0;
                if (e.getClick().isRightClick()) {
                    SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
                    skyblockPlayer.setInventory("Shop Trading Options", Bukkit.createInventory(null, 54, "Shop Trading Options"));
                    Inventory menu = skyblockPlayer.getInventory("Shop Trading Options");

                    cost = Integer.parseInt(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(e.getCurrentItem().getItemMeta().getLore().size() - 4).split(" ")[0]));

                    for (int i = 0; i < menu.getSize(); ++i) {
                        menu.setItem(i, ItemHandler.createEmptySpace());
                    }

                    menu.setItem(20, ItemHandler.createMerchantItem(ItemHandler.merchantItemToItemStack(e.getCurrentItem()), Math.round(cost/e.getCurrentItem().getAmount()), true));
                    menu.getItem(20).setAmount(1);
                    menu.setItem(21, ItemHandler.createMerchantItem(ItemHandler.merchantItemToItemStack(e.getCurrentItem()), Math.round((cost/e.getCurrentItem().getAmount())*5), true));
                    menu.getItem(21).setAmount(5);
                    menu.setItem(22, ItemHandler.createMerchantItem(ItemHandler.merchantItemToItemStack(e.getCurrentItem()), Math.round((cost/e.getCurrentItem().getAmount())*10), true));
                    menu.getItem(22).setAmount(10);
                    menu.setItem(23, ItemHandler.createMerchantItem(ItemHandler.merchantItemToItemStack(e.getCurrentItem()), Math.round((cost/e.getCurrentItem().getAmount())*32), true));
                    menu.getItem(23).setAmount(32);
                    menu.setItem(24, ItemHandler.createMerchantItem(ItemHandler.merchantItemToItemStack(e.getCurrentItem()), Math.round((cost/e.getCurrentItem().getAmount())*64), true));
                    menu.getItem(24).setAmount(64);

                    menu.setItem(49, ItemHandler.createExitBarrier());

                    player.openInventory(menu);
                } else {
                    if (e.getCurrentItem().getMaxStackSize() > 1) {
                        cost = Integer.parseInt(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(e.getCurrentItem().getItemMeta().getLore().size() - 4).split(" ")[0]));
                        if (Config.getPurseCoins(player) >= cost) {
                            player.getInventory().addItem(ItemHandler.merchantItemToItemStack(e.getCurrentItem()));
                            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                                player.sendMessage(ChatColor.GREEN + "You bought " + ChatColor.WHITE + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " x" + e.getCurrentItem().getAmount() + ChatColor.GREEN + " for " + ChatColor.GOLD + cost + " Coins" + ChatColor.GREEN + "!");
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                            } else {
                                player.sendMessage(ChatColor.GREEN + "You bought " + ChatColor.WHITE + WordUtils.capitalize(e.getCurrentItem().getType().name().toLowerCase().replaceAll("_", " ")) + ChatColor.DARK_GRAY + " x" + e.getCurrentItem().getAmount() + ChatColor.GREEN + " for " + ChatColor.GOLD + cost + " Coins" + ChatColor.GREEN + "!");
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                            }
                            Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - cost));
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have enough coins!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 0);
                        }
                    } else {
                        cost = Integer.parseInt(ChatColor.stripColor(e.getCurrentItem().getItemMeta().getLore().get(e.getCurrentItem().getItemMeta().getLore().size() - 3).split(" ")[0]));
                        if (Config.getPurseCoins(player) >= cost) {
                            player.getInventory().addItem(ItemHandler.merchantItemToItemStack(e.getCurrentItem()));

                            if (e.getCurrentItem().getItemMeta().hasDisplayName()) {
                                player.sendMessage(ChatColor.GREEN + "You bought " + ChatColor.WHITE + e.getCurrentItem().getItemMeta().getDisplayName() + ChatColor.DARK_GRAY + " x" + e.getCurrentItem().getAmount() + ChatColor.GREEN + " for " + ChatColor.GOLD + cost + " Coins" + ChatColor.GREEN + "!");
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                            } else {
                                player.sendMessage(ChatColor.GREEN + "You bought " + ChatColor.WHITE + WordUtils.capitalize(e.getCurrentItem().getType().name().toLowerCase().replaceAll("_", " ")) + ChatColor.DARK_GRAY + " x" + e.getCurrentItem().getAmount() + ChatColor.GREEN + " for " + ChatColor.GOLD + cost + " Coins" + ChatColor.GREEN + "!");
                                player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 2);
                            }
                            Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) - cost));
                        } else {
                            player.sendMessage(ChatColor.RED + "You do not have enough coins!");
                            player.playSound(player.getLocation(), Sound.ENDERMAN_TELEPORT, 10, 0);
                        }
                    }
                }

            }
        }
    }
}
