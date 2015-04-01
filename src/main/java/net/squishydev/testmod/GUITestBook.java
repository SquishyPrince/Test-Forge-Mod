package net.squishydev.testmod;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.entity.player.EntityPlayer;
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
	public int TAB_ONE = 0, TAB_TWO = 1, TAB_THREE = 2, TAB_FOUR = 3, TAB_FIVE = 4, TAB_SIX = 5, TAB_SEVEN = 6;
	public int curTab = TAB_ONE;
	private EntityPlayer player;
	
	public GUITestBook(Research research, EntityPlayer player) {
		this.research = research;
		this.player = player;
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
		
		this.mc.getTextureManager().bindTexture(guiIconTextures);
		int a = i-33;
		int b = j+8;
		for (int c = 0;c<7;c++) {
			this.drawTexturedModalRect(a, b+(c*22), 41, 0+(c*20), 41, 20);
		}
		
		int iconSpaceX = i+20;
		int iconSpaceY = j+20;
		if (this.curTab==this.TAB_ONE) {
			if (!((TestPlayerData)this.player.getExtendedProperties("TestModData")).research.getResearchStatusFromName("testResearch")) {
				this.drawTexturedModalRect(iconSpaceX, iconSpaceY, 82, 0, 100, 10);
			} else {
				this.drawTexturedModalRect(iconSpaceX, iconSpaceY, 82, 10, 100, 10);
			}
		}
		
	}
	
	protected void mouseClicked(int x, int y, int button) {
		if (button == 0) {
			int sX = (this.width) /2-this.xSize2+30;
			int sY = (this.height - this.ySize2) /2+20;
			if (x>=sX&&y>=sY&&x<=sX+100&&y<=sY+10) {
				((TestPlayerData)this.player.getExtendedProperties("TestModData")).research.setResearchedFromName("testResearch", true);
			}
		}
	}
	
	public boolean doesGuiPauseGame()
    {
        return false;
    }

}
