package me.FyrenDev.AdminGUI;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {




    @Override
    public void onEnable(){
        this.getCommand("AdminMenu").setExecutor(new commandHandler());
        getServer().getPluginManager().registerEvents(new eventHandler(), this);
    }


    @Override
    public void onDisable(){

    }


}
