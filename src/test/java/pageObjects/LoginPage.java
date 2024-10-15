package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

	public LoginPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//input[@id='input-email']")
	private WebElement txtEmail;

	@FindBy(xpath = "//input[@name='password']")
	private WebElement txtPassword;

	@FindBy(xpath = "//button[contains(text(),'Login')]")
	private WebElement btnLogin;

	@FindBy(xpath = "//div[@id='alert']")
	public WebElement errorMessage;

	@FindBy(xpath = "//div[@class='mb-3']//child::a[normalize-space()='Forgotten Password']")
	private WebElement lnkForgetPassword;
	
	@FindBy(xpath = "//div[contains(text(),'Success: Your password has been successfully updated.')]")
	private WebElement successMsg;
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}

	public void setPassword(String pwd) {
		txtPassword.sendKeys(pwd);
	}

	public void clickOnLogin() {
		btnLogin.click();
	}

	public String getErrorMessage() {
		return errorMessage.getText().trim();
	}

	public boolean isErrorMessageDisplayed() {
		return errorMessage.isDisplayed();
	}
	
	public void clickOnForgetPassword() {
		lnkForgetPassword.click();
	}
	
	public String getSuccessMsg() {
		return successMsg.getText().trim();
	}
}
