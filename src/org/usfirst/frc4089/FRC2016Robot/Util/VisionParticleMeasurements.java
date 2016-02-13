package org.usfirst.frc4089.FRC2016Robot.Util;

import java.util.Comparator;

public class VisionParticleMeasurements implements Comparator<VisionParticleMeasurements>,
	Comparable<VisionParticleMeasurements> {
	public double TotalParticleArea;
	public double BoundingRectLeft;
	public double BoundingRectWidth;
	public double BoundingRectTop;
	public double BoundingRectHeight;
	public double ImageCoverageArea;
	public double BoundingRectCoverageArea;
	
	@Override
	public int compareTo(VisionParticleMeasurements that) {
		// TODO Auto-generated method stub
		return (int)(that.TotalParticleArea - this.TotalParticleArea);
	}
	@Override
	public int compare(VisionParticleMeasurements that, VisionParticleMeasurements theOtherThing) {
		// TODO Auto-generated method stub
		return (int)(that.TotalParticleArea - theOtherThing.TotalParticleArea);
	}
}
