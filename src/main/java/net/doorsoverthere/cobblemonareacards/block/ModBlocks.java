package net.doorsoverthere.cobblemonareacards.block;

import net.doorsoverthere.cobblemonareacards.CobblemonAreaCards;
import net.doorsoverthere.cobblemonareacards.block.custom.AreaBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block AREA_BLOCK = registerBlock("area_block", new AreaBlock(AbstractBlock.Settings.create()
            .nonOpaque()
            .strength(4f)
            .requiresTool()
            .sounds(BlockSoundGroup.STONE)
    ));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(CobblemonAreaCards.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(CobblemonAreaCards.MOD_ID, name), new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        CobblemonAreaCards.LOGGER.info("Registering Mod Blocks for " + CobblemonAreaCards.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.FUNCTIONAL).register(entries -> {
            entries.add(ModBlocks.AREA_BLOCK);
        });
    }
}
