/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import java.awt.Graphics;
import java.io.FileNotFoundException;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author Adam
 */
public class Frame extends JFrame{
	
        public static int no;
	private Screen s;
	private Simulation sim;
        
    

	
        
        
	private float timeSinceLastUpdate;
	public float iterationTime = 1f;
	
	
	public Frame(){
		          setSize(800,600);
                          setTitle("Symulacja");
                          
	
	}


	
	public void createScreen(){
                
                sim = new Simulation();
		sim = new Simulation();
		s = new Screen();
		s.setBounds(0,0,800,600);
		add(s);
                this.addKeyListener(sim);
                
	}
	
	public void repaint(){
		
		s.repaint();
		
	}
	

        
        
        public void update(){
        
              
              sim.update();
        
        }
        
        
       
	
	
	private class Screen extends JLabel{
		
		@Override
		public void paintComponent(Graphics g){
			super.paintComponents(g);
			sim.drawCells(g);
		}
		
		
	}
	
}
