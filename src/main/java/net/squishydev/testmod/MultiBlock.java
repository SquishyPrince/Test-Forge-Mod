package net.squishydev.testmod;

import net.minecraft.entity.player.EntityPlayer;

public class MultiBlock {
	
	String sName = "p";
	private MultiMaster multiBlockController;
	public String chName;
	public int x;
	
	public MultiBlock(int x, int y, int z) {
		this.x = x;
	}

	public void setMultiBlockController(MultiMaster multiController) {
		this.multiBlockController = multiController;
	}
}
