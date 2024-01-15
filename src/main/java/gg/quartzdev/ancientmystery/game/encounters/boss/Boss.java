package gg.quartzdev.ancientmystery.game.encounters.boss;

import com.jeff_media.morepersistentdatatypes.DataType;
import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.util.Loqqer;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;

import java.util.UUID;

public abstract class Boss {

    AncientMystery plugin;
    Loqqer logger;
    UUID gameId;

//    Boss Data
    Entity boss;
    final EntityType type;
    Location spawnLocation;

    public Boss(UUID gameId, @NotNull EntityType type, @NotNull Location spawnLocation){
        this.gameId = gameId;
        this.plugin = AncientMystery.instance;
        this.logger = this.plugin.logger;

        this.type = type;
        this.spawnLocation = spawnLocation;
    }

    public Entity spawn(){

//        Spawns the mob
        Entity boss = this.spawnLocation.getWorld().spawnEntity(this.spawnLocation, this.type);
//        Brands the mob with the game id
        PersistentDataContainer pdc = this.boss.getPersistentDataContainer();
        pdc.set(this.plugin.gameKey, DataType.UUID, this.gameId);
        return boss;
    }

}
