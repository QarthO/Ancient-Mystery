package gg.quartzdev.ancientmystery;

import gg.quartzdev.ancientmystery.commands.CommandManager;
import gg.quartzdev.ancientmystery.data.Confiq;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.game.encounters.guardian.GuardianListeners;
import gg.quartzdev.ancientmystery.listeners.PlayerListener;
import gg.quartzdev.ancientmystery.util.Loqqer;
import org.bstats.bukkit.Metrics;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

public final class AncientMystery extends JavaPlugin {

    public static AncientMystery instance;
    public Loqqer logger;
    public Confiq config;
    public NamespacedKey gameKey;
    public RaidManager raidManager;


    @Override
    public void onEnable() {
        instance = this;
        this.logger = new Loqqer();
        this.config = new Confiq();
        this.gameKey = new NamespacedKey(instance, "game-id");
        this.raidManager = new RaidManager();

        this.enableMetrics();
        this.registerEvents();

        new CommandManager(this.getName().toLowerCase());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void enableMetrics(){
        int pluginId = 20709;
        Metrics metrics = new Metrics(instance, pluginId);
    }

    public void registerEvents(){
        Bukkit.getPluginManager().registerEvents(new PlayerListener(), this);
        Bukkit.getPluginManager().registerEvents(new GuardianListeners(), this);
    }

    @SuppressWarnings("UnstableApiUsage")
    public String getVersion(){
        return instance.getPluginMeta().getVersion();
    }


}
