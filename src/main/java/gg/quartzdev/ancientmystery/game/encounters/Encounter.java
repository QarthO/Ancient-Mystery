package gg.quartzdev.ancientmystery.game.encounters;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.RaidManager;

import javax.xml.stream.Location;
import java.util.UUID;

public abstract class Encounter {

    public final AncientMystery plugin;
    public final RaidManager raidManager;
    public final UUID gameId;
    public EncounterState state;

    public Encounter(UUID gameId){
        this.plugin = AncientMystery.instance;
        this.raidManager = this.plugin.raidManager;
        this.gameId = gameId;
        this.state = EncounterState.IDLE;
    }
}
