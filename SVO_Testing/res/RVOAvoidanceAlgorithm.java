/**
 * 
 */
package modeling.saa.collsionavoidance;

import edu.unc.cs.gamma.rvo.RVO;
import edu.unc.cs.gamma.rvo.RVOSimulator;
import edu.unc.cs.gamma.rvo.Vector2;
import modeling.SAAModel;
import modeling.env.Destination;
import modeling.env.Waypoint;
import modeling.uas.UAS;
import sim.engine.SimState;
import sim.util.Double2D;
import tools.CONFIGURATION;

/**
 * @author Xueyi
 *
 */
public class RVOAvoidanceAlgorithm extends AvoidanceAlgorithm
{

	/**
	 * 
	 */
	private SAAModel state; 
	private UAS hostUAS;

	private Destination destination;
	Double2D destinationCoor;

	public RVOSimulator rvoSimulator;
	private int hostUASIDInRVOSimulator =0;
	private Vector2 targetPosInRVOSimulator;

	private boolean isGoalReached;
	
		
	public RVOAvoidanceAlgorithm(SimState simstate, UAS uas) {
		// TODO Auto-generated constructor stub
		state = (SAAModel) simstate;
		hostUAS = uas;
		
		destination = hostUAS.getDestination();
		destinationCoor = destination.getLocation();
		
		targetPosInRVOSimulator = new Vector2(destinationCoor.x, destinationCoor.y);
		isGoalReached=false;		
				
	}
	
	
	/******************************************************************************************************************************************/
	
	public void init()
	{
		initRVO2Simulator();
	}
	
	
	public void initRVO2Simulator()
	{
		// Create a new simulator instance.
		rvoSimulator = new RVOSimulator();
		// Specify global time step of the simulation.
		rvoSimulator.setTimeStep(1);
		
		/* Specify default parameters for agents that are subsequently added.
		 * 
		 * void RVOSimulator.setAgentDefaults(
		 * int velSampleCountDefault, 
		 * double neighborDistDefault, 
		 * int maxNeighborsDefault, 
		 * double radiusDefault,
		 * double goalRadiusDefault, 
		 * double prefSpeedDefault,
		 * double maxSpeedDefault, 
		 * double safetyFactorDefault, 
		 * double alfaDefault,
		 * double maxAccelDefault)
		 * */
		rvoSimulator.setAgentDefaults( 250, hostUAS.getViewingRange(), 8, hostUAS.getRadius(), 1.0, hostUAS.getSpeed(), hostUAS.getUasPerformance().getCurrentMaxSpeed(), 7.5, hostUAS.getAlpha(), hostUAS.getUasPerformance().getCurrentMaxAccel());
		
		for(int i=0; i<state.uasBag.size(); i++)
		{
			UAS uas= (UAS)state.uasBag.get(i);
			if(uas == hostUAS)
			{
				hostUASIDInRVOSimulator=i;
			}
			Vector2 location= new Vector2(uas.getLocation().x,uas.getLocation().y);
			rvoSimulator.addAgent(location, rvoSimulator.addGoal(new Vector2(uas.getDestination().getLocation().x, uas.getDestination().getLocation().y ) ));
						
		}
		rvoSimulator.initSimulation();
		
	}
	
	
	public Waypoint execute()
	{
		
		updateRVOSimulator(state);
		rvoSimulator.doStep();
		setPreferredVelocity(state);
		
		Vector2 vel= rvoSimulator.getAgentVelocity(hostUASIDInRVOSimulator);
		Double2D velDouble2D = new Double2D(vel.x(), vel.y());
		hostUAS.setOldVelocity(hostUAS.getVelocity());
		hostUAS.setVelocity(velDouble2D);

		
		Vector2 loc = rvoSimulator.getAgentPosition(hostUASIDInRVOSimulator);
		Double2D newLocation = new Double2D(loc.x(), loc.y());
		
		
		Waypoint wp = new Waypoint(state.getNewID(), hostUAS.getDestination());
		wp.setLocation(newLocation);
		return wp;
	
	}

	
	public void updateRVOSimulator(SAAModel state)
	{
		for(int i=0; i<state.uasBag.size(); i++)
		{
			UAS agent= (UAS)state.uasBag.get(i);
			
			Vector2 location;
			Vector2 velocity;
			if(agent==hostUAS)
			{
				location = new Vector2(agent.getLocation().x,agent.getLocation().y);
				double velX = agent.getVelocity().x;
				double velY = agent.getVelocity().y;
				velocity= new Vector2(velX, velY);
				
			}
			else
			{
				if (!agent.isActive)
				{
					rvoSimulator.setAgentPosition(i, new Vector2(Double.MAX_VALUE, Double.MAX_VALUE));
					rvoSimulator.setAgentVelocity(i, new Vector2(0,0));
					continue;
				}
				
				location = new Vector2(agent.getLocation().x,agent.getLocation().y);
				double velX = agent.getVelocity().x;
				double velY = agent.getVelocity().y;
			
//				location = new Vector2(agent.getLocation().x+state.random.nextGaussian(),agent.getLocation().y+state.random.nextGaussian());
//				double velX = agent.getSpeed()*Math.cos(Math.toRadians(agent.getBearing()))+state.random.nextGaussian();
//				double velY = agent.getSpeed()*Math.sin(Math.toRadians(agent.getBearing()))+state.random.nextGaussian();
				
				velocity= new Vector2(velX, velY);
			}
			rvoSimulator.setAgentPosition(i, location);
			rvoSimulator.setAgentVelocity(i, velocity);
			
		}

	}
	
	public void setPreferredVelocity(SAAModel state) 
	{
		if (!isGoalReached) 
		{
			if (RVO.absSq(targetPosInRVOSimulator.sub(rvoSimulator.getAgentPosition(hostUASIDInRVOSimulator))) < 1) 
			{	
				isGoalReached = true;
			
			} 
			else 
			{
	
			}
		}
	}

}
