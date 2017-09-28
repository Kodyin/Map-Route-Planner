/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which reprsents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
package roadgraph;


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;
import java.util.function.Consumer;

import geography.GeographicPoint;
import util.GraphLoader;

/**
 * @author UCSD MOOC development team and YOU
 * 
 * A class which represents a graph of geographic locations
 * Nodes in the graph are intersections between 
 *
 */
public class MapGraph {
	//TODO: Add your member variables here in WEEK 3
	private int numVertices,numEdges;	//numbers of vertices and edges
	private HashMap<GeographicPoint,RoadMapNode> vertices; 
	//A HashMap that relates the location to a node object
	
	
	
	/** 
	 * Create a new empty MapGraph 
	 */
	public MapGraph()
	{
		// TODO: Implement in this constructor in WEEK 3
		numVertices=0;
		numEdges=0;
		vertices = new HashMap<GeographicPoint,RoadMapNode> ();
		
	}
	
	/**
	 * Get the number of vertices (road intersections) in the graph
	 * @return The number of vertices in the graph.
	 */
	public int getNumVertices()
	{
		//TODO: Implement this method in WEEK 3
		return numVertices;
	}
	
	/**
	 * Return the intersections, which are the vertices in this graph.
	 * @return The vertices in this graph as GeographicPoints
	 */
	public Set<GeographicPoint> getVertices()
	{
		//TODO: Implement this method in WEEK 3
		return vertices.keySet();
	}
	
	/**
	 * Get the number of road segments in the graph
	 * @return The number of edges in the graph.
	 */
	public int getNumEdges()
	{
		//TODO: Implement this method in WEEK 3
		return numEdges;
	}

	
	
	/** Add a node corresponding to an intersection at a Geographic Point
	 * If the location is already in the graph or null, this method does 
	 * not change the graph.
	 * @param location  The location of the intersection
	 * @return true if a node was added, false if it was not (the node
	 * was already in the graph, or the parameter is null).
	 */
	public boolean addVertex(GeographicPoint location)
	{
		// TODO: Implement this method in WEEK 3
		if (location.equals(null)) return false;
		if (vertices.containsKey(location)) return false;
		vertices.put(location, new RoadMapNode(location));
		numVertices++;
		return true;
	}
	
