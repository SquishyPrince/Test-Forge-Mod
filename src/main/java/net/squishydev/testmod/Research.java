package net.squishydev.testmod;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Research {
	private static LinkedHashMap<ResearchMaster, String> registeredResearch = new LinkedHashMap<ResearchMaster, String>();
	private static LinkedHashMap<ResearchClass, String> registeredResearchClasses = new LinkedHashMap<ResearchClass, String>();
	private static LinkedHashMap<ResearchMaster, ResearchClass> researchClasses = new LinkedHashMap<ResearchMaster, ResearchClass>();
	
	private String[] researchNames;
	private String[] researchClassNames;
	private boolean[] researchAchieved;
	private Object[] researchNameSet;
	private Object[] researchClassesSet;
	
	public Research() {
		researchNames = new String[registeredResearch.size()];
		researchClassNames = new String[registeredResearchClasses.size()];
		
		researchNameSet = registeredResearch.keySet().toArray();
		researchClassesSet = registeredResearchClasses.keySet().toArray();
		
		for (int i = 0;i<researchNames.length;i++) {
			researchClasses.put((ResearchMaster)researchNameSet[i], ((ResearchClass)(((ResearchMaster)(researchNameSet[i])).getResearchClass())));
			researchNameSet[i] = (ResearchMaster) researchNameSet[i];
			researchNames[i] = registeredResearch.get(researchNameSet[i]);
			((ResearchClass)(researchClasses.get(researchNameSet[i]))).registerResearch((ResearchMaster)researchNameSet[i]);
		}
		
		for (int i = 0;i<researchClassesSet.length;i++) {
			researchClassNames[i] = registeredResearchClasses.get(researchClassesSet[i]);
		}
	}
	
	public String[] getResearchNameArray() {
		return researchNames;
	}

	public boolean[] getResearchedArray() {
		return researchAchieved;
	}
	
	public boolean getResearchStatusFromName(String name) {
		int index = -1;
		
		for (int i = 0;i<this.researchNames.length;i++) {
			if (name==researchNames[i]) {
				index = i;
			}
		}
		
		if (index!=-1&&researchAchieved!=null) {
			return researchAchieved[index];
		} else {
			return false;
		}
	}
	
	public ResearchMaster getResearchFromName(String name) {
		int index = -1;
		
		for (int i = 0;i<this.researchNames.length;i++) {
			if (name==researchNames[i]) {
				index = i;
			}
		}
		
		if (index!=-1) {
			return (ResearchMaster)researchNameSet[index];
		} else {
			return null;
		}
	}
	
	public void setResearchedFromName(String name, boolean setting) {
int index = -1;
		
		for (int i = 0;i<this.researchNames.length;i++) {
			if (name==researchNames[i]) {
				index = i;
			}
		}
		
		if (index!=-1) {
			researchAchieved[index] = setting;
		} else {
			System.out.println("Something weird happened, tried to set a research that doesn't exist!");
		}
	}

	public void setResearched(boolean[] researchAchieved) {
		this.researchAchieved = new boolean[researchNames.length];
		System.out.println(researchNames.length+" woah there research, don't be afraid to exist! Looks like we made an array!");
		for (int i = 0;i<researchNames.length;i++) {
			this.researchAchieved[i] = researchAchieved[i];
			System.out.println("And the #"+i+" value of that array was"+this.researchAchieved[i]);
		}
		System.out.println("All done! now the achievements of our lovely player can be known worldwide! Or similar");
	}

	public static void registerResearch(ResearchMaster testResearch, String string) {
		System.out.println("We set up a research! It was called "+string);
		registeredResearch.put(testResearch, string);
	}
	
	public static void registerResearchClass(ResearchClass testResearch, String string) {
		registeredResearchClasses.put(testResearch, string);
	}

}
