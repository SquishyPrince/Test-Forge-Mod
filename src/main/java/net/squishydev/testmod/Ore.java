package net.squishydev.testmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.util.MathHelper;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class Ore extends Block {

	public Ore(Material material) {
		super(material);
		setHardness(0.1F);
		setStepSound(Block.soundTypePiston);
		setBlockName("Ore");
		setCreativeTab(CreativeTabs.tabMisc);
		setHarvestLevel("pickaxe",0);
	}
	
	@SideOnly(Side.CLIENT)
	protected IIcon[] iconl;
	
	@Override
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		iconl = new IIcon[1];
		iconl[0] = par1IconRegister.registerIcon("testmod:aluminum_ore");
	}

	@Override
	public IIcon getIcon(int p_149691_1_, int p_149691_2_) {
		return iconl[0];
	}
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(TestMod.ore);
	}
}
