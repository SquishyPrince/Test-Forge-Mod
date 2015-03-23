package net.squishydev.testmod;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MultiController extends MultiMaster {

	Block c = this;
	Block p = new MultiPart(Material.rock);
	Block a = Blocks.air;
	//map[y][z][x]
	Block[][][] map = {{{p,p,p},
						{p,p,p},
						{p,p,p}},
						
						{{p,p,p},
						{p,a,c},
						{p,p,p}},
						
						{{p,p,p},
						{p,p,p},
						{p,p,p}}};
	
	List<String> validBlocks = new ArrayList<String>();
	
	public MultiController(Material rock) {
		super(rock);
		setBlockName("MultiController");
		this.maxBlocks = 27;
		validBlocks.add(this.getUnlocalizedName());
		validBlocks.add(new MultiPart(Material.rock).getUnlocalizedName());
		super.buildingHandler = new BuildingHandler(map, validBlocks);
	}
	
	public boolean built(AssemblyResult result, int Sx, int Sy, int Sz, World worldObj, int maxBlocks) {
		return buildingHandler.checkBuilding(result, Sx, Sy, Sz, worldObj, maxBlocks, this);
	}

	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new MultiControllerTileEntity();
	}
}