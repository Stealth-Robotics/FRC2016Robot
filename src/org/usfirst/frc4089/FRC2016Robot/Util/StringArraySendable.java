package org.usfirst.frc4089.FRC2016Robot.Util;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.tables.ITable;

public class StringArraySendable implements Sendable {

	ITable table;
	
	String[] vals;
	
	public StringArraySendable(String[] values)
	{
		vals = values;
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
		return "Auton Options";
	}
	
	public void updateTable(ITable table) {
        table.putStringArray("Auto List", vals);
    }
}
