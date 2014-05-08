
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JComponent;

public class Legend extends JComponent{
	private Point pt; //These two points define our drawing rectangle
	private int ad=0;
	
	public Legend(Point p){
		super();
		// create component of fixed size 
		this.setPreferredSize(new Dimension(300,50));
		this.setMinimumSize(new Dimension(300,50));
		this.setMaximumSize(new Dimension(600,50));
		pt = p;
	}
	
	public int getAd() {
		return ad;
	}

	public void setAd(int ad) {
		this.ad = ad;
	}

	public Point getPoint(){
		return pt;
	}
		
	// Displays graphics of queues 
	protected void paintComponent(Graphics g) {
		
		// "g" is used to create the desired graphics (background)
        g.setColor(Color.lightGray);
        g.fillRect(0, 0, getWidth(), getHeight()); 
        
        //change colors depending on whether they are in a hurry or not
        g.setColor(Color.BLUE);
        g.drawOval(getWidth()-pt.x-90, pt.y, 10, 10); 
        g.fillOval(getWidth()-pt.x-90, pt.y, 10, 10);
        
      //change colors depending on whether they are in a hurry or not
        g.setColor(Color.GREEN);
        g.drawOval(getWidth()/2, pt.y, 10, 10); 
        g.fillOval(getWidth()/2, pt.y, 10, 10);
        
      //change colors depending on whether they are in a hurry or not
        g.setColor(Color.RED);
        g.drawOval(pt.x+80, pt.y, 10, 10); 
        g.fillOval(pt.x+80, pt.y, 10, 10);
	}
}
