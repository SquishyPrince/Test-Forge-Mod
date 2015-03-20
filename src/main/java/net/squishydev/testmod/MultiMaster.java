package net.squishydev.testmod;

import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;

public class MultiMaster extends BlockContainer {

	private boolean blockPlaced = false;
	private MultiBlock multiBlock;
	int maxBlocks;
	int xCoord;
	int yCoord;
	int zCoord;
	public BuildingHandler buildingHandler;
	
	protected MultiMaster(Material material) {
		super(material);
		setHardness(1F);
		setStepSound(Block.soundTypePiston);
		setCreativeTab(CreativeTabs.tabMisc);
		setHarvestLevel("pickaxe",0);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new MultiControllerTileEntity(null);
	}

	@Override
	public void onBlockAdded(World world, int y, int x, int z) {
		world.setTileEntity(x, y, z, createNewTileEntity(world, 1));
	}
	
	public boolean built(AssemblyResult result, int Sx, int Sy, int Sz, World worldObj, int maxBlocks) {
		return false;
	}
	
	@Override
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		
		if (world.isRemote) {
		Item wrench = new MultiWrench();
		if (player.inventory.getCurrentItem()!=null){
			AssemblyResult result = new AssemblyResult();
			result.xOffset = x;
			result.yOffset = y;
			result.zOffset = z;
			if (player.inventory.getCurrentItem().getItem().getUnlocalizedName().equals(wrench.getUnlocalizedName())&&built(result, x, y, z, world, maxBlocks)) {
				MultiControllerTileEntity tileEntity = (MultiControllerTileEntity) world.getTileEntity(x, y, z);
				tileEntity.setMultiBlock(new MultiBlock(x, y, z));
				this.xCoord = x;
				this.yCoord = y;
				this.zCoord = z;
				this.multiBlock = tileEntity.getSlave();
				this.multiBlock.setMultiBlockController(this);
				TestMod.worldData.addData(Vector.createVectorHelper(x, y, z), this.multiBlock);
			}
		}
		}
		return false;
	
	}

}
