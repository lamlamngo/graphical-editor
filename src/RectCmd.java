import java.awt.Point;

/**
 * RectCmd.java
 * Command class for users to drag out a Rectangle of any sizes.
 * 
 * Written by THC for CS 5 Lab Assignment 3.
 * @author Thomas H. Cormen and Lam Ngo
 * @see Command
 * @version 01/20/2017
 */
public class RectCmd extends Command {
	private Rect currentRect; // An instance variable to keep track of the rectangle that has just been created.
	private Point pressedPoint; // The starting point of the drag process.
  
	/**
	 * When mouse is pressed and not yet dragged, pressPoint is set to reference the point being pressed
	 * and a rectangle with top left of pressPoint's coordinates and width and height of 0 is created 
	 * and add to Drawing object's shape list.
	 * 
	 * @Param p: Point being pressed
	 * @Param dwg: a drawing object.
	 */
	public void executePress(Point p, Drawing dwg){
	  pressedPoint = p;
	  currentRect = new Rect(p.x,p.y,0,0,dwg.getColor());
	  dwg.addShape(currentRect);
	}
    
	/**
	 * Gets the current position of the mouse as it is dragged and draws a
	 * rectangle with this point and pressedPoint as corners. This creates a
	 * rubber-banding rectangle effect.
	 * 
	 * Adapted from DrawRects Class Applet example
	 * 
	 * @param p: current position of the mouse
	 * @param dwg: a drawing object
	 */
	public void executeDrag(Point p, Drawing dwg) {
		if (currentRect != null) {
			currentRect.setX(Math.min(p.x, pressedPoint.x));
			currentRect.setY(Math.min(p.y, pressedPoint.y));
			currentRect.setWidth(Math.abs(p.x - pressedPoint.x));
			currentRect.setHeight(Math.abs(p.y - pressedPoint.y));
		}
	}
}

