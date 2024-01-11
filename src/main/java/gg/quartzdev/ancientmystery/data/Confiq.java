package gg.quartzdev.ancientmystery.data;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.util.Loqqer;
import org.bukkit.configuration.file.FileConfiguration;

public class Confiq {

    AncientMystery plugin;
    Loqqer logger;
    FileConfiguration file;

    public Confiq(){
        this.plugin = AncientMystery.getInstance();
        this.logger = plugin.logger;

        this.file = plugin.getConfig();
        this.plugin.saveDefaultConfig();

        this.loadAll();
    }

    private void save() {
        this.plugin.saveConfig();
    }

    public void reload() {
        this.plugin.reloadConfig();
        this.file = this.plugin.getConfig();
        this.save();
        this.loadAll();
    }
    private void loadAll() {
//        Config logic here
    }

}
