package com.itech4kids.skyblock.Objects.Inventories;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import net.citizensnpcs.npc.ai.speech.Chat;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftInventoryCustom;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.List;

public class TradeInventory extends CraftInventoryCustom {

    public Player player;
    public Player player2;

    public TradeInventory(Player player, Player player2) {
        super(null, 54, "You                  " + player2.getName());

        this.player = player;
        this.player2 = player2;

        //Bukkit.getPluginManager().registerEvents(this, Main.getMain());

        ItemStack space1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
        ItemMeta itemMeta = space1.getItemMeta();
        itemMeta.setDisplayName(" ");
        space1.setItemMeta(itemMeta);

        ItemStack clay = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.GREEN.getData());
        ItemMeta clayMeta = clay.getItemMeta();
        List<String> clayLore = new ArrayList<>();
        clayMeta.setDisplayName(ChatColor.GREEN + "Trading!");
        clayLore.add(ChatColor.GRAY + "Click an item in your");
        clayLore.add(ChatColor.GRAY + "inventory to offer it for");
        clayLore.add(ChatColor.GRAY + "trade.");
        clayMeta.setLore(clayLore);
        clay.setItemMeta(clayMeta);

        ItemStack coinsTransaction = new ItemStack(Material.GOLD_NUGGET, 1);
        ItemMeta coinsMeta = coinsTransaction.getItemMeta();
        List<String> coinsLore = new ArrayList<>();
        coinsLore.add(" ");
        coinsLore.add(ChatColor.YELLOW + "Click to add gold!");
        coinsMeta.setDisplayName(ChatColor.GOLD + "Coins transaction");
        coinsMeta.setLore(coinsLore);
        coinsTransaction.setItemMeta(coinsMeta);

        ItemStack newDeal = new ItemStack(Material.INK_SACK, 1, (short) 8);
        ItemMeta dealMeta = newDeal.getItemMeta();
        List<String> dealLore = new ArrayList<>();
        dealMeta.setDisplayName(ChatColor.YELLOW + "New deal");
        dealLore.add(ChatColor.GRAY + "Trading with " + ChatColor.GREEN + player2.getName());

        this.setItem(4, space1);
        this.setItem(13, space1);
        this.setItem(22, space1);
        this.setItem(31, space1);
        this.setItem(40, space1);
        this.setItem(49, space1);
        //this.setItem(58, space1);

        this.setItem(48, clay);
        this.setItem(45, coinsTransaction);
        this.setItem(50, newDeal);

        player.openInventory(this);
    }

    public boolean isOnYourSide(int slot){
        boolean b = false;
        for (int i = 0; i < 4; ++i){
            if (slot == i){
                b = true;
            }else if (slot == i + 9){
                b = true;
            }else if (slot == i + (9 * 2)){
                b = true;
            }else if (slot == i + (9 * 3)){
                b = true;
            }else if (slot == i + (9 * 4)){
                b = true;
            }
        }
        return b;
    }

    public ArrayList<Integer> availibleSlots(){
        ArrayList<Integer> list = new ArrayList<>();
        for (int n = 0; n < 5; ++n) {
            for (int i = n * 9; i < 4 + (n * 9); ++i){
                list.add(i);
            }
        }
        return list;
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        if (e.getView().getTitle().equalsIgnoreCase(this.getName())) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null) {
                if (e.getCurrentItem().getItemMeta() != null) {
                    //if () {
                        if (e.getClickedInventory().equals(this.getInventory())) {
                            if (isOnYourSide(e.getSlot())) {
                                e.getWhoClicked().getInventory().addItem(e.getCurrentItem());
                                player2.getOpenInventory().setItem(e.getSlot() + 5, new ItemStack(Material.AIR));
                                this.setItem(e.getSlot(), new ItemStack(Material.AIR));
                                player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 10, 1);

                            }else if (e.getSlot() == 48){
                                SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(player.getName());
                                SkyblockPlayer skyblockPlayer2 = Main.getMain().getPlayer(player2.getName());

                                ItemStack trade_accepted = new ItemStack(Material.STAINED_CLAY, 1, DyeColor.GREEN.getData());
                                ItemMeta trade_accepted_meta = trade_accepted.getItemMeta();
                                List<String> trade_accepted_lore = new ArrayList<>();
                                trade_accepted_meta.setDisplayName(ChatColor.GREEN + "Deal accepted!");
                                trade_accepted_lore.add(ChatColor.GRAY + "You accepted the trade.");
                                trade_accepted_lore.add(ChatColor.GRAY + "Wait for the other player to");
                                trade_accepted_lore.add(ChatColor.GRAY + "accept.");
                                trade_accepted_meta.setLore(trade_accepted_lore);
                                trade_accepted.setItemMeta(trade_accepted_meta);


                                trade_accepted_lore.clear();
                                trade_accepted_meta.setDisplayName(ChatColor.GREEN + "Deal accepted!");
                                trade_accepted_lore.add(ChatColor.GRAY + player.getName() + " accepted the trade.");
                                trade_accepted_lore.add(ChatColor.GRAY + "You must now accept the");
                                trade_accepted_lore.add(ChatColor.GRAY + "trade.");
                                trade_accepted_meta.setLore(trade_accepted_lore);
                                trade_accepted.setItemMeta(trade_accepted_meta);

                                skyblockPlayer.tradeAccepted = true;

                                this.setItem(48, trade_accepted);
                                player2.getOpenInventory().setItem(50, trade_accepted);

                                player.playSound(player.getLocation(), Sound.VILLAGER_YES, 10, 1);
                                player2.playSound(player.getLocation(), Sound.VILLAGER_YES, 10, 1);
                                if (skyblockPlayer2.tradeAccepted){
                                    for (int i : availibleSlots()){
                                        if (!this.getItem(i).getType().equals(Material.AIR)){
                                            player2.getInventory().addItem(this.getItem(i));
                                            player.closeInventory();
                                            player2.closeInventory();
                                            skyblockPlayer.tradeAccepted = false;
                                            skyblockPlayer2.tradeAccepted = false;
                                            player.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                                            player2.playSound(player.getLocation(), Sound.NOTE_PLING, 10, 1);
                                        }
                                    }
                                }
                            }
                        } else if (e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {
                            for (int i : availibleSlots()) {
                                if (this.getItem(i) == null) {

                                    player2.getOpenInventory().setItem(i + 5, e.getCurrentItem());
                                    this.setItem(i, e.getCurrentItem());

                                    e.getWhoClicked().getInventory().setItem(e.getSlot(), new ItemStack(Material.AIR));
                                    player.playSound(player.getLocation(), Sound.ITEM_PICKUP, 10, 1);
                                    break;
                                }
                            }
                        }
                    //}
                }
            }
        }
    }
}
