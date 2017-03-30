package de.wlanStein.exampleGame.gameObjects;
import java.awt.Image;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Toolkit;
public class Bird {
public double ltime = 0;
private Image i = Toolkit.getDefaultToolkit().getImage("../images/birdi.gif");
private static final double GRAVITATION = 0.01;
public double y = 300;
boolean hop = false;
public void paint(Graphics graphics){
	 graphics.drawImage(i, 150, (int) y, 16, 16, null);
	}
public void update (double dt){
	if (hop == true){
		y -= 40;
		hop = false;
		ltime = 0;
	}
	else{
       y+=Math.pow(ltime, 2)*GRAVITATION;
       ltime += dt;
   	if (y>=580){
		y=580;
	}
   	if (y<=25){
   		y=25;}
   	}
	

}
public void setHop() {
	hop = true;
	
}
}
