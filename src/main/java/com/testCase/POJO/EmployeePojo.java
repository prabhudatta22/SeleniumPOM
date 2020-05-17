/**
 * 
 */

package com.testCase.POJO;

/**
 * @author Prabhudatta.C
 *
 */
public class EmployeePojo {

	private String empname;
	private String taskGroup;
	private String project;
	private String task;
	private String billable;
	private String refId;
	private String status;
	private boolean isApprove;
	private WeeklyTimeEntry timesheetVal;

	public WeeklyTimeEntry getTimesheetVal() {
		return timesheetVal;
	}

	public void setTimesheetVal(WeeklyTimeEntry timesheetVal) {
		this.timesheetVal = timesheetVal;
	}

	public boolean isApprove() {
		return isApprove;
	}

	public void setApprove(boolean isApprove) {
		this.isApprove = isApprove;
	}

	public String getEmpname() {
		return empname;
	}

	public void setEmpname(String empname) {
		this.empname = empname;
	}

	public String getTaskGroup() {
		return taskGroup;
	}

	public void setTaskGroup(String taskGroup) {
		this.taskGroup = taskGroup;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getBillable() {
		return billable;
	}

	public void setBillable(String billable) {
		this.billable = billable;
	}

	public String getRefId() {
		return refId;
	}

	public void setRefId(String refId) {
		this.refId = refId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
