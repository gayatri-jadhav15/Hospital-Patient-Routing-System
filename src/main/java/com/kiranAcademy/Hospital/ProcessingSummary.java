package com.kiranAcademy.Hospital;

public class ProcessingSummary {
	private int totalPending;
	private int criticalCount;
	private int generalCount;
	private int validationFailed;
	private int duplicateCount;
	private int successCount;

	public int getTotalPending() {
		return totalPending;
	}

	public void setTotalPending(int totalPending) {
		this.totalPending = totalPending;
	}

	public int getCriticalCount() {
		return criticalCount;
	}

	public void setCriticalCount(int criticalCount) {
		this.criticalCount = criticalCount;
	}

	public int getGeneralCount() {
		return generalCount;
	}

	public void setGeneralCount(int generalCount) {
		this.generalCount = generalCount;
	}

	public int getValidationFailed() {
		return validationFailed;
	}

	public void setValidationFailed(int validationFailed) {
		this.validationFailed = validationFailed;
	}

	public int getDuplicateCount() {
		return duplicateCount;
	}

	public void setDuplicateCount(int duplicateCount) {
		this.duplicateCount = duplicateCount;
	}

	public int getSuccessCount() {
		return successCount;
	}

	public void setSuccessCount(int successCount) {
		this.successCount = successCount;
	}
}
