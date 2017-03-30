package de.wlanStein.exampleGame.states;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import de.wlanStein.exampleGame.states.Game;
import de.wlanStein.jingularity.State;
import de.wlanStein.jingularity.Frame;

public class Menue implements State {
	private Frame fw;
	private  MouseAdapter mouse;
	@Override
	public void enter() {
		mouse = new MouseAdapter() {
			@Override 
			public void mousePressed(MouseEvent e){
				 fw.pushState(new Game(fw));
			}
		};
		fw.getContentPane().addMouseListener(mouse);

	}
	  
	
	public Menue(Frame fw) {
            this.fw = fw;
	}


	@Override
	public void update(double dt) {
                
	}

	@Override
	public void draw(Graphics graphic) {
        graphic.setColor(Color.black);
	graphic.drawString("Klicken zum Starten", 250, 300);

	}

	@Override
	public void exit() {
		fw.getContentPane().removeMouseListener(mouse);

	}

}
