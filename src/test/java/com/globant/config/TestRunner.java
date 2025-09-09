package com.globant.config;

import lombok.Getter;
import org.testng.annotations.BeforeClass;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class TestRunner {
    private static final String PROPERTIES_FILE = "src/test/resources/config.properties";
    private static final Properties PROPERTIES = new Properties();

    @Getter
    private static String baseUrl;
    @Getter
    private static String apiKey;


    @BeforeClass
    public void setUpEnviroment(){
        loadProperties();
        baseUrl = PROPERTIES.getProperty("url.base");
        apiKey = PROPERTIES.getProperty("apiKey");
    }

    private void loadProperties() {
        try{
            FileInputStream fileInputStream = new FileInputStream(PROPERTIES_FILE);
            PROPERTIES.load(fileInputStream);
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
