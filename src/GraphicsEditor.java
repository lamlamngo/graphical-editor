import java.awt.*;
import javax.swing.*;

public class GraphicsEditor extends javax.swing.JFrame {
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
    
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new GraphicsEditor();
			}
		});
	}
}
