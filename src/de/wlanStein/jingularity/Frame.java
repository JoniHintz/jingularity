package de.wlanStein.jingularity;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.Stack;
public class Frame extends JFrame implements Runnable{
    private Stack<Strategy> strategyStack;
    private Strategy strategy;
    private boolean blit = false;
    private final int w;
    private final int h;
    private final int fps;
    private Image dbImage;
    private Graphics dbGraphics; 
    public Frame( String title, int w, int h, int fps){
        strategyStack     = new Stack<Strategy>();
        this.w         = w;
        this.h         = h;
        this.fps       = fps;
        //Initialize JFrame
        setTitle(title);
        setSize(w, h);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
    //calculates delta time and calls the update method of the strategy
    //returns the time when the previous update was entered 
    private double update(double lastTime){
        double minTime = (1e9/fps);
        //calculate dt
        long now = System.nanoTime();
        double currentTime = now - lastTime;
        double dt = currentTime/minTime;
        lastTime = now;
        if(strategy != null)
            strategy.update(dt);
        //wait when the programm runs too fast
        double sleepTime = (lastTime +minTime-System.nanoTime())/1e6;
        try{
            Thread.sleep((long) sleepTime);
        }catch(Exception e){} //catches the exception when sleepTime is less than zero
        return lastTime;
    }
    /** 
       pushes the strategy to the strategy stack
       @param the strategu the programm should follow
    */ 
    public void pushStrategy(Strategy strategy){
        if(this.strategy != null)
            this.strategy.exit();
        //strategy is pushed to the strategyStack
        this.strategy = strategy;
        strategyStack.push(strategy);
        this.strategy.enter();
    }
    /**
        Returns the current strategy and enters the previous state found on the stateStack
        Warning: When no state is on the state stack the programm will be terminated.
        
    */
   public void popStrategy(){
       safePop(true);
   }
 
    private void safePop(boolean exitOnClear){
       Strategy prevStrategy = null;
       if(!strategyStack.isEmpty()){
           strategy.exit();
           prevStrategy = strategyStack.pop();
       }
       if(exitOnClear && strategyStack.isEmpty()){
           System.exit(0); 
       }
       else{
           strategy = strategyStack.peek();
           strategy.enter(prevStrategy);
       }
    }
   
    
    
    public int getHeight(){
        return h;
    }
 
    public int getWidth(){
        return w;
    }
    /**
        Replaces the strategy on top of the Strategystack
    */
    public void replaceStrategy(Strategy strategy){
        if(strategy != null)
            safePop(false);
            pushStrategy(strategy);
    } 
    @Override 
     public void run(){
         double lastTime = System.nanoTime();
         while(true){
            lastTime = update(lastTime);
         }
     }
     @Override
     public void paint(Graphics g){
         if(blit || dbImage == null){
             dbImage = createImage(w, h);
             dbGraphics = dbImage.getGraphics();
         }
         update(dbGraphics);
         g.drawImage(dbImage, 0, 0, this);
     }
 
     @Override 
     public synchronized void update(Graphics g){
         if(strategy != null)
             strategy.draw(g);
         repaint();
     }
     public void setBlit(boolean blit){
         this.blit = blit;
     }
}
     

