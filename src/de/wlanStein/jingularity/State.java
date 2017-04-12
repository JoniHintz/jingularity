package de.wlanStein.jingularity;
import java.awt.Graphics;
public interface State{
    public void enter();
    public void enter(State prestate);
    public void update(double dt);
    public void draw(Graphics g);
    public void exit();
}
