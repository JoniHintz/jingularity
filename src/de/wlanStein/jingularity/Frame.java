package de.wlanStein.jingularity;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.Color;
import javax.swing.JFrame;
import java.util.Stack;
public class Frame extends JFrame implements Runnable{
    private Stack<State> stateStack;//Used when it is necessary to remember inactive states
    private State state;
    private final int w;
    private final int h;
    private final int fps;
    private Image dbImage;
    private Graphics dbGraphics; 
    public Frame( String title, int w, int h, int fps){
        stateStack     = new Stack<State>();
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
    //calculates delta time and calls the update method of the State.
    //returns the time when the previous update was entered 
    private double update(double lastTime){
        double minTime = (1e9/fps);
        //calculate dt
        long now = System.nanoTime();
        double currentTime = now - lastTime;
        double dt = currentTime/minTime;
        lastTime = now;
        if(state != null)
            state.update(dt);
        //wait when the programm runs too fast
        double sleepTime = (lastTime +minTime-System.nanoTime())/1e6;
        try{
            Thread.sleep((long) sleepTime);
        }catch(Exception e){} //catches the exception when sleepTime is less than zero
        return lastTime;
    }
    /** 
       pushes the state to the state stack
       @param the state the programm should enter
    */ 
    public void pushState(State state){
        if(this.state != null)
            this.state.exit();
        //state is automaticly pushed to the stateStack
        this.state = state;
        stateStack.push(state);
        this.state.enter();
    }
    /**
        Returns the current state and enters the previous state found on the stateStack
        Warning: When no state is on the state stack the programm will be terminated.
    */
   public void popState(){
       safePop(true);
   }
 
    private void safePop(boolean exitOnClear){
       State prevState = null;
       if(!stateStack.isEmpty()){
           state.exit();
           prevState = stateStack.pop();
       }
       if(exitOnClear && stateStack.isEmpty()){
           System.exit(0); 
       }
       else{
           state = stateStack.peek();
           state.enter(prevState);
       }
    }
   
    
    
    public int getHeight(){
        return h;
    }
 
    public int getWidth(){
        return w;
    }
    /**
        Replaces the state on top of the Statestack
    */
    public void replaceState(State state){
        if(state != null)
            safePop(false);
            pushState(state);
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
         if(dbImage == null){//Set this true for autoclear
             dbImage = createImage(w, h);
             dbGraphics = dbImage.getGraphics();
         }
         update(dbGraphics);
         g.drawImage(dbImage, 0, 0, this);
     }
 
     @Override 
     public synchronized void update(Graphics g){
         if(state != null)
             state.draw(g);
         repaint();
     }
}
     

