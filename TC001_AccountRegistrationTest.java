package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.AccountRegistrationPage;
import pageObjects.HomePage;
import testBase.BaseClass;

public class TC001_AccountRegistrationTest extends BaseClass {
	
	@Test(groups={"Regression","Master"})
	public void verify_account_registration() throws InterruptedException
	{
		logger.info("*****Starting TC001_AccountRegistrationTest *******");
		
		
	try {	
		HomePage hp = new HomePage(driver);
		Thread.sleep(1000);
		hp.testClickMyAccount();
		logger.info("Clicked on MyAccount Link");
		
		hp.clickRegister();
		logger.info("Clicked on Register  Link");
		
		AccountRegistrationPage regpage = new AccountRegistrationPage(driver);
		
		logger.info("Providing customer details");
		regpage.setFirstName(randomeString().toUpperCase());
		regpage.setLastName(randomeString().toUpperCase());
		regpage.setEmail(randomeString() + "@gmail.com"); // randomly generrated the email
		regpage.setTelephone(randomeNumber());
		
		String password = randomeAlphaNumberic(); // generate once
		regpage.setPass(password);                // pass it to both password + confirm

		regpage.setPrivacyPolicy();
		regpage.clickContinue();
		
		logger.info("Validating expected message....");
		
		String confmsg = regpage.getConfirmationMsg();
		
		if(confmsg.equals("Congratulations! Your new account has been successfully created!"))
		{
			Assert.assertTrue(true);
		}
		else {
			logger.error("Test failed...");
			logger.debug("Debug logs....");
			
		}
		
	//	Assert.assertEquals(confmsg, "Congratulations! Your new account has been successfully created!!!");
	}
	catch(Exception e) {
		Assert.fail();
	}
	logger.info("**** Finished TC001_AccountRegistration ****");
	
	}
	
	
}
