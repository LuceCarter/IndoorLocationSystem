package userInterfaces;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.*;

import com.phidgets.PhidgetException;

import rfid.RFIDReader;

public class LocationGUI extends JFrame {
	
	private static JFrame locFrame;
	private static JPanel locPanel;
	public static JLabel locLabel;
	private static BufferedImage locMap = null;
	
		
		
		public LocationGUI()
		{
			
			/*
			 * Set up the main window of the application
			 */
			
			
			locPanel = new JPanel();
			locPanel.setLayout(new BorderLayout());
			locPanel.setSize(new Dimension(600,600));
			
			
			locLabel = new JLabel("", SwingConstants.CENTER);
			
			locLabel.setFont(new Font(Font.SERIF, Font.BOLD, 24));
			
			
			locPanel.add(locLabel, BorderLayout.NORTH);
			
			//System.out.println(reader.getLocation());
			
			
			
			try {
				URL url = LocationGUI.class.getClassLoader().getResource("userInterfaces/map1.jpg");
				locMap = ImageIO.read(new File(url.getFile()));
				
			}catch(IOException e) {
				e.printStackTrace();
			}
			
			
	
			
			
			JPanel imagePanel = new JPanel(){
				
				@Override
				public void paintComponent(Graphics g)
				{
					super.paintComponent(g);
					
					g.drawImage(locMap, 0, 50, getWidth(), getHeight(), this);
				}
				
			};
			
			
			imagePanel.setPreferredSize(new Dimension(400, 400));
			
			locPanel.add(imagePanel, BorderLayout.SOUTH);
			
			
			
			locFrame = new JFrame("Where am I?");
			locFrame.setSize(800,800);
			locFrame.setResizable(true);
			
			locFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //Terminate the whole program when the X is pressed	
			
			locFrame.add(locPanel);
			locFrame.pack();
			locFrame.setVisible(true);
			
			
		}
		
			

}
