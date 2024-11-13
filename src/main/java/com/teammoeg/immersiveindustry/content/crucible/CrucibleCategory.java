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

import blusunrize.immersiveengineering.client.ClientUtils;
import blusunrize.immersiveengineering.common.util.compat.jei.JEIHelper;
import com.teammoeg.immersiveindustry.IIContent;
import com.teammoeg.immersiveindustry.IIMain;
import mezz.jei.api.constants.VanillaTypes;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.drawable.IDrawableAnimated;
import mezz.jei.api.gui.drawable.IDrawableStatic;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.IRecipeCategory;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;

public class CrucibleCategory implements IRecipeCategory<CrucibleRecipe> {

    public static ResourceLocation UID = new ResourceLocation(IIMain.MODID, "crucible");
    private IDrawable BACKGROUND;
    private IDrawable ICON;
    private IDrawable TANK;
    private IDrawableAnimated ARROW;
    public CrucibleCategory(IGuiHelper guiHelper) {
        this.ICON = guiHelper.createDrawableIngredient(VanillaTypes.ITEM_STACK,new ItemStack(IIContent.IIMultiblocks.crucible));
        this.BACKGROUND = guiHelper.createDrawable(new ResourceLocation(IIMain.MODID, "textures/gui/crucible_jei.png"), 19, 3, 150, 65);
        this.TANK = guiHelper.createDrawable(new ResourceLocation(IIMain.MODID, "textures/gui/crucible.png"),238,34,18, 48);
        IDrawableStatic arrow=guiHelper.createDrawable(new ResourceLocation(IIMain.MODID, "textures/gui/crucible.png"),204,15,21,15);
        ARROW=guiHelper.createAnimatedDrawable(arrow,40, IDrawableAnimated.StartDirection.LEFT,false);
    }

    @Override
    public RecipeType<CrucibleRecipe> getRecipeType() {
        return RecipeType.create(IIMain.MODID, "crucible",CrucibleRecipe.class);
    }

    public Component getTitle() {
        return Component.translatable("gui.jei.category." + IIMain.MODID + ".crucible");
    }

    @Override
    public IDrawable getBackground() {
        return BACKGROUND;
    }

    @Override
    public IDrawable getIcon() {
        return ICON;
    }

    @Override
    public void draw(CrucibleRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        ARROW.draw(guiGraphics,57, 11);
        int k = recipe.temperature - recipe.temperature % 100 + 300;
        Component temperature = Component.translatable("gui.immersiveindustry.crucible.tooltip.temperature_in_kelvin", k);
        guiGraphics.drawString(ClientUtils.font(),temperature, 45, 52, 14833698);
    }
//    @Override
//    public void setIngredients(CrucibleRecipe recipe, IIngredients ingredients) {
//        ingredients.setInputLists(VanillaTypes.ITEM, JEIIngredientStackListBuilder.make(recipe.inputs).build());
//        ingredients.setOutput(VanillaTypes.ITEM, recipe.getRecipeOutput());
//        if (recipe.output_fluid != FluidStack.EMPTY)
//            ingredients.setOutput(VanillaTypes.FLUID, recipe.output_fluid);
//    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder recipeLayout, CrucibleRecipe recipe, IFocusGroup ingredients) {
        recipeLayout.addSlot(RecipeIngredientRole.INPUT,10,8).addItemStacks(Arrays.asList(recipe.inputs[0].getMatchingStacks())).setBackground(JEIHelper.slotDrawable, -1, -1);
        recipeLayout.addSlot(RecipeIngredientRole.INPUT,31,8).addItemStacks(Arrays.asList(recipe.inputs[1].getMatchingStacks())).setBackground(JEIHelper.slotDrawable, -1, -1);
        recipeLayout.addSlot(RecipeIngredientRole.INPUT,10,29).addItemStacks(Arrays.asList(recipe.inputs[2].getMatchingStacks())).setBackground(JEIHelper.slotDrawable, -1, -1);
        recipeLayout.addSlot(RecipeIngredientRole.INPUT,31,29).addItemStacks(Arrays.asList(recipe.inputs[3].getMatchingStacks())).setBackground(JEIHelper.slotDrawable, -1, -1);
        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT,89,8).addItemStacks(Arrays.asList(recipe.inputs[4].getMatchingStacks())).setBackground(JEIHelper.slotDrawable, -1, -1);
        recipeLayout.addSlot(RecipeIngredientRole.OUTPUT, 48, 3).setFluidRenderer((long)14400, false, 58, 47).addFluidStack(recipe.output_fluid.getFluid(),recipe.output_fluid.getAmount()).addTooltipCallback(JEIHelper.fluidTooltipCallback);

    }
}
