package gg.quartzdev.ancientmystery.game.encounters.boss;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.util.Loqqer;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class Boss {

    AncientMystery plugin;
    Loqqer logger;
    UUID gameId;

    final EntityType type;
    Location spawnLocation;

    public Boss(UUID gameId, @NotNull EntityType type, Location spawnLocation){
        this.gameId = gameId;
        this.plugin = AncientMystery.getInstance();
        this.logger = this.plugin.logger;

        this.type = type;
        this.spawnLocation = spawnLocation;
    }

    public Entity spawn(){
        Entity boss = this.spawnLocation.getWorld().spawnEntity(this.spawnLocation, this.type);
        PdcUtil.brandMob();

    }

}
