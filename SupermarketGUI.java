import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentListener;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.border.EtchedBorder;
import javax.swing.event.AncestorListener;

public class SupermarketGUI extends Thread implements ActionListener{
	
	private ArrayList<Point> p= new ArrayList<Point>();
		
	private ArrayList<StoreComponent> panel= new ArrayList<StoreComponent>();
	
	// Contains all the speeds
	private static ArrayList<Queue1> lines = new ArrayList<Queue1>();
	
	private ArrayList<Box> boxes= new ArrayList<Box>();
	
	// Controls entire center section: lists each queue top to bottom	
	private Box b0 = new Box(BoxLayout.Y_AXIS);
	private Box b1 = new Box(BoxLayout.X_AXIS);
	private Box b2 = new Box(BoxLayout.Y_AXIS);
	private Box b3 = new Box(BoxLayout.X_AXIS);
		
	private JButton button;
	public JTextArea record;
	// Synchronize lock objects
	public static Object lockObject = new Object();
	public static Object lockObject1 = new Object();
	
	public boolean go=true;
	public boolean go2=true;
	
	public SupermarketGUI(){
		// Create a new window & size/locate/close
		JFrame f = new JFrame("Supermarket");
		f.setLocation(400, 300);
		f.setResizable(false);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);		
		
		// Container
		Container cp= f.getContentPane();
		
		Box b0= new Box(BoxLayout.Y_AXIS);
		cp.add(b0, BorderLayout.CENTER);	
		
		cp.add(b1, BorderLayout.SOUTH);	
		b2.add(new Legend(new Point(5,18)), BorderLayout.CENTER);
		b2.add(b3, BorderLayout.SOUTH);
		b1.add(b2, BorderLayout.CENTER);
		b3.add(new JLabel("People in a hurry          "), BorderLayout.WEST);
		b3.add(new JLabel("First person in line        "), BorderLayout.CENTER);
		b3.add(new JLabel("Other customers"), BorderLayout.EAST);
		
		record = new JTextArea();
		b2.add(record);
		
		for (int n= 0; n<5; n++){
			// Controls Queue n
			boxes.add(new Box(BoxLayout.X_AXIS));
			// Holds the customers
			p.add(new Point(5,18));   // initialize starting point
			lines.add(new Queue1());
			lines.get(n).setQueueNum(n+1);
			panel.add(new StoreComponent(p.get(n),lines.get(n)));
			panel.get(n).setBorder(BorderFactory.createEtchedBorder(EtchedBorder.LOWERED));
			// Add Queue n to center section
			boxes.get(n).add(panel.get(n));		
			boxes.get(n).add(new JLabel("Q" + (n+1),SwingConstants.LEFT), BorderLayout.EAST);
			b0.add(boxes.get(n));		
		}
		
		// go through adding to and switching queues
		button = new JButton("Run the Queues!");
		cp.add(button, BorderLayout.EAST);
		
		button.addActionListener(this);

		f.pack();
		f.setVisible(true);
	}

	// perform action (add to queue)
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == button){
			for(int x=  0; x<5; x++){
				panel.get(x).initiate();
			}
								
			new Thread(){
				public void run(){
					while (go){
						synchronized(lockObject){
						    record.setText(Market.getInfo());
							
							panel.get(0).repaint();
							
							panel.get(1).repaint();
							
							panel.get(2).repaint();
							
							panel.get(3).repaint();
							
							panel.get(4).repaint();	
							
							try {
								Thread.sleep(500);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}
				}
			}.start();	
			
			Market cornell= new Market(lines);					
		}		
	}
}

