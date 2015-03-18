package net.squishydev.testmod;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnace;
import net.minecraft.item.ItemStack;

public class ContainerGuiTestFurnace extends Container {

	private testFurnaceTileEntity testFurnace;
	
	public int lastBurnTime; //Time left to burn
	public int lastItemBurnTime; //Start Time for this fuel
	public int lastCookTime; //How long left before cooked
	
	public ContainerGuiTestFurnace(InventoryPlayer invPlayer, testFurnaceTileEntity tileEntity) {
		this.testFurnace = tileEntity;
		
		this.addSlotToContainer(new Slot(tileEntity, 0, 56, 17));
		this.addSlotToContainer(new Slot(tileEntity, 1, 56, 53));
		this.addSlotToContainer(new SlotFurnace(invPlayer.player, tileEntity, 2, 116, 35));
		
		for (int i = 0; i<3; i++) {
			for (int j = 0; j<9; j++) {
				this.addSlotToContainer(new Slot(invPlayer, j + i*9 + 9, 8+(j*18), 84 + i*18));
			}
		}
		
		for (int i = 0;i<9;i++) {
			this.addSlotToContainer(new Slot(invPlayer, i, 8+(i*18), 142));
		}
	}
	
	public void addCraftingToCrafter(ICrafting iCrafting) {
		super.addCraftingToCrafters(iCrafting);
		iCrafting.sendProgressBarUpdate(this, 0, this.testFurnace.cookTime);
		iCrafting.sendProgressBarUpdate(this, 1, this.testFurnace.burnTime);
		iCrafting.sendProgressBarUpdate(this, 2, this.testFurnace.currentItemBurnTime);
	}
	
	public void detectAndSendChanges() {
		super.detectAndSendChanges();
		
		for (int i = 0;i<this.crafters.size();i++) {
			ICrafting icrafting = (ICrafting)this.crafters.get(i);
			
			if (this.lastCookTime!=this.testFurnace.cookTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.testFurnace.cookTime);
			}
			if (this.lastBurnTime!=this.testFurnace.burnTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.testFurnace.burnTime);
			}
			if (this.lastItemBurnTime!=this.testFurnace.currentItemBurnTime) {
				icrafting.sendProgressBarUpdate(this, 0, this.testFurnace.currentItemBurnTime);
			}
		}
		this.lastCookTime = this.testFurnace.cookTime;
		this.lastBurnTime = this.testFurnace.burnTime;
		this.lastBurnTime = this.testFurnace.currentItemBurnTime;
	}
	
	@SideOnly(Side.CLIENT)
	public void updateProgressBar(int par1, int par2) {
		if (par1 == 0) this.testFurnace.cookTime = par2;
		if (par1 == 1) this.testFurnace.cookTime = par2;
		if (par1 == 2) this.testFurnace.currentItemBurnTime = par2;
	}
	
	public ItemStack transferStackInSlot(EntityPlayer player, int slot) {
		return null;
		
	}
	
	public boolean canInteractWith(EntityPlayer player) {
		return this.testFurnace.isUseableByPlayer(player);
	}

}
