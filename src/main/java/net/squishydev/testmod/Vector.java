package net.squishydev.testmod;

import net.minecraft.util.Vec3;

public class Vector extends Vec3{
	
	protected Vector(double x, double y, double z) {
		super(x, y, z);
	}
	
	public static Vector createVectorHelper(double x, double y, double z)
    {
        return new Vector(x, y, z);
    }

	@Override
	public int hashCode() {
		return (73856093 * (int)this.xCoord ^ 19349663 * (int)this.yCoord ^ 83492791 * (int)this.zCoord);
	}
	
	@Override
	public boolean equals(Object obj) {
		Vector vector = (Vector)obj;
		return this.xCoord==vector.xCoord&&this.yCoord==vector.yCoord&&this.zCoord==vector.zCoord;
	}
}
