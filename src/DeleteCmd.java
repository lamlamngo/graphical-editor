import java.awt.Point;

/**
 * DeleteCmd.java
 * Command class for when users want to delete a shape.
 * 
 * @author Lam Ngo
 * @see Command
 * @version 01/20/2017
 */
public class DeleteCmd extends Command{
	Shape shapeToBeDeleted; // An instance variable to record what shape has just been clicked on.
	
	/**
	 * When an object is clicked, the drawing object will first find the front most container of the pressed point, 
	 * and then delete it from the shape list.
	 * 
	 * @Param p: Point being clicked
	 * @Param dwg: a drawing object.
	 */
	public void executeClick(Point p ,Drawing dwg){
		dwg.removeFrontmostContainer(p);
	}
}
