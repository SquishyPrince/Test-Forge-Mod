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
	public Research research;
	
	public TestPlayerData(EntityPlayer player) {
		System.out.println(player.getDisplayName()+" is a cool dude!");
		this.player = player;
		this.research = new Research();
		this.saveNBTData(player.getEntityData());
		this.loadNBTData(player.getEntityData());
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
			System.out.println(researchNames[i]+" Yeah!");
			System.out.println("state: "+researchAchieved[i]);
			properties.setBoolean(researchNames[i], research.getResearchStatusFromName(researchNames[i]));
		}
		
		compound.setTag(EXT_PROP_NAME, properties);
	}

	@Override
	public void loadNBTData(NBTTagCompound compound) {
		System.out.println("Yep, we're definitely loading something, but what could it be?");
		NBTTagCompound properties = (NBTTagCompound)compound.getTag(EXT_PROP_NAME);
		String[] researchNames = research.getResearchNameArray();
		System.out.println("well, we got a few strings, the first of which is "+researchNames[0]);
		boolean[] researchAchieved = new boolean[researchNames.length];
		System.out.println("We made a boolean array with a length of "+researchNames.length);
		for (int i = 0;i<researchNames.length;i++) {
			System.out.println("The name is: "+researchNames[i]);
			try {
				researchAchieved[i] = properties.getBoolean(researchNames[i]);
			} catch (Exception e) {
				
			}
			System.out.println("And in that array, #"+i+" is "+researchAchieved[i]);
		}
		this.research.setResearched(researchAchieved);
		System.out.println("There we go, the array got sent to the Research thingy, good job pooter");
	}

	@Override
	public void init(Entity entity, World world) {
		
	}

}
