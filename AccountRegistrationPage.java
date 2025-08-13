package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

    public AccountRegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "input-firstname")
    WebElement txtFirstName;

    @FindBy(id = "input-lastname")
    WebElement txtLastName;

    @FindBy(id = "input-email")
    WebElement txtEmail;

    @FindBy(id = "input-telephone") // ✅ Telephone
    WebElement txtTelephone;

    @FindBy(id = "input-password")  // ✅ Password
    WebElement txtPassword;

    @FindBy(id = "input-confirm")   // ✅ Confirm Password
    WebElement txtConfirmPassword;

    @FindBy(name = "agree")         // ✅ Checkbox for terms
    WebElement chkPolicy;

    @FindBy(xpath = "//input[@value='Continue']") // ✅ Continue button
    WebElement btnContinue;

    @FindBy(xpath = "//p[contains(text(),'Congratulations! Your new account has been success')]")
    WebElement msgConfirmation;

    // ----- Actions -----

    public void setFirstName(String fname) {
        txtFirstName.sendKeys(fname);
    }

    public void setLastName(String lname) {
        txtLastName.sendKeys(lname);
    }

    public void setEmail(String email) {
        txtEmail.sendKeys(email);
    }

    public void setTelephone(String tel) {
        txtTelephone.sendKeys(tel);
    }

    public String setPass(String pass) {
        txtPassword.sendKeys(pass);
        txtConfirmPassword.sendKeys(pass); // ✅ auto-set confirm password same as password
		return pass;
    }

    public void setPrivacyPolicy() {
        chkPolicy.click();
    }

    public void clickContinue() {
        btnContinue.click();
    }

    public String getConfirmationMsg() {
        return msgConfirmation.getText();
    }
}
