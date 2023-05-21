package utilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;
    private static ConfigReader configReader;
    private String path="src/test/resources/configuration/configuration.properties"; //path for config file location

    private ConfigReader(){
        //This method will help you to read the file:
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(path));
            properties = new Properties();
            try {
                properties.load(reader);
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Config properties file not found: " + path);
        }

    }

    public static ConfigReader getInstance(){
        if(configReader==null){
            configReader=new ConfigReader();
        }
        return configReader;
    }
    //This method will help with baseURI from ConfigFile
    public String getUri(){
        String uri=properties.getProperty("baseURI");
        if(uri!=null) return uri;
        else throw  new RuntimeException("Api uri is not specified!");
    }
    //This method will call access token in configFile
    public String getToken(){
        String token=properties.getProperty("accessToken");
        if(token!=null) return token;
        else throw new RuntimeException("Access token is not found in cofig File!");
    }
}
