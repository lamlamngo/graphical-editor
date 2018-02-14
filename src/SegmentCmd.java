import java.awt.Point;

/**
 * SegmentCmd.java
 * Command class for users to drag out a Rectangle of any sizes.
 * 
 * @author Lam Ngo
 * @see Command
 * @version 01/20/2017
 */
public class SegmentCmd extends Command {
	 private Segment currentShape; // an instance variable that keeps track of the current segment.
	  
	 /**
	  * When a point is pressed, a segment is created at that point with start and endpoint at that same point.
	  * The segment object is added to the drawing object.
	  * 
	  * @param P: the point pressed.
	  * @param dwg: a drawing object.
	  */
	  public void executePress(Point p, Drawing dwg){
		  currentShape = new Segment(p.x,p.y,p.x,p.y,dwg.getColor());
		  dwg.addShape(currentShape);
	  }
	  
		/**
		 * Gets the current position of the mouse as it is dragged and sets the endpoint
		 * of the segment to that point.
		 * 
		 * @param p: current position of the mouse
		 * @param dwg: a drawing object
		 */
	  public void executeDrag(Point p, Drawing dwg){
		  if (currentShape != null){
			  currentShape.setEndPoint(p);
		  }
	  }
}
