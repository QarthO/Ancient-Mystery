package gg.quartzdev.ancientmystery.game.encounters;

import gg.quartzdev.ancientmystery.AncientMystery;

import javax.xml.stream.Location;

public abstract class Encounter {

    AncientMystery plugin;
    Location joinLocation;

    public Encounter(){
        this.plugin = AncientMystery.getInstance();
    }

    public void logic(){

    }

    public void spawnBlaze(){

    }



}
