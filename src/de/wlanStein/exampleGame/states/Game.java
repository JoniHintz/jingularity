package de.wlanStein.exampleGame.states;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import de.wlanStein.exampleGame.gameObjects.Box;
import de.wlanStein.exampleGame.gameObjects.Bird;
import de.wlanStein.exampleGame.states.Menue;
import de.wlanStein.jingularity.Strategy;
import de.wlanStein.jingularity.Frame;
public class Game implements Strategy{
    private double passedTime = 0.0;
    private Box b;
    private Box b2;
    private Bird c;
    private boolean gameOver = false;
    private boolean flip1 = true;
    MouseAdapter mouse;
    Frame fw;
    long counter = 0;
    public Game(Frame fw){
    	this.fw = fw;
    }
	@Override
	public void enter() {
                gameOver = false;
		b = new Box();
		b2= new Box();
		b2.reset();
		
		c = new Bird();
		mouse = new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				c.setHop();
			}
		};
		fw.getContentPane().addMouseListener(mouse);
	}

        @Override
        public void enter(Strategy prevStrategy){
            enter();
        }

	@Override
	public void update(double dt) {
        if(b == null || c == null || b2 == null)//When update is called before the strategy was initialized 
            enter();
        
	if(gameOver)
            fw.popStrategy();
        int cPrevious = (int) c.y; 
	b.update(dt);
	c.update(dt);
	b2.update(dt);
         	
        //Check colission	
        //"oben" is the german word for top
        //"unten" is the german word for bottom
        //"hoehe" is the german word for heigth
	Rectangle box1oben = new Rectangle(b.x,0,70,b.hoehe1);
	Rectangle box1unten = new Rectangle(b.x,600-b.hoehe2,70,b.hoehe2);
	Rectangle box2oben = new Rectangle(b2.x,0,70,b2.hoehe1);
	Rectangle box2unten = new Rectangle(b2.x,600-b2.hoehe2,70,b2.hoehe2);
	Rectangle bird = new Rectangle(150, (int) c.y, 16, 16);

	if(box1oben.intersects(bird)){
                //set the bird to the edge of the box
                if(cPrevious > b.hoehe1)
                    c.y = b.hoehe1;
                    gameOver = true;
	}
	
	if(box1unten.intersects(bird)){
             //set the bird to the edge of the box
             if(cPrevious < 600-b.hoehe2)
                  c.y = 600 - b.hoehe2  -16;
             gameOver = true;
        }
	if(box2oben.intersects(bird)){
              //set the bird to the edge of the box
              if(cPrevious > b2.hoehe1)
                c.y = b2.hoehe1;
              gameOver = true;
	}
	if(box2unten.intersects(bird)){
             if(cPrevious < 600 - b2.hoehe2)
                c.y = 600 - b2.hoehe2 - 16;
             gameOver = true;
	}
	if (b.x <= 300 && flip1){
		b2.reset();
		flip1 = false;
		counter++;
	}
	if(b2.x <=300 && !flip1){
            b.reset();
            flip1 = true;
        	counter++;
	}
    }
        
    @Override
    public void draw(Graphics graphics) {
        graphics.setColor(fw.getBackground());
        graphics.fillRect(0, 0, fw.getWidth(), fw.getHeight());
        b.paint(graphics);
        c.paint(graphics);
	b2.paint(graphics);
	graphics.setColor(Color.white);
	graphics.drawString(String.valueOf(counter), 300, 50);
    }

    @Override
    public void exit() {
	fw.getContentPane().removeMouseListener(mouse);
    }
}

