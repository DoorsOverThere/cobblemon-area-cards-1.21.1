package net.doorsoverthere.cobblemonareacards.block.custom;

import com.mojang.serialization.MapCodec;
import net.doorsoverthere.cobblemonareacards.block.entity.custom.AreaBlockBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemActionResult;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class AreaBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<AreaBlock> CODEC = AreaBlock.createCodec(AreaBlock::new);

    public AreaBlock(Settings settings) {
        super(settings);
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Nullable
    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new AreaBlockBlockEntity(pos, state);
    }

    @Override
    protected BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if(state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if(blockEntity instanceof AreaBlockBlockEntity) {
                ItemScatterer.spawn(world, pos, (AreaBlockBlockEntity) blockEntity);
                world.updateComparators(pos, this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    protected ItemActionResult onUseWithItem(ItemStack stack, BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.getBlockEntity(pos) instanceof AreaBlockBlockEntity areaBlockBlockEntity) {
            if(areaBlockBlockEntity.isEmpty() && !stack.isEmpty()) {
                areaBlockBlockEntity.setStack(0, stack.copyWithCount(1));
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 2f);
                stack.decrement(1);

                areaBlockBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            } else if(stack.isEmpty() && !player.isSneaking()) {
                ItemStack stackOnPedestal = areaBlockBlockEntity.getStack(0);
                player.setStackInHand(Hand.MAIN_HAND, stackOnPedestal);
                world.playSound(player, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1f, 1f);
                areaBlockBlockEntity.clear();

                areaBlockBlockEntity.markDirty();
                world.updateListeners(pos, state, state, 0);
            }
        }

        return ItemActionResult.SUCCESS;
    }
}

//import com.mojang.serialization.MapCodec;
//import net.minecraft.block.*;
//import net.minecraft.block.entity.BlockEntity;
//import net.minecraft.block.entity.StructureBlockBlockEntity;
//import net.minecraft.block.enums.StructureBlockMode;
//import net.minecraft.entity.LivingEntity;
//import net.minecraft.entity.player.PlayerEntity;
//import net.minecraft.item.ItemStack;
//import net.minecraft.server.world.ServerWorld;
//import net.minecraft.state.StateManager;
//import net.minecraft.state.property.EnumProperty;
//import net.minecraft.state.property.Properties;
//import net.minecraft.util.ActionResult;
//import net.minecraft.util.hit.BlockHitResult;
//import net.minecraft.util.math.BlockPos;
//import net.minecraft.world.World;
//import org.jetbrains.annotations.Nullable;
//
//public class AreaBlock extends BlockWithEntity implements OperatorBlock {
//    public static final MapCodec<net.minecraft.block.StructureBlock> CODEC = createCodec(net.minecraft.block.StructureBlock::new);
//    public static final EnumProperty<StructureBlockMode> MODE = Properties.STRUCTURE_BLOCK_MODE;
//
//    @Override
//    public MapCodec<net.minecraft.block.StructureBlock> getCodec() {
//        return CODEC;
//    }
//
//    public AreaBlock(AbstractBlock.Settings settings) {
//        super(settings);
//        this.setDefaultState(this.stateManager.getDefaultState().with(MODE, StructureBlockMode.LOAD));
//    }
//
//    @Override
//    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
//        return new StructureBlockBlockEntity(pos, state);
//    }
//
//    @Override
//    protected ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, BlockHitResult hit) {
//        BlockEntity blockEntity = world.getBlockEntity(pos);
//        if (blockEntity instanceof StructureBlockBlockEntity) {
//            return ((StructureBlockBlockEntity)blockEntity).openScreen(player) ? ActionResult.success(world.isClient) : ActionResult.PASS;
//        } else {
//            return ActionResult.PASS;
//        }
//    }
//
//    @Override
//    public void onPlaced(World world, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack itemStack) {
//        if (!world.isClient) {
//            if (placer != null) {
//                BlockEntity blockEntity = world.getBlockEntity(pos);
//                if (blockEntity instanceof StructureBlockBlockEntity) {
//                    ((StructureBlockBlockEntity)blockEntity).setAuthor(placer);
//                }
//            }
//        }
//    }
//
//    @Override
//    protected BlockRenderType getRenderType(BlockState state) {
//        return BlockRenderType.MODEL;
//    }
//
//    @Override
//    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
//        builder.add(MODE);
//    }
//
//    @Override
//    protected void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
//        if (world instanceof ServerWorld) {
//            if (world.getBlockEntity(pos) instanceof StructureBlockBlockEntity structureBlockBlockEntity) {
//                boolean bl = world.isReceivingRedstonePower(pos);
//                boolean bl2 = structureBlockBlockEntity.isPowered();
//                if (bl && !bl2) {
//                    structureBlockBlockEntity.setPowered(true);
//                    this.doAction((ServerWorld)world, structureBlockBlockEntity);
//                } else if (!bl && bl2) {
//                    structureBlockBlockEntity.setPowered(false);
//                }
//            }
//        }
//    }
//
//    private void doAction(ServerWorld world, StructureBlockBlockEntity blockEntity) {
//        switch (blockEntity.getMode()) {
//            case SAVE:
//                blockEntity.saveStructure(false);
//                break;
//            case LOAD:
//                blockEntity.loadAndPlaceStructure(world);
//                break;
//            case CORNER:
//                blockEntity.unloadStructure();
//            case DATA:
//        }
//    }
//}
