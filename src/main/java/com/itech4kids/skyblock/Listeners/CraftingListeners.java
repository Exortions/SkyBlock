package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Inventories.CraftInventory;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryDragEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CraftingListeners implements Listener {

    //Totally not copy and pasted from some rando plugin

    private HashMap<Player, InventoryView> crafting;
    private HashMap<Player, Entity> playerMap;
    private Main main;
    private ArrayList<Integer> slots;

    public CraftingListeners() {
        this.crafting = new HashMap<Player, InventoryView>();
        this.playerMap = new HashMap<Player, Entity>();
        main = Main.getMain();
        slots = new ArrayList<>();

        slots.add(10);
        slots.add(11);
        slots.add(12);

        slots.add(19);
        slots.add(20);
        slots.add(21);

        slots.add(28);
        slots.add(29);
        slots.add(30);
    }

    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final Block b = e.getClickedBlock();
        if (b == null || b.getType() != Material.WORKBENCH || e.getAction() != Action.RIGHT_CLICK_BLOCK) {
            return;
        }
        e.setCancelled(true);
        CraftInventory craftInventory = new CraftInventory();
        p.openInventory(craftInventory);
    }

    @EventHandler
    public void onDrag(final InventoryDragEvent e) {
        final Player p = (Player)e.getWhoClicked();
        final Inventory inv = e.getInventory();
        if (!inv.getName().equalsIgnoreCase("Craft Item")) {
            return;
        }
        Bukkit.broadcastMessage("test");
        this.prepareCraft(inv, inv, 0, p);
    }

    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        final Inventory inv = e.getInventory();
        final Inventory clicked = e.getClickedInventory();
        final int slot = e.getSlot();
        if (clicked == null || inv == null || clicked.equals(p.getInventory()) || !inv.getName().equalsIgnoreCase("Craft Item")) {
            return;
        }
        if ((!slots.contains(slot) && slot != 23) || (e.getCurrentItem() != null && e.getCurrentItem().getType() == Material.BARRIER)) {
            e.setCancelled(true);
            return;
        }
        this.prepareCraft(inv, clicked, slot, p);
    }

    @EventHandler
    public void onClose(final InventoryCloseEvent e) {
        final Player p = (Player)e.getPlayer();
        if (this.crafting.containsKey(p)) {
            this.crafting.remove(p);
        }
        if (this.playerMap.containsKey(p)) {
            this.playerMap.get(p).remove();
            this.playerMap.remove(p);
        }
    }

    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final Player p = e.getPlayer();
        if (this.crafting.containsKey(p)) {
            this.crafting.remove(p);
        }
        if (this.playerMap.containsKey(p)) {
            this.playerMap.get(p).remove();
            this.playerMap.remove(p);
        }
    }

    private void prepareCraft(final Inventory inv, final Inventory clicked, final int slot, final Player p) {
        final InventoryView view = this.crafting.get(p);
        if (view == null) {
            return;
        }
        if (clicked.equals(inv)) {
            final ItemStack result = inv.getItem(23);
            if (slot == 23 && result != null && result.getType() != Material.AIR) {
                new BukkitRunnable() {
                    public void run() {
                        for (final int i : slots) {
                            final ItemStack item = inv.getItem(i);
                            if (item != null) {
                                if (item.getType() == Material.AIR) {
                                    continue;
                                }
                                if (item.getAmount() <= 1) {
                                    item.setType(Material.AIR);
                                    inv.setItem(i, (ItemStack)null);
                                }
                                else {
                                    item.setAmount(item.getAmount() - 1);
                                }
                            }
                        }
                    }
                }.runTaskLater(main, 1L);
            }
        }
        new BukkitRunnable() {
            public void run() {
                for (int i = 1; i <= slots.size(); ++i) {
                    view.setItem(i, inv.getItem((int)slots.get(i - 1)));
                }
            }
        }.runTaskLater(main, 2L);
        new BukkitRunnable() {
            public void run() {
                if (view.getItem(0) == null || view.getItem(0).getType() == Material.AIR) {
                    ItemStack[] contents;
                    for (int length = (contents = inv.getContents()).length, j = 0; j < length; ++j) {
                        final ItemStack i = contents[j];
                        if (i != null) {
                            if (i.getType() != Material.AIR) {
                                if (i.getDurability() == 5) {
                                    i.setDurability((short)14);
                                }
                            }
                        }
                    }
                }
                else {
                    ItemStack[] contents2;
                    for (int length2 = (contents2 = inv.getContents()).length, k = 0; k < length2; ++k) {
                        final ItemStack i = contents2[k];
                        if (i != null) {
                            if (i.getType() != Material.AIR) {
                                if (i.getDurability() == 14) {
                                    i.setDurability((short)5);
                                }
                            }
                        }
                    }
                }
                ItemStack stack = view.getItem(0);
                if (stack != null && stack.getType() != Material.AIR) {
                    stack = view.getItem(0).clone();
                    inv.setItem(23, stack.clone());
                    return;
                }
                ItemStack recipeRequired = new ItemStack(Material.BARRIER);
                ItemMeta recipeReqMeta = recipeRequired.getItemMeta();
                List<String> recipeReqLore = new ArrayList<>();
                recipeReqLore.add(ChatColor.GRAY + "Add the items for a valid");
                recipeReqLore.add(ChatColor.GRAY + "recipe in the crafting grid");
                recipeReqLore.add(ChatColor.GRAY + "to the left!");
                recipeReqMeta.setDisplayName(ChatColor.RED + "?");
                recipeReqMeta.setLore(recipeReqLore);
                recipeRequired.setItemMeta(recipeReqMeta);
                inv.setItem(23, recipeRequired);
            }
        }.runTaskLater(Main.getMain(), 3L);
    }

