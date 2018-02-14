import java.awt.Point;

/**
 * ColorCmd.java
 * Command class for when users want to change the default color and shapes' color.
 * 
 * @author Lam Ngo
 * @see Command
 * @version 01/20/2017
 */
public class ColorCmd extends Command{
	
	/**
	 * If a shape is clicked on, the drawing object will change the shape color to its default color.
	 * The default color is changed in Editor, whenever users press on the 3 colors button.
	 * 
	 * @param p: point clicked
	 * @param dwg: a drawing object
	 */
	public void executeClick(Point p ,Drawing dwg){
		dwg.changeShapeColor(p);
	}
}