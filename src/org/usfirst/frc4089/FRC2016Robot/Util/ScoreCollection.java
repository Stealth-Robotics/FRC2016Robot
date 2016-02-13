package org.usfirst.frc4089.FRC2016Robot.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.tables.ITable;

public class ScoreCollection implements Comparator<ClassifiedScore> {

	ArrayList<ClassifiedScore> scores;
	
	public ScoreCollection()
	{
		scores = new ArrayList<ClassifiedScore>();
	}
	
	public void add(ClassifiedScore cs)
	{
		scores.add(cs);
	}
	
	public void Send()
	{
		//Filter(75, 75);
		for(int i = 0; i < scores.size(); i++)
		{
			SmartDashboard.putData("Towers/" + i, scores.get(i));
		}
	}
	
	private void Filter(double arThreshold, double caThreshold)
	{
		scores.sort(this);
		Iterator<ClassifiedScore> it = scores.iterator();
		while(it.hasNext())
		{
			ClassifiedScore cs = it.next();
			if(cs.AspectRatioScore < arThreshold ||
					cs.CoverageAreaScore < caThreshold)
			{
				it.remove();
			}
		}
	}

	@Override
	public int compare(ClassifiedScore o1, ClassifiedScore o2) {
		// TODO Auto-generated method stub
		if(o1.AspectRatioScore == o2.AspectRatioScore)
		{
			return (int)(o2.CoverageAreaScore - o1.CoverageAreaScore);
		}
		else
		{
			return (int)(o2.AspectRatioScore - o1.AspectRatioScore);
		}
	}
}
