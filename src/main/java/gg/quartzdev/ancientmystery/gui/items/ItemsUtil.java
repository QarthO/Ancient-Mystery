package gg.quartzdev.ancientmystery.gui.items;

import gg.quartzdev.ancientmystery.util.PdcUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ItemsUtil {

    static final Material backgroundBanner = Material.BLACK_BANNER;
    static final DyeColor backgroundColor = DyeColor.BLACK;
    static final MiniMessage mm = MiniMessage.miniMessage();

    public static ItemStack leftArrow(DyeColor arrowColor){
        ItemStack banner = new ItemStack(backgroundBanner);
        BannerMeta bannerMeta = (BannerMeta) banner.getItemMeta();

        List<Pattern> patterns = new ArrayList<>();
        patterns.add(new Pattern(arrowColor, PatternType.STRIPE_RIGHT));
        patterns.add(new Pattern(arrowColor, PatternType.RHOMBUS_MIDDLE));
        patterns.add(new Pattern(backgroundColor, PatternType.SQUARE_TOP_RIGHT));
        patterns.add(new Pattern(backgroundColor, PatternType.SQUARE_BOTTOM_RIGHT));
        patterns.add(new Pattern(backgroundColor, PatternType.BORDER));
        bannerMeta.setPatterns(patterns);
        banner.setItemMeta(bannerMeta);
        return banner;
    }

    public static ItemStack rightArrow(DyeColor arrowColor){
        ItemStack banner = new ItemStack(backgroundBanner);
        BannerMeta bannerMeta = (BannerMeta) banner.getItemMeta();

        List<Pattern> patterns = new ArrayList<>();
        patterns.add(new Pattern(arrowColor, PatternType.STRIPE_LEFT));
        patterns.add(new Pattern(arrowColor, PatternType.RHOMBUS_MIDDLE));
        patterns.add(new Pattern(backgroundColor, PatternType.SQUARE_TOP_LEFT));
        patterns.add(new Pattern(backgroundColor, PatternType.SQUARE_BOTTOM_LEFT));
        patterns.add(new Pattern(backgroundColor, PatternType.BORDER));
        bannerMeta.setPatterns(patterns);
        banner.setItemMeta(bannerMeta);
        return banner;
    }

    public static ItemStack createRaid(){
        ItemStack item = new ItemStack(Material.SOUL_CAMPFIRE);
        ItemMeta itemMeta = item.getItemMeta();
        PdcUtil.brandItemMeta(itemMeta, ItemAction.CREATE_RAID);

        Component displayName = mm.deserialize("<dark_purple>Create Raid").decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(displayName);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack startRaid(UUID raidId){
        ItemStack item = new ItemStack(Material.END_CRYSTAL);
        ItemMeta itemMeta = item.getItemMeta();
        PdcUtil.brandItemMeta(itemMeta, raidId);
        PdcUtil.brandItemMeta(itemMeta, ItemAction.START_RAID);
        Component displayName = mm.deserialize("<green>Start Raid").decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(displayName);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack pauseRaid(UUID raidId){
        ItemStack item = new ItemStack(Material.STRUCTURE_VOID);
        ItemMeta itemMeta = item.getItemMeta();
        PdcUtil.brandItemMeta(itemMeta, raidId);
        Component displayName = mm.deserialize("<yellow>Pause Raid").decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(displayName);
        item.setItemMeta(itemMeta);
        return item;
    }

    public static ItemStack leaveRaid(UUID raidId){
        ItemStack item = new ItemStack(Material.BARRIER);
        ItemMeta itemMeta = item.getItemMeta();
        PdcUtil.brandItemMeta(itemMeta, raidId);
        Component displayName = mm.deserialize("<red>Leave Raid").decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(displayName);
        item.setItemMeta(itemMeta);
        return item;
    }

}
