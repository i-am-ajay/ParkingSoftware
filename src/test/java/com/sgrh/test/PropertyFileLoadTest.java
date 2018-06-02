package com.sgrh.test;

import java.io.IOException;

import org.junit.Test;

import com.sgrh.dbo.DbConnection;

import junit.framework.TestCase;

public class PropertyFileLoadTest extends TestCase {
	public void testMethod() {
		DbConnection con = new DbConnection();
		try {
			con.getConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
