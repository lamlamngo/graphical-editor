import java.awt.Point;

public class MoveCmd extends Command{
	Shape shapeMoved;
	Point previousPoint;
	
	public void executePress(Point p, Drawing dwg){
		shapeMoved = dwg.getFrontmostContainer(p);
		previousPoint = p;
	}
	
	public void executeDrag(Point p, Drawing dwg){
		if (shapeMoved != null){
			dwg.move(shapeMoved,previousPoint,p);
			previousPoint = p;
		}
	}
}
