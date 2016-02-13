package org.usfirst.frc4089.FRC2016Robot.Util;

import com.ni.vision.NIVision.Point;
import com.ni.vision.NIVision.Rect;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class ClassifiedScore implements Sendable {
	
	public double AspectRatioScore;
	public double CoverageAreaScore;
	public Point AimingPos;
	public double Distance;
	public Rect BoundingRectLocation;
	
	private ITable table;
	
	public ClassifiedScore(VisionParticleMeasurements vpm)
	{
		BoundingRectLocation = new Rect((int)vpm.BoundingRectTop,
				(int)vpm.BoundingRectLeft, 
				(int)vpm.BoundingRectHeight,
				(int)vpm.BoundingRectWidth);
		AspectRatioScore = vpm.AspectRatio;//ScoreAspectRatio(vpm.AspectRatio);
		CoverageAreaScore = vpm.BoundingRectCoverageArea;//ScoreCoverageArea(vpm.BoundingRectCoverageArea);
		AimingPos = GetAimingPos();
		Distance = GetDist();
	}
	
	public double ScoreAspectRatio(double ratio)
	{
		//scoring from 0-100
		//score = 100 when ratio = 1.6
		//score = 0 when ratio = NaN (ideally)
		//(0, 0) (1.6, 100) (3.2, 0)
		if(ratio > 3.2 || ratio < 0)
		{
			return 0;
		}
		//proven by real math: y = -39.06x^2 + 125 x
		return -39.06 * ratio * ratio + 125 * ratio;
	}
	
	public double ScoreCoverageArea(double ratio)
	{
		//scoring from 0-100.
		//score = 100 when ratio = 0.3333333
		//score = 0 when ratio = NaN (ideally)
		//(0, 0) (1/3, 100) (2/3, 0)
		if(ratio > (2.0 / 3.0) || ratio < 0)
		{
			return 0;
		}
		//proven with real math: y = -900x^2 + 600x
		return -900 * ratio * ratio + 600 * ratio;
	}
	
	public Point GetAimingPos()
	{
		return new Point(0, 0);
	}
	
	public double GetDist()
	{
		return 0;
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
        table.putNumberArray("Aiming Coordinates", new double[] { AimingPos.x, AimingPos.y });
        table.putNumber("Distance", Distance);
        table.putNumberArray("Rect Data", convertRectToArray());
    }
	public double[] convertRectToArray()
	{
		return new double[] {BoundingRectLocation.top,
				BoundingRectLocation.top + BoundingRectLocation.height,
				BoundingRectLocation.left, 
				BoundingRectLocation.left + BoundingRectLocation.width};
	}
}
