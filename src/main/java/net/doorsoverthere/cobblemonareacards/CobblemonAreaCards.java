package net.doorsoverthere.cobblemonareacards;

import net.doorsoverthere.cobblemonareacards.block.ModBlocks;
import net.doorsoverthere.cobblemonareacards.block.entity.ModBlockEntities;
import net.doorsoverthere.cobblemonareacards.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
public class CobblemonAreaCards implements ModInitializer {
	public static final String MOD_ID = "cobblemonareacards";

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModBlocks.registerModBlocks();

		ModBlockEntities.RegisterBlockEntities();
		ModScreenHandlers.registerScreenHandlers();
	}
}