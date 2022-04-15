package me.zwee.prestigetokens;

import me.zwee.prestigetokens.utils.ConfigManager;
import org.bukkit.OfflinePlayer;

public class TokenManager {

    private final ConfigManager.Config dataConfig = ConfigManager.getFile("data");
    private  final PrestigeTokens plugin = PrestigeTokens.getInstance();

    public static TokenManager getInstance(){
        return new TokenManager();
    }

    public double getTokensPerPrestige(){
        return plugin.getConfig().getDouble("TokensPerPrestige");
    }

    public double getPlayerTokens(OfflinePlayer player){
        return dataConfig.getConfig().getDouble("Tokens."+player.getUniqueId());
    }

    public void setPlayerTokens(OfflinePlayer player, double amount){
        dataConfig.getConfig().set("Tokens."+player.getUniqueId(), amount);
        dataConfig.saveConfig();
    }
    public void addPlayerTokens(OfflinePlayer player, double amount){
        dataConfig.getConfig().set("Tokens."+player.getUniqueId(), getPlayerTokens(player)+amount);
        dataConfig.saveConfig();
    }
    public void takePlayerTokens(OfflinePlayer player, double amount){
            dataConfig.getConfig().set("Tokens."+player.getUniqueId(), getPlayerTokens(player)-amount);
            dataConfig.saveConfig();
    }

}
