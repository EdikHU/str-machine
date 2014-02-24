package sed.str.machine.SomeMavenResource;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class App 
{
    public static void main( String[] args ) throws IOException
    {
    	
    	InputStream propIs = Thread.class.getClass().getResourceAsStream("/config.properties");
    	Properties prop = new Properties();
    	prop.load(propIs);
    	
    	System.out.println("read : "+prop.get("some"));
    	
    	
    	
    }
}
