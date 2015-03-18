package net.squishydev.testmod;

import net.minecraft.block.material.Material;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MultiController extends MultiMaster {

	public MultiController(Material rock) {
		super(rock);
		setBlockName("MultiController");
	}
	
	@Override
	public boolean built() {
		return true;
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new MultiControllerTileEntity(new MultiBlock(this.xCoord, this.yCoord, this.zCoord));
	}
}