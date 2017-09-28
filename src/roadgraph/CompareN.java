package roadgraph;

import geography.GeographicPoint;

public class CompareN implements Comparable<CompareN> {
	private GeographicPoint location;
	public Double toStart, toGoal;
	private boolean estimateV,searched;
	
	public CompareN(GeographicPoint loc, Double start, Double end, boolean es)
	{
		location=loc;
		toStart = start;
		toGoal = end;
		estimateV = es;
		searched = false;
	}
	
	@Override
	public int compareTo (CompareN n) {
		if (estimateV) return ((Double)(toStart+toGoal)).compareTo((Double)(n.toStart+n.toGoal));
		else return ((Double)(toStart)).compareTo((Double)(n.toStart));
	}
	
	public GeographicPoint getLocation(){
		return location;
	}
	
	public boolean equals(CompareN o){
		return (location == o.location && toStart.equals(o.toStart) && toGoal.equals(o.toGoal));
	}
}
