import java.awt.*;

/**
 * Segment.java
 * Class for a line segment.
 *
 * @author Lam Ngo
 * @see Shape
 */
public class Segment extends Shape {
	private int x1, y1;
	private int x2, y2;
	private static final double tolerance = 3.0;
	  /**
	   * Constructor just saves the parameters in the instance variables.
	   * 
	   * @param left x coordinate of the top-left corner
	   * @param top y coordinate of the top-left corner
	   * @param width the width
	   * @param height the height
	   * @param color the color
	   */
	  public Segment(int x1, int y1, int x2, int y2 , Color color) {
	    super(color);
	    this.x1 = x1;
	    this.y2 = y2;
	    this.y1 = y1;
	    this.x2 = x2;
	  }
 
	  /**
	   * Draw the Shape.
	   * 
	   * @param page the page you wish to draw the shape on
	   */
	  public void drawShape(Graphics page) {
		  page.drawLine(x1, y1, x2, y2);
	  }
	  
	  public void setEndPoint(Point p){
		  this.x2 = p.x;
		  this.y2 = p.y;
	  }

	  
	  public boolean containsPoint(Point p){
		  if (x2 - x1 < 0 && y2 -y1 < 0){
			  return containsPoint(p,x2,y2,x1,y1);
		  }else if (y2 - y1 < 0 && !(x2 - x1 < 0)){
			  return containsPoint(p,x1,y2,x2,y1);
		  }else if (x2 - x1 < 0){
			  return containsPoint(p,x2,y1,x1,y2);
		  }else{
			  return containsPoint(p,x1,y1,x2,y2);
		  }
	  }
	  
	  private boolean containsPoint(Point p, int left, int top, int right, int bottom){
		  if (almostContainsPoint(p,left,top,right,bottom,tolerance) 
				  && (distanceToPoint(p,this.x1,this.y1,this.x2,this.y2) <= tolerance)){
			  return true;
		  }else{
			  return false;
		  }
	  }
	  public void move(int deltaX, int deltaY){
		  x1 += deltaX;
		  y1 += deltaY;
		  x2 += deltaX;
		  y2 += deltaY;
	  }
	  
	  public Point getCenter(){
		  return new Point ((x1 + x2)/2,(y1+y2)/2);
	  }


  // Helper method that returns true if Point p is within a tolerance of a
  // given bounding box. Here, the bounding box is given by the coordinates of
  // its left, top, right, and bottom.
  private static boolean almostContainsPoint(Point p, int left, int top,
      int right, int bottom, double tolerance) {
    return p.x >= left - tolerance && p.y >= top - tolerance
        && p.x <= right + tolerance && p.y <= bottom + tolerance;
  }

  // Helper method that returns the distance from Point p to the line
  // containing a line segment whose endpoints are given.
  private static double distanceToPoint(Point p, int x1, int y1, int x2,
      int y2) {
    if (x1 == x2) // vertical segment?
      return (double) (Math.abs(p.x - x1)); // yes, use horizontal distance
    else if (y1 == y2) // horizontal segment?
      return (double) (Math.abs(p.y - y1)); // yes, use vertical distance
    else {
      // Here, we know that the segment is neither vertical nor
      // horizontal.
      // Compute m, the slope of the line containing the segment.
      double m = ((double) (y1 - y2)) / ((double) (x1 - x2));

      // Compute mperp, the slope of the line perpendicular to the
      // segment.
      double mperp = -1.0 / m;

      // Compute the (x, y) intersection of the line containing the
      // segment and the line that is perpendicular to the segment and that
      // contains Point p.
      double x = (((double) y1) - ((double) p.y) - (m * x1) + (mperp * p.x))
          / (mperp - m);
      double y = m * (x - x1) + y1;

      // Return the distance between Point p and (x, y).
      return Math.sqrt(Math.pow(p.x - x, 2) + Math.pow(p.y - y, 2));
    }
  }
}
