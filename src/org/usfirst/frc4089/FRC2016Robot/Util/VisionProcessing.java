package org.usfirst.frc4089.FRC2016Robot.Util;

import java.util.Vector;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.*;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessing {

	public static ScoreCollection scores;
	
	static int session;
	static Image frame;
	static Image masked;
	static Image binaryFrame;
	static int imaqError;
	
	static NIVision.Range HUE_RANGE = new NIVision.Range(8, 168);	//Default hue range for tape
	static NIVision.Range SAT_RANGE = new NIVision.Range(21, 255);	//Default saturation range for tape
	static NIVision.Range VAL_RANGE = new NIVision.Range(229, 255);	//Default value range for tape
	static float MIN_PARTICLE_AREA = 0.2f;
	public static float LENS_ANGLE = 60;
	
	static NIVision.ParticleFilterCriteria2 criteria[] = new NIVision.ParticleFilterCriteria2[1];
	static NIVision.ParticleFilterOptions2 filterOptions = new NIVision.ParticleFilterOptions2(0,0,1,1);
	
	static boolean exists = false;
	
	public static void VPInit()
	{
		frame = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		masked = NIVision.imaqCreateImage(ImageType.IMAGE_RGB, 0);
		binaryFrame = NIVision.imaqCreateImage(ImageType.IMAGE_U8, 0);
		criteria[0] = new NIVision.ParticleFilterCriteria2(NIVision.MeasurementType.MT_AREA_BY_IMAGE_AREA, MIN_PARTICLE_AREA, 100.0, 0, 0);
		
		session = NIVision.IMAQdxOpenCamera("cam0",
                NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        NIVision.IMAQdxConfigureGrab(session);

		NIVision.IMAQdxStartAcquisition(session);
		
		exists = true;
	}
	
	public static void Process()
	{
		if(!exists) return;
		
		//read file in from disk. For this example to run you need to copy image20.jpg from the SampleImages folder to the
		//directory shown below using FTP or SFTP: http://wpilib.screenstepslive.com/s/4485/m/24166/l/282299-roborio-ftp
		NIVision.IMAQdxGrab(session, frame, 1);

		//Update threshold values from SmartDashboard. For performance reasons it is recommended to remove this after calibration is finished.
		HUE_RANGE.minValue = (int)SmartDashboard.getNumber("Hue min", HUE_RANGE.minValue);
		HUE_RANGE.maxValue = (int)SmartDashboard.getNumber("Hue max", HUE_RANGE.maxValue);
		SAT_RANGE.minValue = (int)SmartDashboard.getNumber("Sat min", SAT_RANGE.minValue);
		SAT_RANGE.maxValue = (int)SmartDashboard.getNumber("Sat max", SAT_RANGE.maxValue);
		VAL_RANGE.minValue = (int)SmartDashboard.getNumber("Val min", VAL_RANGE.minValue);
		VAL_RANGE.maxValue = (int)SmartDashboard.getNumber("Val max", VAL_RANGE.maxValue);

		//Threshold the image looking for yellow (tote color)
		NIVision.imaqColorThreshold(binaryFrame, frame, 255, NIVision.ColorMode.HSV, HUE_RANGE, SAT_RANGE, VAL_RANGE);

		//Send particle count to dashboard
		int numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Masked Particles", numParticles);

		//Send masked image to dashboard to assist in tweaking mask.
		//Mask the image - keep the color of the particles and nothing else
		NIVision.imaqMask(masked, frame, binaryFrame);
		//take measurements
		GetImageSizeResult maskSize = NIVision.imaqGetImageSize(masked);
		GetImageSizeResult frameSize = NIVision.imaqGetImageSize(frame);
		SmartDashboard.putNumber("TotalRes", frameSize.height + frameSize.width);
		//scale masked image down to 1/5
		NIVision.imaqScale(masked, masked, 5, 5, ScalingMode.SCALE_SMALLER,
				new NIVision.Rect(0, 0,
						maskSize.height, maskSize.width));
		//remeasure; size has changed
		maskSize = NIVision.imaqGetImageSize(masked);
		//Copy the masked image into the bottom-right corner of the image
		NIVision.imaqCopyRect(frame, masked, 
				new NIVision.Rect(0, 0, maskSize.height, maskSize.width),
				new NIVision.Point(frameSize.width - maskSize.width,
						frameSize.height - maskSize.height));
		//Post to dashboard
		CameraServer.getInstance().setImage(frame);
		
		//Now we can process for real.
		criteria[0].lower = MIN_PARTICLE_AREA;
		NIVision.imaqParticleFilter4(binaryFrame, binaryFrame, criteria, filterOptions, null);
		numParticles = NIVision.imaqCountParticles(binaryFrame, 1);
		SmartDashboard.putNumber("Filtered Particles", numParticles);
		
		if(numParticles > 0)
		{
			Vector<VisionParticleMeasurements> visionMeas = new Vector<VisionParticleMeasurements>();
			for(int i = 0; i < numParticles; i++)
			{
				VisionParticleMeasurements m = new VisionParticleMeasurements();
				m.TotalParticleArea = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_AREA);
				m.ImageCoverageArea = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_AREA_BY_IMAGE_AREA);
				m.BoundingRectLeft = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_BOUNDING_RECT_LEFT);
				m.BoundingRectWidth = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_BOUNDING_RECT_WIDTH);
				m.BoundingRectTop = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_BOUNDING_RECT_TOP);
				m.BoundingRectHeight = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_BOUNDING_RECT_HEIGHT);
				m.RelativeSkewness = 1 - (NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_PARTICLE_AND_HOLES_AREA) /
						(m.BoundingRectWidth * m.BoundingRectHeight));
				m.NetPerimeterDistance = NIVision.imaqMeasureParticle(binaryFrame, i, 0, MeasurementType.MT_PERIMETER)
						- 2 * (m.BoundingRectWidth + m.BoundingRectHeight);
				m.AspectRatio = m.BoundingRectWidth / m.BoundingRectHeight;				
				m.BoundingRectCoverageArea = m.TotalParticleArea / 
						(m.BoundingRectWidth * m.BoundingRectHeight);
				visionMeas.addElement(m);
			}
			visionMeas.sort(null);
			
			scores = new ScoreCollection();
			for(int j = 0; j < numParticles; j++)
			{
				VisionParticleMeasurements vm = visionMeas.elementAt(j);
				ClassifiedScore sc = new ClassifiedScore(vm);
				scores.add(sc);
			}
			scores.Send();
		}
	}
	
	public static void VPDispose()
	{
		if(exists)
		{
			NIVision.IMAQdxStopAcquisition(session);
			NIVision.IMAQdxCloseCamera(session);
			exists = false;
		}
	}
}
