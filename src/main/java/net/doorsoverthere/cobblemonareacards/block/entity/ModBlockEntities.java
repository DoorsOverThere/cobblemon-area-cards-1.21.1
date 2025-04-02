package net.doorsoverthere.cobblemonareacards.block.entity;

import net.doorsoverthere.cobblemonareacards.CobblemonAreaCards;
import net.doorsoverthere.cobblemonareacards.block.ModBlocks;
import net.doorsoverthere.cobblemonareacards.block.entity.custom.AreaBlockBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<AreaBlockBlockEntity> AREA_BLOCK_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(CobblemonAreaCards.MOD_ID, "area_block_be"),
                    BlockEntityType.Builder.create(AreaBlockBlockEntity::new, ModBlocks.AREA_BLOCK).build(null));

    public static void RegisterBlockEntities() {
        CobblemonAreaCards.LOGGER.info("Registering Block Entities for " + CobblemonAreaCards.MOD_ID);
    }
}
