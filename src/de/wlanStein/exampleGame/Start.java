package de.wlanStein.exampleGame;
import de.wlanStein.exampleGame.states.Menue;
import de.wlanStein.jingularity.Frame;
public class Start{
    public static void main(String args[]){
       Frame fw = new Frame("Example", 600, 600, 60); 
       fw.pushStrategy(new Menue(fw));
       fw.run(); 
    }
}

