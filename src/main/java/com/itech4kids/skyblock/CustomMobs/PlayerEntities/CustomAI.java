package com.itech4kids.skyblock.CustomMobs.PlayerEntities;

import com.itech4kids.skyblock.Main;
import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import com.mojang.authlib.properties.PropertyMap;
import net.citizensnpcs.api.npc.NPC;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class CustomAI {

    public static void yetiAI(NPC npc, ArmorStand armorStand){
        EntityPlayer entityPlayer = ((CraftPlayer) npc.getEntity()).getHandle();;

        GameProfile gameProfile = entityPlayer.getProfile();
        gameProfile.getProperties().removeAll("textures");


        PropertyMap map = gameProfile.getProperties();

        map.put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTYxOTI5ODY0OTg2NSwKICAicHJvZmlsZUlkIiA6ICJkZGVkNTZlMWVmOGI0MGZlOGFkMTYyOTIwZjdhZWNkYSIsCiAgInByb2ZpbGVOYW1lIiA6ICJEaXNjb3JkQXBwIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlL2VmOWY2YTU3ZDhmNjg2ZmVkZDUxODVkMTc4NTUwOTEzZjQ4ZjkxYjU3YzkxNjJkNzBkMzY4YjFmZDlmZTJiNTIiCiAgICB9CiAgfQp9", "qhhZ6wh43dbs20DTzKTPPJqkEjAbqEPgHaIjs3InoEdJpih/7YVyMKTV4tHu9xfohiWCajlF/nmEXx8wd//0D9gFrU/YQQVXC1ZaOWLmZZy7fp4PszR2sF919RAxIs3ZS1KqXtSx6WHW22mcfXjCagvDavF8lV558Rcx6xZWg1Sn3QJN02kGZQyTSwko3veWHPW6N3XEfVr2JL9qZQHmsqGdhEXTKIJwWvdls6IGNzODjcsPllj5M9x6QnElNO0I430KWek9BVSnAkcCjRZGyvAhCVBS0TwL8/WYMzSWNJ6M+waUJqNsUbXRupxfP9T0P7gIfGmFAjFIZ39ZYDcdLlHHUcRtYt5PBj5KnJRi2p2NVE2hKa6l3D1sdsaUAdQAyMAdI/mZNc2xolsSrWjxo3Kd5jV/uqadmn1U0k3s7hB2SOF9dFzmwE8eO3A9E0eugiteFl8+FTPaKPVSm+UxugNNrhkFDUqFqIy3LXdQhoDGiTQ0uJBGFzrUgi+C26QoeXrKHl9OQ5S8WFmiJmJL2T6nR9uskInW2yo4s0eX95wpLQSog4VusdaKTqN1SnNcyrHix86sBGGceZFUpZfm6NaL/i9acs0GnEZhcowmmVTgvAo33g4vb81Mv56KOIegbaE261JMjIUq78DTj4fRAgNMQSzg4AeAjoYtWyBKlv4="));

        ((CraftPlayer) npc.getEntity()).setHealth(1);
        ((CraftPlayer) npc.getEntity()).setMaxHealth(1);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (npc.isSpawned()) {
                    for (Entity entity : entityPlayer.getBukkitEntity().getNearbyEntities(3, 3, 3)) {
                        if (entity instanceof Player) {
                            if (!entity.hasMetadata("NPC")) {
                                //npc.getNavigator().setTarget(entity.getLocation()); // walk to a point
                                npc.getNavigator().setTarget(entity, true);
                                break;
                            }
                        }
                    }
                }else{
                    cancel();
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (npc.isSpawned()) {
                    if (armorStand != null){
                        armorStand.teleport(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()));
                        armorStand.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl " + 175 + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "Yeti " + ChatColor.GREEN + Main.format((long) (((CraftPlayer) npc.getEntity()).getHealth() * 2000000)) + ChatColor.RED + "❤");
                    }else{
                        npc.destroy();
                    }
                }else if (!npc.isSpawned()){
                    armorStand.remove();
                    cancel();
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 1);
    }

    public static void frozenSteveAI(NPC npc, ArmorStand armorStand){
        EntityPlayer entityPlayer = ((CraftPlayer) npc.getEntity()).getHandle();;

        GameProfile gameProfile = entityPlayer.getProfile();
        gameProfile.getProperties().removeAll("textures");


        PropertyMap map = gameProfile.getProperties();

        map.put("textures", new Property("textures", "ewogICJ0aW1lc3RhbXAiIDogMTU5ODQ0MjIzNTE4OSwKICAicHJvZmlsZUlkIiA6ICI5ZDEzZjcyMTcxM2E0N2U0OTAwZTMyZGVkNjBjNDY3MyIsCiAgInByb2ZpbGVOYW1lIiA6ICJUYWxvZGFvIiwKICAic2lnbmF0dXJlUmVxdWlyZWQiIDogdHJ1ZSwKICAidGV4dHVyZXMiIDogewogICAgIlNLSU4iIDogewogICAgICAidXJsIiA6ICJodHRwOi8vdGV4dHVyZXMubWluZWNyYWZ0Lm5ldC90ZXh0dXJlLzYwNjkxYzk5YzQ1NTUxN2RlZDM3YmYzOTgwNjIyNjM2MmQ3Y2U2MTI1YTA0YmU3MGVjNjgzYjNjOGEyNWZlMjQiCiAgICB9CiAgfQp9", "OiON9TUnu4q5bO7RxF1oXezvpIepNVXwI80kG9uBezGGwOwhBaaGoMj5lRGJbZExK7GuOLS27iJxxkXB7vPH/URSymNlA49ccmiVpHEUgT6K8XttoMg3MxYmwATCIcShP9MLnqryJ7xyQuaMZVgJc43Bo4qAKYzdxpvT4auSMMmgsrqcjsmr4nT02Eh3HKxZzvOfKKFd287hcnlYmXGnvnuGAlUhjcO5k/qyxeRBE/ePVh55/tf+grapta6HgK22ri7V1ICmP+ORmJayWAzWZfNbUk9SQNg2DDD8F7mKrlpnbR9sC7xcu8BhIe0xCKLHc1jzryfsa53h7t11Upm3a83QRbh8U+LT+HGv8LwAAA1EC7vTPqS7nlbnpuzMmJdc9raNQiK3RFCbM1A/mrYN6B7DPDUVZG1PXniaOPhcAXbQYoaN9o8YJ8TWUSLSBlw7/wFdk9nBC3k949LoAdwBuBKlpLIU72McfWMO3U61FzDtpXmO3XZ1FsmIZ+D01DxhX/Em5yXxL4/9Ta6OCdhcVyME7vEhjpYgZX3N/T83ZBf9Xceoyx3PFFzurjRxUmFCE27ENXEh1KLiN36oB24hrxqul4lYNA9dCstwMOIi32kvf46WeCq5J9ObCdos9bMDofNB2gWsaFKWMc7h9hyx0/rJoVQZjaiVTJLXYSgV8gw="));

        ((CraftPlayer) npc.getEntity()).setHealth(1);
        ((CraftPlayer) npc.getEntity()).setMaxHealth(1);

        new BukkitRunnable() {
            @Override
            public void run() {
                if (npc.isSpawned()) {
                    for (Entity entity : entityPlayer.getBukkitEntity().getNearbyEntities(3, 3, 3)) {
                        if (entity instanceof Player) {
                            if (!entity.hasMetadata("NPC")) {
                                // npc.getNavigator().setTarget(entity.getLocation()); // walk to a point
                                npc.getNavigator().setTarget(entity, true);
                                break;
                            }
                        }
                    }
                }else{
                    cancel();
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 20);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (npc.isSpawned()) {
                    if (armorStand != null) {
                        armorStand.teleport(new Location(npc.getEntity().getLocation().getWorld(), npc.getEntity().getLocation().getX(), npc.getEntity().getLocation().getY() + 0.75, npc.getEntity().getLocation().getZ()));
                        armorStand.setCustomName(ChatColor.DARK_GRAY + "[" + ChatColor.GRAY + "Lvl " + 7 + ChatColor.DARK_GRAY + "] " + ChatColor.RED + "Frozen Steve " + ChatColor.GREEN + Main.format((long) (((CraftPlayer) npc.getEntity()).getHealth() * 700)) + ChatColor.RED + "❤");
                    }else{
                        npc.destroy();
                    }
                }else if (!npc.isSpawned()){
                    armorStand.remove();
                    cancel();
                }
            }
        }.runTaskTimer(Main.getMain(), 5L, 1);
    }
}
