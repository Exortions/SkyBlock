package com.itech4kids.skyblock.Listeners;

import com.itech4kids.skyblock.Main;
import net.citizensnpcs.api.event.NPCRightClickEvent;
import net.citizensnpcs.trait.SkinTrait;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class MerchantListener implements Listener {

    @EventHandler
    public void onMerchantClick(NPCRightClickEvent e){
        Player player = e.getClicker();
        SkinTrait skin = e.getNPC().getTrait(SkinTrait.class);
        if(skin == Main.getMain().minerSkin){
            player.performCommand("minermerchant");
        }
    }

}
