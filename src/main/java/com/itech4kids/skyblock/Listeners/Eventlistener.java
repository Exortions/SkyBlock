package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Events.SkyblockSkillExpGainEvent;
import com.itech4kids.skyblock.Main;
import com.itech4kids.skyblock.Objects.Island.IslandManager;
import com.itech4kids.skyblock.Objects.Items.GuiItems.ClickGuiItem;
import com.itech4kids.skyblock.Objects.Pets.SkyblockPet;
import com.itech4kids.skyblock.Objects.SkillType;
import com.itech4kids.skyblock.Objects.SkyblockPlayer;
import com.itech4kids.skyblock.Objects.SkyblockStats;
import com.itech4kids.skyblock.Util.Config;
import com.itech4kids.skyblock.Util.ItemUtil;
import com.itech4kids.skyblock.Util.LaunchPadConfig;
import net.citizensnpcs.api.CitizensAPI;
import net.citizensnpcs.api.event.NPCDamageByEntityEvent;
import net.citizensnpcs.api.event.NPCDamageEvent;
import net.citizensnpcs.api.event.NPCDeathEvent;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.*;
import org.bukkit.Material;
import org.bukkit.*;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Entity;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCreativeEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.world.ChunkUnloadEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.io.IOException;
import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import static org.bukkit.Material.*;

public class Eventlistener implements Listener {

    public Main main;
    private HashMap<Player, ArmorStand> isJumping;

    public Eventlistener(){
        main = Main.getMain();
        isJumping = new HashMap<>();
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        final Player player = e.getPlayer();
        if(Config.getBanned(player)){
            player.kickPlayer(ChatColor.RED + "You are permanently banned from this server!\n\n" + ChatColor.GRAY + "Reason: " + ChatColor.WHITE + Config.getBanReason(player) + "\n" + ChatColor.GRAY + "Find out more: " + ChatColor.AQUA + "" + ChatColor.UNDERLINE + "https://www.hypixel.net/appeal/n/n" + ChatColor.GRAY + "Ban ID: " + ChatColor.WHITE + "#1379254\n" + ChatColor.GRAY + "Sharing your ban ID may affect the process of your appeal!");
            return;
        }
        if (!player.hasMetadata("NPC")) {

            try {
                Config.createPlayer(player.getName());
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            ItemStack item1 = new ItemStack(Material.NETHER_STAR);
            ItemMeta itemMeta = item1.getItemMeta();
            List<String> lore = new ArrayList<>();
            itemMeta.setDisplayName(ChatColor.GREEN + "Skyblock Menu (Right Click)");
            ItemUtil.addLoreMessage("View all of your Skyblock progress, including your Skills, Collections, Recipes, and more!", lore);
            itemMeta.setLore(lore);
            item1.setItemMeta(itemMeta);
            player.getInventory().setItem(8, item1);

            SkyblockPlayer skyblockPlayer = new SkyblockPlayer(player);
            main.players.put(player.getName(), skyblockPlayer);

            new BukkitRunnable() {
                @Override
                public void run() {
                    main.onJoin(player);
                    main.updateBoard(player);
                    main.updateMana(player);
                    main.updateItemInHand(player);
                    IChatBaseComponent header = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "\n" +
                            "§bYou are playing on: " + "§e§lHYPIXEL.NET" + "\n" + " " + "\"}");
                    IChatBaseComponent footer = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + "\n" + "§aDiscord:" + "\n" + "§7Discord not set up yet!" + "\n " + "\n" + "§aRanks, Booster & More!" + "§l§c STORE.HYPIXEL.NET" + "\"}");
                    PacketPlayOutPlayerListHeaderFooter info = new PacketPlayOutPlayerListHeaderFooter(header);

                    try {
                        Field field = info.getClass().getDeclaredField("b");
                        field.setAccessible(true);
                        field.set(info, footer);
                    } catch (Exception x) {
                        x.printStackTrace();
                    }

                    player.getWorld().setGameRuleValue("mobGriefing", "false");
                    player.getWorld().setGameRuleValue("randomTickSpeed", "0");
                    player.getWorld().setGameRuleValue("naturalRegeneration", "false");
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(info);

                    IslandManager.createIsland(player);

                    skyblockPlayer.activePet = SkyblockPet.spawnArmorStand(player, Config.getActivePet(player));

                    if (player.getWorld().equals(IslandManager.getIsland(player))){
                        player.performCommand("warp home");
                    }else{
                        player.performCommand("warp hub");
                    }

                    if (player.getName().equalsIgnoreCase("eb80")){
                        player.sendTitle(ChatColor.GOLD + "BAD LMAO", "ROY THE BOY");
                    }else if (player.getName().equalsIgnoreCase("a1omic")){
                        player.sendTitle(ChatColor.GOLD + "nurd", "nurd");
                    }
                }
            }.runTaskLater(main, 1);
            new BukkitRunnable() {
                @Override
                public void run() {
                    Main.getMain().updateMaxHealth(skyblockPlayer);
                    player.setHealth(player.getMaxHealth());

                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            if (!player.isOnline()) {
                                cancel(); // this cancels it when they leave
                            }else if (player.isOnline()){
                                for (String string : LaunchPadConfig.getLaunchPadStrings()) {
                                    if (LaunchPadConfig.getLaunchPadLocations(string).get("to").distance(player.getLocation()) < 4) {
                                        if (skyblockPlayer.padName.toLowerCase().equalsIgnoreCase(string)) {
                                            e.getPlayer().teleport(LaunchPadConfig.getLaunchPadLocations(string).get("teleport"));
                                            e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.HORSE_ARMOR, 10, 2);
                                            skyblockPlayer.padName = "";
                                        }
                                    }
                                }
                            }
                        }
                    }.runTaskTimer(Main.getMain(), 5L, 10);
                }
            }.runTaskLater(main, 5);

