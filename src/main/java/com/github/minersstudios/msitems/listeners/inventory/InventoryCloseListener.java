package com.github.minersstudios.msitems.listeners.inventory;

import com.github.minersstudios.mscore.MSListener;
import com.github.minersstudios.msitems.items.RenameableItem;
import org.bukkit.entity.HumanEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

@MSListener
public class InventoryCloseListener implements Listener {

	@EventHandler
	public void onInventoryClose(@NotNull InventoryCloseEvent event) {
		if (event.getView().title().contains(RenameableItem.Menu.RENAME_SELECTION_NAME)) {
			ItemStack itemStack = event.getInventory().getItem(RenameableItem.Menu.currentRenameableItemSlot);
			if (itemStack != null) {
				HumanEntity player = event.getPlayer();
				PlayerInventory playerInventory = player.getInventory();
				Map<Integer, ItemStack> map = playerInventory.addItem(itemStack);
				if (!map.isEmpty()) {
					player.getWorld().dropItemNaturally(player.getLocation().add(0.0d, 0.5d, 0.0d), itemStack);
				}
			}
		}
	}
}
