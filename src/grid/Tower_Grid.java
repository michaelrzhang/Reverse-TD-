package src.grid;
import src.tower.*;
import src.map.*;
public class Tower_Grid extends Grid{
	Tower tower; // what tower is on the grid *towers should not overlap 
	public Tower_Grid(double x, double y,String nm, Map m){
		super(x, y, nm, m);
		this.can_place = true;
	}
	public String type(){
		return "Tower";
	}
}