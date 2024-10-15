Feature: User Login

  As a registered user of the OpenCart website,
  I want to be able to log in to my account
  so that I can access my account dashboard and make purchases.

  Background:
    Given the user is on the OpenCart home page

	@Sanity @Regression
  Scenario: Successful login with valid credentials
    Given the user navigates to the login page
    When the user enters a valid email "bhagwat22garibe@gmail.com" and password "Bhag@123"
    And clicks on the Login button
    Then the user should see a page title as "My Account"
	
	@Regression
  Scenario: Unsuccessful login with invalid credentials
    Given the user navigates to the login page
    When the user enters an invalid email "varsha@gmail.com" and password "varsha123"
    And clicks on the Login button
    Then the user should see an error message "Warning: No match for E-Mail Address and/or Password."
    
	@Regression
  Scenario: Unsuccessful login with empty fields
    Given the user navigates to the login page
    When the user leaves the email and password fields empty
    And clicks on the Login button
    Then the user should see a warning message "Warning: No match for E-Mail Address and/or Password."
    
	@Regression
  Scenario: Forgotten password flow
    Given the user navigates to the login page
    When the user clicks on the "Forgotten Password" link
    Then the user should see page title "Forgot Your Password?"
    And the user enters their registered email "bhagwat22garibe@gmail.com"
    And clicks on the Continue button
    Then the user should see a confirmation message "Success: Your password has been successfully updated."