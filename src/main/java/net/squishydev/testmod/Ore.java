package net.squishydev.testmod;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Ore extends Block {

	public Ore(Material material) {
		super(material);
		setHardness(0.1F);
		setStepSound(Block.soundTypePiston);
		setBlockName("Ore");
		setCreativeTab(CreativeTabs.tabMisc);
		setHarvestLevel("pickaxe",0);
	}
	@Override
	public Item getItemDropped(int metadata, Random random, int fortune) {
		return Item.getItemFromBlock(TestMod.ore);
	}
}
