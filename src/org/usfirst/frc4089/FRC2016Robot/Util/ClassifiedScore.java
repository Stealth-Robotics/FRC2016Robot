package org.usfirst.frc4089.FRC2016Robot.Util;

import com.ni.vision.NIVision.PointFloat;
import com.ni.vision.NIVision.Rect;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class ClassifiedScore implements Sendable {
	
	public double AspectRatioScore;
	public double CoverageAreaScore;
	public PointFloat AimingPos;
	public double Distance;
	public Rect BoundingRectLocation;
	public double PerimDiffScore;
	public double SkewnessScore;
	public boolean IsDummy;
	
	private ITable table;
	
	public ClassifiedScore()
	{
		BoundingRectLocation = new Rect(0,0,0,0);
		AspectRatioScore = -1;
		SkewnessScore = -1;
		CoverageAreaScore = -1;
		PerimDiffScore = -1;
		AimingPos = new PointFloat(0, 0);
		Distance = -1;
		IsDummy = true;
	}
	
	public ClassifiedScore(VisionParticleMeasurements vpm)
	{
		BoundingRectLocation = new Rect((int)vpm.BoundingRectTop,
				(int)vpm.BoundingRectLeft, 
				(int)vpm.BoundingRectHeight,
				(int)vpm.BoundingRectWidth);
		AspectRatioScore = ScoreAspectRatio(vpm.AspectRatio);
		SkewnessScore = ScoreSkewness(vpm.RelativeSkewness);
		CoverageAreaScore = ScoreCoverageArea(vpm.BoundingRectCoverageArea);
		PerimDiffScore = ScorePerimeterDifference(vpm.NetPerimeterDistance);
		AimingPos = GetAimingPos();
		Distance = GetDist();
		IsDummy = false;
	}
	
	public double ScoreAspectRatio(double ratio)
	{
		//scoring from 0-100
		//score = 100 when ratio = 1.6
		//score = 0 when ratio = NaN (ideally)
		//(0, 0) (1.6, 100) (3.2, 0)
		//m = 100 / 1.6 = 62.5
		//y = -62.5abs(x - 1.6) + 100
		if(ratio > 3.2 || ratio < 0)
		{
			return 0;
		}
		//proven by real math: y = -39.06x^2 + 125 x
		return -62.5 * Math.abs(ratio - 1.6) + 100;
	}
	
	public double ScoreSkewness(double skewness)
	{
		//scoring from 0-100
		//score = 100 when ratio = 0
		//score = 0 when ratio = 1
		//(0, 100) (1, 0)
		//m = -100 / 1 = -100;
		//y = -100x + 100
		if(skewness > 1 || skewness < 0)
		{
			return 0;
		}
		return 100 - (-100 * skewness + 100);
	}
	
	public double ScoreCoverageArea(double ratio)
	{
		//scoring from 0-100.
		//score = 100 when ratio = 0.3333333
		//score = 0 when ratio = NaN (ideally)
		//(0, 0) (1/3, 100) (2/3, 0)
		//m = 100 / (1/3) = 300
		//y = -300abs(x - 1 / 3) + 100
		if(ratio > (2.0 / 3.0) || ratio < 0)
		{
			return 0;
		}
		//proven with real math: y = -900x^2 + 600x
		return -300 * Math.abs(ratio - (1.0 / 3.0)) + 100;
	}
	
	public double ScorePerimeterDifference(double diff)
	{
		//score 0-100
		//score = 100 when diff = 10
		//score = 0 when diff = 0
		//(0, 0) (10, 100) (20, 0)
		//m = 100 / 10 = 10
		//y = -10abs(diff - 10) + 100
		if(diff < 0 || diff > 195)
		{
			return 0;
		}
		return -10 * Math.abs(diff - 10) + 100;
	}
	
	public PointFloat GetAimingPos()
	{
		double resX = VisionProcessing.ResX;
		double resY = VisionProcessing.ResY;
		double px = BoundingRectLocation.left + BoundingRectLocation.width / 2;
		double py = BoundingRectLocation.top + BoundingRectLocation.height / 4;
		double x = (px - resX / 2) / (resX / 2);
		double y = (py - resY / 2) / (resY / 2);
		return new PointFloat(x, -y);
	}
	
	public double GetDist()
	{
		double resX = VisionProcessing.ResX;
		double resY = VisionProcessing.ResY;
		double numTargetWidthsInFrame = resX / BoundingRectLocation.width;
		double inchesWidth = 20 * numTargetWidthsInFrame / 2;
		//Pi/360 because lens angle/2
		double dist = inchesWidth / Math.tan(VisionProcessing.LENS_ANGLE * Math.PI / 360);
		//dist is the straight to target distance (diagonal)
		//return ground distance
		//a^2 + b^2 = c^2
		//b = 8ft = 96in (height)
		//c = dist
		//a = sqrt(dist^2 - 96^2) = sqrt(dist^2 - 9216)
		//a / 12 = ft distance on ground
		return Math.sqrt(dist * dist - 9216) / 12;
	}
	
	@Override
	public void initTable(ITable subtable) {
		// TODO Auto-generated method stub
		table = subtable;
		if(table != null) {
			updateTable(table);
		}
	}
	@Override
	public ITable getTable() {
		// TODO Auto-generated method stub
		return table;
	}
	@Override
	public String getSmartDashboardType() {
		// TODO Auto-generated method stub
		return "Classified Particle Score";
	}
	public void updateTable(ITable table) {
        table.putNumber("Aspect Ratio Score", AspectRatioScore);
        table.putNumber("Coverage Area Score", CoverageAreaScore);
        table.putNumber("Skewness Score", SkewnessScore);
        table.putNumber("Perimeter Difference Score", PerimDiffScore);
        table.putNumberArray("Aiming Coordinates", new double[] { AimingPos.x, AimingPos.y });
        table.putNumber("Distance", Distance);
        table.putNumberArray("Rect Data", convertRectToArray());
        table.putBoolean("Dummy", IsDummy);
    }
	public double[] convertRectToArray()
	{
		return new double[] {BoundingRectLocation.top,
				BoundingRectLocation.top + BoundingRectLocation.height,
				BoundingRectLocation.left, 
				BoundingRectLocation.left + BoundingRectLocation.width};
	}
}
