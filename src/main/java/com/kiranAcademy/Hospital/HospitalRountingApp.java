package com.kiranAcademy.Hospital;

import java.util.List;

public class HospitalRountingApp {
    public static void main(String[] args) {
    	PatientDAO dao = new PatientDaoImple();

    	List<Patient> list = dao.getPendingPatients();

    	for(Patient p : list) {

    		System.out.println("Id : " + p.getPatientId());
    		System.out.println("Name : " + p.getPatientName());
    		System.out.println("Age : " + p.getAge());
    		System.out.println("Disease : " + p.getDisease());

    		System.out.println("---------------------");
    	}
	
    
    	PatientRountingService service =
    			new PatientRountingService();

    	service.processPatients();
}
}