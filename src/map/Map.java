package src.map;
import java.util.ArrayList;
import src.grid.*;
import src.actor.*;
import src.actor.creature.*;
import src.actor.projectile.*;
import src.actor.tower.*;
public class Map{
	Grid[][] grid_Map;
	int grid_Size;
	int xMax_val;
	int yMax_val;
	double[][] path;
	DirectionGrid start; // where creatures enter
	EndGrid end; // where creature leave the map
	ArrayList<DirectionGrid> dGrid = new ArrayList<DirectionGrid>();
	ArrayList<Creature> creatures = new ArrayList<Creature>(); // what happens when it gets huge tho???
	ArrayList<Projectile> projectiles = new ArrayList<Projectile>(); // can be deleted
	ArrayList<Tower> towers = new ArrayList<Tower>(); // these can be deleted
	ArrayList<Actor> actors = new ArrayList<Actor>(); // active actors
	ArrayList<Actor> queue = new ArrayList<Actor>(); // actors to be added next dt
	int path_Width;

	public Map(double[][] p, int n, int xMax, int yMax, int pWidth){
		this.grid_Size = n;
		this.path = p;
		this.xMax_val = xMax;
		this.yMax_val = yMax;
		this.grid_Map = new Grid[this.grid_Size][this.grid_Size];
		this.path_Width = pWidth;
	}

	public void initialize(){
		for (int i = 0; i < grid_Size; i++){
			for (int j = 0; j< grid_Size; j++){ 
				this.grid_Map[i][j] = new TowerGrid(xMax_val*(i+0.5)/grid_Size, yMax_val*(0.5 + j)/grid_Size, String.valueOf(i) + " x " + String.valueOf(j), this);
			}
		}
		initDirection();
	}

	public Grid closestGrid(double[] point){
		int x = (int) (point[0] * grid_Size / xMax_val); 
		int y = (int) (point[1] * grid_Size / yMax_val);
		return grid_Map[x][y]; 
	}

	public Grid closestGrid(double x_pos, double y_pos){
		int x = (int) (x_pos * grid_Size / xMax_val); 
		int y = (int) (y_pos * grid_Size / yMax_val);
		return grid_Map[x][y]; 		
	}

	public int[] closestGridCoordinate(double[] point){
		int[] coordinate = new int[2];
		coordinate[0] = (int) (point[0] * grid_Size / xMax_val); 
		coordinate[1] = (int) (point[1] * grid_Size / yMax_val);
		return coordinate;
	} 
	
