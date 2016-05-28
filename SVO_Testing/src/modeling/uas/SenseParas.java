/* *************************************************************************************
 * Copyright (C) Xueyi Zou - All Rights Reserved
 * Written by Xueyi Zou <xz972@york.ac.uk>, 2015
 * You are free to use/modify/distribute this file for whatever purpose!
 -----------------------------------------------------------------------
 |THIS FILE IS DISTRIBUTED "AS IS", WITHOUT ANY EXPRESS OR IMPLIED
 |WARRANTY. THE USER WILL USE IT AT HIS/HER OWN RISK. THE ORIGINAL
 |AUTHORS AND COPPELIA ROBOTICS GMBH WILL NOT BE LIABLE FOR DATA LOSS,
 |DAMAGES, LOSS OF PROFITS OR ANY OTHER KIND OF LOSS WHILE USING OR
 |MISUSING THIS SOFTWARE.
 ------------------------------------------------------------------------
 **************************************************************************************/
package modeling.uas;

public class SenseParas 
{
	private double viewingRange=38.89; //how many units in front of the uas it can see obstacles 3889/100
	private double viewingAngle=60; //220 this is the angle for the viewing in front of the uas, viewingAngle / 2 in both directions from right in front of the uas
	private double sensitivityForCollisions=0.5; //this is used to see if the uas will collide with obstacles on it's current heading


	public SenseParas(double viewingRange, double viewingAngle, double sensitivityForCollisions) 
	{
		this.viewingRange = viewingRange;
		this.viewingAngle = viewingAngle;
		this.sensitivityForCollisions = sensitivityForCollisions;
	}

	public double getViewingRange() {
		return viewingRange;
	}

	public void setViewingRange(double viewingRange) {
		this.viewingRange = viewingRange;
	}

	public double getViewingAngle() {
		return viewingAngle;
	}

	public void setViewingAngle(double viewingAngle) {
		this.viewingAngle = viewingAngle;
	}

	public double getSensitivityForCollisions() {
		return sensitivityForCollisions;
	}

	public void setSensitivityForCollisions(double sensitivityForCollisions) {
		this.sensitivityForCollisions = sensitivityForCollisions;
	}
	

}
