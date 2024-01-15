package gg.quartzdev.ancientmystery.game.encounters.boss;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public class BossGuardian extends Boss {

    int shields;
    public BossGuardian(UUID gameId, @NotNull Location spawnLocation) {
        super(gameId, EntityType.ELDER_GUARDIAN, spawnLocation);
    }

}
