package gg.quartzdev.ancientmystery.gui;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.util.Loqqer;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.jetbrains.annotations.NotNull;

public abstract class GamesInventory implements InventoryHolder {

    AncientMystery plugin;
    Inventory inventory;
    public Loqqer logger;
    public RaidManager raidManager;

    public GamesInventory(){
        this.plugin = AncientMystery.instance;
        this.logger = this.plugin.logger;
        this.raidManager = this.plugin.raidManager;
    }

    public void createInventory(int size, String titleMsg){
        Component title = MiniMessage.miniMessage().deserialize(titleMsg);
        this.inventory = Bukkit.createInventory(this, size, title);
    }

    @Override
    public @NotNull Inventory getInventory(){
        return this.inventory;
    }

    public abstract void onClick(InventoryClickEvent event);
}
