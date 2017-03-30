package de.wlanStein.exampleGame.gameObjects;
import java.awt.Color;
import java.awt.Graphics;

public class Box {
private static final int BREITE=70;
public int hoehe1=(int) (519*(Math.random())+1);
public int hoehe2 = 600-hoehe1-120;
public int x = 300;
public void update(double dt){
x -= 2*dt;
}

public void paint(Graphics graphics){
	 graphics.setColor(Color.blue);
	 graphics.fillRect(x, 0, BREITE, hoehe1);
	 graphics.setColor(Color.blue);
	// graphics.fillRect(x,600-hoehe2, BREITE,  hoehe2);
	 graphics.fillRect(x, 600 - hoehe2, BREITE, hoehe2);
}

public void reset(){
	x = 600;
	hoehe1=(int) (519*(Math.random())+1);
    hoehe2 = 600-hoehe1-120;
	
}
}
