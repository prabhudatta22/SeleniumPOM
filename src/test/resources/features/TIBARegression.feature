Feature: Login Functionality Feature

Scenario: Validate Availability of Menus in Home Page 

	Given I am on the Home page "https://qahrm.techaspect.com/symfony/web/index.php/dashboard" of Home Page website
	When user logs in using Username as “techadmin” and Password “TechAspectTiBa&$1234”
	Then I should see the items under MenuOne
	And the Menu Text should be "TEST ONE" on Menu 
