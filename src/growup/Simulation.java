/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package growup;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import static java.awt.event.KeyEvent.VK_P;
import static java.awt.event.KeyEvent.VK_S;
import static java.awt.event.KeyEvent.VK_SPACE;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import static java.lang.Math.sqrt;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import javax.swing.JOptionPane;

/**
 *
 * @author Adam
 */
public class Simulation implements KeyListener{
	Random r,randColor,rHex;
    
	private Cell[][] cells;
	private int width = 800/Cell.size;
	private int height = 600/Cell.size;
        
        float colorR, colorG,colorB;
        
        public static boolean pause=false;
        public static int howMany = 99;
        
        public static boolean control=false;
        
        public int HowManyALive(){
            int counter=0;
            
            for(int i=0;i<width;i++){
                for(int j=0;j<height;j++){
                    if(cells[i][j].isAlive()) counter++;
                }
            }
            
        return counter;    
        }
        
        
         public int HowManyReady(){
            int counter=0;
            
            for(int i=0;i<width;i++){
                for(int j=0;j<height;j++){
                    if(cells[i][j].isReady()) counter++;
                }
            }
            
        return counter;    
        }
         
        public int HowManyNOTReady(){
            int counter=0;
            
            for(int i=0;i<width;i++){
                for(int j=0;j<height;j++){
                    if(!cells[i][j].isReady()) counter++;
                }
            }
            
        return counter;    
        } 
         
        
          
        
        
	public Simulation()  {
            
            cells = new Cell[width] [height];
            System.out.print("jestem");
            ArrayList<Color> colorList=new ArrayList<>();
            randColor = new Random();
            
            Random randomGenerator = new Random();
            
            for(int x=0;x<Frame.no;x++){
                
                colorR=randColor.nextFloat();
                colorG=randColor.nextFloat();
                colorB=randColor.nextFloat();
                
                colorList.add(new Color(colorR,colorG,colorB));
                System.out.println(colorList.size());
            }
            
            
           for(int i=0;i<width;i++){
               for(int j=0;j<height;j++){
                   cells[i][j] = new Cell(i,j);
                   cells[i][j].setAlive(true);
               }
           }
           for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
                                if(cells[i][j].isAlive()){
                                    int index = randomGenerator.nextInt(Frame.no);
                                    cells[i][j].color = colorList.get(index);
                                            }
			}
		}
            
	}
	
        
        
        
	
	public void drawCells(Graphics g){
		
		
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				cells[i][j].drawCell(g);
			}
		}
		
	}
	

private boolean checkFilled(){
boolean result = false;    
int deadCounter = 0;    
    for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(!cells[i][j].isAlive())deadCounter++;  
			}
		}
if(deadCounter!=0)result=false;
else
    result=true;
    
return result;    
}


private boolean checkFilledRec(){
boolean result = false;    
int deadCounter = 0;    
    for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if(!cells[i][j].isReady())deadCounter++;  
			}
		}
if(deadCounter!=0)result=false;
else
    result=true;
    
return result;    
}     
        
        

int iterator=0;
ArrayList<Cartez> points = new ArrayList<>();
public void update(){
    
    if(control){
    if(howMany==iterator){ pause=true;
    
    iterator=0;
    }
    }
    if(!pause){
    
    //System.out.println("ITERACJA NR: "+iterator);
    int energyPrev=0;  
    int energySum=0;
    ArrayList<Color> nbColors = new ArrayList<>();
    
    if (points.size()>=cells.length){
        
        iterator++;
        points = new ArrayList<>();
    }
                checkBorders();
                Random rn = new Random();
                int rangeX = (width-1) - 0 + 1;
                int i =  rn.nextInt(rangeX) + 0;
                
                int rangeY = (height-1) - 0 + 1;
                int j =  rn.nextInt(rangeY) + 0;    
        
           //&&!points.contains(new Cartez(i,j))
                
           if(cells[i][j].border&&!points.contains(new Cartez(i,j))){
           points.add(new Cartez(i,j));
            
              
                                int mx = i-1;
				if(mx<0) mx = width - 1;
				int my = j-1;
				if(my<0) my = height - 1;
				int gx= (i+1)%width;
				int gy= (j+1)%height;
				
                               // nbColors.add(cells[i][j].color);
				
                          nbColors.add(cells[mx][my].color);
                          nbColors.add(cells[mx][j].color);
                          nbColors.add(cells[mx][gy].color);
                          nbColors.add(cells[i][my].color);
                          nbColors.add(cells[i][gy].color);
                          nbColors.add(cells[gx][my].color);
                          nbColors.add(cells[gx][j].color);
                          nbColors.add(cells[gx][gy].color);
                                
                                
                                int iterator=0;
		
                                
                                for(Color object: nbColors){
                                energySum=0;
                                Random rg = new Random();
                                int index = rg.nextInt(nbColors.size());
                                Color tested=nbColors.get(index);
                                
                             
				if(cells[mx][gy].color!=tested)  energySum++;
				if(cells[i][my].color!=tested)  energySum++;
				if(cells[i][gy].color!=tested)  energySum++;
				if(cells[gx][my].color!=tested)  energySum++;
				if(cells[gx][j].color!=tested)  energySum++;
				if(cells[gx][gy].color!=tested)  energySum++;
                                
                       
                              //  System.out.println(dEnergy);
                                if (energySum<=energyPrev){
                                    cells[i][j].color=tested;
                                    System.out.println(energySum+" "+energyPrev);
                                    System.out.println("ZAMIENIAM komrókę: "+i+" : "+j);
                                }
                                energyPrev=energySum;
                                
                                }
                             
                           
                             
           }
        }
        
    }


              
              
    



public void checkBorders(){
    for(int i=0;i<width;i++){
        for(int j=0;j<height;j++){
            int mx = i-1;
				if(mx<0) mx = width - 1;
				int my = j-1;
				if(my<0) my = height - 1;
				int gx= (i+1)%width;
				int gy= (j+1)%height;
				
				
				
				
                                
                               Color myColor=cells[i][j].color;
                                
                                    
                                if(cells[mx][my].color!=myColor){
                                    cells[i][j].border=true;
                                }
                               
				 if(cells[mx][j].color!=myColor){
                                    cells[i][j].border=true;
                                }
                                    
                                    
                                
				 if(cells[mx][gy].color!=myColor){
                                    cells[i][j].border=true;
                                }
				 if(cells[i][my].color!=myColor){
                                    cells[i][j].border=true;
                                }
				 if(cells[i][gy].color!=myColor){
                                    cells[i][j].border=true;
                                }
				 if(cells[gx][my].color!=myColor){
                                    cells[i][j].border=true;
                                }
				 if(cells[gx][j].color!=myColor){
                                    cells[i][j].border=true;
                                }
				 if(cells[gx][gy].color!=myColor){
                                    cells[i][j].border=true;
                                }
                                
        }
    }
}

    @Override
    public void keyTyped(KeyEvent e) {
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void keyPressed(KeyEvent e) {
      int codePause = VK_SPACE;
        
        
        if(e.getKeyCode()==codePause){
          
            if(pause) pause=false;
            else pause=true;
            System.out.println("PAUSA");
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }



}
