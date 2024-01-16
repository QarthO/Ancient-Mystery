package gg.quartzdev.ancientmystery.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;

import java.util.UUID;

public class PdcUtil {

    public static UUID getMobBrand(NamespacedKey key, Entity entity){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.get(key, DataType.UUID);
    }

}
