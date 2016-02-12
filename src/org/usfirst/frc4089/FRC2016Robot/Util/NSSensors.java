package org.usfirst.frc4089.FRC2016Robot.Util;

import edu.wpi.first.wpilibj.BuiltInAccelerometer;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;

public class NSSensors {
	
    public static Counter leftSpinRateCounter;
    public static Counter rightSpinRateCounter;
    public static Accelerometer builtInAcc;
    
    public static void init()
    {
    	leftSpinRateCounter = new Counter();
        leftSpinRateCounter.setUpSource(0);
        leftSpinRateCounter.setDistancePerPulse(1.0 / 6.0);
        leftSpinRateCounter.setMaxPeriod(0.1);
        leftSpinRateCounter.setSamplesToAverage(10);
        leftSpinRateCounter.setUpDownCounterMode();
        
        rightSpinRateCounter = new Counter();
        rightSpinRateCounter.setUpSource(1);
        rightSpinRateCounter.setDistancePerPulse(1.0 / 6.0);
        rightSpinRateCounter.setMaxPeriod(0.1);
        rightSpinRateCounter.setSamplesToAverage(10);
        rightSpinRateCounter.setUpDownCounterMode();
        
        builtInAcc = new BuiltInAccelerometer(Accelerometer.Range.k8G);
    }
}
