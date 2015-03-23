package net.squishydev.testmod;

import net.minecraft.tileentity.TileEntity;

public class MultiControllerTileEntity  extends TileEntity{
	
	public MultiBlock slaveMultiBlock;
	
	public MultiControllerTileEntity() {
		
	}

	public void setMultiBlock(MultiBlock multiBlock) {
		this.slaveMultiBlock = multiBlock;
	}

	public MultiBlock getSlave() {
		return this.slaveMultiBlock;
	}
}
