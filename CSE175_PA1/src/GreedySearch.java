import java.util.HashSet;

public class GreedySearch 
{
	public int expansionCount = 0;
	Map map;
	Location start;
	Location end;
	int limit;
	
	public GreedySearch(Map map, String initial, String dest, int limit)
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

		SortedFrontier front = new SortedFrontier(SortBy.h);
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
				GoodHeuristic h = new GoodHeuristic(end);
				temp.expand(h);
				expansionCount++;
				if (stateChecking == true)
				{
					explored.add(temp.loc);
					for (Waypoint i : temp.options)
					{
						if(explored.contains(i.loc) || front.contains(i))
						{
							trash.add(i);
						}
					}
					temp.options.remove(trash);
					front.addSorted(temp.options);
				}
				else
				{
					front.addSorted(temp.options);
				}
			}
			node = temp;
		}
		return null;
	}
}
