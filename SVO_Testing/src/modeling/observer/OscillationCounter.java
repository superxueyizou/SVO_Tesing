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
import modeling.env.Constants;
import modeling.env.Obstacle;
import modeling.env.Waypoint;
import modeling.uas.UAS;
import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Bag;
import sim.util.Double2D;


public class OscillationCounter implements Constants,Steppable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SAAModel state;

	public OscillationCounter()
	{
		
	}
	
	@Override
	public void step(SimState simState) 
	{
		this.state = (SAAModel)simState;
		UAS uas1;

	    for(int i=0; i<state.uasBag.size(); i++)
		{	
	    	
			uas1= (UAS)state.uasBag.get(i);
			int oscillationNo=0;
			for(int j=0; j<uas1.getAchievedWaypoints().size()-1; j++)
			{
				int wp1Action = ((Waypoint)uas1.getAchievedWaypoints().get(j)).getAction();
				int wp2Action = ((Waypoint)uas1.getAchievedWaypoints().get(j+1)).getAction();
				if(wp1Action!=wp2Action)
				{
					oscillationNo++;
				}
			}
			uas1.setOscillationNo(oscillationNo);	
//			System.out.println(uas1+"  "+ oscillationNo);
		}
		
	}

}