	/**
	 * Adds a directed edge to the graph from pt1 to pt2.  
	 * Precondition: Both GeographicPoints have already been added to the graph
	 * @param from The starting point of the edge
	 * @param to The ending point of the edge
	 * @param roadName The name of the road
	 * @param roadType The type of the road
	 * @param length The length of the road, in km
	 * @throws IllegalArgumentException If the points have not already been
	 *   added as nodes to the graph, if any of the arguments is null,
	 *   or if the length is less than 0.
	 */
	public void addEdge(GeographicPoint from, GeographicPoint to, String roadName,
			String roadType, double length) throws IllegalArgumentException {

		//TODO: Implement this method in WEEK 3
		if (!vertices.containsKey(from) || !vertices.containsKey(to) || length<0 || from == null 
				|| to == null || roadName == null || roadType == null || new Double(length).equals(null))
			throw new IllegalArgumentException();
		vertices.get(from).getadj().add(new RoadMapEdge(from, to, roadName, roadType, length));
		numEdges++;
	}
	

	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return bfs(start, goal, temp);
	}
	
	/** Find the path from start to goal using breadth first search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest (unweighted)
	 *   path from start to goal (including both start and goal).
	 */
	public List<GeographicPoint> bfs(GeographicPoint start, 
			 					     GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 3
		ArrayList<GeographicPoint> queue = new ArrayList<GeographicPoint>();
		// the queue
		HashSet <GeographicPoint> visited = new HashSet <GeographicPoint>();
		// the visited node list
		HashMap <GeographicPoint,GeographicPoint> parent =  
				new HashMap <GeographicPoint,GeographicPoint>();
		// a HashMap relates a node to its parent
		queue.add(start);
		visited.add(start);
		
		//traverse the nodes
		while (!queue.isEmpty()){
			GeographicPoint curr = queue.remove(0);
			
			for (RoadMapEdge n : vertices.get(curr).getadj()){
				if (!visited.contains(n.getEnd())) {
					nodeSearched.accept(n.getEnd());	//Visualization
					visited.add(n.getEnd());
					parent.put(n.getEnd(),curr);
					queue.add(n.getEnd());
					if (n.getEnd().equals(goal)) return getPath(n.getEnd() , parent); 
					// if found then getPath() (getPath is a method I added
				}
			}
		}
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());

		return null;
	}
	
	private List<GeographicPoint> getPath(GeographicPoint goal, 
			HashMap <GeographicPoint,GeographicPoint> parent)// the method return the path list
	{
		 List<GeographicPoint> ans = new ArrayList<GeographicPoint>();
		 ans.add(goal);
		 GeographicPoint curr = goal;
		 while (parent.containsKey(curr)){
			 curr = parent.get(curr);
			 ans.add(curr);
		 }
		 Collections.reverse(ans); //reverse the list
		 return ans;
	}

	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
		// You do not need to change this method.
        Consumer<GeographicPoint> temp = (x) -> {};
        return dijkstra(start, goal, temp);
	}
	
	//class GeographicPoint implements Comparable<GeographicPoint>
	//{
		
	//}
	/** Find the path from start to goal using Dijkstra's algorithm
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> dijkstra(GeographicPoint start, 
										  GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		PriorityQueue<CompareN> pq = new PriorityQueue<CompareN>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<GeographicPoint, GeographicPoint>();
		HashMap<GeographicPoint, Double> toStart = new HashMap<GeographicPoint, Double>();
		int count=0;
		pq.add(new CompareN(start,0.0,0.0,false));
		visited.add(start);
		toStart.put(start, 0.0);
		//traverse the nodes
		while (!pq.isEmpty()){
			GeographicPoint curr = pq.peek().getLocation();
			pq.remove(pq.peek());
			count++;
			if(!visited.contains(curr)){
				visited.add(curr);
				nodeSearched.accept(curr);
				if (curr.equals(goal)) {System.out.println(count);return getPath(curr,parent);}
			}
			for (RoadMapEdge n : vertices.get(curr).getadj()){
				if (!visited.contains(n.getEnd())) {
					if (!toStart.containsKey(n.getEnd()) || 
					toStart.get(n.getEnd()).doubleValue() > toStart.get(curr).doubleValue()+ n.getDistance().doubleValue())
					{
						toStart.put(n.getEnd(),toStart.get(curr).doubleValue()+ n.getDistance().doubleValue());
						parent.put(n.getEnd(),curr);
						pq.add(new CompareN(n.getEnd(),toStart.get(curr).doubleValue()+ n.getDistance().doubleValue(), 0.0, false));
					}
				}
			}
		}
		
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, GeographicPoint goal) {
		// Dummy variable for calling the search algorithms
        Consumer<GeographicPoint> temp = (x) -> {};
        return aStarSearch(start, goal, temp);
	}
	
	/** Find the path from start to goal using A-Star search
	 * 
	 * @param start The starting location
	 * @param goal The goal location
	 * @param nodeSearched A hook for visualization.  See assignment instructions for how to use it.
	 * @return The list of intersections that form the shortest path from 
	 *   start to goal (including both start and goal).
	 */
	public List<GeographicPoint> aStarSearch(GeographicPoint start, 
											 GeographicPoint goal, Consumer<GeographicPoint> nodeSearched)
	{
		// TODO: Implement this method in WEEK 4
		PriorityQueue<CompareN> pq = new PriorityQueue<CompareN>();
		HashSet<GeographicPoint> visited = new HashSet<GeographicPoint>();
		HashMap<GeographicPoint, GeographicPoint> parent = new HashMap<GeographicPoint, GeographicPoint>();
		HashMap<GeographicPoint, Double> total = new HashMap<GeographicPoint, Double>();
		HashMap<GeographicPoint, Double> toStart = new HashMap<GeographicPoint, Double>();
		int count=0;
		
		pq.add(new CompareN(start,0.0,0.0,true));
		visited.add(start);
		toStart.put(start, 0.0);
		total.put(start, start.distance(goal));
		//traverse the nodes
		while (!pq.isEmpty())
		{
			GeographicPoint curr = pq.peek().getLocation();
			pq.remove(pq.peek());
			count++;
			if(!visited.contains(curr)){
				visited.add(curr);
				nodeSearched.accept(curr);
				if (curr.equals(goal)) {System.out.println(count);return getPath(curr,parent);}
			}
			for (RoadMapEdge n : vertices.get(curr).getadj()){
				if (!visited.contains(n.getEnd())) {
					if (!total.containsKey(n.getEnd()) || 
					toStart.get(n.getEnd()).doubleValue() > toStart.get(curr).doubleValue()+ n.getDistance().doubleValue())
					{
						toStart.put(n.getEnd(),toStart.get(curr).doubleValue()+ n.getDistance().doubleValue());
					}
					if (!total.containsKey(n.getEnd()) || 
							total.get(n.getEnd()).doubleValue() > 
							toStart.get(curr).doubleValue()+ n.getDistance().doubleValue()+n.getEnd().distance(goal))
					{
						parent.put(n.getEnd(),curr);
						total.put(n.getEnd(),toStart.get(curr).doubleValue()+ n.getDistance().doubleValue()+n.getEnd().distance(goal));
						pq.add(new CompareN(n.getEnd(),toStart.get(curr).doubleValue()+ n.getDistance().doubleValue(),
								n.getEnd().distance(goal), true));
					}
				}
			}
		}
		// Hook for visualization.  See writeup.
		//nodeSearched.accept(next.getLocation());
		
		return null;
	}

	
	
	public static void main(String[] args)
	{
		System.out.print("Making a new map...");
		MapGraph firstMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", firstMap);
		System.out.println("DONE.");
		
		// You can use this method for testing.  
		
		
		/* Here are some test cases you should try before you attempt 
		 * the Week 3 End of Week Quiz, EVEN IF you score 100% on the 
		 * programming assignment.
		 */
		///*
		MapGraph simpleTestMap = new MapGraph();
		GraphLoader.loadRoadMap("data/testdata/simpletest.map", simpleTestMap);
		
		GeographicPoint testStart = new GeographicPoint(1.0, 1.0);
		GeographicPoint testEnd = new GeographicPoint(8.0, -1.0);
		//System.out.println(simpleTestMap.bfs(testStart, testEnd));
		System.out.println("Test 1 using simpletest: Dijkstra should be 9 and AStar should be 5");
		List<GeographicPoint> testroute = simpleTestMap.dijkstra(testStart,testEnd);
		List<GeographicPoint> testroute2 = simpleTestMap.aStarSearch(testStart,testEnd);
		System.out.println(testroute);
		System.out.println(testroute2);
		MapGraph testMap = new MapGraph();
		GraphLoader.loadRoadMap("data/maps/utc.map", testMap);
		
		// A very simple test using real data
		testStart = new GeographicPoint(32.869423, -117.220917);
		testEnd = new GeographicPoint(32.869255, -117.216927);
		System.out.println("Test 2 using utc: Dijkstra should be 13 and AStar should be 5");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		
		
		// A slightly more complex test using real data
		testStart = new GeographicPoint(32.8674388, -117.2190213);
		testEnd = new GeographicPoint(32.8697828, -117.2244506);
		System.out.println("Test 3 using utc: Dijkstra should be 37 and AStar should be 10");
		testroute = testMap.dijkstra(testStart,testEnd);
		testroute2 = testMap.aStarSearch(testStart,testEnd);
		//*/
		
		
		/* Use this code in Week 3 End of Week Quiz */
		///*
		MapGraph theMap = new MapGraph();
		System.out.print("DONE. \nLoading the map...");
		GraphLoader.loadRoadMap("data/maps/utc.map", theMap);
		System.out.println("DONE.");

		GeographicPoint start = new GeographicPoint(32.8648772, -117.2254046);
		GeographicPoint end = new GeographicPoint(32.8660691, -117.217393);
		
		
		List<GeographicPoint> route = theMap.dijkstra(start,end);
		List<GeographicPoint> route2 = theMap.aStarSearch(start,end);

		//*/
		
	}
	
}
