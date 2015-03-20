package net.squishydev.testmod;

import net.minecraft.block.Block;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.ChunkPosition;
import net.minecraft.world.World;

public class FoundBlock {

	public final Block block;
	public final int blockMetaData;
	public final TileEntity tileEntity;
	public final ChunkPosition coords;
	
	public FoundBlock(Block block, int  metaData, ChunkPosition coords) {
		this(block, metaData, null, coords);
	}
	
	public FoundBlock(Block block, int metaData, TileEntity tileEntity, ChunkPosition coords) {
		this.block = block;
		this.blockMetaData = metaData;
		this.tileEntity = tileEntity;
		this.coords = coords;
	}
	
	public FoundBlock(NBTTagCompound compound, World world) {
		this.block = Block.getBlockById(compound.getInteger("block"));
		this.blockMetaData = compound.getInteger("meta");
		this.coords = new ChunkPosition(compound.getInteger("x"), compound.getInteger("y"), compound.getInteger("z"));
		this.tileEntity = world == null ? null : world.getTileEntity(coords.chunkPosX, coords.chunkPosY, coords.chunkPosZ);
	}
	
	@Override
	public String toString() {
		return new StringBuilder("FoundBlock [block=").append(block).append(", metaData=").append(blockMetaData).append(", coords=[").append(coords.chunkPosX).append(", ").append(coords.chunkPosY).append(", ").append(coords.chunkPosZ).append("]]").toString();
	}
	
	public void writeToNBT(NBTTagCompound compound) {
		compound.setShort("block", (short)Block.getIdFromBlock(block));
		compound.setInteger("meta", blockMetaData);
		compound.setInteger("x", coords.chunkPosX);
		compound.setInteger("y", coords.chunkPosY);
		compound.setInteger("z", coords.chunkPosZ);
	}
}
