package me.zwee.prestigetokens.gui;

import me.zwee.prestigetokens.PrestigeTokens;
import me.zwee.prestigetokens.utils.ConfigManager;
import me.zwee.prestigetokens.utils.ItemStackBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class ShopGUI {

	private static final ConfigManager.Config shopConfig = ConfigManager.getFile("Shop");
	private static final PrestigeTokens plugin = PrestigeTokens.getInstance();

	public Inventory getShopGUI() {

	Inventory shopGui = plugin.getServer().createInventory(null , 45, shopConfig.getConfig().getString("Shop.Name"));

		for(String item : shopConfig.getConfig().getConfigurationSection("Shop").getKeys(false)){
			shopGui.setItem(shopConfig.getConfig().getInt("Shop." + item + ".Slot"),
					new ItemStackBuilder()
					.displayName(shopConfig.getConfig().getString("Shop." + item + ".DisplayName"))
					.material(Material.valueOf(shopConfig.getConfig().getString("Shop." + item + ".Material")))
					.data((byte) shopConfig.getConfig().getInt("Shop." + item + ".Data"))
					.lore(shopConfig.getConfig().getStringList("Shop." + item + ".Description"))
					.buildItemStack());
		}
		for(int slot = 0; slot < shopGui.getSize() ; slot++){
			ItemStack filler =  new ItemStackBuilder()
					.displayName(shopConfig.getConfig().getString("Shop.Filler.DisplayName"))
					.data((byte) shopConfig.getConfig().getInt("Shop.Filler.Data"))
					.lore(shopConfig.getConfig().getStringList("Shop.Filler.Lore"))
					.material(Material.valueOf(shopConfig.getConfig().getString("Shop.Filler.Material")))
					.buildItemStack();
		shopGui.setItem(slot , filler);
		}
		return shopGui;
	}
}