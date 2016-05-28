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
package modeling.saa.collsionavoidance;

import modeling.SAAModel;
import modeling.env.Destination;
import modeling.env.Waypoint;
import modeling.uas.UAS;
import sim.engine.SimState;
import sim.util.Double2D;
import sim.util.MutableDouble2D;
import tools.CONFIGURATION;

/**
 * @author Xueyi
 *
 */
public class CollisionAvoidanceAlgorithmAdapter extends CollisionAvoidanceAlgorithm
{

	/**
	 * 
	 */
	private SAAModel state; 
	private UAS hostUAS;

	private Destination destination;
	Double2D destinationCoor;
	
	public CollisionAvoidanceAlgorithmAdapter(SimState simstate, UAS uas) 
	{
		state = (SAAModel) simstate;
		hostUAS = uas;
		
		destination = hostUAS.getDestination();
		destinationCoor = destination.getLocation();
	}

	public void init()
	{
		
	}
	
	@Override
	public void step(SimState simState)
	{
		if(hostUAS.isActive == true)
		{			
			if (CONFIGURATION.collisionAvoidanceEnabler)
			{
				hostUAS.setCaaWp(execute());
				
			}
		}
	}
	
	public Waypoint execute()
	{
//		MutableDouble2D sumForces = new MutableDouble2D(); //used to record the changes to be made to the location of the uas
//		sumForces.addIn(hostUAS.getLocation());
//		double moveX = hostUAS.getVelocity().x; //CALCULATION.xMovement(bearing, speed);
//		double moveY = hostUAS.getVelocity().y; //CALCULATION.yMovement(bearing, speed);
//		sumForces.addIn(new Double2D(moveX, moveY));
//		Double2D newLocation= new Double2D(sumForces);
//		
//		Waypoint wp = new Waypoint(state.getNewID(), hostUAS.getDestination());
//		wp.setLocation(newLocation);
//		return wp;
		
		return null;
	}
	
	

}
