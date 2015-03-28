package net.squishydev.testmod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;

public class TestBook extends Item{
	public TestBook() {
		setUnlocalizedName("TestBook");
		setMaxStackSize(1);
		setCreativeTab(CreativeTabs.tabMisc);
	}
	@SideOnly(Side.CLIENT)
	protected IIcon[] iconl;
	
	@Override
	public void registerIcons(IIconRegister par1IconRegister) {
		iconl = new IIcon[1];
		iconl[0] = par1IconRegister.registerIcon("testmod:testBook");
	}
	
	@Override
	public IIcon getIconFromDamage(int par1) {
		return iconl[par1];
	}
	
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
		if (player.getExtendedProperties("TestModData")==null) {
			player.registerExtendedProperties("TestModData", new TestPlayerData(player));
		}
		
		int x = player.chunkCoordX;
		int y = player.chunkCoordY;
		int z = player.chunkCoordZ;
		
		player.openGui(TestMod.instance, 1, world, x, y, z);
		
        return itemStack;
    }
}
