package gg.quartzdev.ancientmystery.game.encounters;

import org.bukkit.Location;
import org.bukkit.NamespacedKey;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.UUID;

public abstract class EncounterMob {

    UUID gameId;
    EntityType mobType;
    NamespacedKey encounterKey;
    World world;

    public EncounterMob(UUID gameId, EntityType mobType, NamespacedKey encounterKey){
        this.gameId = gameId;
        this.mobType = mobType;
        this.encounterKey = encounterKey;
    }

    public Entity spawn(Location location){
        return location.getWorld().spawnEntity(location, this.mobType);
    }

}
