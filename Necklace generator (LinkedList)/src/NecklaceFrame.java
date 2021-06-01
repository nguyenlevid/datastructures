import java.awt.*;
import javax.swing.*;


/**
 * A frame that draws a necklace. Can be refreshed.
 * 
 * @author Joel
 * @author revised by Diane Mueller
 * @version Sp21
 */
public class NecklaceFrame extends JFrame
{
	private static final int PANEL_HEIGHT = 50; //the height of the frame
	private static final int PANEL_WIDTH = 800; //the width of the frame;
	private static final int CONNECTOR_SIZE = 5; //the length of the connecting twine between beads
	
	private Necklace necklace;
	private NecklacePanel panel; //the panel we'll draw the necklace on
	
	public NecklaceFrame(Necklace necklace)
	{
		this.necklace = necklace; //set the necklace
	
		panel = new NecklacePanel();
		panel.setPreferredSize(new Dimension(PANEL_WIDTH,PANEL_HEIGHT));
		panel.setBackground(Color.WHITE);
		this.getContentPane().add(panel);

	    this.setTitle("Bead Necklace");
	    this.setResizable(false);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
	}
	
	/**
	 * A private inner class that contains the panel that we're drawing on.
	 * We've overwritten paintComponent to specify our own painting.
	 */
	private class NecklacePanel extends JPanel {
		public void paintComponent(Graphics g)
		{
			//this will "white out" the background of the Panel
			g.setColor(Color.WHITE);
			g.clearRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT);
			g.fillRect(0, 0, PANEL_WIDTH, PANEL_HEIGHT); //erase what has come before

		
			// This code draws the bead necklace
			int halfHeight = PANEL_HEIGHT / 2;
			int xCoord = 10;
			for (int i = 0; i < necklace.numBeads(); i++){
				Bead temp = necklace.getBead(i);
				g.setColor(temp.color);
				g.fillOval(xCoord, halfHeight - temp.size/2, temp.size, temp.size);
				xCoord = xCoord + temp.size;
				g.setColor(Color.BLACK);
				g.drawLine(xCoord, halfHeight, xCoord + CONNECTOR_SIZE, halfHeight);
				xCoord = xCoord + CONNECTOR_SIZE;	
			}
		}
	}

	/**
	 * A method that "redraws" the frame to update the shown necklace.
	 */
	public void refreshNecklace()
	{
		panel.repaint();
	}
}
