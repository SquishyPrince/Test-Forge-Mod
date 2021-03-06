package net.squishydev.testmod;

import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemTool;
import net.minecraft.item.crafting.FurnaceRecipes;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.tileentity.TileEntity;

public class TileEntityTestFurnace2 extends TileEntity implements ISidedInventory{

	private static final int[] slotsTop = new int[]{0};
	private static final int[] slotsBottom = new int[]{2, 1};
	private static final int[] slotsSides = new int[]{1};
	
	private ItemStack[] furnaceItemStacks = new ItemStack[3];
	
	public int furnaceBurnTime;
	public int currentBurnTime;
	
	public int furnaceCookTime;
	
	private String furnaceName;
	
	public void furnaceName(String string) {
		this.furnaceName = string;
	}
	
	@Override
	public int getSizeInventory() {
		return this.furnaceItemStacks.length;
	}

	@Override
	public ItemStack getStackInSlot(int var1) {
		return this.furnaceItemStacks[var1];
	}

	@Override
	public ItemStack decrStackSize(int var1, int var2) {
		if (this.furnaceItemStacks[var1] != null) {
			ItemStack itemStack;
			if (this.furnaceItemStacks[var1].stackSize <= var2) {
				itemStack = this.furnaceItemStacks[var1];
				this.furnaceItemStacks[var1] = null;
				return itemStack;
			} else {
				itemStack = this.furnaceItemStacks[var1].splitStack(var2);
				
				if (this.furnaceItemStacks[var1].stackSize == 0) {
					this.furnaceItemStacks[var1] = null;
				}
				return itemStack;
			}
		} else {
			return null;
		}
	}

	@Override
	public ItemStack getStackInSlotOnClosing(int var1) {
		if (this.furnaceItemStacks[var1] != null) {
			ItemStack itemStack = this.furnaceItemStacks[var1];
			this.furnaceItemStacks[var1] = null;
			return itemStack;
		} else {
			return null;
		}
	}

	@Override
	public void setInventorySlotContents(int var1, ItemStack var2) {
		this.furnaceItemStacks[var1] = var2;
		
		if (var2 != null && var2.stackSize > this.getInventoryStackLimit()) {
			var2.stackSize = this.getInventoryStackLimit();
		}
	}

	@Override
	public String getInventoryName() {
		return this.hasCustomInventoryName() ? this.furnaceName : "Test Furnace";
	}

	@Override
	public boolean hasCustomInventoryName() {
		return this.furnaceName != null && this.furnaceName.length()>0;
	}

	@Override
	public int getInventoryStackLimit() {
		return 64;
	}
	
	public void readFromNBT(NBTTagCompound tagCompound) {
		super.readFromNBT(tagCompound);
		//if (this.furnaceItemStacks.length<2){ 
		NBTTagList tagList = tagCompound.getTagList("Items", 10);
		this.furnaceItemStacks = new ItemStack[this.getSizeInventory()];
		//System.out.println(tagList.tagCount()+ "Items Read");
		for (int i = 0;i < tagList.tagCount();i++) {
			NBTTagCompound tabCompound1 = tagList.getCompoundTagAt(i);
			int byte0 = tabCompound1.getInteger("Slot");
			if (byte0 >= 0 && byte0 < this.furnaceItemStacks.length){
				this.furnaceItemStacks[byte0] = ItemStack.loadItemStackFromNBT(tabCompound1);
			}
			//System.out.println("tag num."+i+" "+this.furnaceItemStacks[i]);
		}
		
		this.furnaceBurnTime = tagCompound.getShort("BurnTime");
		this.furnaceCookTime = tagCompound.getShort("CookTime");
		this.currentBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
		
		if (tagCompound.hasKey("CustomName", 8)) {
			this.furnaceName = tagCompound.getString("CustomName");
		}
		//}
	}
	
	public void writeToNBT(NBTTagCompound tagCompound) {
		super.writeToNBT(tagCompound);
		tagCompound.setShort("BurnTime", (short)this.furnaceBurnTime);
		tagCompound.setShort("CookTime", (short)this.furnaceCookTime);
		NBTTagList tagList = new NBTTagList();
		//System.out.println(this.furnaceItemStacks.length+ "Items Written");
		for (int i = 0; i<this.furnaceItemStacks.length;i++) {
			if (this.furnaceItemStacks[i] != null) {
				//System.out.println("tag num."+i+" "+this.furnaceItemStacks[i]);
				//System.out.println("tag written num."+i);
				NBTTagCompound tagCompound1 = new NBTTagCompound();
				tagCompound1.setInteger("Slot", i);
				this.furnaceItemStacks[i].writeToNBT(tagCompound1);
				tagList.appendTag(tagCompound1);
			
			}
		}
		
		tagCompound.setTag("Items", tagList);
		
		if (this.hasCustomInventoryName()) {
			tagCompound.setString("CustomName", this.furnaceName);
		}
	}
	
	@SideOnly(Side.CLIENT)
	public int getCookProgressScaled(int par1) {
		return this.furnaceCookTime * par1 / 200;
	}

