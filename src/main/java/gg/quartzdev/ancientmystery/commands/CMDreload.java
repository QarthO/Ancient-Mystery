package gg.quartzdev.ancientmystery.commands;

import org.bukkit.command.CommandSender;

public class CMDreload extends qCMD{
    public CMDreload(String cmdName, String group) {
        super(cmdName, group);
    }

    @Override
    public boolean logic(CommandSender sender, String label, String[] args) {
        return false;
    }

    @Override
    public Iterable<String> tabCompletionLogic(CommandSender sender, String[] args) {
        return null;
    }
}
