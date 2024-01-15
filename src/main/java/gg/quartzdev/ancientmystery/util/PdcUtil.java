package gg.quartzdev.ancientmystery.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.UUID;

public class PdcUtil {

    public static NamespacedKey brandKey = new NamespacedKey()

    /**
     * Assigns the Game ID to a mob
     * @param key - the namespace key for the encounter
     * @param entity - the mob to assign the game id
     * @param gameId - the game id the mob was spawned in
     */
    public static void brandMob(NamespacedKey key, Entity entity, UUID gameId){
    }

    public static UUID getMobBrand(NamespacedKey key, Entity entity){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.get(key, DataType.UUID);
    }

}
