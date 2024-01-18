package gg.quartzdev.ancientmystery.util;

import com.jeff_media.morepersistentdatatypes.DataType;
import gg.quartzdev.ancientmystery.AncientMystery;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;

import javax.xml.crypto.Data;
import java.util.UUID;

public class PdcUtil {

    final static NamespacedKey raidKey = new NamespacedKey(AncientMystery.instance, "raid-id");
    final static NamespacedKey dupeKey = new NamespacedKey(AncientMystery.instance, "dupe-fix");

    public static void brandMob(Entity entity, UUID raidId){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        pdc.set(raidKey, DataType.UUID, raidId);
    }

    public static UUID getMobBrand(Entity entity){
        PersistentDataContainer pdc = entity.getPersistentDataContainer();
        return pdc.get(raidKey, DataType.UUID);
    }

    public static void brandItemMeta(ItemMeta itemMeta, UUID raidId){
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        pdc.set(raidKey, DataType.UUID, raidId);
    }

    public static UUID getItemBrand(ItemStack item, UUID raidId){
        ItemMeta itemMeta = item.getItemMeta();
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        return pdc.get(raidKey, DataType.UUID);
    }

    public static UUID getItemMetaBrand(ItemMeta itemMeta, UUID raidId){
        PersistentDataContainer pdc = itemMeta.getPersistentDataContainer();
        return pdc.get(raidKey, DataType.UUID);
    }


}
