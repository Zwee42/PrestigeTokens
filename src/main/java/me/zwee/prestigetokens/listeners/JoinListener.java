package me.zwee.prestigetokens.listeners;

import me.zwee.prestigetokens.TokenManager;
import me.zwee.prestigetokens.utils.ConfigManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

	private final ConfigManager.Config dataConfig = ConfigManager.getFile("data");

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player player = e.getPlayer();
		if (dataConfig.getConfig().getString("Tokens."+player.getUniqueId()) == null) {
			TokenManager.getInstance().setPlayerTokens(player, 0);
		}
	}
}
