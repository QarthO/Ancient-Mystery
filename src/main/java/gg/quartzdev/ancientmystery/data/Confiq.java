package gg.quartzdev.ancientmystery.data;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.util.Loqqer;
import gg.quartzdev.ancientmystery.util.ReadUtil;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;

public class Confiq {

    AncientMystery plugin;
    Loqqer logger;
    FileConfiguration file;

    public Confiq(){
        this.plugin = AncientMystery.instance;
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
        this.loadPlayerHealth();
        this.loadMaxPlayers();
        this.loadMinPlayers();
        this.loadStartLocation();
    }

//    Player Health

    private double playerHealth = 200;
    private final String PATH_PLAYER_HEALTH = "player-health";

    public double getPlayerHealth(){
        return this.playerHealth;
    }

    public void loadPlayerHealth(){
        Object data = this.file.get(this.PATH_PLAYER_HEALTH);
        this.playerHealth = ReadUtil.number(data).doubleValue();
    }
    public void setPlayerHealth(double value){
        this.playerHealth = value;
        this.file.set(PATH_PLAYER_HEALTH, value);
        this.save();
    }

//    Min/Max Players
    //    Player Health

    private int minPlayers = 2;
    private int maxPlayers = 4;
    private final String PATH_MIN_PLAYERS = "min-players";
    private final String PATH_MAX_PLAYERS = "max-players";

    public double getMinPlayers(){
        return this.minPlayers;
    }
    public double getMaxPlayers(){
        return this.maxPlayers;
    }

    public void loadMinPlayers(){
        Object data = this.file.get(this.PATH_MIN_PLAYERS);
        this.minPlayers = ReadUtil.number(data).intValue();
    }

    public void loadMaxPlayers(){
        Object data = this.file.get(this.PATH_MAX_PLAYERS);
        this.maxPlayers = ReadUtil.number(data).intValue();
    }

    public void setMinPlayers(int value){
        this.minPlayers = value;
        this.file.set(PATH_MIN_PLAYERS, value);
        this.save();
    }
    public void setMaxPlayers(int value){
        this.maxPlayers = value;
        this.file.set(PATH_MAX_PLAYERS, value);
        this.save();
    }

    private Location startLocation;
    private final String PATH_START_LOCATION = "start-location";

    public void loadStartLocation(){
        this.startLocation = this.file.getLocation(this.PATH_START_LOCATION);
    }

    public Location getStartLocation(){
        return this.startLocation;
    }

    public void setStartLocation(Location location){
        this.startLocation = location;
        this.file.set(this.PATH_START_LOCATION, location);
        this.save();
    }


}
