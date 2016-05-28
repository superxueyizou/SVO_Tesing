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

/**
 *
 * @author rl576
 */
public class Waypoint extends Entity
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Waypoint nextPoint; //the id of the point to go to after this waypoint
	private int action=-1;
	
	/** Constructor for Waypoint
	 * 
	 * @param ID the id of the waypoint
	 * @param next the id of the point that should be travelled to after this waypoint is reached
	 */
	public Waypoint(int ID, Waypoint next)
	{
		super(ID, Constants.EntityType.TWAYPOINT);
		nextPoint = next;
	}
	
	/**
	 * A method which returns the id of the point to go to after this waypoint has
	 * been reached
	 * 
	 * @return the id of the next point for the uas to travel to
	 */
	public Waypoint getNextWaypoint() {return nextPoint;}
	public void setNextWaypoint(Waypoint next) {nextPoint = next;}

	public int getAction() {
		return action;
	}

	public void setAction(int action) {
		this.action = action;
	}
}
