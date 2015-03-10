package net.squishydev.testmod;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySpecialRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import org.lwjgl.opengl.GL11;

public class ModelRenderer extends TileEntitySpecialRenderer{
	ModelCrushingBlock model = new ModelCrushingBlock();
    public void renderTileEntityAt(TileEntity tileEntity, double d, double d1, double d2, float f) {
        GL11.glPushMatrix();
         GL11.glTranslatef((float)d, (float)d1, (float)d2);
         CrushingBlockTileEntity tileEntityYour = new CrushingBlockTileEntity();
         renderBlockYour(tileEntityYour, tileEntity.getWorldObj(), tileEntity.xCoord, tileEntity.yCoord, tileEntity.zCoord, TestMod.crushingblock);
        GL11.glPopMatrix();
    }
    public void renderBlockYour(CrushingBlockTileEntity tl, World world, int i, int j, int k, Block block) {
        Tessellator tessellator = Tessellator.instance;
        float f = block.getLightValue(world, i, j, k);
        int l = world.getLightBrightnessForSkyBlocks(i, j, k, 0);
        int l1 = l % 65536;
        int l2 = l / 65536;
        tessellator.setColorOpaque_F(f, f, f);
        OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float)l1, (float)l2);

        int dir = world.getBlockMetadata(i, j, k);
       
        GL11.glPushMatrix();
         GL11.glTranslatef(0.0F, 0, 0.0F);
         //This line actually rotates the renderer.
         GL11.glRotatef(180F, 0F, 0F, 1F);
         GL11.glTranslatef(-0.5F, -1.5F, 0.5F);
         bindTexture(new ResourceLocation("testmod:textures/model/crusherblock.png"));
         this.model.render((Entity)null, 0.0F, -0.1F, 0.0F, 0.0F, 0.0F, 0.0625F);

        GL11.glPopMatrix();
    }
}