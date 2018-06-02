package com.sgrh.dbo;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class DbConnection {
	Properties dbProperties = new Properties();
	
	public void getConnection() throws IOException {
		URL url = getClass().getResource("/db.prop");
		dbProperties.load(new FileInputStream(url.getFile()));
	}

}
