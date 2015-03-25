package net.squishydev.testmod;

import java.util.HashMap;
import java.util.Hashtable;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraft.world.WorldSavedData;
import net.minecraft.world.storage.MapStorage;
import net.minecraft.block.Block;

public class CustomWorldData extends WorldSavedData{
	
	private final String mapName;
	
	private HashMap<Vector, MultiBlock> multiBlocks;
	
	public CustomWorldData(String i) {
		super(i);
		this.multiBlocks = new HashMap<Vector, MultiBlock>();
		this.mapName = i;
	}

	final static String key = "TestModData";

	public CustomWorldData forWorld(World world) {
		MapStorage storage = world.perWorldStorage;
		CustomWorldData result = (CustomWorldData)storage.loadData(CustomWorldData.class, key);
		if (result == null) {
			result = new CustomWorldData(this.mapName);
			storage.setData(key, result);
		}
		
		return result;
	}
	
	public void addData(Vector vector, MultiBlock multiBlock) {
		multiBlocks.put(vector, multiBlock);
		this.markDirty();
	}
	
	public MultiBlock getData(Vector vector) {
		if (multiBlocks.get(vector)!=null) {
			return multiBlocks.get(vector);
		}
		return null;
	}
	
	public boolean getValue(MultiBlock multiBlock) {
		return multiBlocks.containsValue(multiBlock);
	}
	
	public void removeData(Vector vector) {
		if (multiBlocks.get(vector)!=null) {
			multiBlocks.remove(vector);
		}
		this.markDirty();
	}

	@Override
	public void readFromNBT(NBTTagCompound nbt) {
		
	}

	@Override
	public void writeToNBT(NBTTagCompound nbt) {
		
	}

}
