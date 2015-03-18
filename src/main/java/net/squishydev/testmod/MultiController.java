package net.squishydev.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MultiController extends MultiMaster {

	Block c = new MultiController(Material.rock);
	Block p = new MultiPart(Material.rock);
	Block a = Blocks.air;
	Block[][][] map = {{{p,p,p},
						{p,p,p},
						{p,p,p}},
						
						{{p,p,p},
						{p,a,c},
						{p,p,p}},
						
						{{p,p,p},
						{p,p,p},
						{p,p,p}}};
	BuildingHandler buildingHandler = new BuildingHandler(map);
	
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