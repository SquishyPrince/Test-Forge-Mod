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
import net.minecraftforge.client.model.AdvancedModelLoader;
import net.minecraftforge.client.model.IModelCustom;

import org.lwjgl.opengl.GL11;

public class ModelRenderer2 extends TileEntitySpecialRenderer
{
    private IModelCustom casinoMachine;
    private ResourceLocation casinoTexture;

    public ModelRenderer2()
    {
        casinoMachine = AdvancedModelLoader.loadModel(new ResourceLocation("testmod:textures/model/crushingblock2.obj"));
        casinoTexture = new ResourceLocation("testmod:textures/crusherblock2.png");
    }

    public void renderTileEntityAt(TileEntity tileEntity, double x, double y, double z, float f)
    {
    	GL11.glPushMatrix();
    	GL11.glTranslated(x+0.5, y, z+0.5);
    	GL11.glScalef(0.025f, 0.025f, 0.025f);

    	bindTexture(casinoTexture);
    	casinoMachine.renderAll();

    	GL11.glPopMatrix();

    }

}
