package gg.quartzdev.ancientmystery.game.encounters;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.util.Loqqer;

import java.util.UUID;

public abstract class Encounter {

    public final AncientMystery plugin;
    public final Loqqer logger;
    public final RaidManager raidManager;
    public final UUID raidId;
    public EncounterState state;

    public Encounter(UUID raidId){
        this.plugin = AncientMystery.instance;
        this.logger = this.plugin.logger;
        this.raidManager = this.plugin.raidManager;
        this.raidId = raidId;
        this.state = EncounterState.IDLE;
    }
}
