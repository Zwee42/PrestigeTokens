package me.zwee.prestigetokens.listeners;


import me.zwee.prestigetokens.TokenManager;
import me.zwee.prestigetokens.utils.Colour;
import me.zwee.prestigetokens.utils.ConfigManager;
import me.zwee.prestigetokens.utils.Message;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class PurchaseListener implements Listener {

	private final ConfigManager.Config shopConfig = ConfigManager.getFile("shop");
	TokenManager tokenManager = TokenManager.getInstance();

	private void BuyItem(Player player, String itemNum) {

		final double cost =  shopConfig.getConfig().getDouble("Shop.Content."+itemNum+".Cost");

		if (cost > tokenManager.getPlayerTokens(player)) {
			player.sendMessage(Colour.colour(Message.getMessage("NOTENOUGHTOKENS")));
			return;
		}

		final String itemName = shopConfig.getConfig().getString("Shop.Content."+itemNum+".DisplayName");
		final String command = shopConfig.getConfig().getString("Shop.Content." + itemNum + ".Command").replace("%player%", player.getName());

		tokenManager.takePlayerTokens(player, cost);
		player.sendMessage(Colour.colour(Message.getMessage("ONBUY").replace("%bought_item%", itemName).replace("%price%", cost+"")));
		player.getServer().dispatchCommand(Bukkit.getConsoleSender(), command);
	}

	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent e) {

		String title = e.getView().getTitle();
		ItemStack item = e.getCurrentItem();

		Player player = (Player) e.getWhoClicked();

		if (!title.equals(shopConfig.getConfig().getString("Shop.Name")) || item == null) return;

		e.setCancelled(true);

		for(String shopItemNum : shopConfig.getConfig().getConfigurationSection("Shop.Content").getKeys(false)) {

			int slot = shopConfig.getConfig().getInt("Shop.Content." + shopItemNum + ".Slot");

			if(e.getSlot() == slot) {
				BuyItem(player, shopItemNum);
				return;
			}
		}
	}
}