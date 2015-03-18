package net.squishydev.testmod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiTestFurnace extends GuiContainer {

	public static final ResourceLocation texture = new ResourceLocation("testmod:textures/gui/furnace.png");
	
	public testFurnaceTileEntity testFurnace;
	
	public GuiTestFurnace(InventoryPlayer inventoryPlayer, testFurnaceTileEntity entity) {
		
		super(new ContainerGuiTestFurnace(inventoryPlayer, entity));
		
		this.testFurnace = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2) {
		String name = this.testFurnace.isInvNameLocalized() ? this.testFurnace.getInvName() : I18n.format(this.testFurnace.getInvName());
		
		this.fontRendererObj.drawString(name, this.xSize/2-this.fontRendererObj.getStringWidth(name)/2, 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize-96+2, 4210752);
	}
	
	@Override
	public void drawGuiContainerBackgroundLayer(float f, int i, int j) {
		GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if (this.testFurnace.isBurning()) {
			int k = this.testFurnace.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(guiLeft+56, guiTop+36+12-k, 176, 12-k, 14, k + 2);
		}
		
		int k = this.testFurnace.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft+79, guiTop+34, 176, 14, k+1, 16);
	}

}
