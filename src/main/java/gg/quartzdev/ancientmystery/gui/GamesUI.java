package gg.quartzdev.ancientmystery.gui;

import com.jeff_media.morepersistentdatatypes.DataType;
import gg.quartzdev.ancientmystery.game.Raid;
import gg.quartzdev.ancientmystery.gui.items.ItemAction;
import gg.quartzdev.ancientmystery.gui.items.ItemsUtil;
import gg.quartzdev.ancientmystery.util.PdcUtil;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Difficulty;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.UUID;

public class GamesUI extends GamesInventory {

    public GamesUI(Player player){
        super();
        String title = "Raids";
        this.createInventory(18, title);

        UUID raidId = PdcUtil.getEntityBrand(player);
        ItemStack actionItem;
        if(raidId == null || raidManager.getRaid(raidId) == null){
            actionItem = ItemsUtil.createRaid();
            ItemMeta itemMeta = actionItem.getItemMeta();
            actionItem.setItemMeta(itemMeta);

        } else {
            this.fillParty(raidManager.getRaid(raidId));
            actionItem = ItemsUtil.startRaid(raidId);
        }
        this.getInventory().setItem(4, actionItem);
//        player.closeInventory(InventoryCloseEvent.Reason.OPEN_NEW);
        player.openInventory(this.getInventory());
    }

    public void fillParty(Raid raid){
        int slot = 9;

        for(Player player : raid.getRaidParty()){
            ItemStack item = new ItemStack(Material.PLAYER_HEAD);
            SkullMeta skullMeta = (SkullMeta) item.getItemMeta();
            skullMeta.setOwningPlayer(player);
            Component displayName = Component.text(player.getName()).decoration(TextDecoration.ITALIC, false);
            skullMeta.displayName(displayName);
            item.setItemMeta(skullMeta);
            this.getInventory().setItem(slot++, item);
        }
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
        ItemStack clickedItem = event.getCurrentItem();
        if(clickedItem == null){
            return;
        }
        ItemAction itemAction = PdcUtil.getItemAction(clickedItem);
        event.getWhoClicked().sendMessage("ItemAction: " + itemAction);
        if(itemAction == null){
            return;
        }
        if(itemAction.equals(ItemAction.CREATE_RAID)){
            Raid raid = raidManager.create(Difficulty.NORMAL, (Player) event.getWhoClicked());
            ItemStack actionItem = ItemsUtil.startRaid(raid.getId());
            this.getInventory().setItem(4, actionItem);
            fillParty(raid);
            return;
        }

        if(itemAction.equals(ItemAction.START_RAID)){
            UUID raidId = PdcUtil.getEntityBrand(event.getWhoClicked());
            if(raidId == null){
                return;
            }
            this.raidManager.startRaid(raidId);
            event.getWhoClicked().closeInventory();
        }


    }
}
