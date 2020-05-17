/**
 * 
 */
package com.internal.page;

import java.util.Hashtable;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import com.framework.testutils.Constants;
import com.framework.testutils.DriverManager;
import com.framework.ui.pageobjects.BasePage;

/**
 * @author Prabhudatta.C
 *
 */
public class OpportunityPage extends BasePage {

	@FindBy(xpath = ".//*[@type='email']")
	private WebElement signEmail;

	@FindBy(xpath = ".//*[@type='submit']")
	private WebElement submit;

	@FindBy(xpath = ".//*[@type='password']")
	private WebElement password;

	@FindBy(id = "dashboardSelectorLink")
	private WebElement sales_activity_social_dashboard;

	@FindBy(id = "TabSFA")
	private WebElement salesPointer;

	@FindBy(xpath = ".//*[@title='Opportunities']")
	private WebElement opportunityLinkUpBar;

	@FindBy(id = "opportunity|NoRelationship|HomePageGrid|Mscrm.HomepageGrid.opportunity.NewRecord")
	private WebElement newOpportunityBtn;

	@FindBy(id = "header_crmFormSelector")
	private WebElement opportunityForm;

	@FindBy(id = "formselectorcontainer")
	private WebElement opportunityFormContainer;

	@FindBy(id = "formSelectorDropdown")
	private WebElement formselectoDropdownr;

	@FindBy(xpath = ".//*[@role='menuitem']")
	private List<WebElement> forms;

	@FindBy(id = "tasales_rfpdropdown")
	private WebElement RFP;

	@FindBy(id = "tasales_rfpdropdown_i")
	private WebElement rfpSelect;

	@FindBy(id = "tasales_topic")
	private WebElement topic;

	@FindBy(id = "tasales_typeofbidding")
	private WebElement biddingType;

	@FindBy(id = "tasales_typeofbidding_IMenu")
	private WebElement biddingTypeSelect;

	@FindBy(id = "tasales_deliverylocation")
	private WebElement deliveryLocation;

	@FindBy(id = "tasales_deliverylocation_i")
	private WebElement deliveryLocationSelect;

	@FindBy(id = "tasales_salesregion")
	private WebElement salesRegion;

	@FindBy(id = "tasales_salesregion_IMenu")
	private WebElement salesRegionSelect;

	@FindBy(id = "parentaccountid")
	private WebElement account;

	@FindBy(id = "parentaccountid_IMenu")
	private WebElement accountSelect;

	@FindBy(id = "parentcontactid")
	private WebElement contact;

	@FindBy(id = "parentcontactid_IMenu")
	private WebElement contactSelect;

	@FindBy(id = "purchasetimeframe")
	private WebElement purchaseTimeFrame;

	@FindBy(id = "purchasetimeframe_i")
	private WebElement purchaseTimeFrameSelect;

	@FindBy(id = "tasales_forecaserating")
	private WebElement forcasteRating;

	@FindBy(id = "tasales_forecaserating_i")
	private WebElement forcasteRatingSelect;

	@FindBy(id = "tasales_areaofinterest")
	private WebElement areaOfInterest;

	@FindBy(id = "tasales_areaofinterest_i")
	private WebElement areaOfInterestSelect;

	@FindBy(id = "opportunity|NoRelationship|Form|Mscrm.Form.opportunity.Save")
	private WebElement saveBtn;

	@FindBy(id = "opportunity|NoRelationship|Form|Mscrm.Form.opportunity.SaveAndClose")
	private WebElement saveCloseBtn;

	@FindBy(id = "msdyn_contractorganizationalunitid")
	private WebElement contractingUnit;

	@FindBy(id = "msdyn_contractorganizationalunitid_IMenu")
	private WebElement contractingUnitSelect;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.framework.ui.pageobjects.BasePage#getPageLoadCondition()
	 */
	@Override
	public ExpectedCondition getPageLoadCondition() {
		return ExpectedConditions
				.visibilityOf(DriverManager.getDriver().findElement(By.className(Constants.OR_FOOTER_DIV2)));
	}

	public OpportunityPage getOpportunityPage() {

		return (OpportunityPage) openPage(OpportunityPage.class);
	}

	/**
	 * @param data
	 */
	public void createNewOpportunity(Hashtable<String, String> data) {

		click(salesPointer, "Sales pointer");
		click(opportunityLinkUpBar, "Opportunity link");

		click(newOpportunityBtn, "New opportunity");

		System.out.println("opportunity form selection");
		jsClick(opportunityForm, "opportunityForm");
		jsClick(formselectoDropdownr, "formselectoDropdownr");
		selectListBox(formselectoDropdownr, "1", "Index");

		System.out.println("RFP selection");
		click(RFP, "RFP");
		selectListBox(rfpSelect, "Yes", "ByVisibleText");

		System.out.println("Topic selection");
		sendKeys(topic, data.get("Topic"), "Topic added");

		System.out.println("Bidding type selection");
		click(biddingType, "Bidding type");
		selectListBox(biddingTypeSelect, "Fixed Bid", "ByVisibleText");

		System.out.println("Delivery Location selection");
		click(deliveryLocation, "deliveryLocation");
		selectListBox(deliveryLocationSelect, "US", "ByVisibleText");

		System.out.println("Sales Region selection");
		click(salesRegion, "salesRegion");
		selectListBox(salesRegionSelect, "US", "ByVisibleText");

		System.out.println("account form selection");
		sendKeys(account, "Adobe*", "Account");
		selectListBox(accountSelect, "Adobe Systems", "ByVisibleText");

		System.out.println("contact form selection");
		click(contact, "contact");
		selectListBox(contactSelect, "1", "index");

		System.out.println("time frame form selection");
		click(purchaseTimeFrame, "purchaseTimeFrame");
		selectListBox(purchaseTimeFrameSelect, "0", "index");

		System.out.println("forecasting form selection");
		click(forcasteRating, "forcasteRating");
		selectListBox(forcasteRatingSelect, "0", "index");

		System.out.println("area of interest form selection");
		click(areaOfInterest, "area Of Interest");
		selectListBox(areaOfInterestSelect, "0", "index");

		click(saveBtn, "Save button");

		System.out.println("Project information form select");
		selectListBox(formselectoDropdownr, "0", "Index");

		System.out.println("contract unit form selection");
		click(contractingUnit, "contractingUnit");
		selectListBox(contractingUnitSelect, "1", "index");

		click(saveBtn, "Save button");
		click(saveCloseBtn, "saveCloseBtn");

	}

	/**
	 * @param data
	 */
	public void login(Hashtable<String, String> data) {
		OpportunityPage opage = new OpportunityPage().getOpportunityPage();
		getPageLoadCondition(opage.signEmail);
		sendKeys(opage.signEmail, data.get("Username"), "Username");
		click(opage.submit, "Next Button");
		sendKeys(opage.password, data.get("password"), "password");
		click(opage.submit, "Login");
		click(opage.submit, "Login");
		click(opage.submit, "yes");

	}

}
