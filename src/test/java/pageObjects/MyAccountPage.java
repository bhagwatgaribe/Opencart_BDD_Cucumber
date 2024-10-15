package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage {

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//h2[text()='My Account']") // MyAccount Page heading
	private WebElement msgHeading;

	@FindBy(xpath = "//a[@class='list-group-item'][normalize-space()='Logout']")
	private WebElement lnkLogout;

	public boolean isMyAccountPageExists() // MyAccount Page heading display status
	{
		try {
			return (msgHeading.isDisplayed());
		} catch (Exception e) {
			return (false);
		}
	}

	public void clickLogout() {
		lnkLogout.click();

	}

	public boolean isUserOnAccountDashboard() {
		String expectedUrl = "http://localhost/opencart/upload/index.php?route=account/account";
		return driver.getCurrentUrl().contains(expectedUrl);
	}

	public String getPageTitle() {
		return driver.getTitle();
	}
}
