package com.framework.customlisteners;

import java.util.Arrays;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.framework.setup.TestSetUp;

public class CustomeListeners extends TestSetUp implements ITestListener, ISuiteListener {

	public void onFinish(ISuite arg0) {
	}

	public void onStart(ISuite arg0) {
	}

	public void onFinish(ITestContext arg0) {
	}

	public void onStart(ITestContext arg0) {
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
	}

	public void onTestFailure(ITestResult arg0) {

		String excepionMessage = arg0.getThrowable().getMessage();

		String excepionTrace = Arrays.toString(arg0.getThrowable().getStackTrace());
		testCaseLogger.get()
				.fail("<details>" + "<summary style='cursor: pointer;'>" + "<b>" + "<font color=" + "red>"
						+ "Exception Occured: Click to see" + "</font>" + "</b >" + "</summary>"
						+ excepionMessage.replaceAll(",", "<br>") + "<br>" + excepionTrace.replaceAll(",", "<br>")
						+ "</details>" + " \n");

		String failureLogg = "This Test case got Failed";
		Markup m = MarkupHelper.createLabel(failureLogg, ExtentColor.RED);
		testCaseLogger.get().log(Status.FAIL, m);

	}

	public void onTestSkipped(ITestResult arg0) {
		String methodName = arg0.getMethod().getMethodName();
		String logText = "<b>" + "<font color=blue>" + "Test Case:- " + methodName + " Skipped." + "</font></b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
		testCaseLogger.get().skip(m);
		// extent.flush();

	}

	public void onTestStart(ITestResult arg0) {
		ExtentTest child = classLevelExtentTest.get().createNode(arg0.getMethod().getMethodName());
		testCaseLogger.set(child);
		testCaseLogger.get().log(Status.INFO,
				"Execution of Test case- <b>" + arg0.getMethod().getMethodName() + "</b> started.");
	}

	public void onTestSuccess(ITestResult arg0) {

		String methodName = arg0.getMethod().getMethodName();
		String logText = "<b>" + "Test Case:- " + methodName + " Passed" + "</b>";
		Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
		testCaseLogger.get().pass(m);
		// extent.flush();
	}

}