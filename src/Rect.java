import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

/**
 * Project 1: A Graphical Editor
 * Rect.java
 * Class for a Rectangle, subclass of Shape.
 * 
 * Written by THC for CS 5 Lab Assignment 3.
 *
 * @author Thomas H. Cormen and Lam Ngo 
 * @see Shape
 * @version 01/20/2017
 */
public class Rect extends Shape {
  private int x, y; // leftmost and top of Rectangle
  private int width, height; // Rectangle's height and width
  
  /**
   * Constructor just saves the parameters in the instance variables.
   * 
   * @param x: x coordinate of the top-left corner
   * @param y: y coordinate of the top-left corner
   * @param width: the width
   * @param height: the height
   * @param color: the color
   */
  public Rect(int x, int y, int width, int height, Color color) {
    super(color);
    this.x = x;
    this.y = y;
    this.width = width;
    this.height = height;
  }

  /**
   * Have the Rectangle draw itself.
   *
   * @param page the page you wish to draw on
   */
  public void drawShape(Graphics page) {
    page.fillRect(x, y, width, height);
  }
 
  
  /**
   * Setter method, set the x coordinate of the top-left corner to a certain value.
   * @param newX: value for new x coordinate of the top-left corner
   */
  public void setX(int newX){
	  this.x = newX;
  }
  
  /**
   * Setter method, set the y coordinate of the top-left corner to a certain value.
   * @param newY: value for new y coordinate of the top-left corner
   */
  public void setY(int newY){
	  this.y = newY;
  }
  
  /**
   * Setter method, set the width of the rectangle to a certain value.
   * @param newWidth: new width of the rectangle
   */
  public void setWidth(int newWidth){
	  this.width = newWidth;
  }
  
  /**
   * Setter method, set the height of the rectangle to a certain value.
   * @param newHeight: new height of the rectangle
   */
  public void setHeight(int newHeight){
	  this.height = newHeight;
  }

  /**
   * Return true if the Rectangle contains Point p, false otherwise.
   * 
   * @param p: point tested for containment
   */
  public boolean containsPoint(Point p) {
    return pointInRectangle(p, x, y, width, height);
  }
  
  /**
   * Helper method that will check to see if a point is inside a rectangle or not. 
   * The logic is fairly understandable from the code.
   * @param p: point tested for containment
   * @param x: x coordinate of top-left corner of a rectangle
   * @param y: y coordinate of the-left corner of a rectangle
   * @param width: width of a rectangle
   * @param height: height of a rectangle
   * @return true if point is inside rectangle and false otherwise.
   */
  private static boolean pointInRectangle(Point p, int x, int y, int width, int height){
	  return ((x <= p.x) && (p.x <= (x + width)) && (y <= p.y) && (p.y <= y + height));
  }

  /**
   * Have the Rectangle move itself.
   * 
   * @param deltaX: difference between new x coordinate and old x coordinate
   * @param deltaY: difference between new y coordinate and old y coordinate
   */
  public void move(int deltaX, int deltaY) {
	  x += deltaX;
	  y += deltaY;
  }

  /**
   * Return the Rectangle's center.
   * 
   * @return the center of the Rectangle
   */
  public Point getCenter() {
    return new Point(x + (width / 2), y + (height / 2));
  }
}
