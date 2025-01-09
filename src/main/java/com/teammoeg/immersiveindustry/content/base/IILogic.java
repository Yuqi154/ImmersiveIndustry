package com.teammoeg.immersiveindustry.content.base;

import blusunrize.immersiveengineering.api.multiblocks.blocks.component.IClientTickableComponent;
import blusunrize.immersiveengineering.api.multiblocks.blocks.component.IServerTickableComponent;
import blusunrize.immersiveengineering.api.multiblocks.blocks.env.IInitialMultiblockContext;
import blusunrize.immersiveengineering.api.multiblocks.blocks.env.IMultiblockContext;
import blusunrize.immersiveengineering.api.multiblocks.blocks.logic.IMultiblockLogic;
import blusunrize.immersiveengineering.api.multiblocks.blocks.util.ShapeType;
import net.minecraft.core.BlockPos;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.function.Function;

public class IILogic<T extends IILogic<T, ?>, R extends IIState> implements IServerTickableComponent<R>, IMultiblockLogic<R>, IClientTickableComponent<R> {
    @Override
    public void tickClient(IMultiblockContext<R> iMultiblockContext) {

    }

    @Override
    public void tickServer(IMultiblockContext<R> iMultiblockContext) {

    }

    @Override
    public R createInitialState(IInitialMultiblockContext<R> iInitialMultiblockContext) {
        return null;
    }

    @Override
    public Function<BlockPos, VoxelShape> shapeGetter(ShapeType shapeType) {
        return null;
    }
}
