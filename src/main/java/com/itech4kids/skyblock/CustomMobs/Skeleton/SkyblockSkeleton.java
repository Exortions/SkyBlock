package com.itech4kids.skyblock.CustomMobs.Skeleton;

import com.itech4kids.skyblock.CustomMobs.SEntity;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;

public class SkyblockSkeleton extends SEntity {

    public SkyblockSkeleton(SkyblockSkeletonType skeletonType, Location spawnLocation) {
        super(EntityType.SKELETON);
    }
}
