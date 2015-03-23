package net.squishydev.testmod;

import io.netty.buffer.ByteBuf;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class AssemblyResult {

	public static final int NONE = 0, OK = 1, OVERFLOW = 2, MISSING = 3, ERROR = 4, COMPILING = 5, INCONSISTENT = 6, OK_WARNINGS = 7;
	
	FoundBlock controllerBlock;
	final List<FoundBlock> assembledBlocks = new ArrayList<FoundBlock>();
	int resultCode;
	int blockCount;
	int tileEntityCount;
	public int xOffset, yOffset, zOffset;
	
	public AssemblyResult(ByteBuf buffer) {
		resultCode = buffer.readByte();
		if (resultCode == NONE) return;
		blockCount = buffer.readInt();
		tileEntityCount = buffer.readInt();
	}
	
	public AssemblyResult(NBTTagCompound compound, World world) {
		resultCode = compound.getByte("res");
		blockCount = compound.getInteger("blockc");
		tileEntityCount = compound.getInteger("tec");
		xOffset = compound.getInteger("xO");
		yOffset = compound.getInteger("yO");
		zOffset = compound.getInteger("zO");
		if (compound.hasKey("list"))
		{
			NBTTagList list = compound.getTagList("list", 10);
			for (int i = 0; i < list.tagCount(); i++)
			{
				NBTTagCompound comp = list.getCompoundTagAt(i);
				assembledBlocks.add(new FoundBlock(comp, world));
			}
		}
		if (compound.hasKey("marker"))
		{
			NBTTagCompound comp = compound.getCompoundTag("marker");
			controllerBlock = new FoundBlock(comp, world);
		}
	}
	
	AssemblyResult() {
		clear();
	}
	
	void assembleBlock(FoundBlock lb)
	{
		assembledBlocks.add(lb);
		blockCount = assembledBlocks.size();

		if (lb.tileEntity != null)
		{
			tileEntityCount++;
		}
		xOffset = Math.min(xOffset, lb.coords.chunkPosX);
		yOffset = Math.min(yOffset, lb.coords.chunkPosY);
		zOffset = Math.min(zOffset, lb.coords.chunkPosZ);
	}
	
	public void clear()
	{
		resultCode = NONE;
		controllerBlock = null;
		assembledBlocks.clear();
		blockCount = tileEntityCount = 0;
		xOffset = yOffset = zOffset = 0;
	}
	
	public int getCode()
	{
		return resultCode;
	}
	
	public boolean isOK()
	{
		return resultCode == OK || resultCode == OK_WARNINGS;
	}
	
	public FoundBlock getController()
	{
		return controllerBlock;
	}
	
	public int getBlockCount()
	{
		return blockCount;
	}
	
	public int getTileEntityCount()
	{
		return tileEntityCount;
	}
	
	public FoundBlock getBlock(int i) {
		return assembledBlocks.get(i);
	}
	
	public void checkConsistent(World world)
	{
		boolean warn = false;
		for (FoundBlock lb : assembledBlocks)
		{
			Block block = world.getBlock(lb.coords.chunkPosX, lb.coords.chunkPosY, lb.coords.chunkPosZ);
			if (block != lb.block)
			{
				resultCode = INCONSISTENT;
				return;
			}
			int meta = world.getBlockMetadata(lb.coords.chunkPosX, lb.coords.chunkPosY, lb.coords.chunkPosZ);
			if (meta != lb.blockMetaData)
			{
				warn = true;
			}
		}
		resultCode = warn ? OK_WARNINGS : OK;
	}
	
	public Block[][][] getStructure(World world) {
		ChunkPosition min = assembledBlocks.get(0).coords;
		ChunkPosition max = min;
		
		for (int i = 0; i<assembledBlocks.size();i++) {
			ChunkPosition curr = assembledBlocks.get(i).coords;
			System.out.println(curr.chunkPosX+" X");
			System.out.println(curr.chunkPosY+" Y");
			System.out.println(curr.chunkPosZ+" Z");
			if (curr.chunkPosX<min.chunkPosX||curr.chunkPosY<min.chunkPosY||curr.chunkPosZ<min.chunkPosZ) {
				min = curr;
			}
			
			if (curr.chunkPosX>max.chunkPosX||curr.chunkPosY>max.chunkPosY||curr.chunkPosZ>max.chunkPosZ) {
				max = curr;
			}
		}
		System.out.println(assembledBlocks.size()+" LIST SIZE");
		System.out.println(min.chunkPosX+" FINAL X MIN");
		System.out.println(min.chunkPosY+" FINAL Y MIN");
		System.out.println(min.chunkPosZ+" FINAL Z MIN");
		System.out.println(max.chunkPosX+" FINAL X MAX");
		System.out.println(max.chunkPosY+" FINAL Y MAX");
		System.out.println(max.chunkPosZ+" FINAL Z MAX");
		System.out.println(max.chunkPosX-min.chunkPosX+" FINAL X DIFF");
		System.out.println(max.chunkPosY-min.chunkPosX+" FINAL Y DIFF");
		System.out.println(max.chunkPosZ-min.chunkPosX+" FINAL Z DIFF");
		
		Block[][][] temp = new Block[max.chunkPosY-min.chunkPosY+1][max.chunkPosX-min.chunkPosX+1][max.chunkPosZ-min.chunkPosZ+1];
		
		System.out.println(temp[0][0].length+" TEMP X");
		System.out.println(temp.length+" TEMP Y");
		System.out.println(temp[0].length+" TEMP Z");
		for (int i = 0;i<temp.length;i++) {
			for (int j = 0;j<temp[i].length;j++) {
				for (int k = 0;k<temp[i][j].length;k++) {
					temp[k][i][j] = world.getBlock(j+min.chunkPosX, k+min.chunkPosY, i+min.chunkPosZ);
					System.out.println(temp[k][i][j].getUnlocalizedName());
				}
			}
		}
		
		return temp;
	}
	
	public void commit(MultiBlock multiBlock) {
		for (int i = 0;i<assembledBlocks.size();i++) {
			ChunkPosition coords = assembledBlocks.get(i).coords;
			TestMod.worldData.addData(Vector.createVectorHelper(coords.chunkPosX, coords.chunkPosY, coords.chunkPosZ), multiBlock);
		}
	}
	
	public void writeNBTFully(NBTTagCompound compound)
	{
		writeNBTMetadata(compound);
		NBTTagList list = new NBTTagList();
		for (FoundBlock lb : assembledBlocks)
		{
			NBTTagCompound comp = new NBTTagCompound();
			lb.writeToNBT(comp);
			list.appendTag(comp);
		}
		compound.setTag("list", list);
		
		if (controllerBlock != null)
		{
			NBTTagCompound comp = new NBTTagCompound();
			controllerBlock.writeToNBT(comp);
			compound.setTag("marker", comp);
		}
	}
	
	public void writeNBTMetadata(NBTTagCompound compound)
	{
		compound.setByte("res", (byte) getCode());
		compound.setInteger("blockc", getBlockCount());
		compound.setInteger("tec", getTileEntityCount());
		compound.setInteger("xO", xOffset);
		compound.setInteger("yO", yOffset);
		compound.setInteger("zO", zOffset);
	}
}
