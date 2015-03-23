package net.squishydev.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.World;

public class MultiPart extends Block{

	protected MultiPart(Material rock) {
		super(rock);
		setBlockName("Part");
		setCreativeTab(CreativeTabs.tabMisc);
	}

	@Override
	public void breakBlock(World world, int x, int y, int z, Block block, int meta) {
		super.breakBlock(world, x, y, z, block, meta);
		System.out.println(x+" "+y+" "+z);
		if (TestMod.worldData.getData(Vector.createVectorHelper(x, y, z))!=null) {
			TestMod.worldData.getData(Vector.createVectorHelper(x, y, z)).removeMultiBlock();
		}
	}
}