/*
    @EventHandler
    public void onClose(InventoryCloseEvent e) {
        Inventory inv = e.getInventory();

        slots.clear();
        slots.add(10);
        slots.add(11);
        slots.add(12);

        slots.add(19);
        slots.add(20);
        slots.add(21);

        slots.add(28);
        slots.add(29);
        slots.add(30);

        if (!inv.getName().equals("Craft Item")) return;

        for (int i : slots) {
            if (inv.getItem(i) == null) continue;

            e.getPlayer().getInventory().addItem(inv.getItem(i));
        }
    }

    @EventHandler
    public void onVCraft(InventoryClickEvent e) {
        if (e.getInventory() != e.getWhoClicked().getInventory()) return;

        slots.clear();
        slots.add(10);
        slots.add(11);
        slots.add(12);

        slots.add(19);
        slots.add(20);
        slots.add(21);

        slots.add(28);
        slots.add(29);
        slots.add(30);

        Player p = (Player) e.getWhoClicked();
    }

    @EventHandler
    public void onVDrag(InventoryDragEvent e) {
        if (e.getInventory() != e.getWhoClicked().getInventory()) return;

        slots.clear();
        slots.add(10);
        slots.add(11);
        slots.add(12);

        slots.add(19);
        slots.add(20);
        slots.add(21);

        slots.add(28);
        slots.add(29);
        slots.add(30);

        Player p = (Player) e.getWhoClicked();
    }

    @EventHandler
    public void onDrag(InventoryDragEvent e) {

        if (e.getInventory() == null) return;
        if (!e.getInventory().getName().equals("Craft Item")) return;

        Inventory inv = e.getInventory();

        slots.clear();
        slots.add(10);
        slots.add(11);
        slots.add(12);

        slots.add(19);
        slots.add(20);
        slots.add(21);

        slots.add(28);
        slots.add(29);
        slots.add(30);

        Player p = (Player) e.getWhoClicked();
        p.sendMessage("hi");
        new BukkitRunnable() {
            @Override
            public void run() {
                if (map.containsKey(p)) {
                    map.remove(p);
                }
                Bukkit.getConsoleSender().sendMessage(main.recipe.getGrid(inv, slots));
                map.put(p, main.recipe.getResult(inv, main.recipe.getGrid(inv, slots)));
                p.sendMessage(map + "");
            }
        }.runTaskLater(main, 1);
    }

    @SuppressWarnings("deprecation")
    @EventHandler
    public void onCraft(InventoryClickEvent e) {
        if (!e.getInventory().getName().equals("Craft Item")) return;
        Inventory inv = e.getInventory();
        Player p = (Player) e.getWhoClicked();

        p.sendMessage("craft_test");
        e.setCancelled(true);

        slots.clear();
        slots.add(10);
        slots.add(11);
        slots.add(12);

        slots.add(19);
        slots.add(20);
        slots.add(21);

        slots.add(28);
        slots.add(29);
        slots.add(30);
        p.sendMessage("craft_test2");

        if (e.getClickedInventory() == null) return;

        if (inv.getItem(24) != null) {
            if (!inv.getItem(24).equals(e.getCurrentItem()) && e.getCurrentItem().isSimilar(inv.getItem(24)) && e.isShiftClick()) {
                e.setCancelled(true);
                return;
            }
        }

        p.sendMessage("craft_test3");

        if (e.getClickedInventory().getName().equals("Craft Item")) {
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.sendMessage("craft_test4");
                    boolean c = false;
                    List<String> recipe = new ArrayList<String>();

                    if ((e.getSlot() == 24 && inv.getItem(e.getSlot()).getType() != Material.BARRIER)) {
                        e.setCancelled(true);
                        if (e.getCursor().isSimilar(e.getCurrentItem())) {
                            int amount = e.getCursor().getAmount();
                            int curAmmount = e.getCurrentItem().getAmount();

                            e.setCursor(e.getCurrentItem());
                            e.getCursor().setAmount(amount + curAmmount);
                        } else {
                            if (e.getCursor() != null) {
                                p.getInventory().addItem(e.getCursor());
                            }
                            e.setCursor(e.getCurrentItem());
                        }

                        if (map.get(p) != null) {
                            c = true;
                            recipe = Arrays.asList(map.get(p).replace("E", "").split(""));
                            Bukkit.getConsoleSender().sendMessage(String.valueOf(recipe.size()) + "size");
                        }
                    }

                    p.sendMessage("craft_test5");
                    int in = 0;
                    for (int i : slots) {
                        p.sendMessage("craft_test6");
                        if (c) {
                            p.sendMessage("craft_test7");
                            if (!recipe.get(in).equals("-") && !recipe.get(in).equals("0")) {
                                p.sendMessage("craft_test8");
                                if (inv.getItem(i) == null) continue;
                                if (inv.getItem(i).getAmount() - 1 <= 0) {
                                    inv.clear(i);
                                    p.sendMessage("craft_test");
                                } else {
                                    Bukkit.getConsoleSender().sendMessage(" minus 1");
                                    p.sendMessage("craft_test10");
                                    inv.getItem(i).setAmount(inv.getItem(i).getAmount() - 1);
                                }
                            }
                            in++;
                        }
                        if (e.getSlot() == i) {
                            e.setCancelled(false);
                            break;
                        }
                    }
                }
            }.runTaskLater(main, 1);

        } else {
            e.setCancelled(false);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                if (map.containsKey(p)) {
                    map.remove(p);
                }
                p.sendMessage("a");
                Bukkit.getConsoleSender().sendMessage(main.recipe.getGrid(inv, slots));
                p.sendMessage("a2");
                map.put(p, main.recipe.getResult(inv, main.recipe.getGrid(inv, slots)));
                p.sendMessage(map + " map");
            }
        }.runTaskLater(main, 2);
    }


    @EventHandler
    public void onCloseMap(InventoryCloseEvent e) {
        if (map.keySet().contains(e.getPlayer())) {
            map.remove(e.getPlayer());
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        if (map.keySet().contains(e.getPlayer())) {
            map.remove(e.getPlayer());
        }
    }
*/
}
