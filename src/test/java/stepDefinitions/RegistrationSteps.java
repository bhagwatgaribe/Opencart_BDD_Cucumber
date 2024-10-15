package stepDefinitions;

import java.util.Map;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import factory.BaseClass;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.RegistrationPage;

public class RegistrationSteps {
	
	WebDriver driver;
	HomePage hp = new HomePage(BaseClass.getDriver());
	RegistrationPage rp = new RegistrationPage(BaseClass.getDriver());
	
	@Given("the user navigates to Register Account page")
	public void the_user_navigates_to_register_account_page() {
	    hp.clickOnMyAccount();
	    hp.clickOnRegister();
	}

	@When("the user enters the details into below fields")
	public void the_user_enters_the_details_into_below_fields(DataTable dataTable) {
		Map<String, String> dataMap = dataTable.asMap(String.class,String.class);
	    
		rp.setFirstName(dataMap.get("firstName"));
		rp.setLastName(dataMap.get("lastName"));
		rp.setEmail(BaseClass.randomAlphaNumeric()+"@gmail.com");
		rp.setPassword(dataMap.get("password"));
	}

	@When("the user selects Privacy Policy")
	public void the_user_selects_privacy_policy() {
	    rp.setPrivacyPolicy();
	}

	@When("the user clicks on Continue button")
	public void the_user_clicks_on_continue_button() {
	    rp.clickContinue();
	}

	@Then("the user account should get created successfully")
	public void the_user_account_should_get_created_successfully() {
	    String confmsg = rp.getConfirmationMsg();
	    Assert.assertEquals(confmsg, "Your Account Has Been Created!");
	}
}
