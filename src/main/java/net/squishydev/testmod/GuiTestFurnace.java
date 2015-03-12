package net.squishydev.testmod;

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
		
	}

}
