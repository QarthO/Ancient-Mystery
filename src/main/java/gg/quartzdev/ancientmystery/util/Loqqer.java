package gg.quartzdev.ancientmystery.util;

import org.bukkit.Bukkit;

public class Loqqer {
    /**
     * Logs a general message
     * @param msg - String to log
     */
    public void info(String msg){
            Sender.message(Bukkit.getConsoleSender(), msg);
    }
    /**
     * Logs a general message
     * @param msg - Message to log
     */
    public void info(Messages msg){
        Sender.message(Bukkit.getConsoleSender(), msg);
    }

    /**
     * Logs a warning
     * @param msg
     */
    public void warning(String msg){
        info("<yellow>" + msg);
//        TODO: Optional log warnings to file
    }

    /**
     * Logs error
     * @param msg
     */
    public void error(String msg){
        info("<red>" + msg);
//        TODO: Optional log errors to file
    }

    public void error(Messages msg){
        info("<red>" + msg.get());
//        TODO: Optional log errors to file
    }

    public void error(StackTraceElement[] error){
        for(StackTraceElement e : error){
            error(e.toString());
        }
    }

}
