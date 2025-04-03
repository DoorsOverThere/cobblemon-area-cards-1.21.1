package net.doorsoverthere.cobblemonareacards;

import net.doorsoverthere.cobblemonareacards.screen.ModScreenHandlers;
import net.doorsoverthere.cobblemonareacards.screen.custom.AreaBlockScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class CobblemonAreaCardsClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.AREA_BLOCK_SCREEN_HANDLER, AreaBlockScreen::new);
    }
}
