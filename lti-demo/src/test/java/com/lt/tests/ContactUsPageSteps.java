package com.lt.tests;

import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lt.autotest.handlers.ActionHandler;
import com.lt.autotest.handlers.AssertHandler;
import com.lt.base.TestBase;
import com.lt.constants.Constants;
import com.lt.containers.ContactUsPageContainer;
import com.lt.utilities.CommonFunctions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ContactUsPageSteps extends TestBase {

	private LoginPage loginPage = new LoginPage();
	public CommonFunctions commonFunctions=new CommonFunctions();
	public WebDriver driver = getDriver();
	private ContactUsPageContainer contactUsPageContainer = PageFactory.initElements(driver,
			ContactUsPageContainer.class);
	private static final Logger LOGGER = LoggerFactory.getLogger(ContactUsPageSteps.class.getName());

	@When("User navigate to contact us page")
	public void user_navigate_to_contact_us_page() {
		ActionHandler.click(contactUsPageContainer.contactUsLink);
		ActionHandler.wait(2);
	}

	@When("User send message with contact details")
	public void user_send_message_with_contact_details_and(DataTable credTable) {
		List<Map<String, String>> credList = credTable.asMaps();
		String subject = credList.get(0).get("Subject");
		String emailId = credList.get(0).get("Email");
		String message = credList.get(0).get("Message");

		Select sel= new Select(contactUsPageContainer.subjectSelect);
		sel.selectByVisibleText(subject);
		
		ActionHandler.setText(contactUsPageContainer.emailTxt, emailId);
		ActionHandler.setText(contactUsPageContainer.messageArea, message);
		ActionHandler.click(contactUsPageContainer.submitBtn);
		
	}

	@Then("Verify success message")
	public void verify_success_message() {
		AssertHandler.assertTextEqualsOnElement(contactUsPageContainer.successMsgAlert, Constants.CONTACTUSSUCCESSMSG);
	}

}
