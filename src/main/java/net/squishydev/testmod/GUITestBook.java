package net.squishydev.testmod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.ResourceLocation;

public class GUITestBook extends GuiScreen{
	private int xSize1 = 146;
	private int ySize1 = 180;
	private int xSize2 = 146;
	private int ySize2 = 180;
	private Research research;
	private static final ResourceLocation guiTextures1 = new ResourceLocation("TestMod:textures/gui/bookGui.png");
	private static final ResourceLocation guiTextures2 = new ResourceLocation("TestMod:textures/gui/bookGui2.png");
	private static final ResourceLocation guiIconTextures = new ResourceLocation("TestMod:textures/gui/bookGuiIcons.png");
	
	public GUITestBook(Research research) {
		this.research = research;
	}
	
	public void drawScreen(int x, int y, float var1) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(guiTextures1);
		int k = (this.width) /2 - 10;
		int l = (this.height - this.ySize1) /2;
		this.drawTexturedModalRect(k, l, 20, 1, this.xSize1, this.ySize1);

		this.mc.getTextureManager().bindTexture(guiTextures2);
		int i = (this.width) /2-this.xSize2+10;
		int j = (this.height - this.ySize2) /2;
		this.drawTexturedModalRect(i, j, 20, 1, this.xSize2, this.ySize2);
	}

}
