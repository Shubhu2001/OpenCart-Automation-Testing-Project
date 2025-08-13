package utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.ITestContext;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.util.List; 


import testBase.BaseClass;

public class ExtentReportManager implements ITestListener 
{
	public ExtentSparkReporter sparkReporter; // UI of the report
	public   ExtentReports extent; // populate common info on the report
	public ExtentTest test; // creating test case entrie and update status on test method
	String repName;
	
	public void onStart(ITestContext testContext)
	{
		/*
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Date dt = new Date();
		String currentdatetimestamp=df.format(dt);
		*/
		
		String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date()); //time stram
		
		repName="Test -Report-"+ timeStamp +".html";
		sparkReporter = new ExtentSparkReporter(".\\reports\\"+repName); // specify.location of the report 
		
		sparkReporter.config().setDocumentTitle("opencart Automation Testing"); //title of report
		sparkReporter.config().setReportName("opencart Functional Testing"); //name of the report
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "opencart");
		extent.setSystemInfo("Module", "Admin");
		extent.setSystemInfo("Sub Module", "Customer");
		extent.setSystemInfo("User Name", System.getProperty("user.name"));
		extent.setSystemInfo("Environment", "QA");
		
		
		String os =testContext.getCurrentXmlTest().getParameter("os");
		extent.setSystemInfo("Operationg System", os);
		
		String browser =testContext.getCurrentXmlTest().getParameter("browser");
		extent.setSystemInfo("Browser", browser);
	
		 List<String> includedGroups = (List<String>) testContext.getCurrentXmlTest().getIncludedGroups();
		if(!includedGroups.isEmpty()) {
			extent.setSystemInfo("Groups", includedGroups.toString());
		}
	}
	
	
	
	public void onTestSuccess(ITestResult result)
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());// to display group in report
		test.log(Status.PASS, result.getName()+"got Successfully executed");
	}
	
	
	public void onTestFailure(ITestResult result) 
	{
		test=extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		
		
		test.log(Status.FAIL, result.getName()+"got failed");
		test.log(Status.INFO, result.getThrowable().getMessage());
		
		try {
			String imgPath = new BaseClass().captureScreen(result.getName());
			if (imgPath != null) {
			    test.addScreenCaptureFromPath(imgPath);
			}
			test.addScreenCaptureFromPath(imgPath);
		} catch (Exception e1) {
			e1.printStackTrace();
		}

	}
	
	
	public void onTestSkipped(ITestResult result)
	{
		test = extent.createTest(result.getTestClass().getName());
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP, result.getName()+"got skipped");
		test.log(Status.INFO, result.getThrowable().getMessage());
	}
	
	@Override
	public void onFinish(ITestContext testContext) {
	    extent.flush();

	    String pathOfExtentReport = System.getProperty("user.dir") + "\\reports\\" + repName;
	    File extentReport = new File(pathOfExtentReport);

	    try {
	        if (extentReport.exists()) {
	            Desktop.getDesktop().browse(extentReport.toURI());
	        } else {
	            System.out.println("Extent report not found at: " + pathOfExtentReport);
	        }
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

}
