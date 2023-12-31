package com.github.minersstudios.msitems.commands;

import com.github.minersstudios.mscore.utils.ChatUtils;
import com.github.minersstudios.msitems.MSItems;
import net.kyori.adventure.key.Keyed;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.Recipe;
import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

import static com.github.minersstudios.mscore.MSCore.getConfigCache;

public class ReloadCommand {

	public static void runCommand(@NotNull CommandSender sender) {
		long time = System.currentTimeMillis();
		Iterator<Recipe> crafts = Bukkit.recipeIterator();
		while (crafts.hasNext()) {
			Recipe recipe = crafts.next();
			if (recipe instanceof Keyed keyed && keyed.key().namespace().equals("msitems")) {
				Bukkit.removeRecipe(new NamespacedKey(MSItems.getInstance(), keyed.key().value()));
			}
		}
		getConfigCache().customItemMap.clear();
		getConfigCache().customItemRecipes.clear();
		getConfigCache().renameableItemMap.clear();
		getConfigCache().renameableItemsMenu.clear();
		MSItems.reloadConfigs();
		if (MSItems.getInstance().isEnabled()) {
			ChatUtils.sendFine(sender, Component.text("Плагин был успешно перезагружен за " + (System.currentTimeMillis() - time) + "ms"));
			return;
		}
		ChatUtils.sendError(sender, Component.text("Плагин был перезагружен неудачно"));
	}
}
