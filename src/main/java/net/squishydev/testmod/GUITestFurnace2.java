package net.squishydev.testmod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import cpw.mods.fml.relauncher.SideOnly;
import cpw.mods.fml.relauncher.Side;

@SideOnly(Side.CLIENT)
public class GUITestFurnace2  extends GuiContainer{
	
	private static final ResourceLocation furnaceGuiTextures = new ResourceLocation("TestMod:textures/gui/furnace.png");
	private TileEntityTestFurnace2 tileFurnace;
	
	public GUITestFurnace2(InventoryPlayer invPlayer, TileEntityTestFurnace2 tile) {
		super(new ContainerTestFurnace(invPlayer, tile));
		this.tileFurnace = tile;
	}
	
	protected void drawGuiContainerForegroundLayer(int par1, int par2) {
		String string = this.tileFurnace.hasCustomInventoryName() ? this.tileFurnace.getInventoryName() : I18n.format(this.tileFurnace.getInventoryName(), new Object[0]);
		this.fontRendererObj.drawString(string, this.xSize/2-this.fontRendererObj.getStringWidth(string), 6, 4210752);
		this.fontRendererObj.drawString(I18n.format("container.inventory", new Object[0]), 8, this.ySize - 94, 4210752);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,
			int p_146976_2_, int p_146976_3_) {
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		this.mc.getTextureManager().bindTexture(furnaceGuiTextures);
		int k = (this.width - this.xSize) /2;
		int l = (this.height - this.ySize) /2;
		this.drawTexturedModalRect(k, l, 0, 0, this.xSize, this.ySize);
		int i1;
		
		if (this.tileFurnace.isBurning()) {
			i1 = this.tileFurnace.getBurnTimeRemainingScaled(12);
			this.drawTexturedModalRect(k+56, l+36+12-i1, 176, 12-i1, 14, i1+2);
		}
		
		i1 = this.tileFurnace.getCookProgressScaled(24);
		this.drawTexturedModalRect(k+79, l+34, 176, 14, i1+l, 16);
	}

}
