package gg.quartzdev.ancientmystery.game.player;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;

public class GamePlayer {

    Player player;
    int maxHealth;
    int playerHealth;

    public GamePlayer(Player player){
        this.player = player;
        this.initHealth();
    }

    public void initHealth(){
        this.maxHealth = 200;
        this.playerHealth = maxHealth;
    }

    public void updateHealthBar(){
        MiniMessage mm = MiniMessage.miniMessage();
        Component component = mm.deserialize("<red>" + this.playerHealth + "<dark_red> ❤ / <red>" + this.maxHealth + "<dark_red> ❤");
        this.player.sendActionBar(component);
    }

    public void damage(int damage){
        this.playerHealth -= damage;
    }

    public void heal(int heal){
        this.playerHealth += heal;
    }

    public void kill(){
        this.playerHealth = 0;
        this.player.setHealth(0);
    }
}
