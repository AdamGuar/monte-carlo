/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import java.awt.Window;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Adam
 */
public class simThread implements Runnable {

   
  
   
 
    
    @Override
    public void run() {
       Frame f = new Frame();
  
		//f.iterationTime=simSpeed;
		
           
		f.setVisible(true);
		f.setResizable(false);
		
                f.setLocationRelativeTo(null);
		
                f.createScreen();
		
		
		
		while(true){
                    /*long thisFrame= System.currentTimeMillis();
                    float tslf = (float) ((thisFrame-lastFrame)/100.0);
                    lastFrame=thisFrame;*/
			
			//f.update(tslf);
                    
                        
                    
                        f.update();
                    
                        f.repaint();
			
                        /*try {
                        Thread.sleep(10);
                        } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        }*/
          
		}
    }
    
}
