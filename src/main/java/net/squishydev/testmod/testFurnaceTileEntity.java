package net.squishydev.testmod;

import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;

public class testFurnaceTileEntity extends TileEntity implements ISidedInventory{

	private String localizedName;
	
	private static final int[] slots_top = new int[]{0};
	private static final int[] slots_bottom = new int[]{2};
	private static final int[] slots_sides = new int[]{1};
	
	private ItemStack[] slots = new ItemStack[3];
	
	public int getSizeInventory() {
		return this.slots.length;
	}
	
	public void setGuiDisplayName(String displayName) {
		this.localizedName = displayName;
	}

	public boolean isInvNameLocalized() {
		return this.localizedName!=null && this.localizedName.length() > 0;
	}

	public String getInvName() {
		return this.isInvNameLocalized() ? this.localizedName : "container.testFurnace";
	}

	@Override
	public ItemStack getStackInSlot(int i) {
		return null;
	}

	@Override
	public ItemStack decrStackSize(int i, int j) {
		return null;
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int i) {
		return null;
	}

	@Override
	public void setInventorySlotContents(int i, ItemStack itemStack) {
		
	}

	@Override
	public String getInventoryName() {
		return null;
	}

	@Override
	public boolean hasCustomInventoryName() {
		return false;
	}

	@Override
	public int getInventoryStackLimit() {
		return 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer player) {
		return false;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}
	
	public static int getItemBurnTime(ItemStack itemStack) {
		if (itemStack == null) {
			return 0;
		} else {
			if (itemStack==new ItemStack(Items.stick)) return 100;
			if (itemStack==new ItemStack(Items.coal)) return 1600;
			if (itemStack==new ItemStack(Items.lava_bucket)) return 20000;
			if (itemStack==new ItemStack(Items.blaze_rod)) return 2400;
			return GameRegistry.getFuelValue(itemStack);
		}
	}
	
	public static boolean isItemFuel(ItemStack itemStack) {
		return getItemBurnTime(itemStack) > 0;
	}

	@Override
	public boolean isItemValidForSlot(int i, ItemStack itemStack) {
		return i == 2 ? false : (i==1 ? isItemFuel(itemStack) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int i) {
		return i==0 ? slots_bottom : (i==1 ? slots_top : slots_sides);
	}

	@Override
	public boolean canInsertItem(int i, ItemStack itemStack,int j) {
		return this.isItemValidForSlot(i, itemStack);
	}

	@Override
	public boolean canExtractItem(int i, ItemStack itemStack,int j) {
		return j !=0 || i !=1 || itemStack == new ItemStack(Items.bucket);
	}
}
