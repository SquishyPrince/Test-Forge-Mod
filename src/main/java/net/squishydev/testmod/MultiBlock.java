package net.squishydev.testmod;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.ChunkPosition;

public class MultiBlock {
	
	String sName = "p";
	private MultiMaster multiBlockController;
	public String chName;
	public int x;
	public AssemblyResult building;
	
	public MultiBlock(int x, int y, int z, AssemblyResult result) {
		this.building = result;
		this.x = x;
	}

	public void setMultiBlockController(MultiMaster multiController) {
		this.multiBlockController = multiController;
	}

	public void removeMultiBlock() {
		for (int i = 0;i<building.getBlockCount();i++) {
			ChunkPosition temp = building.getBlock(i).coords;
			System.out.println(temp.chunkPosX);
			TestMod.worldData.removeData(Vector.createVectorHelper(temp.chunkPosX, temp.chunkPosY, temp.chunkPosZ));
		}
	}
}
