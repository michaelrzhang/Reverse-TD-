package src.shape;
public class Circle{
	double x_position;
	double y_position;
	double apothem;
	String name = "Circle";

	public Circle(double x_pos, double y_pos, double radius){
		x_position = x_pos;
		y_position = y_pos;
		apothem = radius;


	public void draw(){
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(x_position, y_position, apothem);
	}
}