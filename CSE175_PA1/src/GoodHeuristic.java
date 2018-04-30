//
// GoodHeuristic
//
// This class extends the Heuristic class, providing a reasonable
// implementation of the heuristic function method.  The provided "good"
// heuristic function is admissible.
//
// Jeff Foreman -- 13 October 2016
//
import static java.lang.Math.sqrt;
public class GoodHeuristic extends Heuristic {

    // YOU CAN ADD ANYTHING YOU LIKE TO THIS CLASS ... WHATEVER WOULD
    // ASSIST IN THE CALCULATION OF YOUR GOOD HEURISTIC VALUE.

    // heuristicFunction -- Return the appropriate heuristic values for the
    // given search tree node.  Note that the given Waypoint should not be
    // modified within the body of this function.
	public GoodHeuristic(Location destination) 
	{
		this.destination = destination;
	}
	
    public double heuristicFunction(Waypoint wp) 
    {
		double hVal = 0.0;
		double lowestRCost = 3.1; //Fastest road on the map
		
		//Road r = wp.previous.loc.findRoad(wp.loc);
		double lat = destination.latitude - wp.loc.latitude;
		double lon = destination.longitude - wp.loc.longitude;
		hVal =  (sqrt((lat * lat) + (lon * lon))) / lowestRCost;
		
		return (hVal);
    }

}
