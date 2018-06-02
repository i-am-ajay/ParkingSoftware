package com.sgrh.dbo;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.ParameterMode;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.query.Query;
import org.hibernate.result.ResultSetOutput;

import com.sgrh.entities.Category;
import com.sgrh.entities.ParkingDetails;
import com.sgrh.entities.User;

public class ParkingDbo {
	SessionFactory factory;
	
	public ParkingDbo() {
		setup();
	}
	
	public void setup() {
		try {
			StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
			factory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	public List<String> getCategory() {
		Session session = factory.openSession();
		session.beginTransaction();
		Query<String> query = session.createQuery("SELECT category FROM "+
				"Category ORDER BY category",String.class);
		List<String> categoryList = query.getResultList();
		session.close();
		return categoryList;
	}
	
	// add parking recrode in ParkingDetail table -> Database: SGRHEMP_Parking, SERVER:10S.
	public void addRecord(ParkingDetails pd) throws Exception {
		Session session = factory.openSession();
		Transaction trx = session.beginTransaction();
		String passNo1 = pd.getPassNo();
		if(passNo1 != null) {
			System.out.println("Pass No"+pd.getEmpcode());
			try {
				// If record exists then fetch it. Using passNo.
				Query<ParkingDetails> parkingD = session.createQuery("FROM ParkingDetails WHERE passNo = :pass",ParkingDetails.class);
				parkingD.setParameter("pass",passNo1.trim());
				ParkingDetails parkingDetails = parkingD.getSingleResult();
				
				// update the values of object.
				parkingDetails.setName(pd.getName());
				if(pd.getCategory() != null) {
					parkingDetails.setCategory(pd.getCategory());
				}
				parkingDetails.setAddress(pd.getAddress());
				parkingDetails.setcMake(pd.getcMake());
				parkingDetails.setcModel(pd.getcModel());
				parkingDetails.setcReg(pd.getcReg());
				parkingDetails.setIssueDate(pd.getIssueDate());
				parkingDetails.setValidDate(pd.getValidDate());
				parkingDetails.setEmpcode(pd.getEmpcode());
				parkingDetails.setMobileNo(pd.getMobileNo());
				// update value in database.
				session.update(parkingDetails);
			}
			catch(Exception ex) {
				
				System.out.println(ex.getMessage());
				session.save(pd);
			}
		}
		else {
			System.out.println("going to throw error");
			throw new Exception();
		}
		session.flush();
		trx.commit();
		session.close();
	}
	
	// Get latest n rows from the ParkingDetails Table.
	public List<ParkingDetails> getLatestParkingDetails(int rows){
		Session session = factory.openSession();
		session.beginTransaction();
		
		Query<ParkingDetails> parkingQuery = session.createQuery("FROM ParkingDetails ORDER BY creationDate DESC",ParkingDetails.class);
		parkingQuery.setFirstResult(0);
		parkingQuery.setMaxResults(rows);
		List<ParkingDetails> parkingList = parkingQuery.getResultList();
		session.close();
		return parkingList;
	}
	
	// Fetch record from table on basis of employee code. 
	public ParkingDetails fetchSingleRecord(String empcode, String passNo) {
		Session session = factory.openSession();
		session.beginTransaction();
		Query<ParkingDetails> fetchRecordQuery = null;
		if(passNo != null && !(passNo.equals("null")) && passNo != "" ) {
			fetchRecordQuery = session.createQuery("FROM ParkingDetails WHERE passNo = :passNo",ParkingDetails.class);
			fetchRecordQuery.setParameter("passNo", passNo);
			System.out.println("I am giving you data.. basis on pass number");
		}
		else {
			fetchRecordQuery = session.createQuery("FROM ParkingDetails WHERE empcode = :empcode",ParkingDetails.class); 
			fetchRecordQuery.setParameter("empcode", empcode);
		}
		ParkingDetails detail = null;
		try {
			List<ParkingDetails> pList = fetchRecordQuery.getResultList();
			detail = pList.get(0);
		}
		catch(Exception ex) {
			detail = null;
		}
		session.close();
		return detail;
	}
	
	public User getLoginDetails(String userCode) {
		Session session = factory.openSession();
		session.beginTransaction();
		Query<User> userQuery = session.createQuery("FROM User WHERE id = :username ", User.class);
		userQuery.setParameter("username", userCode);
		User user = null;
		try {
			user = userQuery.getSingleResult();
		}
		catch(Exception ex) {
			user = null;
		}
		session.close();
		return user;
	}
	
	public List<ParkingDetails> searchParkingDetails(ParkingDetails pd){
		String empcode = pd.getEmpcode()!= null ? pd.getEmpcode().trim():"";
		String name = pd.getName() != null ? pd.getName().trim():"";
		//If category null or equalto "Select Category" Set category = ""
		String category = pd.getCategory() != null ? (pd.getCategory().equals("Select Category") ? "":pd.getCategory().trim()): "";
		String passNo = pd.getPassNo() != null ? pd.getPassNo().trim() : "";
		String cMake = pd.getcMake() != null ? pd.getcMake().trim() : "";
		String cModel = pd.getcModel() != null ? pd.getcModel().trim() : "";
		String cReg = pd.getcReg() != null ? pd.getcReg().trim() : "";
		Date iDate = pd.getIssueDate() != null ? pd.getIssueDate(): Date.valueOf(LocalDate.of(2000, 04, 01));
		Date vDate = pd.getValidDate() !=null ? pd.getValidDate() : Date.valueOf(LocalDate.of(9999, 12, 31));
		
		System.out.println(empcode);
		System.out.println(name);
		System.out.println(category);
		System.out.println(passNo);
		System.out.println(cMake);
		System.out.println(cModel);
		System.out.println(cReg);
		System.out.println(iDate);
		System.out.println(vDate);
		
		Session session = factory.openSession();
		ProcedureCall call = session.createStoredProcedureCall("SGRH_Parking_Search",ParkingDetails.class);
		call.registerParameter(1,String.class, ParameterMode.IN).bindValue(empcode);
		call.registerParameter(2,String.class, ParameterMode.IN).bindValue(name);
		call.registerParameter(3,String.class, ParameterMode.IN).bindValue(category);
		call.registerParameter(4,String.class, ParameterMode.IN).bindValue(passNo);
		call.registerParameter(5,String.class, ParameterMode.IN).bindValue(cMake);
		call.registerParameter(6,String.class, ParameterMode.IN).bindValue(cModel);
		call.registerParameter(7,String.class, ParameterMode.IN).bindValue(cReg);
		call.registerParameter(8,Date.class, ParameterMode.IN).bindValue(iDate);
		call.registerParameter(9,Date.class, ParameterMode.IN).bindValue(vDate);
		//call.registerParameter(8,Date.class, ParameterMode.IN).bindValue(Date.valueOf(LocalDate.of(2018, 4, 1)));
		//call.registerParameter(9,Date.class, ParameterMode.IN).bindValue(Date.valueOf(LocalDate.now()));
		ResultSetOutput resultOutput = (ResultSetOutput)call.getOutputs().getCurrent();
		List<ParkingDetails> parkingDetailsList = resultOutput.getResultList();
		System.out.println(parkingDetailsList.size());
		/*
		query.setParameter("empCode", pd.getEmpcode());
		query.setParameter("name", pd.getName());
		query.setParameter("category", pd.getCategory());
		query.setParameter("pass", pd.getPassNo());
		query.setParameter("make", pd.getcMake());
		query.setParameter("model", pd.getcModel());
		query.setParameter("reg", pd.getcReg());
		query.setParameter("issue", pd.getIssueDate());
		query.setParameter("valid", pd.getValidDate());
		List<ParkingDetails> listParking = query.getResultList();*/
		/*return listParking;*/
		session.close();
		return parkingDetailsList;
	}
}
