package me.zwee.prestigetokens.listeners;

import com.mysql.jdbc.Messages;
import me.drawethree.ultraprisoncore.UltraPrisonCore;
import me.drawethree.ultraprisoncore.prestiges.api.events.PlayerPrestigeEvent;
import me.zwee.prestigetokens.TokenManager;
import me.zwee.prestigetokens.utils.Colour;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UPCPrestigeListener implements Listener {

    private final TokenManager tokenManager = TokenManager.getInstance();

    @EventHandler
    public void onPrestige(PlayerPrestigeEvent event){

        OfflinePlayer offlinePlayer = event.getPlayer();

        final double tokensToAdd = tokenManager.getTokensPerPrestige();
        tokenManager.addPlayerTokens(offlinePlayer, tokensToAdd);

        if(Bukkit.getPlayer(offlinePlayer.getUniqueId()) != null){
            Player player = (Player)offlinePlayer;
            player.sendMessage(Colour.colour(Messages.getString("TOKENS_RECEIVED").replace("%amount%",  String.valueOf(tokensToAdd))));
        }



    }
}
