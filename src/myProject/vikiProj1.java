package myProject; 

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

public class vikiProj1 {
	
	public static void main (String args[]) throws Exception
	{
		
		String offsetTextLength = "\"hd\":true";
		URL url;
		int countFalse = 0;
		InputStream is = null;
	    int countTrue = 0;
	    int offset = 0;
	    String text = "";
	    int test = 1; 
	    int finalTrue = 0;
	    int finalFalse = 0;
	
	  url = new URL("http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=" + test);  
	  System.out.println("url: " + "http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=" + test);
	    is = url.openStream();
	    BufferedReader br = new BufferedReader(new InputStreamReader(is));
	    text = br.readLine();
	    
	    
	    while (text.indexOf("\"more\":true") == 1) {
	   System.out.println("Last Index: " + (text.lastIndexOf("\"hd\":true")));
	   offset  =  text.indexOf("\"hd\":true", 0);
	   
	   System.out.println("initial offset: " + offset);
	   System.out.println(text.lastIndexOf("\"hd\":true"));
	    while  (offset != text.lastIndexOf("\"hd\":true"))
		    	{
	    			
		            offset  =  text.indexOf("\"hd\":true", offset + offsetTextLength.length());
		            System.out.println("next offset: " + offset);
		            countTrue++;		 
		            continue;
		        }
		    	
System.out.println("True:" + ++countTrue);
        offset = text.lastIndexOf("\"hd\":false") ;
        System.out.println("first false offset: " + offset);
        while (offset != -1)
    	{
			System.out.println("next false offset: " + offset);
            offset  =  text.indexOf("\"hd\":false", offset + offsetTextLength.length());
            System.out.println("next false offset: " + offset);
            if (offset != -1)
            countFalse++;		            
            continue;
        }

  	    	++test; 
	    	url = new URL("http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=" + test);
	    	System.out.println("url: " + "http://api.viki.io/v4/videos.json?app=100250a&per_page=10&page=" + test);
	    	is = url.openStream();
	    	br = new BufferedReader(new InputStreamReader(is));
	    	text = br.readLine();
	    	
	}	
	    	
finalTrue += countTrue;
finalFalse += countFalse;
   
System.out.println("no of pages : " + --test);
System.out.println("Final False : " + finalFalse);
System.out.println("Final True : " + finalTrue);

	}	
}