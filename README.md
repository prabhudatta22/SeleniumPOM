# SeleniumPOM
------------------------------------------------------------------------------------------------------------------------------
Descritption: 

This Framework is based on Java + Selenium. This is purely based on POM. It can execute below type of tests 

			1. UI Regression using POM
			2. Rest API test cases
			3. Visual Regression using pixel-to-pxel comparision
			4. BDD cucumber 
			5. Jemeter scripts for load testing
			
All these tests are driven by maven on profile based. The test can be executed via SeleniumGRID and on desktop browser. All the tests can be executed in multi browser and multi thread way. 
---------------------------------------------------------------------------------------------------------------------------------

Reporting: 

Extent and extentX report has been configured. For local configuration of ExtentX please refer to http://extentreports.com/docs/extentx/

-----------------------------------------------------------------------------------------------------------------------------------
 Usage:
 
 Download the code. and follow below instruction to use it.
 
 1. Create test class under src/test/java in your package. Your test class must extend TestSetup class.
 2. Create corresponding page class in src/main/java in your package. You page classs must extend BasePage class
 3. You need to pass the events to extent testcaselogger to get it recorded in report
 
 after this, modify the information accordingly in proerties file to suite your requirement.
 
 Then run the below command to execute the test cases.
 
 for UI Regression test cases use below:
 
 mvn integration-test -Denv=./src/test/resources/config/config.properties -Dtype=POM -P POM -DtestSuite=./src/test/resources/runner/UI.xml 
 
 -------------------------------------------------------------------------------------------------------------------------------------------------
 