	@SideOnly(Side.CLIENT)
	public int getBurnTimeRemainingScaled(int par1) {
		if (this.currentBurnTime == 0) {
			this.currentBurnTime = 200;
		}

		return this.furnaceBurnTime * par1 / this.currentBurnTime;
	}
	
	public boolean isBurning() {
		return this.furnaceBurnTime > 0;
	}
	
	public void updateEntity() {
		boolean flag = this.furnaceBurnTime > 0;
		boolean flag1 = false;
		
		if (this.furnaceBurnTime>0) {
			--this.furnaceBurnTime;
		}
		
		if (!this.worldObj.isRemote) {
			if (this.furnaceBurnTime == 0 && this.canSmelt()) {
				this.currentBurnTime = this.furnaceBurnTime = getItemBurnTime(this.furnaceItemStacks[1]);
				
				if (this.furnaceBurnTime > 0) {
					flag1 = true;
					if (this.furnaceItemStacks[1] != null) {
						--this.furnaceItemStacks[1].stackSize;
						
						if (this.furnaceItemStacks[1].stackSize == 0) {
							this.furnaceItemStacks[1] = furnaceItemStacks[1].getItem().getContainerItem(this.furnaceItemStacks[1]);
						}
					}
				}
			}
			
			if (this.isBurning() && this.canSmelt()) {
				++this.furnaceCookTime;
				if (this.furnaceCookTime == 200) {
					this.furnaceCookTime = 0;
					this.smeltItem();
					flag1 = true;
				}
			} else {
				this.furnaceCookTime = 0;
			}
		}
		
		if (flag != this.furnaceBurnTime > 0) {
			flag1 = true;
			TestFurnace2.updateBlockState(this.furnaceBurnTime > 0, this.worldObj, this.xCoord, this.yCoord, this.zCoord);
		}
		
		if (flag1) {
			this.markDirty();
		}
	}
	
	private boolean canSmelt() {
		if (this.furnaceItemStacks[0] == null) {
			return false;
		} else {
			ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
			if (itemStack == null) {
				return false;
			}
			if (this.furnaceItemStacks[2] == null) {
				return true;
			}
			if (!this.furnaceItemStacks[2].isItemEqual(itemStack)) {
				return false;
			}
			int result = furnaceItemStacks[2].stackSize+itemStack.stackSize;
			return result <= getInventoryStackLimit() && result <= this.furnaceItemStacks[2].getMaxStackSize();
		}
	}
	
	public void smeltItem() {
		if (this.canSmelt()) {
			ItemStack itemStack = FurnaceRecipes.smelting().getSmeltingResult(this.furnaceItemStacks[0]);
			if (this.furnaceItemStacks[2] == null) {
				this.furnaceItemStacks[2] = itemStack.copy();
			} else if (this.furnaceItemStacks[2].getItem() == itemStack.getItem()) {
				this.furnaceItemStacks[2].stackSize += itemStack.stackSize;
			}
			
			--this.furnaceItemStacks[0].stackSize;
			
			if (this.furnaceItemStacks[0].stackSize == 0 ) {
				this.furnaceItemStacks[0] = null;
			}
		}
	}
	
	public static int getItemBurnTime(ItemStack itemStack) {
		if (itemStack == null) {
			return 0;
		} else {
			Item item = itemStack.getItem();
			
			if (item instanceof ItemBlock && Block.getBlockFromItem(item) != Blocks.air) {
				Block block = Block.getBlockFromItem(item);
				
				if (block.getMaterial() == Material.rock){
					return 300;
				}
			}
			
			if (item == TestMod.multiWrench) return 1600;
			if (item instanceof ItemTool && ((ItemTool) item).getToolMaterialName().equals("EMERALD")) return 300;
			return GameRegistry.getFuelValue(itemStack);
		}
	}
	
	public static boolean isItemFuel(ItemStack itemStack) {
		return getItemBurnTime(itemStack) > 0;
	}

	@Override
	public boolean isUseableByPlayer(EntityPlayer var1) {
		return this.worldObj.getTileEntity(this.xCoord, this.yCoord, this.zCoord) != this ? false : var1.getDistanceSq((double) this.xCoord+0.5D, (double) this.yCoord+0.5D, (double) this.zCoord+0.5D) <= 64.0D;
	}

	@Override
	public void openInventory() {
		
	}

	@Override
	public void closeInventory() {
		
	}

	@Override
	public boolean isItemValidForSlot(int var1, ItemStack var2) {
		return var1 == 2 ? false : (var1 == 1 ? isItemFuel(var2) : true);
	}

	@Override
	public int[] getAccessibleSlotsFromSide(int var1) {
		return var1 == 0 ? slotsBottom : (var1 == 1 ? slotsTop : slotsSides);
	}

	@Override
	public boolean canInsertItem(int var1, ItemStack var2,
			int var3) {
		return this.isItemValidForSlot(var1, var2);
	}

	@Override
	public boolean canExtractItem(int var1, ItemStack var2,
			int var3) {
		return var3!=0 || var1!=1 || var2.getItem() == Items.bucket;
	}

}
