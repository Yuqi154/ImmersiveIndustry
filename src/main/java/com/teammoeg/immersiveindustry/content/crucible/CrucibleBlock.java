/*
 * Copyright (c) 2021 TeamMoeg
 *
 * This file is part of Immersive Industry.
 *
 * Immersive Industry is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, version 3.
 *
 * Immersive Industry is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Immersive Industry. If not, see <https://www.gnu.org/licenses/>.
 */

package com.teammoeg.immersiveindustry.content.crucible;

import blusunrize.immersiveengineering.api.multiblocks.blocks.MultiblockRegistration;
import blusunrize.immersiveengineering.api.multiblocks.blocks.registry.MultiblockPartBlock;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.blocks.multiblocks.IETemplateMultiblock;
import blusunrize.immersiveengineering.common.util.Utils;
import com.google.common.collect.ImmutableMap;
import com.teammoeg.immersiveindustry.IIContent;
import com.teammoeg.immersiveindustry.IIMain;
import net.minecraft.block.material.Material;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.items.ItemHandlerHelper;
import net.minecraftforge.registries.RegistryObject;

public class CrucibleBlock extends MultiblockPartBlock<CrucibleBlockEntity> {


	public static final BooleanProperty LIT = BlockStateProperties.LIT;

    public CrucibleBlock() {
        super(Properties.of().destroyTime(4.0F).explosionResistance(40.0F).noOcclusion(),type);
        this.setDefaultState(this.stateContainer.getBaseState().with(LIT, false));
        IIContent.registeredBlocks.add(this);
    }


    @Override
    public ResourceLocation createRegistryName() {
        return new ResourceLocation(IIMain.MODID, name);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(LIT);
    }

	@Override
	public void onEntityWalk(Level worldIn, BlockPos pos, Entity entityIn) {
		super.onEntityWalk(worldIn, pos, entityIn);
		if(!(entityIn instanceof ItemEntity)) {
			if(entityIn instanceof LivingEntity &&worldIn.getBlockState(pos).get(LIT))
				entityIn.attackEntityFrom(DamageSource.HOT_FLOOR,4);
			return;
		}
		ItemEntity itemEntity=(ItemEntity) entityIn;
    	TileEntity te=Utils.getExistingTileEntity(worldIn,pos);
    	if(te instanceof CrucibleBlockEntity) {
    		if(((CrucibleBlockEntity) te).isDummy())return;
    		ItemStack insertItem = ItemHandlerHelper.insertItem(((CrucibleBlockEntity) te).inputHandler.resolve().get(), itemEntity.getItem().copy(), false);
			if (insertItem.isEmpty()) {
				itemEntity.remove();
				return;
			}
			itemEntity.setItem(insertItem);
    	}
	}


	@Override
	public float getManualScale() {
		return 0;
	}
}

