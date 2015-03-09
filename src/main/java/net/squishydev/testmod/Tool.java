package net.squishydev.testmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class Tool extends Item {

	public Tool() {
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("Tool");
		setHarvestLevel("pickaxe", 4);
	}
	
}
