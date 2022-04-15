package me.zwee.prestigetokens.listeners;


import me.prisonranksx.events.AsyncPrestigeMaxEvent;
import me.prisonranksx.events.PrestigeUpdateEvent;
import me.zwee.prestigetokens.TokenManager;
import me.zwee.prestigetokens.utils.Colour;
import me.zwee.prestigetokens.utils.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;



public class PRXPrestigeListener implements Listener{
	
	private final ConfigManager.Config messagesConfig = ConfigManager.getFile("messages");
	private final TokenManager tokenManager = TokenManager.getInstance();

	@EventHandler
	public void onPrestige(PrestigeUpdateEvent e) {
		Player player = e.getPlayer();

		final double tokensToAdd = tokenManager.getTokensPerPrestige();
		tokenManager.addPlayerTokens(player, tokensToAdd);
		player.sendMessage(Colour.colour(messagesConfig.getConfig().getString("TOKENS_RECEIVED").replace("%amount%",  String.valueOf(tokensToAdd))));
	}
	
	@EventHandler
	public void onPrestigeMax(AsyncPrestigeMaxEvent e) {
	
		Player player = e.getPlayer();
		final int prePrestigeAmount = Integer.parseInt(e.getFromPrestige().substring(1));
		final int postPrestigeAmount = Integer.parseInt(e.getFinalPrestige().substring(1));
		final int prestigeAmount = postPrestigeAmount - prePrestigeAmount;
		final double tokensToAdd = tokenManager.getTokensPerPrestige() * prestigeAmount;
		tokenManager.setPlayerTokens(player, tokensToAdd);
		player.sendMessage(Colour.colour(messagesConfig.getConfig().getString("TOKENS_RECEIVED").replace("%amount%",  String.valueOf(tokensToAdd))));
	}
	
	

	
}
