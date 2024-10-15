package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

	public HomePage(WebDriver driver) {
		super(driver);
	}
	
	@FindBy(xpath = "//span[normalize-space()='My Account']")
	private WebElement lnkMyAccount;
	
	@FindBy(xpath = "//a[@class='dropdown-item'][normalize-space()='Register']")
	private WebElement lnkRegister;
	
	@FindBy(linkText = "Login")
	private WebElement lnkLogin;
	
	public void clickOnMyAccount() {
		lnkMyAccount.click();
	}
	
	public void clickOnRegister() {
		lnkRegister.click();
	}
	
	public void clickOnLogin() {
		lnkLogin.click();
	}
}
