package testBase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.beust.jcommander.Parameter;

//package for logger
import org.apache.logging.log4j.LogManager;		//Log4j 
import org.apache.logging.log4j.Logger;	//Log4j
public class BaseClass {
	public static WebDriver driver;
	public Logger logger; // Log4j
	public Properties p;
	
	@BeforeClass(groups= {"Sanity","Regression","Master"})
	@Parameters({"os","browser"})
	public void setup(String os ,String br) throws IOException 
	{
		//loading Config.properties file
		FileReader file = new FileReader("D:\\ecllipse java project\\Selenium\\OpencartV121\\src\\test\\resources//config.properties");
		p= new Properties();
		p.load(file);
		
		logger=LogManager.getLogger(this.getClass());
		
		
		switch(br.toLowerCase()) {
		
		case "chrome" :driver = new ChromeDriver(); break;
		case "edge" :driver = new EdgeDriver(); break;
		case "firefox" : driver = new FirefoxDriver(); break;
		
		default : System.out.println("Invalid browser name..."); return;
		}
		
		
		
		driver.manage().deleteAllCookies();
		//driver.get("https://demo.opencart.com.gr/");
		
		driver.get(p.getProperty("appURL1"));// reading url forom properties file
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	
	@AfterClass(groups= {"Sanity","Regression","Master"})
	public void tearDown()
	{
		driver.quit();
	}
	
	
	
	public String randomeString()
	{
		String generatedstring=RandomStringUtils.randomAlphabetic(5);
		return generatedstring;
	}
	
	public String randomeNumber()
	{
		String generatednumber=RandomStringUtils.randomNumeric(10);
		return generatednumber;
	}
	
	public String randomeAlphaNumberic()
	{
		String generatestring=RandomStringUtils.randomAlphabetic(3);
		String generatednumber=RandomStringUtils.randomNumeric(3);
		
		return(generatestring+"@"+generatednumber);
	}
	
	public String captureScreen(String tname) throws IOException {
	    String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

	    TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
	    File sourceFile = takeScreenshot.getScreenshotAs(OutputType.FILE);

	    String screenshotDir = System.getProperty("user.dir") + "\\screenshots\\";
	    File dir = new File(screenshotDir);
	    if (!dir.exists()) {
	        dir.mkdirs(); // create screenshots folder if not exists
	    }

	    String targetFilePath = screenshotDir + tname + "_" + timeStamp + ".png";
	    File targetFile = new File(targetFilePath);

	    FileUtils.copyFile(sourceFile, targetFile); // preferred over renameTo

	    return targetFilePath;
	}
}
