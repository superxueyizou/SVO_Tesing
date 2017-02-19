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

package dominant;

import ec.EvolutionState;
import ec.simple.SimpleShortStatistics;

public class MyStatistics extends SimpleShortStatistics 
{
	protected static int[] accidents= new int[500];

	public MyStatistics() 
	{
		super();
	}
	
	public void postEvaluationStatistics(final EvolutionState state)
	{
		super.postEvaluationStatistics(state);
		state.output.println(accidents[state.generation]+"", statisticslog);
	}

}
