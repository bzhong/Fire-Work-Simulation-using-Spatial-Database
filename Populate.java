package mainProgram;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashSet;

import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

public class Populate {
        public Populate(String[] args) {
            files = args;
        }
    
        public Connection genConnection() throws SQLException {
		DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
		String host = "localhost";
		String dbName = "db11g";
		int port = 1521;
		String oracleURL = "jdbc:oracle:thin:@" + host + ":" + port + ":" + dbName;
		String username = "";
		String password = "";
		return DriverManager.getConnection(oracleURL, username,password);
	}
	
	public void dropTable(Connection conn) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "delete from bldg";
		stmt.executeUpdate(query);
		query = "delete from hydrant";
		stmt.executeUpdate(query);
	}	
	
	public void populateBldg(Connection conn, HashSet<String> fireBldg, String fileName) throws SQLException, NumberFormatException, IOException {
		PreparedStatement statement = conn.prepareStatement("insert into bldg values(?, ?, ?, ?)");
		String file = fileName, line;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
                        //System.out.println(line);
			String[] parts = line.split(",");
			statement.setString(1, parts[0].trim());
			statement.setString(2, parts[1].trim());
			if (fireBldg.contains(parts[1].trim()))
				statement.setString(3, "T");
			else
				statement.setString(3,  "F");
			int count = Integer.parseInt(parts[2].trim());
			int[] sdo_elem_info_array = {1, 1003, 1};
			double[] sdo_ordinate_array = new double[count * 2 + 2];
			for (int i = 0; i < 2 * count; ++i)
				sdo_ordinate_array[i] = Integer.parseInt(parts[3 + i].trim());
			sdo_ordinate_array[2 * count] = Integer.parseInt(parts[3].trim());
			sdo_ordinate_array[2 * count + 1] = Integer.parseInt(parts[4].trim());
			JGeometry sdoGeo= new JGeometry(2003, 0, sdo_elem_info_array, sdo_ordinate_array);
			STRUCT obj = JGeometry.store(sdoGeo, conn);
			statement.setObject(4, obj);
			statement.executeUpdate();
		}
		br.close();
	}
	
	public void populateHydrant(Connection conn, String fileName) throws SQLException, NumberFormatException, IOException {
		PreparedStatement statement = conn.prepareStatement("insert into hydrant values(?, ?)");
		String file = fileName, line;
		BufferedReader br = new BufferedReader(new FileReader(file));
		while ((line = br.readLine()) != null) {
			//System.out.println(line);
			String[] parts = line.split(",");
			statement.setString(1, parts[0].trim());
			int[] sdo_elem_info_array = {1, 1, 1};
			double[] sdo_ordinate_array = new double[2];
			sdo_ordinate_array[0] = Integer.parseInt(parts[1].trim());
			sdo_ordinate_array[1] = Integer.parseInt(parts[2].trim());
			JGeometry sdoGeo= new JGeometry(2001, 0, sdo_elem_info_array, sdo_ordinate_array);
			STRUCT obj = JGeometry.store(sdoGeo, conn);
			statement.setObject(2, obj);
			statement.executeUpdate();
		}
		br.close();
	}
        
        public void execPopulate() throws SQLException, FileNotFoundException, IOException {
            Connection conn = genConnection();
		dropTable(conn);
		
		String file = files[2], line;
		BufferedReader br = new BufferedReader(new FileReader(file));
		HashSet<String> fireBldg = new HashSet<String>(); 
		while ((line = br.readLine()) != null) {
			fireBldg.add(line.trim());
		}
		br.close();
		
		populateBldg(conn, fireBldg, files[0]);
		populateHydrant(conn, files[1]);
		
		conn.close();
        }
        
        private String[] files;
}
