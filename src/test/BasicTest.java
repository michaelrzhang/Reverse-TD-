package src.test;
import src.actor.creature.*;
import src.map.*;
import src.actor.projectile.*;
import src.actor.tower.*;
import lib.*;
import src.grid.*;
import src.player.*;
import src.bank.*;
import src.UI.*;
import java.util.ArrayList;
public class BasicTest{
	public static void main(String[] args){
	// 	boolean[] key = {false, false, false, false};
	// 	StdDraw.setXscale(0,50);
	// 	StdDraw.setYscale(0,50);
	// 	double[][] path = {{1,0} ,{1,49}};
	// 	ArrayList<UI> ui = new ArrayList<UI>();
	// 	ui.add(new CreatureUI(25.0, 0.0, 10.0,10.0));
	// 	Map m = new Map(path, 50, 50, 50, 3, ui);
	// 	m.initialize();
	// 	ArrayList<DirectionGrid> dGrid = m.get_dGrid();
	// 	Player player1 = new Player(1, m, new Bank(100));
	// 	Player player2 = new Player(2, m, new Bank(100));
	// 	while(true){
	// 		// StdDraw.picture(25.0, 25.0, "images/background.jpg"); 
	// 		StdDraw.picture(25.0, 25.0, "images/black_background.jpg", 90, 100, 100);
	// 		// if you make the picture too small (try the thing below) some weird shit happens
	// 		// StdDraw.picture(25.0, 25.0, "images/black_background.jpg", 90);
	// 		for (DirectionGrid g: dGrid){
	// 			g.setCreaturesDirection();
	// 		}
	// 		isSpace(key, player1);
	// 		right(key, player1);
	// 		left(key, player1);
	// 		mouse(key, player2);
	// 		m.action(0.01);
	// 		m.draw();
	// 		StdDraw.show(1);
	// 	}
	// }

	// public static void isSpace(boolean[] key, Player p){
	// 	if (StdDraw.isKeyPressed(32)){
	// 		if (!key[0]){
	// 			((CreatureUI) p.map.ui.get(0)).get_Creature().copy(p);
	// 		}
	// 		key[0] = true;
	// 	}
	// 	else{
	// 		key[0] = false;
	// 	}
	// }

	// public static void mouse(boolean[] key, Player p){
	// 	if (StdDraw.mousePressed()){
	// 		double x = StdDraw.mouseX();
	// 		double y = StdDraw.mouseY();
	// 		Grid g = p.map.closestGrid(x,y);
	// 		System.out.println(g.type());
	// 		if (!key[1] && g.can_place()){
	// 			// p.buyActor(new BasicTower("testtower", p.map, x, y));
	// 			p.buyActor(new HoningTower("testtower", p.map, x, y));
	// 		}
	// 		key[1] = true;
	// 	}
	// 	else{
	// 		key[1] = false;
	// 	}
	// }
	// public static void right(boolean[] key, Player p){
	// 	if (StdDraw.isKeyPressed(39)){
	// 		if (!key[2]){
	// 			((CreatureUI) p.map.ui.get(0)).change(1);
	// 		}
	// 		key[2] = true;
	// 	}
	// 	else{
	// 		key[2] = false;
	// 	}
	// }
	// public static void left(boolean[] key, Player p){
	// 	if (StdDraw.isKeyPressed(37)){
	// 		if (!key[3]){
	// 			((CreatureUI) p.map.ui.get(0)).change(-1);
	// 		}
	// 		key[3] = true;
	// 	}
	// 	else{
	// 		key[3] = false;
	// 	}
	// }
		int[][] path = {{100,100} ,{1800,100}, {1800, 1079}};
		Map frame = new Map(path, 1920, 1080, 20, "images/black_background.jpg");
		int FPS = 20;
		while(true){
			frame.action(1.0/FPS);
			// frame.right();
			// frame.left();
			// frame.space();
			frame.mouseClick();
			try{
				Thread.sleep(1000/FPS);
			}
			catch(Exception e){}
		}
	}
}