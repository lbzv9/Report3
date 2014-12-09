package technicalelvis.habakkuk.bolt;


import org.apache.log4j.Logger;

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Random;

import javax.imageio.ImageIO;

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class ImageNegativeBolt extends BaseBasicBolt
{
    File folder = new File("/home/cloudera/ImageFolder/");
    File[] list = folder.listFiles();
     
    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
    	 //  declarer.declare(new Fields("result"));
    }

    public void execute(Tuple input, BasicOutputCollector collector)
    {
    	//!for(File file:list){
    		
    	//!	String newFileName = file.getName()+"-Negative";
    	
    	
    	BufferedImage img = null;
         File f = null;
         
         //read image
         try{
             //!f = new File(file.getName());
        	 f = new File("/home/cloudera/ImageFolder/112.jpg");
             img = ImageIO.read(f);
         }catch(IOException e){
             System.out.println(e);
         }
         
         //get image width and height
         int width = img.getWidth();
         int height = img.getHeight();
         
         //convert to negative
         for(int y = 0; y < height; y++){
             for(int x = 0; x < width; x++){
                 int p = img.getRGB(x,y);
                 
                 int a = (p>>24)&0xff;
                 int r = (p>>16)&0xff;
                 int g = (p>>8)&0xff;
                 int b = p&0xff;
                 
                 //subtract RGB from 255
                 r = 255 - r;
                 g = 255 - g;
                 b = 255 - b;
                 
                 //set new RGB value
                 p = (a<<24) | (r<<16) | (g<<8) | b;
                 
                 img.setRGB(x, y, p);
             }
         }
         
         //write image
         try{
         //!    f = new File("/home/cloudera/ImageFolder/Negative/"+newFileName);
        	 f = new File("/home/cloudera/ImageFolder/Negative/112Negative.jpg");
             ImageIO.write(img, "jpg", f);
         }catch(IOException e){
             System.out.println(e);
         }
    	
    	//!}
    	 
    }

    public Map<String, Object> getComponenetConfiguration() { return null; }
}


