package roadgraph;

import java.util.List;

import geography.GeographicPoint;

class RoadMapEdge {
	private GeographicPoint start;	//the start Node of the edge
	private GeographicPoint end;	// the end Node of the edge
	private String roadname;	//the name	
	private String roadtype;	// the type
	private Double distance;	//the distance
	
	public RoadMapEdge(GeographicPoint s, GeographicPoint e, String roadin, String roadty,
			Double distancein)	//the constructor
	{
		start = s;
		end = e;
		roadname = roadin;
		roadtype = roadty;
		distance = distancein;
	}
	
	public String getName() //The getter
	{
		return roadname;		
	}
	
	public Double getDistance() //The getter
	{
		return distance;		
	}
	
	public String getType() //The getter
	{
		return roadtype;		
	}
	
	public GeographicPoint getStart() //The getter
	{
		return start;		
	}
	
	public GeographicPoint getEnd() //The getter
	{
		return end;		
	}
	
	
}
