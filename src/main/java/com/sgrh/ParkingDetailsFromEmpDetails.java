package com.sgrh;

import com.sgrh.entities.EmpDetail;
import com.sgrh.entities.ParkingDetails;

public class ParkingDetailsFromEmpDetails {
	public static ParkingDetails convertToParkingDetails(EmpDetail emp) {
		System.out.println(emp);
		ParkingDetails pDetails = new ParkingDetails();
		if(emp != null) {
			String name = emp.getFName().concat(" ".concat(emp.getLName()));
			pDetails.setEmpcode(emp.getEmpcode());
			pDetails.setName(name);
			//pDetails.setCategory(emp.getCategory());
			pDetails.setAddress(emp.getAddress());
			pDetails.setMobileNo(emp.getMobileNo());
		}
		return pDetails;
	}
}
