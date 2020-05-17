Feature: Acceptance testing to validate Availability of Menus in Home Page 
	In order to validate that the Menus in Home Page are available we will do acceptance testing

 @BDDTest
Scenario: Validate Availability of Menus in Home Page 

	Given I am on the Home page "https://www.test.com/" of Home Page website
	When I move to test MenuOne
	Then I should see the items under MenuOne
	And the Menu Text should be "TEST ONE" on Menu 