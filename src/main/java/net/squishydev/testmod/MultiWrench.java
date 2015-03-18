package net.squishydev.testmod;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class MultiWrench extends Item{
	public MultiWrench() {
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("MultiWrench");
	}

}
