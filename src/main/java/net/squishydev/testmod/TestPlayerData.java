package net.squishydev.testmod;

import java.util.HashMap;
import java.util.Set;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.nbt.NBTBase;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;

public class TestPlayerData implements IExtendedEntityProperties{

	public final static String EXT_PROP_NAME = "TestModData";
	private final EntityPlayer player;
	public Research research = new Research();
	
	public TestPlayerData(EntityPlayer player) {
		this.player = player;
	}
	
	public static final void register(EntityPlayer player) {
		player.registerExtendedProperties(TestPlayerData.EXT_PROP_NAME, new TestPlayerData(player));
	}
	
	public static final TestPlayerData get(EntityPlayer player) {
		return (TestPlayerData) player.getExtendedProperties(EXT_PROP_NAME);
	}
	
	@Override
	public void saveNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = new NBTTagCompound();
		String[] researchNames = research.getResearchNameArray();
		boolean[] researchAchieved = research.getResearchedArray();
		if (researchAchieved==null) {
			researchAchieved = new boolean[researchNames.length];
		}
		
		for (int i = 0;i<researchNames.length;i++) {
			properties.setBoolean(researchNames[i], researchAchieved[i]);
		}
		
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
		String[] researchNames = research.getResearchNameArray();
		boolean[] researchAchieved = new boolean[researchNames.length];
		
		for (int i = 0;i<researchNames.length;i++) {
			researchAchieved[i] = properties.getBoolean(researchNames[i]);
		}
		this.research.setResearched(researchAchieved);
	}

	@Override
	public void init(Entity entity, World world) {
		
	}

}
