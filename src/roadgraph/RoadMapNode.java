package roadgraph;

import java.util.ArrayList;
import java.util.List;

import geography.GeographicPoint;

class RoadMapNode {
	private GeographicPoint location; //The Location this Node represent	
	private List<RoadMapEdge> adj; // A list of RoadMapEdge that includes all the out edge
	
	public RoadMapNode(GeographicPoint newL) //The constructor
	{
		location = newL;
		adj = new ArrayList<RoadMapEdge>();
		
	}
	
	public List<RoadMapEdge> getadj() //The getter
	{
		return adj;		
	}
	
	public GeographicPoint getLocation() //The getter
	{
		return location;		
	}
}