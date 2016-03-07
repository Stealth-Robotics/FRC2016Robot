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
	
	public ClassifiedScore getBestTarget()
	{
		if(scores.size() == 0) return null;
		ClassifiedScore ret = null;
		for(int i = 0; i < scores.size(); i++)
		{
			ClassifiedScore sc = scores.get(i);
			if(ret == null || ret.IsDummy)
			{
				ret = sc;
				continue;
			}
			if(Math.abs(ret.AimingPos.x) > Math.abs(sc.AimingPos.x))
			{
				ret = sc;
			}
		}
		return ret;
	}
	
	public void Send()
	{
		Filter(20.75, 37.5, 70, 0);
		scores.sort(this);
		for(int i = 0; i < 3; i++)
		{
			if(i < scores.size())
			{
				SmartDashboard.putData("Towers/" + i, scores.get(i));
			}
			else
			{
				ClassifiedScore dummy = new ClassifiedScore();
				SmartDashboard.putData("Towers/" + i, dummy);
			}
		}
	}
	
	private void Filter(double arThreshold, double caThreshold,
			double skewThreshold, double pdThreshold)
	{
		scores.sort(this);
		Iterator<ClassifiedScore> it = scores.iterator();
		while(it.hasNext())
		{
			ClassifiedScore cs = it.next();
			if(cs.AspectRatioScore < arThreshold ||
					cs.CoverageAreaScore < caThreshold ||
					cs.SkewnessScore < skewThreshold ||
					cs.PerimDiffScore < pdThreshold)
			{
				it.remove();
			}
		}
	}

	@Override
	public int compare(ClassifiedScore o1, ClassifiedScore o2) {
		// TODO Auto-generated method stub
		//sort first by aspect ratio, then coverage area, then skewness
		if(o1.AspectRatioScore != o2.AspectRatioScore)
		{
			return (int)(o2.AspectRatioScore - o1.AspectRatioScore);
		}
		else if(o1.CoverageAreaScore != o2.CoverageAreaScore)
		{
			return (int)(o2.CoverageAreaScore - o1.CoverageAreaScore);
		}
		else
		{
			return (int)(o2.SkewnessScore - o1.SkewnessScore);
		}
	}
}
