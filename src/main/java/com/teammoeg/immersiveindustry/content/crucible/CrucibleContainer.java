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

import blusunrize.immersiveengineering.common.gui.IEContainerMenu;
import blusunrize.immersiveengineering.common.gui.IESlot;


import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.SlotItemHandler;

public class CrucibleContainer extends IEContainerMenu {
    private final ContainerData stateView;

    public CrucibleContainer(IEContainerMenu.MenuContext ctx, Inventory inventoryPlayer, IItemHandler crucibleInv, ContainerData stateView) {
        super(ctx);
        this.stateView = stateView;
        // input
        this.addSlot(new SlotItemHandler(crucibleInv, 0, 30, 12){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return CrucibleRecipe.isValidInput(itemStack);
            }
        });
        this.addSlot(new SlotItemHandler(crucibleInv, 1, 51, 12){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return CrucibleRecipe.isValidInput(itemStack);
            }
        });
        this.addSlot(new SlotItemHandler(crucibleInv, 2, 30, 33){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return CrucibleRecipe.isValidInput(itemStack);
            }
        });
        this.addSlot(new SlotItemHandler(crucibleInv, 3, 51, 33){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return CrucibleRecipe.isValidInput(itemStack);
            }
        });

        // input fuel
        this.addSlot(new IESlot.IEFurnaceSFuelSlot(crucibleInv, 4, 80, 51){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return CrucibleRecipe.getFuelTime(inventoryPlayer.player.level(),itemStack) > 0;
            }
        });
        // output
        this.addSlot(new IESlot.NewOutput(crucibleInv, 5, 109, 12));
        this.ownSlotCount = 6;

        // fluid output

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 9; j++)
                addSlot(new Slot(inventoryPlayer, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
        for (int i = 0; i < 9; i++)
            addSlot(new Slot(inventoryPlayer, i, 8 + i * 18, 142));

        this.addDataSlots(stateView);
    }


    public ContainerData getStateView() {
        return this.stateView;
    }
}

