

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;
import javax.swing.JPanel;

// Draws a dot representing a customer
public class StoreComponent extends JComponent {
	private Point pt; // These two points define our drawing rectangle

	private boolean hold = true;// checks if the button has been hit

	private Queue1 line;// The assigned queue

	public StoreComponent(Point p) {
		super();
		// create component of fixed size
		this.setPreferredSize(new Dimension(300, 70));
		this.setMinimumSize(new Dimension(300, 70));
		this.setMaximumSize(new Dimension(600, 70));
		pt = p;
	}

	public StoreComponent(Point p, Queue1 line) {
		super();
		// create component of fixed size
		this.setPreferredSize(new Dimension(300, 50));
		this.setMinimumSize(new Dimension(300, 50));
		this.setMaximumSize(new Dimension(600, 50));
		pt = p;
		this.line = line;
	}

	public void initiate() {
		hold = false;
	}

	public Point getPoint() {
		return pt;
	}

	// Displays graphics of queues
	protected void paintComponent(Graphics g) {
		if (hold == true) {
			// "g" is used to create the desired graphics (background)
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, getWidth(), getHeight());
		} else if (line.getLength() >= 1) {
			Node first= line.getFirst();
			for (int x = 1; x <= line.getLength(); x++) {
				if (first.getData().getHurry() >= 8){
					g.setColor(Color.RED);
					g.drawOval(getWidth() - pt.x - 15 * x, pt.y, 10, 10);
					g.fillOval(getWidth() - pt.x - 15 * x, pt.y, 10, 10);
				}
				else if (x == 1) {
					g.setColor(Color.GREEN);
					g.drawOval(getWidth() - pt.x - 15 * x, pt.y, 10, 10);
					g.fillOval(getWidth() - pt.x - 15 * x, pt.y, 10, 10);
				} 
				else {
					g.setColor(Color.BLUE);
					g.drawOval(getWidth() - pt.x - 15 * x, pt.y, 10, 10);
					g.fillOval(getWidth() - pt.x - 15 * x, pt.y, 10, 10);
				}
				first= first.getNext();
			}
		}		
	}

	/**
	 * @return the line
	 */
	public Queue1 getLine() {
		return line;
	}

	/**
	 * @param line
	 *            the line to set
	 */
	public void setLine(Queue1 line) {
		this.line = line;
	}
}
