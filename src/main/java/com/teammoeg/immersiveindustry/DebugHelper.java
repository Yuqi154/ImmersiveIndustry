package com.teammoeg.immersiveindustry;

import java.util.OptionalDouble;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.RenderStateShard;
import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;


import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.RenderType;

public class DebugHelper {
	public static RenderType BOLD_LINE_TYPE;
	static {
		RenderStateShard.ShaderStateShard renderState;
		renderState = RenderStateAccess.getState();
		BOLD_LINE_TYPE = RenderType.makeType("ii_line_bold", DefaultVertexFormats.POSITION_COLOR, GL11.GL_LINES,
				128, renderState);
	}
	//hack to access render state protected members
	public static class RenderStateAccess extends RenderStateShard.ShaderStateShard {
		public static RenderType.ShaderStateShard getState() {
			return RenderType.State.getBuilder().line(new RenderState.LineState(OptionalDouble.of(1)))//this is line width
					.layer(VIEW_OFFSET_Z_LAYERING).target(MAIN_TARGET).writeMask(COLOR_DEPTH_WRITE).build(true);
		}

		public RenderStateAccess(String p_i225973_1_, Runnable p_i225973_2_, Runnable p_i225973_3_) {
			super(p_i225973_1_, p_i225973_2_, p_i225973_3_);
		}

	}
	//draw a line from start to end by color, ABSOLUTE POSITION
	public static void drawLine(PoseStack matrixStack, float startX, float startY, float startZ, float endX, float endY, float endZ) {
		VertexConsumer vertexBuilderLines = Minecraft.getInstance().renderBuffers().crumblingBufferSource().getBuffer(BOLD_LINE_TYPE);
		drawLine(matrixStack.last().pose(),vertexBuilderLines,startX,startY,startZ,endX,endY,endZ);
	}

	private static void drawLine(Matrix4f mat, VertexConsumer renderBuffer, float startX, float startY, float startZ, float endX, float endY, float endZ) {
		renderBuffer.vertex(mat,startX,startY,startZ)
				.color(255,0,0,0)
				.endVertex();
		renderBuffer.vertex(mat,endX,endY,endZ)
				.color(255,0,0,0)
				.endVertex();
	}
}
