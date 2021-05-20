package com.itech4kids.skyblock.CustomMobs.Slayer;

import org.bukkit.entity.Player;

public class SlayerQuest {

    private int exp;
    private int neededExp;
    private SlayerBoss boss;
    private SlayerType type;
    private SlayerState state;
    private Player player;

    private int expReward;
    private String bossName;

    public SlayerQuest(SlayerType type, Player player, SlayerBoss boss){
        exp = 0;
        this.type = type;
        this.boss = boss;
        this.state = SlayerState.SUMMONING;
        this.player = player;

        this.bossName = this.boss.getName() + " " + this.boss.getBossLevel();
        this.expReward = this.boss.getExpReward();

        switch (boss.getBossLevel()){
            case 1:
                switch (type){
                    case SVEN:
                        neededExp = 250;
                        break;
                    case TARANTULA:
                        neededExp = 250;
                        break;
                    case REVENANT:
                        neededExp = 150;
                        break;
                }
                break;
            case 2:
                switch (type){
                    case SVEN:
                        neededExp = 600;
                        break;
                    case TARANTULA:
                        neededExp = 600;
                        break;
                    case REVENANT:
                        neededExp = 1440;
                        break;
                }
                break;
            case 3:
                switch (type){
                    case SVEN:
                        neededExp = 1500;
                        break;
                    case TARANTULA:
                        neededExp = 1000;
                        break;
                    case REVENANT:
                        neededExp = 2400;
                        break;
                }
                break;
            case 4:
                switch (type){
                    case SVEN:
                        neededExp = 3000;
                        break;
                    case TARANTULA:
                        neededExp = 2000;
                        break;
                    case REVENANT:
                        neededExp = 4800;
                        break;
                }
                break;
            case 5:
                neededExp = 6000;
                break;
        }
    }

    public int getExpReward(){
        return expReward;
    }

    public String getBossName(){
        return bossName;
    }

    public Player getSummoner(){
        return player;
    }

    public void setState(SlayerState state){
        this.state = state;
    }

    public SlayerState getState(){
        return state;
    }

    public SlayerBoss getBoss(){
        return boss;
    }

    public SlayerType getType(){
        return type;
    }

    public int getExp(){
        return exp;
    }

    public int getNeededExp(){
        return neededExp;
    }

    public void addExp(int i){
        exp = exp + i;
    }

}
