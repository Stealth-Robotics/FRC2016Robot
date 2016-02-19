package org.usfirst.frc4089.FRC2016Robot.Util;

import java.util.concurrent.atomic.AtomicBoolean;

public class VisionRunnable implements Runnable {

	AtomicBoolean finished;
	
	Thread visionRunnableThread;
	
	public VisionRunnable()
	{
		finished = new AtomicBoolean(false);
		visionRunnableThread = new Thread(this);
	}
	
	public void startup()
	{
		visionRunnableThread.start();
	}
	
	public void shutdown()
	{
		finished.set(true);
	}
	
	public static ScoreCollection getThreadSafeScore()
	{
		return VisionProcessing.scores.get();
	}
	
	public void init()
	{
		VisionProcessing.VPInit();
	}
	
	public void execute()
	{
		VisionProcessing.Process();
	}
	
	public void end()
	{
		VisionProcessing.VPDispose();
	}
	
	@Override
	public void run() {
		init();
		while(!finished.get())
		{
			execute();
		}
		end();
	}

}
