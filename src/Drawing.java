import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.ListIterator;

/**
 * Drawing.java
 * Class for a drawing object.
 * @author Lam Ngo
 * @version 01/22/2017
 */
public class Drawing {
	private ArrayList<Shape> shapeList; //an instance variable to store the Shape objects that are in the drawing.
	private Color myColor; //The current default color.
	private ArrayList<Memento> changes; // keep track of changes
	private ArrayList<Memento> undos; // keep track of undos
	private State currentS;
	private boolean save = false;
	/**
	 * Constructor, which creates an empty drawing with an initial default color given as a parameter.
	 * @param initialColor default color of the drawing object
	 */
	public Drawing (Color initialColor){
		shapeList = new ArrayList<Shape>();
		this.myColor = initialColor;
		changes = new ArrayList<Drawing.Memento>();
		undos = new ArrayList<Drawing.Memento>();
	}
	
	/**
	 * Set color of the drawing object to a specific color
	 * @param color: the color to be set
	 */
	public void setColor(Color color){
		Color temp = myColor;
		this.myColor = color;
		if (!save){
			changes.add(new Memento(0,null,temp,color,"changeDC"));
		}
		save = false;
	}
	
	public void move(Shape shape, Point first, Point second){
		shape.move(second.x - first.x,second.y - first.y);
		changes.add(new Memento(shapeList.indexOf(shape),shape,myColor,myColor,"move"));
	}
	
	public void shapesSwapped(Shape firstS, Shape secondS){
		changes.add(new Memento(firstS,secondS,"exchange"));
	}
	/**
	 * Given a reference to a graphics object, the method has each shape in the drawing draw itself.
	 * @param page A graphics object
	 */
	public void draw(Graphics page){
		
		ListIterator<Shape> lit = shapeList.listIterator(shapeList.size());
		while (lit.hasPrevious()){
			lit.previous().draw(page);
		}
	}
	
	/**
	 * Given a point, find the front most container of that point, and then has it change it color to the color
	 * of the drawing object
	 * @param p: given point
	 */
	public void changeShapeColor(Point p){
		Color temp = myColor;
		Shape aShape = getFrontmostContainer(p);
		if (aShape != null){
			temp = aShape.getColor();
			shapeList.get(shapeList.indexOf(aShape)).setColor(myColor);
		}
		changes.add(new Memento(shapeList.indexOf(aShape), aShape, temp,myColor, "changeC"));
	}
	
	/**
	 * Function that will handle redo. Handle every case but Move. No time to finish.
	 */
	public void redo(){
		if (!undos.isEmpty()){
			currentS = undos.get(undos.size()-1).getSavedState();
			switch (currentS.getOperation()){
			case "add": shapeList.add(currentS.getIndexSaved(),currentS.getShapeSaved());
						break;
			case "remove": 	shapeList.remove(currentS.getShapeSaved());
							break;
			case "changeC":	shapeList.get(currentS.getIndexSaved()).setColor(currentS.getNewColor());
							break;
			case "moveFront": 	moveToFront(currentS.getShapeSaved());						
								break;
			case "moveBack":	moveToBack(currentS.getShapeSaved());
								break;
			case "changeDC":	setColor(currentS.getNewColor());
								break;
			case "exchange":	Point firstCenter = currentS.getShapeSaved().getCenter();
	        					Point secondCenter = currentS.getSecondShapeSaved().getCenter();
	        					currentS.getShapeSaved().setCenter(secondCenter);
	        					currentS.getSecondShapeSaved().setCenter(firstCenter);
	        					break;
	        					
			}
			changes.add(undos.get(undos.size()-1));
			undos.remove(undos.size()-1);
		}else{
			System.out.println("No more redo possible");
		}
	}
	
