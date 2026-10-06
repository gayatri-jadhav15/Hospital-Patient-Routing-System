package com.kiranAcademy.Hospital;

import java.util.ArrayList;
import java.util.List;

public class PatientValidator {
	public List<String> validatePatient(Patient patient) {

		List<String> errors = new ArrayList<>();

		// Patient Name
		if (patient.getPatientName() == null
				|| patient.getPatientName().trim().isEmpty()
				|| patient.getPatientName().trim().length() < 3) {

			errors.add("Invalid Patient Name");
		}

		// Age
		if (patient.getAge() < 0 || patient.getAge() > 120) {

			errors.add("Invalid Age");
		}

		// Gender
		if (!(patient.getGender().equalsIgnoreCase("Male")
				|| patient.getGender().equalsIgnoreCase("Female")
				|| patient.getGender().equalsIgnoreCase("Other"))) {

			errors.add("Invalid Gender");
		}

		// Disease
		if (patient.getDisease() == null
				|| patient.getDisease().trim().isEmpty()) {

			errors.add("Invalid Disease");
		}

		// Admission Type
		if (!(patient.getAdmissionType().equalsIgnoreCase("Emergency")
				|| patient.getAdmissionType().equalsIgnoreCase("Regular"))) {

			errors.add("Invalid Admission Type");
		}

		// Condition Status
		if (!(patient.getConditionStatus().equalsIgnoreCase("Critical")
				|| patient.getConditionStatus().equalsIgnoreCase("Moderate")
				|| patient.getConditionStatus().equalsIgnoreCase("Stable"))) {

			errors.add("Invalid Condition Status");
		}

		// Triage Score
		if (patient.getTriageScore() < 1
				|| patient.getTriageScore() > 10) {

			errors.add("Invalid Triage Score");
		}

		// Doctor Name
		if (patient.getDoctorName() == null
				|| patient.getDoctorName().trim().isEmpty()) {

			errors.add("Invalid Doctor Name");
		}

		// Mobile
		if (patient.getMobile() == null
				|| !patient.getMobile().matches("\\d{10}")) {

			errors.add("Invalid Mobile Number");
		}
        
		// Transfer Status
		if (patient.getTransferStatus() == null
				|| !patient.getTransferStatus().equalsIgnoreCase("PENDING")) {

			errors.add("Invalid Transfer Status");
		}
		return errors;
	}
	
}
