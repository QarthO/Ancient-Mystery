package gg.quartzdev.ancientmystery.util;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.block.banner.Pattern;
import org.bukkit.block.banner.PatternType;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BannerMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemStackUtil {

    static final Material backgroundBanner = Material.BLACK_BANNER;
    static final DyeColor backgroundColor = DyeColor.BLACK;

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


}
