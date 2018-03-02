import java.awt.*;
import javax.swing.*;

import Editor.ColorIndicator;

public class GraphicsEditor extends javax.swing.JFrame {
	
	private final int APPLET_WIDTH = 700, APPLET_HEIGHT = 500;
	private final Color initialColor = Color.red; // default color starts as red
	
	private Command cmd; // the command being executed
	private Drawing dwg; // the drawing: shapes in order
	private ColorIndicator colorBox; // a GUI component to show the current default color
	
    public GraphicsEditor() {
    		JFrame frame = new JFrame("Object-Oriented Graphical Editor");
        
    		frame.setLayout(new GridBagLayout());
    		
    		JTextField textFieldUserName = new JTextField(50);
    		frame.add(textFieldUserName);
    		
    		JMenuBar menuBar = new JMenuBar();
    		JMenu menuFile = new JMenu("File");
    		JMenuItem menuItemExit = new JMenuItem("Exit");
    		menuFile.add(menuItemExit);
    		 
    		menuBar.add(menuFile);
    		 
    		// adds menu bar to the frame
    		frame.setJMenuBar(menuBar);
    		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    		
    		frame.setSize(300, 200);
    		frame.setVisible(true);
    }
    
    /**
     * A ColorIndicator shows what the current color is.
     */
    private class ColorIndicator extends JPanel {
      private static final long serialVersionUID = 0;
      
      private final int COLORBOX_WIDTH = 20, COLORBOX_HEIGHT = 20;

      /**
       * Constructor sets the size and border.
       */
      public ColorIndicator() {
        setBorder(BorderFactory.createEtchedBorder());
        setPreferredSize(new Dimension(COLORBOX_WIDTH, COLORBOX_HEIGHT));
      }

      /**
       * Show a new color.
       * @param color the color to show
       */
      public void show(Color color) {
        setBackground(color);
      }
    }
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GraphicsEditor();
			}
		});
	}
}
