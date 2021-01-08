package com.lt.containers;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class ContactUsPageContainer {

	@FindBy(how = How.LINK_TEXT, using = "Contact us")
	public static WebElement contactUsLink;

	
	@FindBy(how = How.ID, using = "uniform-id_contact")
	public static WebElement subjectSelectBtn;
	
	@FindBy(how = How.ID, using = "id_contact")
	public static WebElement subjectSelect;
	
	@FindBy(how = How.ID, using = "email")
	public static WebElement emailTxt;
	
	@FindBy(how = How.ID, using = "submitMessage")
	public static WebElement submitBtn;
	
	@FindBy(how = How.ID, using = "message")
	public static WebElement messageArea;
	
	@FindBy(how = How.XPATH, using = "//p[@class='alert alert-success']")
	public static WebElement successMsgAlert;
}
