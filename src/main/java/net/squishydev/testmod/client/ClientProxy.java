package net.squishydev.testmod.client;

import cpw.mods.fml.client.registry.ClientRegistry;
import net.minecraftforge.client.MinecraftForgeClient;
import net.squishydev.testmod.CommonProxy;
import net.squishydev.testmod.ModelRenderer;
import net.squishydev.testmod.ModelRenderer2;

public class ClientProxy extends CommonProxy{

	@Override
	public void registerRenderers() {
		ClientRegistry.bindTileEntitySpecialRenderer(net.squishydev.testmod.CrushingBlockTileEntity.class, new ModelRenderer());
		ClientRegistry.bindTileEntitySpecialRenderer(net.squishydev.testmod.CrushingBlockTileEntity2.class, new ModelRenderer2());
	}
	
}
