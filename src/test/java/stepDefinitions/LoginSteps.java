package stepDefinitions;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import factory.BaseClass;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.MyAccountPage;
import pageObjects.ForgetPasswordPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.DataReader;

public class LoginSteps {

	WebDriver driver;
	HomePage homepage = new HomePage(BaseClass.getDriver());
	LoginPage loginpage = new LoginPage(BaseClass.getDriver());;
	MyAccountPage accountpage = new MyAccountPage(BaseClass.getDriver());
	ForgetPasswordPage fpp = new ForgetPasswordPage(BaseClass.getDriver());

	List<HashMap<String, String>> datamap; // Data driven

	WebDriverWait wait = new WebDriverWait(BaseClass.getDriver(), Duration.ofSeconds(5));

	@Given("the user is on the OpenCart home page")
	public void the_user_is_on_the_open_cart_home_page() {
		BaseClass.getLogger().info("User on home page.. ");
	}

	@Given("the user navigates to the login page")
	public void the_user_navigates_to_the_login_page() {
		BaseClass.getLogger().info("Goto my account --> Click on Login.. ");
		homepage.clickOnMyAccount();
		homepage.clickOnLogin();

	}

	@When("the user enters a valid email {string} and password {string}")
	public void the_user_enters_a_valid_email_and_password(String email, String pwd) {
		BaseClass.getLogger().info("Entering email and password.. ");
		loginpage.setEmail(email);
		loginpage.setPassword(pwd);
	}

	@When("clicks on the Login button")
	public void clicks_on_the_login_button() {
		loginpage.clickOnLogin();
		BaseClass.getLogger().info("clicked on login button...");
	}

	@Then("the user should see a page title as {string}")
	public void the_user_should_see_a_page_title_as(String expectedTitle) {
		wait.until(ExpectedConditions.titleIs(expectedTitle));

		String actualTitle = accountpage.getPageTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}

	@When("the user enters an invalid email {string} and password {string}")
	public void the_user_enters_an_invalid_email_and_password(String email, String password) {
		loginpage.setEmail(email);
		loginpage.setPassword(password);
	}

	@Then("the user should see an error message {string}")
	public void the_user_should_see_an_error_message(String expectedErrorMessage) {
		wait.until(ExpectedConditions.visibilityOf(loginpage.errorMessage));

		String actualErrorMessage = loginpage.getErrorMessage();
		Assert.assertTrue("Error message not found!", actualErrorMessage.contains(expectedErrorMessage));
	}

	@When("the user leaves the email and password fields empty")
	public void theUserLeavesFieldsEmpty() {
		loginpage.setEmail("");
		loginpage.setPassword("");
	}

	@Then("the user should see a warning message {string}")
	public void theUserShouldSeeAWarningMessage(String expectedErrorMessage) {
		wait.until(ExpectedConditions.visibilityOf(loginpage.errorMessage));

		String actualErrorMessage = loginpage.getErrorMessage();
		Assert.assertTrue("Error message not found!", actualErrorMessage.contains(expectedErrorMessage));
	}

	@When("the user clicks on the {string} link")
	public void the_user_clicks_on_the_link(String link) {
		loginpage.clickOnForgetPassword();
	}

	@Then("the user should see page title {string}")
	public void the_user_should_see_page_title(String expectedTitle) {
		wait.until(ExpectedConditions.titleIs(expectedTitle));

		String actualTitle = fpp.getPageTitle();
		Assert.assertEquals(expectedTitle, actualTitle);
	}

	@Then("the user enters their registered email {string}")
	public void the_user_enters_their_registered_email(String email) {
		fpp.setEmail(email);
	}

	@Then("clicks on the Continue button")
	public void clicks_on_the_continue_button() {
		fpp.clickOnContinue();
	}

	@Then("the user should see a confirmation message {string}")
	public void the_user_should_see_a_confirmation_message(String successMessage) {
		String actualMsg = loginpage.getSuccessMsg();
		Assert.assertEquals(actualMsg, successMessage);
	}

	// ******* Data Driven test **************
	@Then("the user should be redirected to the MyAccount Page by passing email and password with excel row {string}")
	public void check_user_navigates_to_my_account_page_by_passing_email_and_password_with_excel_data(String rows) {
		try {
			datamap = DataReader.data(System.getProperty("user.dir") + "\\testData\\Opencart_LoginData.xlsx", "Sheet1");
		} catch (IOException e) {
			e.printStackTrace();
		}

		int index = Integer.parseInt(rows) - 1;
		String email = datamap.get(index).get("username");
		String pwd = datamap.get(index).get("password");
		String exp_res = datamap.get(index).get("res");
		loginpage.setEmail(email);
		loginpage.setPassword(pwd);

		loginpage.clickOnLogin();

		try {
			boolean targetpage = accountpage.isMyAccountPageExists();
			System.out.println("target page: " + targetpage);
			if (exp_res.equalsIgnoreCase("Valid")) {
				if (targetpage == true) {
					//accountpage.clickLogout();
					Assert.assertTrue(true);
				} else {
					Assert.assertTrue(false);
				}
			}

			if (exp_res.equalsIgnoreCase("Invalid")) {
				if (targetpage == true) {
					//accountpage.clickLogout();
					Assert.assertTrue(false);
				} else {
					Assert.assertTrue(true);
				}
			}

		} catch (Exception e) {

			Assert.assertTrue(false);
		}
	}

}
