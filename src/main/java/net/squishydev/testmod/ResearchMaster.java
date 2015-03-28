package net.squishydev.testmod;

public class ResearchMaster {
	private static ResearchClass researchClass;
	private String name;

	public ResearchMaster(ResearchClass testResearchClass, String string) {
		researchClass = testResearchClass;
		name = string;
	}

	public ResearchClass getResearchClass() {
		return researchClass;
	}

}
