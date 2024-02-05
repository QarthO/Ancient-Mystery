package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.Raid;
import gg.quartzdev.ancientmystery.game.RaidManager;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Optional;

public class CMDjoin extends qCMD {
    RaidManager raidManager;
    AncientMystery plugin;
    public CMDjoin(String cmdName, String group) {
        super(cmdName, group);
        this.raidManager = AncientMystery.instance.raidManager;
        this.plugin = AncientMystery.instance;
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player player)){
            Sender.message(sender, Messages.ERROR_PLAYER_ONLY_COMMAND);
            return false;
        }
        Optional<Raid> raid = raidManager.getRaids().stream().findFirst();
        if(raid.isEmpty()){
            Sender.message(player, Messages.NO_RAIDING_PARTY_FOUND);
            return false;
        }
        raid.get().addPlayer(player);
        Sender.message(player, Messages.JOINED_RAIDING_PARTY);
        return true;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
