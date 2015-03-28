package net.squishydev.testmod;

import java.util.ArrayList;
import java.util.List;

public class ResearchClass {
	public static List<ResearchMaster> research = new ArrayList<ResearchMaster>();

	public static void registerResearch(ResearchMaster researchMaster) {
		research.add(researchMaster);
	}

}
