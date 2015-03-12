package net.squishydev.testmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;

public class GuiHandler implements IGuiHandler{

	public GuiHandler() {
		NetworkRegistry.INSTANCE.registerGuiHandler(TestMod.instance, this);
	}

	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x,y,z);
		
		if (entity != null) {
			switch (ID) {
				case TestMod.testFurnaceGuiId:
					if (entity instanceof testFurnaceTileEntity) {
						return new ContainerGuiTestFurnace(player.inventory, (testFurnaceTileEntity) entity);
					}
			
			}
		}
		return null;
	}

	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x,y,z);
		
		if (entity != null) {
			switch (ID) {
				case TestMod.testFurnaceGuiId:
					if (entity instanceof testFurnaceTileEntity) {
						return new GuiTestFurnace(player.inventory, (testFurnaceTileEntity) entity);
					}
			}
		}
		
		return null;
	}

}
