package net.squishydev.testmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler2 implements IGuiHandler{

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		if (ID == 0) {
			TileEntityTestFurnace2 tileEntityFurnace = (TileEntityTestFurnace2)world.getTileEntity(x, y, z);
			return new ContainerTestFurnace(player.inventory, tileEntityFurnace);
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,
			int x, int y, int z) {
		//System.out.println("Tried!");
		if (ID==0) {
			TileEntityTestFurnace2 tileEntityFurnace = (TileEntityTestFurnace2)world.getTileEntity(x, y, z);
			return new GUITestFurnace2(player.inventory, tileEntityFurnace);
		}
		return null;
	}
	
}
