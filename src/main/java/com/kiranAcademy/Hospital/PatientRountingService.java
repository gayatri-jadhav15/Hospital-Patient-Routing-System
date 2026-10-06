package com.kiranAcademy.Hospital;

import java.sql.Connection;
import java.util.List;

public class PatientRountingService {
	public boolean isCritical(Patient patient) {

		// Rule 1
		if (patient.getConditionStatus().equalsIgnoreCase("Critical")) {
			return true;
		}

		// Rule 2
		if (patient.getAdmissionType().equalsIgnoreCase("Emergency")
				&& patient.getTriageScore() >= 7) {
			return true;
		}

		// Rule 3
		if (patient.getConditionStatus().equalsIgnoreCase("Moderate")
				&& patient.getTriageScore() >= 8) {
			return true;
		}

		return false;
	}
	
	public void processPatients() {
		PatientDAO dao = new PatientDaoImple();

		PatientValidator validator = new PatientValidator();
		ProcessingSummary summary =
				new ProcessingSummary();
		
		Connection con = DB_Connection.getConnection();

		List<Patient> patients = dao.getPendingPatients();
		summary.setTotalPending(
				patients.size());
		for (Patient patient : patients) {

			List<String> errors =
					validator.validatePatient(patient);

			if (!errors.isEmpty()) {
				summary.setValidationFailed(
						summary.getValidationFailed() + 1);
				System.out.println(
					"Validation Failed : "
					+ patient.getPatientName());

				continue;
			}

			if (dao.alreadyTransferred(
					patient.getPatientId(), con)) {
				summary.setDuplicateCount(
						summary.getDuplicateCount() + 1);
				System.out.println(
					"Already Transferred : "
					+ patient.getPatientName());

				continue;
			}

			if (isCritical(patient)) {
				summary.setCriticalCount(
						summary.getCriticalCount() + 1);
				dao.insertCriticalPatient(
						patient, con);

				System.out.println(
					patient.getPatientName()
					+ " -> Critical Care");

			} else {

				dao.insertGeneralPatient(
						patient, con);
				summary.setGeneralCount(
						summary.getGeneralCount() + 1);
				System.out.println(
					patient.getPatientName()
					+ " -> General Care");
			}

			dao.markProcessed(
					patient.getPatientId(), con);
			summary.setSuccessCount(
					summary.getSuccessCount() + 1);
		}
	
	System.out.println("\n========== SUMMARY ==========");

	System.out.println(
			"Total Pending : "
					+ summary.getTotalPending());

	System.out.println(
			"Critical Count : "
					+ summary.getCriticalCount());

	System.out.println(
			"General Count : "
					+ summary.getGeneralCount());

	System.out.println(
			"Validation Failed : "
					+ summary.getValidationFailed());

	System.out.println(
			"Duplicate Count : "
					+ summary.getDuplicateCount());

	System.out.println(
			"Success Count : "
					+ summary.getSuccessCount());

	System.out.println("=============================");
	}
}
