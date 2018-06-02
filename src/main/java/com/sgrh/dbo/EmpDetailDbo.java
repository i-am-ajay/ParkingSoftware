package com.sgrh.dbo;

import javax.persistence.NoResultException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.query.Query;

import com.sgrh.entities.EmpDetail;

public class EmpDetailDbo {
	SessionFactory sessionFactory;
	public EmpDetailDbo() {
		setup();
	}
	public void setup() {
		StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure("hibernate_pis.cfg.xml").build();
		sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
	}
	
	public EmpDetail getEmployee(String empcode) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		Query<EmpDetail> query = session.createQuery("FROM EmpDetail WHERE empcode = :empcode",EmpDetail.class);
		query.setParameter("empcode",empcode);
		EmpDetail empDetails = null;
		try {
			empDetails = query.getSingleResult();
		}
		catch(NullPointerException | NoResultException ex) {
			empDetails = null;
		}
		return empDetails;
	}
}
