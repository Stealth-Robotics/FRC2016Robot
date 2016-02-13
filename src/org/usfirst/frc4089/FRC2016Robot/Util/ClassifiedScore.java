package org.usfirst.frc4089.FRC2016Robot.Util;

import com.ni.vision.NIVision.Rect;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class ClassifiedScore implements Sendable {
	
	public double ClassificationScore;
	public double IDScore;
	public String ClassName;
	public Rect BoundingRectLocation;
	
	private ITable table;
	
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
        table.putNumber("Class. Score", ClassificationScore);
        table.putNumber("ID Score", IDScore);
        table.putString("Class Name", ClassName);
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
