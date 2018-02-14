import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Ellipse.java
 * Class for an ellipse, subclass of Shape
 * 
 * Written by THC for CS 5 Lab Assignment 3.
 *
 * @author Thomas H. Cormen and Lam Ngo
 * @see Shape
 * @version 01/20/2016
 */
public class Ellipse extends Shape {
	private int left, top; // left and top of Ellipse
	private int width, height; // Ellipse's height and width

  /**
   * Constructor just saves the parameters in the instance variables.
   * 
   * @param left x coordinate of the top-left corner
   * @param top y coordinate of the top-left corner
   * @param width the width
   * @param height the height
   * @param color the color
   */
  public Ellipse(int left, int top, int width, int height, Color color) {
    super(color);
    this.left = left;
    this.top = top;
    this.width = width;
    this.height = height;
  }

  /**
   * Have the Ellipse draw itself.
   *
   * @param page the page you wish to draw on
   */
  public void drawShape(Graphics page) {
    page.fillOval(left, top, width, height);
  }

  /**
   * Return true if the Ellipse contains Point p, false otherwise.
   * 
   * @param p point tested for containment
   */
  public boolean containsPoint(Point p) {
	  return pointInEllipse(p, left, top, width, height);
  }


  /**
   * Heper method that returns whether point p is in an Ellipse with the given
   * top left corner and size 
   * @param p: Point tested for containment
   * @param left: x coordinate of the top-left corner of an Ellipse
   * @param top: y coordinate of the top-left corner of an Ellipse
   * @param width: width of an Ellipse
   * @param height: height of an Ellipse
   * @return True if point is in the Ellipse, and false otherwise.
   */
  private static boolean pointInEllipse(Point p, int left, int top, int width, int height) {
	  double a = width / 2.0; // half of the width
	  double b = height / 2.0; // half of the height
	  double centerx = left + a; // x-coord of the center
	  double centery = top + b; // y-coord of the center
	  double x = p.x - centerx; // horizontal distance between p and center
	  double y = p.y - centery; // vertical distance between p and center
    // Now we just apply the standard geometry formula.
    // (See CRC, 29th edition, p. 178.)
	  return Math.pow(x / a, 2) + Math.pow(y / b, 2) <= 1;
  }

  /**
   * Have the Ellipse move itself.
   * 
   * @param deltaX new x coordinate
   * @param deltaY new y coordinate
   */
  public void move(int deltaX, int deltaY) {
	  left += deltaX;
	  top += deltaY;
  }
  
  /**
   * Setter method, set the x coordinate of top left corner of an Ellipse to a certain value.
   * @param newX: new x coordinate of top left corner of the Ellipse.
   */
  public void setX(int newX){
	  this.left = newX;
  }
  
  /**
   * Setter method, set the y coordinate of top left corner of an Ellipse to a certain value.
   * @param newY: new y coordinate of top left corner of the Ellipse
   */
  public void setY(int newY){
	  this.top = newY;
  }
  
  /**
   * Setter method, set the width of an Ellipse to a certain value.
   * @param newWidth: new width of an Ellipse.
   */
  public void setWidth(int newWidth){
	  this.width = newWidth;
  }
  
  /**
   * Setter method, set the height of an Ellipse to a certain value.
   * @param newHeight: new height of an Ellipse.
   */
  public void setHeight(int newHeight){
	  this.height = newHeight;
  }
  
  /**
   * Return the Ellipse's center.
   * 
   * @return the center of the Ellipse
   */
  public Point getCenter() {
    return new Point(left + (width / 2), top + (height / 2));
  }
}
