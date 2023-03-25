package com.github.minersstudios.msitems.listeners.mechanic;

import com.github.minersstudios.mscore.MSListener;
import com.github.minersstudios.mscore.utils.MSItemUtils;
import com.github.minersstudios.msitems.MSItems;
import com.github.minersstudios.msitems.items.register.items.Cocaine;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.jetbrains.annotations.NotNull;

@MSListener
public class CocaineMechanic implements Listener {

	@EventHandler
	public void onInventoryClick(@NotNull PlayerItemConsumeEvent event) {
		ItemStack itemStack = event.getItem();
		if (
				!(itemStack.getItemMeta() instanceof PotionMeta)
				|| !(MSItemUtils.getCustomItem(itemStack) instanceof Cocaine)
		) return;
		Bukkit.getScheduler().runTaskAsynchronously(MSItems.getInstance(),
				() -> event.getPlayer().getInventory().getItem(event.getHand()).setAmount(0)
		);
	}
}
