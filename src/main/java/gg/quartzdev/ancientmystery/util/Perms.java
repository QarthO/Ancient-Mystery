package gg.quartzdev.ancientmystery.util;

public enum Perms {

//    GROUPS
    GROUP_PLAYER("ancientmystery.player"),
    GROUP_MOD("ancientmystery.mod"),
    GROUP_ADMIN("ancientmystery.admin"),

    COMMAND_JOIN("ancientmystery.command.join");

    private String permission;

    Perms(String permission){
        this.permission = permission;
    }

    public String get(){
        return this.permission;
    }

    @Override
    public String toString(){
        return this.permission;
    }

}
