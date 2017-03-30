package de.wlanStein.tmxGlue;
import java.awt.Rectangle;
class Region{
    private final int gid;
    private final Rectangle rectangle;
    public Region(int gid, Rectangle rectangle){
         this.gid       = gid;
         this.rectangle = rectangle;
    } 
    int getGid(){
        return gid;
    }
    Rectangle getRectangle(){
        return rectangle;
    }
}

