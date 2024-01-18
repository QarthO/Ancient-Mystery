package gg.quartzdev.ancientmystery.gui;

import com.jeff_media.morepersistentdatatypes.DataType;
import gg.quartzdev.ancientmystery.game.Raid;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class GamesUI extends GamesInventory {

    public GamesUI(Player player){
        super();
        String title = "Raids";
        this.createInventory(18, title);

        this.fillGames();
        this.fillActionRow();

        player.openInventory(this.getInventory());
    }

    public void fillGames(){
        int slot = 9;
        for(Raid raid : this.raidManager.getRaids()){
            Player player = raid.getCreator();
            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setOwningPlayer(raid.getCreator());
            Component displayName = Component.text(player.getName() + "'s Raid");
            skullMeta.displayName(displayName);
            skullMeta.getPersistentDataContainer().set(this.plugin.gameKey, DataType.UUID, raid.getId());
            item.setItemMeta(skullMeta);
            this.getInventory().setItem(slot++, item);
        }
    }

    public void fillActionRow(){

//        ItemStack leftArrow = ItemStackUtil.leftArrow(DyeColor.RED);
//        this.getInventory().setItem(3, leftArrow);
//        ItemStack rightArrow = ItemStackUtil.rightArrow(DyeColor.RED);
//        this.getInventory().setItem(5, rightArrow);

//        Middle (Start new Raid Item)
        ItemStack item = new ItemStack(Material.END_CRYSTAL);
        ItemMeta itemMeta = item.getItemMeta();
        Component displayName = Component.text("Start Raid").decoration(TextDecoration.ITALIC, false);
        itemMeta.displayName(displayName);
        item.setItemMeta(itemMeta);
        this.getInventory().setItem(4, item);
    }


    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        if(event.getCurrentItem() == null){
            return;
        }
        ItemStack clickedItem = event.getCurrentItem();
        UUID gameId = clickedItem.getItemMeta().getPersistentDataContainer().get(this.plugin.gameKey, DataType.UUID);
        if(gameId == null){
            return;
        }
        this.raidManager.startRaid(gameId);
    }
}
