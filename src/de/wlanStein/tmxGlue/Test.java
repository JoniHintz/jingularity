package de.kaguia.tmxGlue;
import de.kaguia.tmxGlue.Map;
public class Test{
    public static void main(String agrs[]){
        Map map =   new Map("../exterior.tmx");
        System.out.println(map.isValid());
    }
}

