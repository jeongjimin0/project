package batch_file;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.util.Properties;

import javax.naming.InsufficientResourcesException;

public class Prop {
	
    public static void main(String[] args) {
        String resource = "C:\\Users\\정지민\\Desktop\\test\\File search\\src\\db.properties";
        Properties properties = new Properties();
        
        try {
        	;
            properties.load(new FileInputStream(resource));
            System.out.println(properties.getProperty("user"));
            System.out.println(properties.getProperty("passwd"));
            System.out.println(properties.getProperty("url"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
    }
	
	
}
