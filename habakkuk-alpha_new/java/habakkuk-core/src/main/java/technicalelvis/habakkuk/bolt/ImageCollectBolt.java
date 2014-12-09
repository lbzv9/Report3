
package technicalelvis.habakkuk.bolt;

import org.apache.log4j.Logger;

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

import backtype.storm.topology.BasicOutputCollector;
import backtype.storm.topology.OutputFieldsDeclarer;
import backtype.storm.topology.base.BaseBasicBolt;
import backtype.storm.tuple.Fields;
import backtype.storm.tuple.Tuple;
import backtype.storm.tuple.Values;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

public class ImageCollectBolt extends BaseBasicBolt
{
    private static final long serialVersionUID = 42L;
/*    private static final Logger LOGGER =
        Logger.getLogger(PrinterBolt.class);*/
    private static final ObjectMapper mapper = new ObjectMapper();
    
    File file = new File("/home/group8/Twitter_image.txt");
    File file2 = new File("/home/group8/Image.txt");

    FileWriter fw = null;
     BufferedWriter bw = null;
     
     FileWriter fw2 = null;
     BufferedWriter bw2 = null;
    
     long id;
     String text = null;
     
    public void declareOutputFields(OutputFieldsDeclarer declarer)
    {
    	   declarer.declare(new Fields("result"));
    }

    public void execute(Tuple input, BasicOutputCollector collector)
    {
    	
    	
    	/* try {

    	    	if(!file.exists())
    	    	{
    	    		file.createNewFile();
    	    	}
     		
     		fw = new FileWriter(file.getAbsoluteFile(),true);
     		 bw = new BufferedWriter(fw);
     			
     	    	bw.write("\n"+input.getString(0));
     	    	bw.close();
     	        
     	} catch (IOException e) {
     		// TODO Auto-generated catch block
     		e.printStackTrace();
     	}*/
    	
    	Random rnd = new Random();
    	 
    			 URL url;
				try {
					url = new URL(input.getString(0));
				
    			 InputStream in = new BufferedInputStream(url.openStream());
    			 ByteArrayOutputStream out = new ByteArrayOutputStream();
    			 byte[]buf = new byte[1024];
    			 int n =0;
    			 while(-1!=(n=in.read(buf)))
    			 {
    				 out.write(buf,0,n);
    			 }
    			 
    			 out.close();
    			 in.close();
    			 byte[] response = out.toByteArray();
    			 
    			 FileOutputStream fos = new FileOutputStream("/home/group8/ImageFolder/"+rnd.nextInt(1000)+".jpg");
    			 
    			 fos.write(response);
    			 fos.close();
    			 
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    		 
    		 
    	
				collector.emit(new Values("xxx"));
    	 
    	 
    }

    public Map<String, Object> getComponenetConfiguration() { return null; }
}




/*File file = new File("/home/cloudera/Twitter.txt");

FileWriter fw = null;
 BufferedWriter bw = null;
 try {
		
		fw = new FileWriter(file.getAbsoluteFile(),true);
		 bw = new BufferedWriter(fw);
			
	    	bw.write("\n"+tuple);
	    	bw.close();
	        
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
*/
