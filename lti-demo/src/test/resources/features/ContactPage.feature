Feature: Contact Us Page

@Contactus @ETE_1
Scenario: Verifying Customer Service Message
	Given User is on home page
	When User navigate to contact us page
	And User send message with contact details
	|Subject|Message|Email|
	|Customer service|new meesage|sa@gmail.com|
	Then Verify success message 
	