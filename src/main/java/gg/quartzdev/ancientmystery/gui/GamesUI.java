package gg.quartzdev.ancientmystery.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public class GamesUI extends GamesInventory {

    public GamesUI(){
        super();
        String title = "Raids";
        this.createInventory(27, title);
    }

    public void fill(){
//        for()
    }


    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
