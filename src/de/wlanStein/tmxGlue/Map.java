package de.wlanStein.tmxGlue;
import java.util.HashMap;
import java.io.IOException;
import javax.management.StringValueExp;
import javax.script.ScriptException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
public class Map{
    
    private int[] imageBatch;
    private boolean valid = true;
    private HashMap<Integer, Region[]> regions;
    //the Regions can be used to generate poligons for the collider
    public Map(String pathToTmx){ 
         //Read the TMX file
         try{
             DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
             Document document = documentBuilder.parse(pathToTmx);
             document.getDocumentElement().normalize();
             //Get the map orientation 
             String orientation = document.getDocumentElement().getAttribute("orientation");
             //Test whether the map is othorgonal
             if(orientation.equals("orthogonal")){
                 //get the tileset when it is external (.tsx)
                 
                 //get the path to the tileset
                                                
                 //get the size of the tileset
      
                 //load tiles into image batch 
                
                 //load the tile regions into regions 
                
                 //generate terain polygons optional
             }
             else{ 
                   System.err.println("Orientation not supported");
                   valid = false;
             }
       } catch (ParserConfigurationException pce) {
           valid = false;
           System.err.println(pce.getMessage());
       } catch (SAXException se) {
           valid = false;
           System.out.println(se.getMessage());
       } catch (IOException ioe) {
           System.err.println(ioe.getMessage());
           valid = false;
       }
   }  
   public boolean isValid(){
       return valid;
   }  
}
