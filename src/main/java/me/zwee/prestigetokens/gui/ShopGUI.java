package me.zwee.prestigetokens.gui;

import me.zwee.prestigetokens.PrestigeTokens;
import me.zwee.prestigetokens.utils.ConfigManager;
import me.zwee.prestigetokens.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopGUI {

	private static final ConfigManager.Config shopConfig = ConfigManager.getFile("shop");
	private static final PrestigeTokens plugin = PrestigeTokens.getInstance();

	public Inventory getShopGUI() {

	Inventory shopGui = plugin.getServer().createInventory(null , 45, shopConfig.getConfig().getString("Shop.Name"));

		for(String item : shopConfig.getConfig().getConfigurationSection("Shop.Content").getKeys(false)){
			shopGui.setItem(shopConfig.getConfig().getInt("Shop.Content." + item + ".Slot"),
					new ItemStackBuilder()
					.displayName(shopConfig.getConfig().getString("Shop.Content." + item + ".DisplayName"))
					.material(Material.valueOf(shopConfig.getConfig().getString("Shop.Content." + item + ".Material")))
					.data((byte) shopConfig.getConfig().getInt("Shop.Content." + item + ".Data"))
					.lore(shopConfig.getConfig().getStringList("Shop.Content." + item + ".Description"))
					.buildItemStack());
		}

		ItemStack filler =  new ItemStackBuilder()
				.displayName(shopConfig.getConfig().getString("Shop.Filler.DisplayName"))
				.data((byte) shopConfig.getConfig().getInt("Shop.Filler.Data"))
				.lore(shopConfig.getConfig().getStringList("Shop.Filler.Lore"))
				.material(Material.valueOf(shopConfig.getConfig().getString("Shop.Filler.Material")))
				.buildItemStack();

		for(int slot = 0; slot < shopGui.getSize(); slot++){
			if(shopGui.getItem(slot) == null) shopGui.setItem(slot , filler);
		}
		return shopGui;
	}
}