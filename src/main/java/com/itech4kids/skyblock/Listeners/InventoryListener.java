package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Commands.TodoListCommand;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Items.ItemHandler;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenSignEditor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class InventoryListener implements Listener {

    private ItemHandler item;
    private TodoListCommand todoList;

    @EventHandler
    public void onClick(InventoryClickEvent event){
        this.item = item;
        Player player = (Player) event.getWhoClicked();
        SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());

        if(event.getInventory() == null) { return; }
        if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("close")) {
                player.closeInventory();
            }
        }
        if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "skyblock menu (right click)")) {
                event.setCancelled(true);
                player.performCommand("sbmenu");
            }
        }
        if(event.getInventory().getName().equals("Item Browser")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "swords")){
                player.sendMessage(ChatColor.YELLOW + "Opening sword category...");
                player.performCommand("swordcategory");
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "helmets")){
                player.sendMessage(ChatColor.YELLOW + "Opening helmet category...");
                player.performCommand("helmetcategory");
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "chestplates")){
                player.sendMessage(ChatColor.YELLOW + "Opening chestplate category...");
                player.performCommand("chestplatecategory");
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "leggings")){
                player.sendMessage(ChatColor.YELLOW + "Opening leggings category...");
                player.performCommand("leggingscategory");
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "boots")){
                player.sendMessage(ChatColor.YELLOW + "Opening boots category...");
                player.performCommand("bootscategory");
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "material")){
                player.sendMessage(ChatColor.YELLOW + "Opening materials category...");
                player.performCommand("materialscategory");
            } else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "bows")) {
                player.sendMessage(ChatColor.YELLOW + "Opening materials category...");
                player.performCommand("bowscategory");
            }else if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Search " + ChatColor.GRAY + "(Click)")){
                skyblockPlayer.search = true;
                player.closeInventory();
                player.sendMessage(ChatColor.GRAY + "Please enter your Query");
            }
        } else if(event.getInventory().getName().equals("Swords") || event.getInventory().getName().equals("Swords - Page 2")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(!(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) && !(event.getCurrentItem().getType().equals(Material.ARROW)) && !(event.getCurrentItem().getType().equals(Material.BARRIER))){
                String old_item_name = event.getCurrentItem().getItemMeta().getDisplayName();
                String new_item_name=old_item_name.replace(' ','_');
                player.sendMessage(ChatColor.GREEN + "Giving one of " + ChatColor.YELLOW + new_item_name.toUpperCase() + ChatColor.GREEN + " to " + ChatColor.WHITE + player.getName());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                player.getInventory().addItem(event.getCurrentItem());
            } else if(event.getCurrentItem().getType().equals(Material.BARRIER)){
                player.closeInventory();
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("itembrowser");
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "next page")){
                player.performCommand("swordcategory2");
            }
        } else if(event.getInventory().getName().equals("Helmets") || event.getInventory().getName().equals("Item Browser - Search")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(!(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) && !(event.getCurrentItem().getType().equals(Material.ARROW)) && !(event.getCurrentItem().getType().equals(Material.BARRIER))){
                if (event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    String old_item_name = event.getCurrentItem().getItemMeta().getDisplayName();
                    String new_item_name = old_item_name.replace(' ', '_');
                    player.sendMessage(ChatColor.GREEN + "Giving one of " + new_item_name.toUpperCase() + ChatColor.GREEN + " to " + ChatColor.WHITE + player.getName());
                }
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                player.getInventory().addItem(event.getCurrentItem());
            } else if(event.getCurrentItem().getType().equals(Material.BARRIER) && event.getCurrentItem().getItemMeta().hasDisplayName()){
                player.closeInventory();
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("itembrowser");
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "next page")){
                player.sendMessage(ChatColor.RED + "Page not implemented yet!");
            }
        } else if(event.getInventory().getName().equals("Chestplates")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(!(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) && !(event.getCurrentItem().getType().equals(Material.ARROW)) && !(event.getCurrentItem().getType().equals(Material.BARRIER))){
                String old_item_name = event.getCurrentItem().getItemMeta().getDisplayName();
                String new_item_name = old_item_name.replace(' ', '_');
                player.sendMessage(ChatColor.GREEN + "Giving one of " + new_item_name.toUpperCase() + ChatColor.GREEN + " to " + ChatColor.WHITE + player.getName());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                player.getInventory().addItem(event.getCurrentItem());
            } else if(event.getCurrentItem().getType().equals(Material.BARRIER)){
                player.closeInventory();
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("itembrowser");
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "next page")){
                player.sendMessage(ChatColor.RED + "Page not implemented yet!");
            }
        } else if(event.getInventory().getName().equals("Leggings")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(!(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) && !(event.getCurrentItem().getType().equals(Material.ARROW)) && !(event.getCurrentItem().getType().equals(Material.BARRIER))){
                String old_item_name = event.getCurrentItem().getItemMeta().getDisplayName();
                String new_item_name = old_item_name.replace(' ', '_');
                player.sendMessage(ChatColor.GREEN + "Giving one of " + new_item_name.toUpperCase() + ChatColor.GREEN + " to " + ChatColor.WHITE + player.getName());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                player.getInventory().addItem(event.getCurrentItem());
            } else if(event.getCurrentItem().getType().equals(Material.BARRIER)){
                player.closeInventory();
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("itembrowser");
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "next page")){
                player.sendMessage(ChatColor.RED + "Page not implemented yet!");
            }
        } else if(event.getInventory().getName().equals("Boots")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(!(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) && !(event.getCurrentItem().getType().equals(Material.ARROW)) && !(event.getCurrentItem().getType().equals(Material.BARRIER))){
                String old_item_name = event.getCurrentItem().getItemMeta().getDisplayName();
                String new_item_name = old_item_name.replace(' ', '_');
                player.sendMessage(ChatColor.GREEN + "Giving one of " + new_item_name.toUpperCase() + ChatColor.GREEN + " to " + ChatColor.WHITE + player.getName());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                player.getInventory().addItem(event.getCurrentItem());
            } else if(event.getCurrentItem().getType().equals(Material.BARRIER)){
                player.closeInventory();
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("itembrowser");
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "next page")){
                player.sendMessage(ChatColor.RED + "Page not implemented yet!");
            }
        } else if(event.getInventory().getName().equals("Materials")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(!(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) && !(event.getCurrentItem().getType().equals(Material.ARROW)) && !(event.getCurrentItem().getType().equals(Material.BARRIER))){
                String old_item_name = event.getCurrentItem().getItemMeta().getDisplayName();
                String new_item_name = old_item_name.replace(' ', '_');
                player.sendMessage(ChatColor.GREEN + "Giving one of " + new_item_name.toUpperCase() + ChatColor.GREEN + " to " + ChatColor.WHITE + player.getName());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                player.getInventory().addItem(event.getCurrentItem());
            } else if(event.getCurrentItem().getType().equals(Material.BARRIER)){
                player.closeInventory();
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("itembrowser");
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "next page")){
                player.sendMessage(ChatColor.RED + "Page not implemented yet!");
            }
        } else if(event.getInventory().getName().equals("Bows")){
            event.setCancelled(true);
            if(event.getCurrentItem() == null) { return; }
            if(!(event.getCurrentItem().getType().equals(Material.STAINED_GLASS_PANE)) && !(event.getCurrentItem().getType().equals(Material.ARROW)) && !(event.getCurrentItem().getType().equals(Material.BARRIER))){
                String old_item_name = event.getCurrentItem().getItemMeta().getDisplayName();
                String new_item_name = old_item_name.replace(' ', '_');
                player.sendMessage(ChatColor.GREEN + "Giving one of " + new_item_name.toUpperCase() + ChatColor.GREEN + " to " + ChatColor.WHITE + player.getName());
                player.playSound(player.getLocation(), Sound.NOTE_PLING, 100, 2);
                player.getInventory().addItem(event.getCurrentItem());
            } else if(event.getCurrentItem().getType().equals(Material.BARRIER)){
                player.closeInventory();
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "go back")){
                player.performCommand("itembrowser");
            } else if(event.getCurrentItem().getType().equals(Material.ARROW) && event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "next page")){
                player.sendMessage(ChatColor.RED + "Page not implemented yet!");
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event){
        if(event.getItemDrop().equals(null)) { return; }
        Player player = event.getPlayer();
        if(event.getItemDrop().getItemStack().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "skyblock menu (right click)")){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onClear(PlayerCommandPreprocessEvent event){
        Player player = event.getPlayer();
        if(event.getMessage().equalsIgnoreCase("/clear")){
            event.setCancelled(true);
            player.getInventory().clear();
            player.getInventory().setItem(8, ItemHandler.skyblock_menu);
        }
    }

    public Class<?> getNMSClass(String clazz) {
        try {
            return Class.forName("net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3] + "." + clazz);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}