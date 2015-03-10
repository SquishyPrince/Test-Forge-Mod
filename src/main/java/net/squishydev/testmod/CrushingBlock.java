package net.squishydev.testmod;

import java.util.Random;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class CrushingBlock extends BlockContainer {

	protected CrushingBlock(Material p_i45386_1_) {
		super(p_i45386_1_);
		setHardness(1F);
		setStepSound(Block.soundTypePiston);
		setBlockName("CrushingBlock");
		setCreativeTab(CreativeTabs.tabMisc);
		setHarvestLevel("pickaxe",0);
	}

	public boolean shouldSideBeRendered(IBlockAccess iblockaccess, int i, int j, int k, int l) {
		return false;
	}
	
	public boolean isOpaqueCube() {
		return false;
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return new CrushingBlockTileEntity();
	}
	@SideOnly(Side.CLIENT)
	protected IIcon[] iconl;
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		iconl = new IIcon[1];
		iconl[0] = par1IconRegister.registerIcon("testmod:crushingblock");
	}

	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return iconl[0];
	}
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(TestMod.crushingblock);
	}
}
