package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.annotations.Test;

public class HomePage extends BasePage{
	public HomePage(WebDriver driver) {
        super(driver); // ✅ Passes to BasePage, which sets it
    }

    public void testClickMyAccount() {
        driver.findElement(By.xpath("//span[text()='My Account']")).click(); // ✅ Uses inherited driver from BasePage
    }

    public void clickRegister() {
        driver.findElement(By.linkText("Register")).click();
    }
    
    public void clickLogin()
    {
    	driver.findElement(By.linkText("Login")).click();
    }
}
