package net.squishydev.testmod;

import java.util.Random;

import cpw.mods.fml.common.network.internal.FMLNetworkHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;

public class testFurnace extends BlockContainer {

	private final boolean isActive;
	
	@SideOnly(Side.CLIENT)
	private IIcon iconFront;
	@SideOnly(Side.CLIENT)
	private IIcon iconTop;

	public testFurnace(Material material, boolean active) {
		super(material);
		this.isActive = active;
		
		if (active){
			setBlockName("testFurnaceActive");
		} else {
			setBlockName("testFurnaceIdle");
		}
	}
	
	@Override
	public TileEntity createNewTileEntity(World world, int i) {
		return new testFurnaceTileEntity();
	}
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister iconRegister) {
		this.iconTop = iconRegister.registerIcon("testmod:FurnTop");
		this.blockIcon = iconRegister.registerIcon("testmod:"+"FurnSide");
		this.iconFront = iconRegister.registerIcon("testmod:"+(this.isActive ? "FurnActiveFront" : "FurnIdleFront"));
	}
	
	public void onBlockAdded(World world, int x, int y, int z)
    {
        super.onBlockAdded(world, x, y, z);
        this.func_149930_e(world, x, y, z);
    }
	public void onBlockPlacedBy(World p_149689_1_, int p_149689_2_, int p_149689_3_, int p_149689_4_, EntityLivingBase p_149689_5_, ItemStack p_149689_6_)
    {
        int l = MathHelper.floor_double((double)(p_149689_5_.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;

        if (l == 0)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 2, 2);
        }

        if (l == 1)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 5, 2);
        }

        if (l == 2)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 3, 2);
        }

        if (l == 3)
        {
            p_149689_1_.setBlockMetadataWithNotify(p_149689_2_, p_149689_3_, p_149689_4_, 4, 2);
        }

        if (p_149689_6_.hasDisplayName())
        {
            ((TileEntityFurnace)p_149689_1_.getTileEntity(p_149689_2_, p_149689_3_, p_149689_4_)).func_145951_a(p_149689_6_.getDisplayName());
        }
    }
	private void func_149930_e(World p_149930_1_, int p_149930_2_, int p_149930_3_, int p_149930_4_)
	    {
	        if (!p_149930_1_.isRemote)
	        {
	            Block block = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ - 1);
	            Block block1 = p_149930_1_.getBlock(p_149930_2_, p_149930_3_, p_149930_4_ + 1);
	            Block block2 = p_149930_1_.getBlock(p_149930_2_ - 1, p_149930_3_, p_149930_4_);
	            Block block3 = p_149930_1_.getBlock(p_149930_2_ + 1, p_149930_3_, p_149930_4_);
	            byte b0 = 3;

	            if (block.func_149730_j() && !block1.func_149730_j())
	            {
	                b0 = 3;
	            }

	            if (block1.func_149730_j() && !block.func_149730_j())
	            {
	                b0 = 2;
	            }

	            if (block2.func_149730_j() && !block3.func_149730_j())
	            {
	                b0 = 5;
	            }

	            if (block3.func_149730_j() && !block2.func_149730_j())
	            {
	                b0 = 4;
	            }

	            p_149930_1_.setBlockMetadataWithNotify(p_149930_2_, p_149930_3_, p_149930_4_, b0, 2);
	        }
	    }
	@Override
	public Item getItemDropped(int i, Random random, int j) {
		return Item.getItemFromBlock(TestMod.testFurnaceIdle);
	}
	@Override
	public IIcon getIcon(int side, int metaData) {
		//System.out.println(metaData+"");
		if (metaData >5)
			metaData = 0;
		return side == 1 ? this.iconTop : (side == 0 ? this.iconTop : (side != metaData ? this.blockIcon : this.iconFront));
	}
	
	public boolean onBlockActivated(World world, int x, int y, int z, EntityPlayer player, int side, float hitX, float hitY, float hitZ) {
		if (!world.isRemote) {
			FMLNetworkHandler.openGui(player, TestMod.instance, TestMod.testFurnaceGuiId, world, x, y, z);
		}
		
		return true;
	}
}