//        PacketReader reader = new PacketReader();
//        reader.inject(player);
//        if (SkyblockYeti.getNpcs() == null){
//            return;
//        }else if (SkyblockYeti.getNpcs().isEmpty()){
//            return;
//        }else{
//            new BukkitRunnable() {
//                @Override
//                public void run() {
//                    SkyblockYeti.addJoinPacket(player.getPlayer());
//                }
//            }.runTaskLater(Main.getMain(), 1);
//        }
        }
    }

    @EventHandler
    public void onTeleport(EntityTeleportEvent e){
        if (e.getEntity().getType().equals(EntityType.ENDERMAN)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onCombust(EntityCombustEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) throws IOException {
        SkyblockPlayer skyblockPlayer = main.getPlayer(e.getPlayer().getName());
        skyblockPlayer.saveStats();
        skyblockPlayer.activePet.remove();
        main.players.remove(skyblockPlayer);
    }

    @EventHandler
    public void onPickUp(PlayerPickupItemEvent e){
        if (e.getItem().getItemStack().getItemMeta().getLore() == null){
            List<String> lore = new ArrayList<>();
            ItemMeta itemMeta = e.getItem().getItemStack().getItemMeta();
            itemMeta.setDisplayName(e.getItem().getItemStack().getItemMeta().getDisplayName());
            lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
            if (CraftItemStack.asNMSCopy(e.getItem().getItemStack()).getItem() instanceof ItemTool || CraftItemStack.asNMSCopy(e.getItem().getItemStack()).getItem() instanceof ItemSword){
                itemMeta.spigot().setUnbreakable(true);
                itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            }
            itemMeta.setLore(lore);
            e.getItem().getItemStack().setItemMeta(itemMeta);
        }
    }

    @EventHandler
    public void addLore(InventoryClickEvent e){
        if (e.getCurrentItem().getItemMeta().getLore() == null){
            List<String> lore = new ArrayList<>();
            ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
            if (e.getClickedInventory().equals(e.getWhoClicked().getInventory())) {
                itemMeta.setDisplayName(e.getCurrentItem().getItemMeta().getDisplayName());
                lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
                if (CraftItemStack.asNMSCopy(e.getCurrentItem()).getItem() instanceof ItemTool || CraftItemStack.asNMSCopy(e.getCurrentItem()).getItem() instanceof ItemSword){
                    itemMeta.spigot().setUnbreakable(true);
                    itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
                }
                itemMeta.setLore(lore);
                e.getCurrentItem().setItemMeta(itemMeta);
            }
        }
    }

    @EventHandler
    public void addLore(InventoryCreativeEvent e){
        if (e.getCurrentItem().getItemMeta().getLore() == null){
            List<String> lore = new ArrayList<>();
            ItemMeta itemMeta = e.getCurrentItem().getItemMeta();
            itemMeta.setDisplayName(e.getCurrentItem().getItemMeta().getDisplayName());
            lore.add(ChatColor.WHITE + "" + ChatColor.BOLD + "COMMON");
            if (CraftItemStack.asNMSCopy(e.getCurrentItem()).getItem() instanceof ItemTool || CraftItemStack.asNMSCopy(e.getCurrentItem()).getItem() instanceof ItemSword){
                itemMeta.spigot().setUnbreakable(true);
                itemMeta.addItemFlags(ItemFlag.HIDE_UNBREAKABLE);
            }
            itemMeta.setLore(lore);
            e.getCurrentItem().setItemMeta(itemMeta);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e){
        DecimalFormat formatter = new DecimalFormat("#,###");
        formatter.setGroupingUsed(true);

        if (!e.getCause().equals(EntityDamageEvent.DamageCause.ENTITY_ATTACK)){
            if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ChatColor.GRAY + "" + formatter.format(Math.round(e.getFinalDamage())));
            }
        }

        if (e.getEntity() instanceof Player){
            if (!e.getEntity().hasMetadata("NPC")) {
                SkyblockPlayer skyblockPlayer = main.getPlayer(e.getEntity().getName());
                skyblockPlayer.setStat(SkyblockStats.HEALTH, (int) (skyblockPlayer.getStat(SkyblockStats.HEALTH) - e.getFinalDamage()));
                e.setDamage((e.getFinalDamage() * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))));
                //skyblockPlayer.getBukkitPlayer().sendMessage((e.getFinalDamage() * (skyblockPlayer.getStat(SkyblockStats.DEFENSE)/(skyblockPlayer.getStat(SkyblockStats.DEFENSE) + 100))) + " damage");
            }
        }

    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e){
        if (e.getPlayer().getWorld().equals(IslandManager.getIsland(e.getPlayer()))){

        }else{
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onUpdate(BlockPhysicsEvent e){
        if (e.getChangedType().equals(AIR)){
            e.setCancelled(true);
        }
    }

    @EventHandler
    public void onWorld(PlayerChangedWorldEvent e){
        Player player = e.getPlayer();
        SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent e) throws IOException {
        e.setDeathMessage(null);
        if (!e.getEntity().hasMetadata("NPC")) {
            Player player = e.getEntity();
            SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
            skyblockPlayer.setStat(SkyblockStats.HEALTH, skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH));
            ItemStack item1 = new ItemStack(Material.NETHER_STAR);
            ItemMeta itemMeta = item1.getItemMeta();
            List<String> lore = new ArrayList<>();
            itemMeta.setDisplayName(ChatColor.GREEN + "Skyblock Menu (Right Click)");
            ItemUtil.addLoreMessage("View all of your Skyblock progress, including your Skills, Collections, Recipes, and more!", lore);
            itemMeta.setLore(lore);
            item1.setItemMeta(itemMeta);
            player.getInventory().setItem(8, item1);
            Config.setPurseCoins(player, (int) (Config.getPurseCoins(player) / 2));

            DecimalFormat formatter = new DecimalFormat("#,###");
            formatter.setGroupingUsed(true);

            if (Config.getPurseCoins(player) == 0){
                player.sendMessage(ChatColor.RED + "You died and lost 0 coins!");
            }else{
                player.sendMessage(ChatColor.RED + "You died and lost " + formatter.format(Config.getPurseCoins(player)) + " coins!");
            }
            e.setKeepInventory(true);

            player.playSound(player.getLocation(), Sound.ITEM_BREAK, 100, 1);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent e){
        Player player = e.getPlayer();

        if (player.getWorld().getName().equals("island_" + player.getName())){
            e.setRespawnLocation(new Location(player.getWorld(), 0, 100, 0));
        }
    }

    @EventHandler
    public void onNpcDamage(NPCDamageEvent e){
        LivingEntity livingEntity = (LivingEntity) e.getNPC().getEntity();

        NPC npc = e.getNPC();

        if (e.getNPC().getName().contains(ChatColor.RED + "Yeti")){
            if (e.getDamage() > livingEntity.getHealth()*2000000){
                livingEntity.setHealth(0);
                livingEntity.remove();
                npc.destroy();
            }else {
                livingEntity.setHealth(livingEntity.getHealth() - e.getDamage() / 2000000);
            }
        }else if (e.getNPC().getName().contains(ChatColor.RED + "Frozen Steve")){
            if (e.getDamage() > livingEntity.getHealth()*700){
                livingEntity.setHealth(0);
                livingEntity.remove();
                npc.destroy();
            }else {
                livingEntity.setHealth(livingEntity.getHealth() - e.getDamage() / 700);
            }
        }
        e.setDamage(0);
        ((LivingEntity) e.getNPC().getEntity()).setHealth(livingEntity.getHealth());
        ((LivingEntity) e.getNPC().getEntity()).setMaxHealth(livingEntity.getMaxHealth());
    }

    @EventHandler
    public void onNpcDamage(NPCDamageByEntityEvent e) throws IOException {
        int divider = 0;
        LivingEntity livingEntity = (LivingEntity) e.getNPC().getEntity();

        NPC npc = e.getNPC();

        e.setDamage(0);
        if (e.getNPC().getName().contains(ChatColor.RED + "Yeti")){
            divider = 2000000;
            loadDamage(e.getNPC(), divider, e.getDamager(), SkillType.FISHING, 3000);
            //e.getNPC().setName(ChatColor.RED + "Yeti " + ChatColor.GREEN + Main.format(Math.round(livingEntity.getHealth() * 2000000)) + ChatColor.RED + "❤");
            //CustomAI.yetiAI(npc);

        }else if (e.getNPC().getName().contains(ChatColor.RED + "Frozen Steve")){
            divider = 700;
            loadDamage(e.getNPC(), divider, e.getDamager(), SkillType.FISHING, 0);
            //e.getNPC().setName(ChatColor.RED + "Frozen Steve " + ChatColor.GREEN + Math.round(livingEntity.getHealth() * 700) + ChatColor.RED + "❤");
            //CustomAI.frozenSteveAI(e.getNPC());
        }
        ((LivingEntity) e.getNPC().getEntity()).setHealth(livingEntity.getHealth());
        ((LivingEntity) e.getNPC().getEntity()).setMaxHealth(livingEntity.getMaxHealth());
    }

    public void loadDamage(NPC npc, int divider, Entity damager, SkillType skillType, int xp) throws IOException {
        LivingEntity livingEntity = (LivingEntity) npc.getEntity();
        SkyblockPlayer skyblockPlayer = main.getPlayer(damager.getName());
        int i = new Random().nextInt(100);
        int r = new Random().nextInt(100);

        DecimalFormat formatter = new DecimalFormat("#,###");
        formatter.setGroupingUsed(true);
        double damage = 5 + skyblockPlayer.getStat((SkyblockStats.DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100);
        if (!npc.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
            if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100))))));
                if (((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) / divider) >= livingEntity.getHealth()) {
                    new NPCDeathEvent(npc, new EntityDeathEvent((LivingEntity) npc.getEntity(), new ArrayList<>()));
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, skillType, xp));
                    livingEntity.setHealth(0);
                    livingEntity.remove();
                    npc.destroy();
                } else {
                    livingEntity.setHealth(livingEntity.getHealth() - ((damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100) / divider));
                }
            } else {
                ItemUtil.setDamageIndicator(npc.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(damage)));
                if (damage / divider >= livingEntity.getHealth()) {
                    new NPCDeathEvent(npc, new EntityDeathEvent((LivingEntity) npc.getEntity(), new ArrayList<>()));
                    Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, skillType, xp));
                    livingEntity.setHealth(0);
                    livingEntity.remove();
                    npc.destroy();
                } else {
                    livingEntity.setHealth(livingEntity.getHealth() - damage / divider);
                }
            }
            if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                if (!skyblockPlayer.ferocityCooldown) {
                    for (int f = 0; f < Integer.valueOf(skyblockPlayer.getStat(SkyblockStats.FEROCITY)/100 + 1); ++f) {
                        skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                        skyblockPlayer.ferocityCooldown = true;
                        Bukkit.getPluginManager().callEvent(new NPCDamageByEntityEvent(npc, new EntityDamageByEntityEvent(damager, livingEntity, EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage)));
                    }
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            skyblockPlayer.ferocityCooldown = false;
                        }
                    }.runTaskLater(main, 5);
                }
            }
        }
    }

    @EventHandler
    public void onNPCDeath(NPCDeathEvent e) throws IOException {
        if (e.getNPC().getEntity() instanceof Player){
            e.getNPC().spawn(e.getEvent().getEntity().getLocation());
            SkyblockPlayer skyblockPlayer = Main.getMain().getPlayer(((CraftPlayer) e.getNPC()).getKiller().getName());
            e.getNPC().despawn();
            if (e.getNPC().getName().contains(ChatColor.RED + "Yeti")) {
                Bukkit.getPluginManager().callEvent(new SkyblockSkillExpGainEvent(skyblockPlayer, SkillType.FISHING, 3000));
            }
        }
        e.getNPC().destroy();
    }

    @EventHandler
    public void onPlayerClick(PlayerInteractEntityEvent e){
        Player player = e.getPlayer();
        if (e.getRightClicked() instanceof Player){
            if (!e.getRightClicked().hasMetadata("NPC") && player.getItemInHand().getType().equals(Material.AIR) && !player.isSneaking()) {
                    Player righClicked = (Player) e.getRightClicked();
                    SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
                    SkyblockPlayer rightClickedPlayer = main.getPlayer(righClicked.getName());

                    skyblockPlayer.setInventory(righClicked.getName() + "'s Profile", Bukkit.createInventory(null, 54, righClicked.getName() + "'s Profile"));

                    Inventory menu = skyblockPlayer.getInventory(righClicked.getName() + "'s Profile");

                    ItemStack close = new ItemStack(Material.BARRIER);
                    ItemMeta closeMeta = close.getItemMeta();
                    closeMeta.setDisplayName(ChatColor.RED + "Close");

                    ItemStack space1 = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.BLACK.getData());
                    ItemMeta itemMeta = space1.getItemMeta();
                    itemMeta.setDisplayName(" ");
                    space1.setItemMeta(itemMeta);
                    close.setItemMeta(closeMeta);

                    ItemStack noItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.RED.getData());
                    ItemMeta noItemMeta = noItem.getItemMeta();
                    List<String> lore2 = new ArrayList<>();
                    noItemMeta.setDisplayName(ChatColor.RED + "Empty Slot!");
                    lore2.add(ChatColor.RED + "This slot is currently");
                    lore2.add(ChatColor.RED + "empty!");
                    noItemMeta.setLore(lore2);
                    noItem.setItemMeta(noItemMeta);

                    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (byte) SkullType.PLAYER.ordinal());
                    SkullMeta skullMeta = (SkullMeta) skull.getItemMeta();
                    skullMeta.setOwner(rightClickedPlayer.getBukkitPlayer().getName());
                    skull.setItemMeta(skullMeta);
                    ArrayList<String> lore = new ArrayList<String>();
                    skullMeta.setDisplayName(ChatColor.GREEN + righClicked.getName() + "'s Skyblock Stats");
                    lore.add(ChatColor.RED + "❤ Health " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.HEALTH) + " HP");
                    lore.add(ChatColor.GREEN + "❈ Defence " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.DEFENSE));
                    lore.add(ChatColor.RED + "❁ Strength " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.STRENGTH));
                    lore.add(ChatColor.WHITE + "✦ Speed " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.SPEED));
                    lore.add(ChatColor.BLUE + "☣ Crit Chance " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.CRIT_CHANCE) + "%");
                    lore.add(ChatColor.BLUE + "☠ Crit Damage " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.CRIT_DAMAGE) + "%");
                    lore.add(ChatColor.AQUA + "✎ Intelligence " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.MANA));
                    lore.add(ChatColor.YELLOW + "⚔ Attack Speed " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.ATTACK_SPEED) + "%");
                    lore.add(ChatColor.DARK_AQUA + "∞ Sea Creature Chance " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.SEA_CREATURE_CHANCE) + "%");
                    lore.add(ChatColor.AQUA + "✯ Magic Find " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.MAGIC_FIND));
                    lore.add(ChatColor.LIGHT_PURPLE + "♣ Pet Luck " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.PET_LUCK));
                    lore.add(ChatColor.WHITE + "❂ True Defense " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.TRUE_DEFENSE));
                    lore.add(ChatColor.RED + "⫽ Ferocity " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.FEROCITY));
                    lore.add(ChatColor.RED + "✹ Ability Damage " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.ABILITY_DAMAGE) + "%");
                    lore.add(ChatColor.GOLD + "☘ Mining Fortune " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.MINING_FORTUNE));
                    lore.add(ChatColor.GOLD + "⸕ Mining Speed " + ChatColor.WHITE + rightClickedPlayer.getStat(SkyblockStats.MINING_SPEED));
                    skullMeta.setLore(lore);
                    skull.setItemMeta(skullMeta);

                    for (int i = 0; i < 54; ++i) {
                        menu.setItem(i, space1);
                    }

                    if (!righClicked.getItemInHand().getType().equals(Material.AIR)) {
                        menu.setItem(1, righClicked.getItemInHand());
                    } else {
                        menu.setItem(1, noItem);
                    }
                    if (righClicked.getInventory().getHelmet() != null) {
                        menu.setItem(10, righClicked.getInventory().getHelmet());
                    } else {
                        menu.setItem(10, noItem);
                    }
                    if (righClicked.getInventory().getChestplate() != null) {
                        menu.setItem(19, righClicked.getInventory().getChestplate());
                    } else {
                        menu.setItem(19, noItem);
                    }
                    if (righClicked.getInventory().getLeggings() != null) {
                        menu.setItem(28, righClicked.getInventory().getLeggings());
                    } else {
                        menu.setItem(28, noItem);
                    }
                    if (righClicked.getInventory().getBoots() != null) {
                        menu.setItem(37, righClicked.getInventory().getBoots());
                    } else {
                        menu.setItem(37, noItem);
                    }
                    if (rightClickedPlayer.activePet != null) {
                        menu.setItem(46, rightClickedPlayer.activePet.getHelmet());
                    } else {
                        menu.setItem(46, noItem);
                    }


                    menu.setItem(15, new ClickGuiItem("Visit Island", "Click to visit!", 288, 0));
                    menu.setItem(24, new ClickGuiItem("Invite to Island", "Click to invite", 38, 0));
                    menu.setItem(33, new ClickGuiItem("Personal Vault", "Click to view!", 130, 0));
                    menu.setItem(16, new ClickGuiItem("Trade Request", "Send a trade request", 388, 0));
                    menu.setItem(25, new ClickGuiItem("Co-op Request", "Send a co-op request!", 264, 0));

                    menu.setItem(22, skull);
                    menu.setItem(49, close);

                    player.openInventory(menu);
            }else if (!e.getRightClicked().hasMetadata("NPC") && player.isSneaking()){
                player.performCommand("trade " + e.getRightClicked().getName());
            }
        }
    }

    @EventHandler
    public void onUnload(ChunkUnloadEvent e){
        for (Entity entity : e.getChunk().getEntities()){
            if (entity instanceof LivingEntity){
                entity.remove();
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e){
        if (e.getDamager() instanceof Player) {
            if (!(e.getEntity() instanceof Player) && !(e.getEntity().hasMetadata("NPC"))) {
                if (!e.getDamager().hasMetadata("NPC") && !e.getEntity().hasMetadata("NPC")) {
                    SkyblockPlayer skyblockPlayer = main.getPlayer(e.getDamager().getName());
                    int i = new Random().nextInt(100);
                    int r = new Random().nextInt(100);
                    DecimalFormat formatter = new DecimalFormat("#,###");
                    formatter.setGroupingUsed(true);
                    double damage = 5 + skyblockPlayer.getStat((SkyblockStats.DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100);
                    if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                        if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                            e.setDamage(damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100);
                            ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round(e.getDamage())))));
                        } else {
                            e.setDamage(damage);
                            ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(e.getDamage())));
                        }
                        if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                            if (!skyblockPlayer.ferocityCooldown) {
                                skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                                skyblockPlayer.ferocityCooldown = true;
                                Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(skyblockPlayer.getBukkitPlayer(), e.getEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                                new BukkitRunnable() {
                                    @Override
                                    public void run() {
                                        skyblockPlayer.ferocityCooldown = false;
                                    }
                                }.runTaskLater(main, 5);
                            }
                        }
                    }
                } else if (e.getDamager().hasMetadata("NPC") && !e.getEntity().hasMetadata("NPC")) {
                    NPC npc = CitizensAPI.getNPCRegistry().getNPC(e.getDamager());
                    SkyblockPlayer skyblockPlayer = main.getPlayer(e.getEntity().getName());
                    if (npc.getName().contains(ChatColor.RED + "Yeti")) {
                        e.setDamage((300 * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))));
                        skyblockPlayer.setStat(SkyblockStats.HEALTH, (skyblockPlayer.getStat(SkyblockStats.HEALTH) - 300));
                    } else if (npc.getName().contains(ChatColor.RED + "Frozen Steve")) {
                        e.setDamage((20 * (skyblockPlayer.getBukkitPlayer().getMaxHealth() / skyblockPlayer.getStat(SkyblockStats.MAX_HEALTH))));
                        skyblockPlayer.setStat(SkyblockStats.HEALTH, (skyblockPlayer.getStat(SkyblockStats.HEALTH) - 50));
                    }
                }

            }else{
                e.setCancelled(true);
                SkyblockPlayer vicitm = main.getPlayer(e.getEntity().getName());
                vicitm.setStat(SkyblockStats.HEALTH, (int) (vicitm.getStat(SkyblockStats.HEALTH) + e.getFinalDamage()));
            }
        }else if (e.getDamager() instanceof Projectile){
            Projectile projectile = (Projectile) e.getDamager();
            if (projectile.getShooter() instanceof Player){
                SkyblockPlayer skyblockPlayer = main.getPlayer(e.getDamager().getName());
                int i = new Random().nextInt(100);
                int r = new Random().nextInt(100);
                DecimalFormat formatter = new DecimalFormat("#,###");
                formatter.setGroupingUsed(true);
                double damage = 5 + skyblockPlayer.getStat((SkyblockStats.DAMAGE)) + (skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 5) * (1 + skyblockPlayer.getStat(SkyblockStats.STRENGTH) / 100);
                if (!e.getEntity().getType().equals(EntityType.ARMOR_STAND)) {
                    if (i <= skyblockPlayer.getStat(SkyblockStats.CRIT_CHANCE)) {
                        e.setDamage(damage * ((100 + skyblockPlayer.getStat(SkyblockStats.CRIT_DAMAGE))) / 100);
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), ItemUtil.addCritTexture(String.valueOf(formatter.format(Math.round(e.getDamage())))));
                    } else {
                        e.setDamage(damage);
                        ItemUtil.setDamageIndicator(e.getEntity().getLocation(), org.bukkit.ChatColor.GRAY + "" + formatter.format(Math.round(e.getDamage())));
                    }
                    if (r <= skyblockPlayer.getStat(SkyblockStats.FEROCITY)) {
                        if (!skyblockPlayer.ferocityCooldown) {
                            skyblockPlayer.getBukkitPlayer().playSound(skyblockPlayer.getBukkitPlayer().getLocation(), Sound.FIRE_IGNITE, 100, 0);
                            skyblockPlayer.ferocityCooldown = true;
                            Bukkit.getPluginManager().callEvent(new EntityDamageByEntityEvent(skyblockPlayer.getBukkitPlayer(), e.getEntity(), EntityDamageEvent.DamageCause.ENTITY_ATTACK, damage));
                            new BukkitRunnable() {
                                @Override
                                public void run() {
                                    skyblockPlayer.ferocityCooldown = false;
                                }
                            }.runTaskLater(main, 5);
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e){
        Player player = e.getPlayer();
        SkyblockPlayer skyblockPlayer = main.getPlayer(player.getName());
        Vector vector = player.getVelocity();
        double blocksPerSecond = vector.length();
        //player.sendMessage(blocksPerSecond + " b/s");
        if (skyblockPlayer.getStat(SkyblockStats.SPEED) > 900){
            skyblockPlayer.setStat(SkyblockStats.SPEED, 900);
        }
        player.setWalkSpeed(skyblockPlayer.getStat(SkyblockStats.SPEED)/1000F + 0.1F);
        if (player.getLocation().getY() <= -11){
            player.setHealth(0);
        }

        Location l = new Location(player.getLocation().getWorld(), player.getLocation().getX(), player.getLocation().getY() - 1, player.getLocation().getZ());

        if (l.getBlock().getType().equals(ENDER_PORTAL)){
            if (skyblockPlayer.activePet != null) {
                skyblockPlayer.activePet.remove();
                skyblockPlayer.activePet = null;

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        skyblockPlayer.activePet = SkyblockPet.spawnArmorStand(player, Config.getActivePet(player));
                    }
                }.runTaskLater(Main.getMain(), 5);
                player.performCommand("warp home");
            }else{
                player.performCommand("warp home");
            }
        }else if (player.getLocation().getBlock().getType().equals(PORTAL)){
            if (skyblockPlayer.activePet != null) {
                skyblockPlayer.activePet.remove();
                skyblockPlayer.activePet = null;

                new BukkitRunnable() {
                    @Override
                    public void run() {
                        skyblockPlayer.activePet = SkyblockPet.spawnArmorStand(player, Config.getActivePet(player));
                    }
                }.runTaskLater(Main.getMain(), 5);
                player.performCommand("warp hub");
            }else{
                player.performCommand("warp hub");
            }
        }
    }

    @EventHandler
    public void onSlime(final PlayerMoveEvent e) {
        Location from = e.getFrom();
        Location to = e.getTo();
        if (from.getBlockX() != to.getBlockX() || from.getBlockY() != to.getBlockY() || from.getBlockZ() != to.getBlockZ()) {
            final int x = to.getBlockX();
            final int z = to.getBlockZ();
            for (String string : LaunchPadConfig.getLaunchPadStrings()) {
                if (e.getTo().distance(LaunchPadConfig.getLaunchPadLocations(string).get("from")) < 2) {
                    if (!isJumping.containsKey(e.getPlayer())) {
                        e.getPlayer().teleport(LaunchPadConfig.getLaunchPadLocations(string).get("infront"));
                        this.launch(e.getPlayer(), string);
                        Main.getMain().getPlayer(e.getPlayer().getName()).padName = string;
                    }
                }
            }
        }
    }

    private void launch(final Player p, final String padName) {
        final ArmorStand am = (ArmorStand)p.getWorld().spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
        am.setVisible(false);
        am.setPassenger((Entity)p);
        this.isJumping.put(p, am);
        spawnExplosion(p);
        new BukkitRunnable() {
            double x1 = 0.0;
            double x3 = LaunchPadConfig.getLaunchPadLocations(padName).get("to").distance(p.getLocation()) - this.x1;
            double x2 = this.x3 / 3.0;
            double y1 = 0.0;
            double y3 = Math.abs(LaunchPadConfig.getLaunchPadLocations(padName).get("to").getBlockY() - p.getLocation().getBlockY()) % 10;
            double y2 = this.x2;
            double A3 = -((-this.x2 + this.x3) / (-this.x1 + this.x2)) * (-(this.x1 * this.x1) + this.x2 * this.x2) - this.x2 * this.x2 + this.x3 * this.x3;
            double D3 = -((-this.x2 + this.x3) / (-this.x1 + this.x2)) * (-this.y1 + this.y2) - this.y2 + this.y3;
            double a = this.D3 / this.A3;
            double b = (-this.y1 + this.y2 - (-(this.x1 * this.x1) + this.x2 * this.x2) * this.a) / (-this.x1 + this.x2);
            double c = this.y1 - this.a * this.x1 * this.x1 - this.b * this.x1;
            double xC = 0.0;

            public void run() {
                if (LaunchPadConfig.getLaunchPadLocations(padName).get("to").distance(am.getLocation()) < 2.0 || this.xC > 200.0 || !isJumping.containsKey(p)) {
                    this.cancel();
                    am.remove();
                    isJumping.remove(p);
                }
                moveToward((Entity)am, 0.8, yCalculate(this.a, this.b, this.c, this.xC), padName);
                this.xC += 0.84;
            }
        }.runTaskTimerAsynchronously(Main.getMain(), 1L, 1L);
    }

    private void spawnExplosion(final Player player) {
        final PacketPlayOutWorldParticles packet = new PacketPlayOutWorldParticles(EnumParticle.EXPLOSION_HUGE, true, (float)player.getLocation().getBlockX(), (float)player.getLocation().getBlockY(), (float)player.getLocation().getBlockZ(), 0.0f, 0.0f, 0.0f, 0.0f, 2, new int[0]);
        for (final Player p : player.getLocation().getWorld().getPlayers()) {
            ((CraftPlayer)p).getHandle().playerConnection.sendPacket(packet);
            p.playSound(player.getLocation(), Sound.EXPLODE, 10, 2);
        }
    }

    private void moveToward(final Entity player, final double speed, final double yC, final String padName) {
        final Location loc = player.getLocation();
        final double x = loc.getX() - LaunchPadConfig.getLaunchPadLocations(padName).get("to").getX();
        final double y = loc.getY() - LaunchPadConfig.getLaunchPadLocations(padName).get("to").getY() - ((yC > 0.0) ? yC : 0.0);
        final double z = loc.getZ() - LaunchPadConfig.getLaunchPadLocations(padName).get("to").getZ();
        final Vector velocity = new Vector(x, y, z).normalize().multiply(-speed);
        player.setVelocity(velocity);
    }

    private double yCalculate(final double a, final double b, final double c, final double x) {
        return a * x * x + x * b + c;
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e){
        e.setCancelled(true);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) throws IOException {
        Player player = e.getPlayer();
        if (e.getPlayer().getItemInHand() != null) {
            if (e.getPlayer().getInventory().getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.GREEN + "Skyblock Menu (Right Click)")) {
                player.performCommand("sbmenu");
            } else if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().startsWith(ChatColor.GRAY + "[Lvl ")) {
                if (e.getAction().equals(Action.RIGHT_CLICK_AIR) || e.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    if (player.getItemInHand().getItemMeta().getDisplayName().split(" ").length == 3) {
                        player.sendMessage(ChatColor.GREEN + "Successfully added " + ChatColor.getLastColors(player.getItemInHand().getItemMeta().getDisplayName()) + player.getItemInHand().getItemMeta().getDisplayName().split(" ")[2] + ChatColor.GREEN + " to your pets menu!");
                    } else {
                        player.sendMessage(ChatColor.GREEN + "Successfully added " + ChatColor.getLastColors(player.getItemInHand().getItemMeta().getDisplayName()) + player.getItemInHand().getItemMeta().getDisplayName().split(" ")[2] + ChatColor.getLastColors(player.getItemInHand().getItemMeta().getDisplayName()) + " " + player.getItemInHand().getItemMeta().getDisplayName().split(" ")[3] + ChatColor.GREEN + " to your pets menu!");
                    }
                    Config.addPet(player, player.getItemInHand());
                    player.setItemInHand(null);
                    player.playSound(player.getLocation(), Sound.ORB_PICKUP, 10, 1);
                }
            }
        }
    }
}
