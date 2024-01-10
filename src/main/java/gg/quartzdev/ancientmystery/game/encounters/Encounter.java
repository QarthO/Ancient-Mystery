package gg.quartzdev.ancientmystery.game.encounters;

import gg.quartzdev.ancientmystery.AncientMystery;

import javax.xml.stream.Location;
import java.util.UUID;

public abstract class Encounter {

    public final AncientMystery plugin;
    public final UUID gameId;
    public EncounterState state;

    public Encounter(UUID gameId){
        this.plugin = AncientMystery.getInstance();
        this.gameId = gameId;
        this.state = EncounterState.IDLE;
    }

    public void logic(){

    }

    public void spawnBlaze(){

    }



}
