import java.util.HashSet;

public class UniformCostSearch 
{
	public int expansionCount = 0;
	Map map;
	Location start;
	Location end;
	int limit;
	
	public UniformCostSearch(Map map, String initial, String dest, int limit)
	{
		this.map = map;
		start = map.findLocation(initial);
		end = map.findLocation(dest);
		this.limit = limit;
	}
	
	public Waypoint search(boolean stateChecking)
	{
		expansionCount = 0;
		
		
		HashSet<Location> explored = new HashSet<Location>();
		HashSet<Waypoint> trash = new HashSet<Waypoint>();
		
		SortedFrontier front = new SortedFrontier();
		Waypoint node = new Waypoint(start);
		front.addSorted(node);
		
		while(expansionCount < limit)
		{
			if (front.isEmpty())
			{
				return null;
			}
			Waypoint temp = front.removeTop();
			if (temp.isFinalDestination(end.name))
			{
				return temp;
			}
			else
			{
				temp.expand();
				expansionCount++;
				if (stateChecking == true)
				{
					explored.add(temp.loc);
					for (Waypoint i : temp.options)
					{
						if(explored.contains(temp.loc) || front.contains(temp.loc))
						{
							trash.add(i);
						}
					}
					temp.options.removeAll(trash);
					front.addSorted(temp.options);
				}
				else
				{
					front.addSorted(temp.options);
					/*for (Waypoint i : front.fringe)
					{
						System.out.println(i.partialPathCost);
					}*/
				}
			}
			node = temp;
		}
		return null;
	}
}
