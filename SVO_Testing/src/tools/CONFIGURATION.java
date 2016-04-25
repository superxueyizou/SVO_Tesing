/**
 * 
 */
package tools;

/**
 * @author Xueyi
 *
 */
public class CONFIGURATION {

	/**
	 * 
	 */
	public CONFIGURATION() 
	{
		// TODO Auto-generated constructor stub
	}
	
	public static double worldX = 5000; // unit m
	public static double worldY = 3000; // unit m
	
	public static double lengthScale = 0.29; // 1m for ? grid
	//public static double timeScale = 1; // 1s for ? step
	
	public static double fieldXVal = worldX*lengthScale; //do not change
	public static double fieldYVal = worldY*lengthScale; //do not change	
	
	public static boolean collisionAvoidanceEnabler=true;
	public static boolean selfSeparationEnabler=false;
	public static boolean accidentDetectorEnabler=true;
	public static boolean sensorValueUncertainty=true;
	
	
	public static double selfDestDist = 4000*lengthScale;
	public static double selfDestAngle = Math.toRadians(0);	
	
	public static double selfMaxSpeed =92.6*lengthScale;
	public static double selfMinSpeed =51.4*lengthScale;
	public static double selfMaxAcceleration = 0*lengthScale;
	public static double selfMaxDeceleration = 10*lengthScale;
	public static double selfMaxClimb = 17.78*lengthScale;
	public static double selfMaxDescent = 20.23*lengthScale;
	public static double selfMaxTurning = Math.toRadians(2.5);
	public static double selfPrefSpeed = 77.2*lengthScale;
	public static double selfViewingRange =7000*lengthScale;
	public static double selfViewingAngle = Math.toRadians(60);
	public static double selfSensitivityForCollisions = 5*lengthScale;
	public static double selfSafetyRadius=20*lengthScale;
	public static double selfAlpha=1.0; //RVO[0,1.0], HRVO(1.0,2.0], ORCA(2.0,3.0]
	public static String selfCollisionAvoidanceAlgorithmSelection = "SVOAvoidanceAlgorithm"; //"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 
	public static String selfSelfSeparationAlgorithmSelection = "SVOAvoidanceAlgorithm"; //"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 

	
	
	public static double headOnSelected = 0;
	public static double headOnIsRightSide = 0;
	public static double headOnOffset= 0*lengthScale;
	public static int headOnTimes=1;
	public static double headOnMaxSpeed =92.6*lengthScale;
	public static double headOnMinSpeed =51.4*lengthScale;
	public static double headOnMaxAcceleration = 0*lengthScale;
	public static double headOnMaxDeceleration = 10*lengthScale;
	public static double headOnMaxClimb = 17.78*lengthScale;
	public static double headOnMaxDescent = 20.23*lengthScale;
	public static double headOnMaxTurning = Math.toRadians(2.5);
	public static double headOnPrefSpeed = 77.2*lengthScale;
	public static double headOnViewingRange =7000*lengthScale;
	public static double headOnViewingAngle =  Math.toRadians(60);
	public static double headOnSensitivityForCollisions = 5*lengthScale;
	public static double headOnSafetyRadius=20*lengthScale;
	public static double headOnAlpha=1.0;
	public static String headOnCollisionAvoidanceAlgorithmSelection = "SVOAvoidanceAlgorithm";//"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 
	public static String headOnSelfSeparationAlgorithmSelection = "SVOAvoidanceAlgorithm";//"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 

	
	public static double crossingSelected = 1.0;
	public static double crossingEncounterAngle= Math.toRadians(30);
	public static double crossingIsRightSide=1;
	public static int crossingTimes=1;
	public static double crossingMaxSpeed =92.6*lengthScale;
	public static double crossingMinSpeed =51.4*lengthScale;
	public static double crossingMaxAcceleration = 0*lengthScale;
	public static double crossingMaxDeceleration = 10*lengthScale;
	public static double crossingMaxClimb = 17.78*lengthScale;
	public static double crossingMaxDescent = 20.23*lengthScale;
	public static double crossingMaxTurning = Math.toRadians(2.50);
	public static double crossingPrefSpeed = 77.2*lengthScale;
	public static double crossingViewingRange =7000*lengthScale;
	public static double crossingViewingAngle =  Math.toRadians(60);
	public static double crossingSensitivityForCollisions = 5*lengthScale;
	public static double crossingSafetyRadius=20*lengthScale;
	public static double crossingAlpha=1;
	public static String crossingCollisionAvoidanceAlgorithmSelection = "SVOAvoidanceAlgorithm";//"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 
	public static String crossingSelfSeparationAlgorithmSelection = "SVOAvoidanceAlgorithm";//"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 

	
	public static double tailApproachSelected = 0;
	public static double tailApproachOffset = 0*lengthScale;
    public static double tailApproachIsRightSide =1;
	public static int tailApproachTimes=1;
	public static double tailApproachMaxSpeed =92.6*lengthScale;
	public static double tailApproachMinSpeed =51.4*lengthScale;
	public static double tailApproachMaxAcceleration = 0*lengthScale;
	public static double tailApproachMaxDeceleration = 10*lengthScale;
	public static double tailApproachMaxClimb = 17.78*lengthScale;
	public static double tailApproachMaxDescent = 20.23*lengthScale;
	public static double tailApproachMaxTurning = Math.toRadians(2.50);	
	public static double tailApproachPrefSpeed = 92.6*lengthScale;
	public static double tailApproachViewingRange =7000*lengthScale;
	public static double tailApproachViewingAngle =  Math.toRadians(60);
	public static double tailApproachSensitivityForCollisions = 5*lengthScale;
	public static double tailApproachSafetyRadius=20*lengthScale;
	public static double tailApproachAlpha=1;
	public static String tailApproachCollisionAvoidanceAlgorithmSelection = "SVOAvoidanceAlgorithm";//"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 
	public static String tailApproachSelfSeparationAlgorithmSelection = "SVOAvoidanceAlgorithm";//"AVOAvoidanceAlgorithm","HRVOAvoidanceAlgorithm","RVOAvoidanceAlgorithm","ORCAAvoidanceAlgorithm","TurnRightAvoidanceAlgorithm", "SmartTurnAvoidanceAlgorithm", "None", "RIPNAvoidanceAlgorithm" 

	
	
}
