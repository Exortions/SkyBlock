package com.itech4kids.skyblock.Enums;

public enum CollectionTypes {

    FARMING,
    MINING,
    COMBAT,
    FORAGING,
    FISHING,
    BOSS;

    public String getName(String s){
        String farming = "Farming";
        String mining = "Mining";
        String combat = "Combat";
        String foraging = "Foraging";
        String fishing = "Fishing";
        String boss = "Boss";
        if(s.equalsIgnoreCase("farming")){
            return farming;
        } else if(s.equalsIgnoreCase("mining")){
            return mining;
        } else if(s.equalsIgnoreCase("combat")){
            return combat;
        } else if(s.equalsIgnoreCase("foraging")){
            return foraging;
        } else if(s.equalsIgnoreCase("fishing")){
            return fishing;
        } else if(s.equalsIgnoreCase("boss")){
            return boss;
        } else{
            return null;
        }
    }

}