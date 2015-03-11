package net.squishydev.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class testFurnace extends BlockContainer {

	private final boolean isActive;

	public testFurnace(Material material, boolean active) {
		super(material);
		this.isActive = active;
		if (active){
			setBlockName("testFurnaceActive");
		} else {
			setBlockName("testFurnaceIdle");
		}
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		// TODO Auto-generated method stub
		return null;
	}
}
