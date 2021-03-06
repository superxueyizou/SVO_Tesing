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
package modeling.env;
import sim.util.Double2D;

/**
 *
 * @author Robert Lee
 */
public class CircleObstacle extends Obstacle
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected double radius;

	public CircleObstacle(int idNo, double r)
	{
		super(idNo, Constants.EntityType.TCIROBSTACLE);
		radius = r;
	}
	
	public CircleObstacle(int idNo, double r, Constants.EntityType type)
	{
		super(idNo, type);
		radius = r;
	}
	
	/**
	 * Returns true or false if a provided coordinate is in the shape, used to
	 * detect collisions.
	 * 
	 * @param coord the coordinate to be tested if it's in the object
	 * @return a value based on if the coordinate is in the shape (true) or not (false)
	 */
	@Override
	public boolean pointInShape(Double2D coord)
	{
		if (location.distance(coord) <= radius)
		{
			return true;
		} 
		else 
		{
			return false;
		}
	}	
	
	@Override
	public boolean inCollisionWith(Double2D coord, double safeMargin)
	{
		return location.distance(coord) <= radius + safeMargin;
		
	}

	/**
	 * Returns the distance from the provided coordinate to the closest part of
	 * the obstacle to that point.
	 * 
	 * @param coord
	 * @return 
	 */
	@Override
	public double pointToObstacle(Double2D coord)
	{
		//as the shape of this obstacle is a circle the closest point is the edge
		//of the circle to the point.
		double val = (location.distance(coord) - radius);
		
		if (val < 0)
		{
			return 0;
		} 
		else 
		{
			return val;
		}
	}
	
	//accessor methods
	public double getRadius() {return radius;}
	public void setRadius(double radius)
	{
		this.radius = radius;
	}
}
