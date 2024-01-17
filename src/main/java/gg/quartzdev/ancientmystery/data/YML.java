package gg.quartzdev.ancientmystery.data;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.util.Loqqer;
import gg.quartzdev.ancientmystery.util.Messages;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class YML {

    AncientMystery plugin;
    Loqqer logger;
    File file;
    YamlConfiguration config;
    String schemaVersion = "1.0";

    public YML(String fileName){
        this.plugin = AncientMystery.instance;
        this.logger = this.plugin.logger;
        this.file = loadFile(fileName);
    }

    private File loadFile(String name){
        File file = new File(plugin.getDataFolder(), name);
        try {
            if(file.createNewFile()){
                this.plugin.saveResource(name, true);
                logger.info(Messages.FILE_CREATED.parse("file", file.getName()));
            }
            this.config = YamlConfiguration.loadConfiguration(file);
            List<String> notes = new ArrayList<>();
            notes.add("Last loaded on " + plugin.getName() + " v" + plugin.getVersion());
            if(this.config.get("schema-version") == null)
                this.config.set("schema-version", this.schemaVersion);
            this.config.setComments("schema-version", notes);
            this.config.save(file);
        } catch(IOException e){
            logger.error(Messages.ERROR_CREATE_FILE.parse("file", file.getName()));
            return null;
        }
        return file;
    }
    public void saveFile() {
        Bukkit.getAsyncScheduler().runNow(plugin, scheduledTask -> {
            try {
                this.config.save(this.file);
            } catch(IOException e) {
                logger.error(Messages.ERROR_SAVE_FILE.parse("file", this.file.getName()));
            }
        });
    }

    public void reload(){
        this.loadFile(this.file.getName());
    }

}
