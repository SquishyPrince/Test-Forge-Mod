package net.squishydev.testmod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;

public class Tool extends Item {

	public Tool() {
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
		setUnlocalizedName("Tool");
		setHarvestLevel("pickaxe", 4);
	}
	@SideOnly(Side.CLIENT)
	protected IIcon[] iconl;
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		iconl = new IIcon[1];
		iconl[0] = par1IconRegister.registerIcon("testmod:aluminum_pickaxe");
	}
	@Override
	public IIcon getIconFromDamage(int par1) {
		return iconl[par1];
	}
}
