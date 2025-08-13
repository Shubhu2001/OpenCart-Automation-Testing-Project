package testCases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.BasePage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
 Data is valid  -- login success -test pass -logout
 Data is valid - login failed -test fail
 
 Data is invalid  -- login success -test fail -logout
 Data is invalid  -- login failed -test pass 
 */
public class TC003_LoginDDT  extends BaseClass{
	
	@Test(dataProvider = "LoginData",dataProviderClass = DataProviders.class,groups="Datadriven")//getting data provider from different class
	public void verify_loginDDt(String email,String pwd,String exp)
	{
		logger.info("**** Starting TC003_LoginDDT *****");
	try {	
		//HomePage
		HomePage hp = new HomePage(driver);	
		hp.testClickMyAccount();
		hp.clickLogin();
				
				
		//Login Page
		LoginPage lp = new LoginPage(driver);
		Thread.sleep(2000);
		lp.setEmail(email);
		Thread.sleep(2000);
		lp.setPassword(pwd);
		lp.clickLogin();
			
				
		// MyAccpunt
		MyAccountPage macc = new MyAccountPage(driver);
		boolean targetPage=macc.isMyAccountPageExists();
		
		/*
		 Data is valid  -- login success -test pass -logout
		 				- login failed -test fail
		 
		 Data is invalid  -- login success -test fail -logout
		  					-- login failed -test pass 
		 */
		
		if(exp.equalsIgnoreCase("Valid"))
		{
			if(targetPage ==true)
			{
				macc.logout();
				Assert.assertTrue(true);
				
			}
			else {
				Assert.assertTrue(false);
			}
		}
		
		
		if(exp.equalsIgnoreCase("Invalid"))
		{
			if(targetPage ==true)
			{	macc.logout();
				Assert.assertTrue(false);
				
			}
			else {
				Assert.assertTrue(true);
			}
		}
	}catch(Exception e) {
		Assert.fail();
	}
	logger.info("**** Finished TC003_LoginDDT *****");

	}
}
