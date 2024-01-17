package gg.quartzdev.ancientmystery.commands;

import gg.quartzdev.ancientmystery.AncientMystery;
import gg.quartzdev.ancientmystery.game.GameManager;
import gg.quartzdev.ancientmystery.util.Messages;
import gg.quartzdev.ancientmystery.util.Sender;
import org.bukkit.Difficulty;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CMDstart extends qCMD {

    AncientMystery plugin;
    GameManager gameManager;

    public CMDstart(String cmdName, String group) {
        super(cmdName, group);
        this.plugin = AncientMystery.instance;
        this.gameManager = this.plugin.gameManager;

    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        if(!(sender instanceof Player player)) {
            Sender.message(sender, Messages.ERROR_PLAYER_ONLY_COMMAND);
            return false;
        }
        gameManager.create(Difficulty.NORMAL, player);
        player.sendMessage("Game created, join");
        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
