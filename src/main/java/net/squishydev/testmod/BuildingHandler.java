package net.squishydev.testmod;

import net.minecraft.block.Block;

public class BuildingHandler {
	
	Block[][][] map;
	
	public BuildingHandler(Block[][][] map) {
		this.map=map;
	}
	
	public boolean  checkBuilding() {
		return true;
	}
}
