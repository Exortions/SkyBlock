package com.itech4kids.skyblock.CustomMobs;

import com.itech4kids.skyblock.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Entity;
import org.bukkit.metadata.FixedMetadataValue;

import java.util.HashMap;
import java.util.Map;

public class SEntityHandler {

    private HashMap<Integer, SEntity> entities;
    private int id;

    public SEntityHandler(){
        entities = new HashMap<>();
        id = -1;
    }

    public void registerEntity(SEntity sEntity){
        id = id + 1;
        entities.put(id, sEntity);
        sEntity.getVanillaEntity().setMetadata("identifier", new FixedMetadataValue(Main.getMain(), id));
    }

    public void unRegisterEntity(int identifier){
        entities.get(identifier).getVanillaEntity().removeMetadata("identifier", Main.getMain());
        entities.remove(identifier);
    }

    public void unRegisterEntity(Entity entity){
        HashMap<Integer, SEntity> entities_2 = new HashMap<>(entities);
        for (Map.Entry<Integer, SEntity> entry : entities.entrySet()){
            if (entry.getValue().getVanillaEntity().getMetadata("identifier").equals(entity.getMetadata("identifier"))){
                entities_2.remove(entry.getKey(), entry.getValue());
                entity.removeMetadata("identifier", Main.getMain());
            }
        }

        entities = entities_2;
    }

    public SEntity getEntity(Entity entity){
        SEntity sEntity = null;
        for (Map.Entry<Integer, SEntity> entry : entities.entrySet()){
            if (entry.getValue().getVanillaEntity().getMetadata("identifier").equals(entity.getMetadata("identifier"))){
                sEntity = entry.getValue();
            }
        }
        return sEntity;
    }

}
