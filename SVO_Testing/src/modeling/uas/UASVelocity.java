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

import sim.util.Double2D;
import tools.CALCULATION;

public class UASVelocity 
{
	private double bearing = 0; //will be a value between -pi(inc) and pi(exc)
	private double speed = 0; //the speed the vehicle is travelling at
	private Double2D velocity = new Double2D(0,0);
	
	public UASVelocity(Double2D velocity) 
	{
		super();
		this.velocity = velocity;
		this.speed = velocity.length();
		this.bearing = velocity.masonAngle();
	}

	public double getSpeed() 
	{
		return speed;
	}

	public void setSpeed(double speed) 
	{
		this.speed = speed;
		this.velocity= this.velocity.normalize().multiply(speed);
	}

	public double getBearing() 
	{
		return bearing;
	}

	public void setBearing(double bearing) 
	{
		double oldBearing = this.bearing;
		this.bearing = bearing;
		this.velocity = this.velocity.masonRotate(this.bearing-oldBearing);
	}

	public Double2D getVelocity() 
	{
		return velocity;
	}

	public void setVelocity(Double2D velocity) 
	{
		this.velocity = velocity;
		this.speed = this.velocity.length();
		this.bearing = velocity.masonAngle();
	}
	
	

}