	public void initDirection(){
		int[] prev;
		int n = path.length;
		int[] next;
		for (int i = 0; i < path.length-1; i++){
			prev = closestGridCoordinate(path[i]);
			next = closestGridCoordinate(path[i+1]);
			if (next[0]>prev[0]){
				for (int k = prev[0]; k < next[0]; k++){
					grid_Map[k][prev[1]] = new DirectionGrid(grid_Map[k][prev[1]], grid_Map[k+1][prev[1]]);
					dGrid.add((DirectionGrid) grid_Map[k][prev[1]]);
					convertPath(k, prev[1], 0);
				}
			}
			else if(next[0] < prev[0]){
				for (int k = prev[0]; k > next[0]; k--){
					grid_Map[k][prev[1]] = new DirectionGrid(grid_Map[k][prev[1]], grid_Map[k-1][prev[1]]);
					dGrid.add((DirectionGrid) grid_Map[k][prev[1]]);					
					convertPath(k, prev[1], 0);
				}
			}
			if (next[1]>prev[1]){
				for (int k = prev[1]; k < next[1]; k++){
					grid_Map[prev[0]][k] = new DirectionGrid(grid_Map[prev[0]][k], grid_Map[prev[0]][k+1]);
					dGrid.add((DirectionGrid) grid_Map[prev[0]][k]);					
					convertPath(prev[0], k, 1);
				}
			}
			else if(next[1]<prev[1]){
				for (int k = prev[1]; k > next[1]; k--){
					grid_Map[prev[0]][k] = new DirectionGrid(grid_Map[prev[0]][k], grid_Map[prev[0]][k-1]);
					dGrid.add((DirectionGrid) grid_Map[prev[0]][k]);
					convertPath(prev[0], k, 1);
				}
			}
		}
		this.start = (DirectionGrid) closestGrid(path[0]);
		int[] ending = closestGridCoordinate(path[n-1]);
		grid_Map[ending[0]][ending[1]] =  
		end = new EndGrid(closestGrid(path[n-1]));
		grid_Map[ending[0]][ending[1]] = end;
		dGrid.add(end);
		this.start.setStart();
		// set_dGrid();
	}
	private void convertPath(int a, int b, int direction){ // direction = 1 is vertical direction = 0 is horizontal
		if (direction == 1){ // vertical
			for (int j = 1; j < path_Width+1; j++){
				if (a + j >= 0 && a + j < grid_Size){
					if (grid_Map[a+j][b].type() != "Direction"){
						grid_Map[a+j][b] = new PathGrid(grid_Map[a+j][b]);
					}
				}
				if (a-j >= 0 && a-j < grid_Size){
					if (grid_Map[a-j][b].type() != "Direction"){
						grid_Map[a-j][b] = new PathGrid(grid_Map[a-j][b]);
					}
				}
			}
		}
		else{ // horizontal
			for (int j = 1; j < path_Width+1; j++){
				if (b+j >= 0 && b+j < grid_Size){
					if (grid_Map[a][b+j].type() != "Direction"){
						grid_Map[a][b+j] = new PathGrid(grid_Map[a][b+j]);
					}
				}
				if (b-j >= 0 && b-j < grid_Size){
					if (grid_Map[a][b-j].type() != "Direction"){
						grid_Map[a][b-j] = new PathGrid(grid_Map[a][b-j]);
					}
				}
			}
		}
	}
	// public Creature remove(Creature c){
	// 	creatures.remove(c);
	// 	return c;
	// } // NEEDS TO BE WRITTEN

	// public Projectile remove(Projectile p){
	// 	projectiles.remove(p);
	// 	return p;
	// } 

	// public Tower remove(Tower t){
	// 	towers.remove(t);
	// 	return t;
	// }

	public void initUI(){

	}
	public DirectionGrid get_start(){
		return start;
	}
	public Grid[][] get_Grid_map(){
		return grid_Map;
	}
	public void set_dGrid(){
		int i = 0;
		for (Grid[] garray: grid_Map){
			for (Grid g: garray){
				if(g.type() == "Direction"){
					dGrid.add((DirectionGrid) g);
					i++;
				}
			}
		}
	}
	public ArrayList<DirectionGrid> get_dGrid(){
		return dGrid;
	}
	// public void addCreature(Creature c){
	// 	creatures.add(c);
	// }

	// public void addTower(Tower t){
	// 	towers.add(t);
	// }

	// public void addProjectile(Projectile p){
	// 	projectiles.add(p);
	// }

	public void addActor(Actor a){
		queue.add(a); // cant be immediately added because then it causes problems when looping through the actors
	}

	public void unQueue(){
		for (Actor a : queue){
			actors.add(a);
		}
		queue = new ArrayList<Actor>();
	}

	public Actor remove(Actor a){
		actors.remove(a);
		return a;
	}

	public void action(double dt){
		unQueue(); // avoids concurent modification 
		// for (Creature c : creatures){
		// 	c.action(dt);
		// }
		// for (Projectile p : projectiles){
		// 	p.action(dt);
		// }
		// for (Tower t : towers){
		// 	t.action(dt);
		// }
		for (Actor a : actors){
			a.action(dt);
		}

	}
	public void draw(){
		// for (Creature c : creatures){
		// 	c.draw();
		// }
		// for (Projectile p : projectiles){
		// 	p.draw();
		// }
		// for (Tower t : towers){
		// 	t.draw();
		// }
		for (Actor a: actors){
			a.draw();
		}
	}
}