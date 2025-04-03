package net.doorsoverthere.cobblemonareacards.screen;

import net.doorsoverthere.cobblemonareacards.CobblemonAreaCards;
import net.doorsoverthere.cobblemonareacards.screen.custom.AreaBlockScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<AreaBlockScreenHandler> AREA_BLOCK_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(CobblemonAreaCards.MOD_ID, "area_block_screen_handler"),
                    new ExtendedScreenHandlerType<>(AreaBlockScreenHandler::new, BlockPos.PACKET_CODEC));

    public static void registerScreenHandlers() {
        CobblemonAreaCards.LOGGER.info("Registering Screen Handlers for " + CobblemonAreaCards.MOD_ID);
    }
}
