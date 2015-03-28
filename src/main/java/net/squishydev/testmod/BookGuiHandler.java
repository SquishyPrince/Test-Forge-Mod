package net.squishydev.testmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class BookGuiHandler implements IGuiHandler{
	
	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
		//System.out.println("Tried!");
		if (ID==1) {
			TileEntityTestFurnace2 tileEntityFurnace = (TileEntityTestFurnace2)world.getTileEntity(x, y, z);
			return new GUITestBook(((TestPlayerData)player.getExtendedProperties("TestModData")).research);
		}
		return null;
	}
		
}
