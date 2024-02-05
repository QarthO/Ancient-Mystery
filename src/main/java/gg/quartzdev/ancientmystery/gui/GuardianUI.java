package gg.quartzdev.ancientmystery.gui;

import org.bukkit.event.inventory.InventoryClickEvent;

public class GuardianUI extends GamesInventory {

    public GuardianUI(){
        super();
        String title = "Encounter Editor: Guardian";
        this.createInventory(18, title);
    }

    @Override
    public void onClick(InventoryClickEvent event) {
        event.setCancelled(true);
    }
}
