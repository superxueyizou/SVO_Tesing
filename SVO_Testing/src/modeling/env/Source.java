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
 * @author Robert Lee
 */
public class Source extends Waypoint
{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Waypoint nextWp;

	public Source(int idNo, Waypoint next)
	{
		super(idNo, next);
		nextWp=next;
	}
	
	public Waypoint getNextWaypoint() {return null;}
	
	public Waypoint getNextWp() {
		return nextWp;
	}

	public void setNextWp(Waypoint nextWp) {
		this.nextWp = nextWp;
	}
}
