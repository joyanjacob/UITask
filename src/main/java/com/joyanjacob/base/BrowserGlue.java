package com.joyanjacob.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class BrowserGlue {
    public static WebDriver driver;
    public Properties props;
    FileInputStream propIs;
    String filePath;

    public BrowserGlue() throws IOException {
        filePath = new File("").getAbsolutePath() + "/src/main/resources/app.properties";
        propIs = new FileInputStream(filePath);
        props = new Properties();
        props.load(propIs);
        System.setProperty(props.getProperty("driver_name"), props.getProperty("driver_path"));
    }

    public WebDriver getDriver(){
        driver = new ChromeDriver();
        return driver;
    }

    private static final Thread CLOSE_THREAD = new Thread() {
        public void run(){
            try{
                driver.quit();
            }catch(Exception e){
            }
        }
    };

    static{
        try{
            new BrowserGlue();
        }catch(IOException e){
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(CLOSE_THREAD);
    }
}