	/**
	 * Function that will handle undo. Handle every case but Move. No time to finish.
	 */
	public void undo(){
		if (!changes.isEmpty()){
			currentS = changes.get(changes.size()-1).getSavedState();
			switch (currentS.getOperation()){
			case "add": shapeList.remove(currentS.getIndexSaved());
						break;
			case "remove": 	shapeList.add(currentS.getIndexSaved(),currentS.getShapeSaved());
							break;
			case "changeC":	shapeList.get(currentS.getIndexSaved()).setColor(currentS.getColorSaved());
							break;
			case "moveFront": 	shapeList.add(currentS.getIndexSaved(),currentS.getShapeSaved());
								shapeList.remove(0);
								break;
			case "moveBack":	shapeList.add(currentS.getIndexSaved(),currentS.getShapeSaved());
								break;
			case "changeDC":	setColor(currentS.getColorSaved());
								break;
			case "exchange":	Point firstCenter = currentS.getShapeSaved().getCenter();
	        					Point secondCenter = currentS.getSecondShapeSaved().getCenter();
	        					currentS.getShapeSaved().setCenter(secondCenter);
	        					currentS.getSecondShapeSaved().setCenter(firstCenter);
	        					break;
	        					
			}
			undos.add(changes.get(changes.size()-1));
			changes.remove(changes.size()-1);
			save = true;
		}else{
			System.out.println("No more undo possible");
		}
	}
	/**
	 * Given a Shape, move it to the front of the shape list, so it will appear front on the canvas.
	 * @param aShape: Shape to be moved
	 */
	public void moveToFront(Shape aShape){
		int index = shapeList.indexOf(aShape);
		shapeList.remove(aShape);
		shapeList.add(0, aShape);
		changes.add(new Memento(index,aShape,myColor,null,"moveFront"));	
	}
	
	/**
	 * Given a Shape, move it to the back of the shape list, so it will appear at the back of the linear order
	 * @param aShape: Shape to be moved
	 */
	public void moveToBack(Shape aShape){
		int index = shapeList.indexOf(aShape);
		shapeList.remove(aShape);
		shapeList.add(aShape);
		changes.add(new Memento(index,aShape,myColor,null,"moveBack"));
	}
	
	/**
	 * Given a point, return the front most container of that point.
	 * @param p: Point to be found container
	 * @return a front most Shape that contains the point, or null otherwise.
	 */
	public Shape getFrontmostContainer(Point p){
		ListIterator<Shape> lit = shapeList.listIterator();
		while (lit.hasNext()){
			if (lit.next().containsPoint(p)){
				return lit.previous();
			}
		}
		return null;
	}
	
	/**
	 * remove the front most Container of a given point.
	 * @param p: point to be used.
	 */
	public void removeFrontmostContainer(Point p){
		Shape aShape = getFrontmostContainer(p);
		int index = shapeList.indexOf(aShape);
		shapeList.remove(aShape);
		changes.add(new Memento(index,aShape,myColor,null,"remove"));
	}
	
	/**
	 * Getter method, return the color of the drawing object
	 * @return Color the drawing object's color
	 */
	public Color getColor(){
		return myColor;
	}
	
	/**
	 * Add a shape to the shape List, at the front.
	 * @param myShape: a Shape to be added.
	 */
	public void addShape(Shape myShape){
		shapeList.add(0,myShape);
		Memento newM = new Memento(0,myShape,myColor,null,"add");
		changes.add(newM);
	}
	
	private class State{
		private int index;
		private Shape shape;
		private Shape secondS;
		private Color color;
		private String operation;
		private Color newColor;
		public State(int indexToSave, Shape shapeToSave, Color colorToSave, Color newColor, String operation){
			this.index = indexToSave;
			this.shape = shapeToSave;
			this.color = colorToSave;
			this.operation = operation;
			this.newColor = newColor;
		}
		public State(Shape firstShape, Shape secondShape,String operation){
			this.shape = firstShape;
			this.secondS = secondShape;
			this.operation = operation;
		}
		public Color getNewColor(){
			return newColor;
		}
		public Shape getSecondShapeSaved(){
			return secondS;
		}
		public int getIndexSaved(){
			return index;
		}
		public Shape getShapeSaved(){
			return shape;
		}
		public Color getColorSaved(){
			return color;
		}
		public String getOperation(){
			return operation;
		}
	}
	
	private class Memento {
		private State state;
		
		public Memento(int indexToSave, Shape shapeToSave, Color colorToSave,Color newColor,String operation){
			state = new State(indexToSave,shapeToSave,colorToSave,newColor,operation);
		}
		public Memento(Shape firstShape, Shape secondShape,String operation){
			state = new State(firstShape,secondShape,operation);
		}
		
		public State getSavedState(){
			return state;
		}
		
	}

}
