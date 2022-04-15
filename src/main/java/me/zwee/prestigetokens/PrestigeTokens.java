package me.zwee.prestigetokens;


import lombok.Getter;
import me.zwee.prestigetokens.commands.PrestigeTokensCommand;
import me.zwee.prestigetokens.listeners.PRXPrestigeListener;
import me.zwee.prestigetokens.listeners.PurchaseListener;
import me.zwee.prestigetokens.listeners.JoinListener;
import me.zwee.prestigetokens.utils.ConfigManager;
import me.zwee.prestigetokens.utils.commands.CommandManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class PrestigeTokens extends JavaPlugin {

    @Getter
    private static PrestigeTokens instance;



    @Override
    public void onEnable() {
        instance = this;
        new ConfigManager(this).RegisterConfigs();

        new PrestigeTokensCommand().RegisterCommand();
        new CommandManager(this).SetupCommands();

//        new TokenManager(this);

        getServer().getPluginManager().registerEvents(new PRXPrestigeListener(), this);
        getServer().getPluginManager().registerEvents(new JoinListener(), this);
        getServer().getPluginManager().registerEvents(new PurchaseListener(), this);


    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
