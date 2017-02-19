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
package modeling.observer;

import modeling.SAAModel;
import modeling.env.CircleObstacle;
import modeling.env.Constants;
import modeling.env.Obstacle;
import modeling.uas.UAS;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;


public class ProximityMeasurer implements Constants,Steppable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SAAModel state;

	public ProximityMeasurer()
	{
		
	}
	
	@Override
	public void step(SimState simState) 
	{
		this.state = (SAAModel)simState;
		
		Obstacle obstacle;
		UAS uas1;		

	    for(int i=0; i<state.uasBag.size(); i++)
		{	
	    	
			uas1= (UAS)state.uasBag.get(i);
			if(!uas1.isActive)
			{
				continue;
			}

			for(int j=0; j<state.obstacles.size(); j++)
			{
				obstacle=(Obstacle)state.obstacles.get(j);
								
				if(obstacle.type == Constants.EntityType.TUAS && !((UAS)obstacle).isActive)
				{
						//System.out.println(state.obstacles.get(j)+": this obstacle is UAS, don't mind!");
						continue;
				}						
			
			    if(obstacle.equals(uas1))
				{
			    	continue;
				}
			    
			    double tempProximityToDanger=Double.MAX_VALUE;
			    
			    if(obstacle.type == Constants.EntityType.TUAS)
				{
					UAS uas2=(UAS)obstacle;
					tempProximityToDanger= uas1.getLocation().distance(uas2.getLocation())-uas1.getSafetyRadius()-uas2.getSafetyRadius();
					if(tempProximityToDanger<0)
					{
						tempProximityToDanger=0;
					}
						
				}
				else if (obstacle.type == Constants.EntityType.TCIROBSTACLE)
				{
					CircleObstacle o=(CircleObstacle)obstacle;
					tempProximityToDanger= uas1.getLocation().distance(o.getLocation())-uas1.getSafetyRadius()-o.getRadius();
					if(tempProximityToDanger<0)
					{
						tempProximityToDanger=0;
					}
				}
				else
				{
					System.err.println("please decide how to judge collision with non-circular obstacle!");
					
				}
//			    System.out.println(uas1+"--"+obstacle+"-------*"+tempProximityToDanger);
			    
				uas1.setTempDistanceToDanger(tempProximityToDanger);
				if (tempProximityToDanger < uas1.getDistanceToDanger())
				{
					uas1.setDistanceToDanger(tempProximityToDanger);
					
				}					
				
			}
							
		}
		
	}



}
