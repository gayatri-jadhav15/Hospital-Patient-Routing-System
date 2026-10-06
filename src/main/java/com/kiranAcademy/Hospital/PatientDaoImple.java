package com.kiranAcademy.Hospital;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PatientDaoImple implements PatientDAO {
	Connection con = DB_Connection.getConnection();
	@Override
	public List<Patient> getPendingPatients() {

		List<Patient> patientList = new ArrayList<>();

		try {

			String sql =
				"select * from hospital_patient_intake where transfer_status='PENDING'";

			PreparedStatement ps = con.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();

			while(rs.next()) {

				Patient patient = new Patient();

				patient.setPatientId(rs.getInt("patient_id"));
				patient.setPatientName(rs.getString("patient_name"));
				patient.setAge(rs.getInt("age"));
				patient.setGender(rs.getString("gender"));
				patient.setDisease(rs.getString("disease"));
				patient.setAdmissionType(rs.getString("admission_type"));
				patient.setConditionStatus(rs.getString("condition_status"));
				patient.setTriageScore(rs.getInt("triage_score"));
				patient.setDoctorName(rs.getString("doctor_name"));
				patient.setAdmissionDate(rs.getDate("admission_date"));
				patient.setMobile(rs.getString("mobile"));
				patient.setTransferStatus(rs.getString("transfer_status"));

				patientList.add(patient);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return patientList;
	}

	@Override
	public void insertCriticalPatient(Patient patient, Connection con) {
		try {

				String sql =
					"insert into critical_care_patients "
					+ "(source_patient_id,patient_name,age,disease,"
					+ "admission_type,condition_status,triage_score) "
					+ "values(?,?,?,?,?,?,?)";

				PreparedStatement ps =
						con.prepareStatement(sql);

				ps.setInt(1, patient.getPatientId());
				ps.setString(2, patient.getPatientName());
				ps.setInt(3, patient.getAge());
				ps.setString(4, patient.getDisease());
				ps.setString(5, patient.getAdmissionType());
				ps.setString(6, patient.getConditionStatus());
				ps.setInt(7, patient.getTriageScore());

				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	

		@Override
		public void insertGeneralPatient(Patient patient, Connection con) {

			try {

				String sql =
					"insert into general_care_patients "
					+ "(source_patient_id,patient_name,age,disease,"
					+ "admission_type,condition_status,triage_score) "
					+ "values(?,?,?,?,?,?,?)";

				PreparedStatement ps = con.prepareStatement(sql);

				ps.setInt(1, patient.getPatientId());
				ps.setString(2, patient.getPatientName());
				ps.setInt(3, patient.getAge());
				ps.setString(4, patient.getDisease());
				ps.setString(5, patient.getAdmissionType());
				ps.setString(6, patient.getConditionStatus());
				ps.setInt(7, patient.getTriageScore());

				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	

		@Override
		public void markProcessed(int patientId, Connection con) {

			try {

				String sql =
					"update hospital_patient_intake "
					+ "set transfer_status='PROCESSED', "
					+ "processed_at=NOW() "
					+ "where patient_id=?";

				PreparedStatement ps =
						con.prepareStatement(sql);

				ps.setInt(1, patientId);

				ps.executeUpdate();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		@Override
		public boolean alreadyTransferred(int patientId, Connection con) {

			try {

				String sql1 =
					"select * from critical_care_patients "
					+ "where source_patient_id=?";

				PreparedStatement ps1 =
						con.prepareStatement(sql1);

				ps1.setInt(1, patientId);

				ResultSet rs1 = ps1.executeQuery();

				if (rs1.next()) {
					return true;
				}

				String sql2 =
					"select * from general_care_patients "
					+ "where source_patient_id=?";

				PreparedStatement ps2 =
						con.prepareStatement(sql2);

				ps2.setInt(1, patientId);

				ResultSet rs2 = ps2.executeQuery();

				if (rs2.next()) {
					return true;
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			return false;
		}
}
