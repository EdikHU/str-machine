package sed.jettys;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Some {

	public static void main(String[] args) throws IOException {
		Properties prop = new Properties();

		InputStream resStream = new Some().getClass().getResourceAsStream("/strconfig.properties");
		prop.load(resStream);
		String ss = prop.getProperty("some");
		System.out.println("here readed resources "+ss);
		
		
	}

}
