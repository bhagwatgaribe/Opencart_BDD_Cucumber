package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ForgetPasswordPage extends BasePage{

	public ForgetPasswordPage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//input[@name='email']")
	private WebElement txtEmail;
	
	@FindBy(xpath = "//button[@type='submit']")
	private WebElement btnContinue;
	
	public void setEmail(String email) {
		txtEmail.sendKeys(email);
	}
	
	public void clickOnContinue() {
		btnContinue.click();
	}
	
	public String getPageTitle() {
		return driver.getTitle();
	}
}
