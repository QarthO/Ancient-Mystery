package gg.quartzdev.ancientmystery.util;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LootUtil {
    public static ItemStack guardianMinionLoot(int count){
        ItemStack item = new ItemStack(Material.GLOWSTONE_DUST, count);
        ItemMeta itemMeta = item.getItemMeta();
        Component component = Component.text("Charge Dust").decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(component);
        item.setItemMeta(itemMeta);
        return item;
    }
}
