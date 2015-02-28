package mainProgram;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Objects;

import oracle.spatial.geometry.JGeometry;
import oracle.sql.STRUCT;

public class HW3 {
        public HW3() throws SQLException {
            conn = genConnection();
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
	
	public ResultSet execBldgQueryForWholeRegion(boolean actBldg, boolean actBldgOnFire, HW3GUI gui) throws SQLException {
			Statement stmt = conn.createStatement();
			String query;
			if (actBldg == true && actBldgOnFire == true) {
				query = "select b.geom, case b.on_fire when 'T' then 'red' else 'yellow' end as color from bldg b";
			}
			else if (actBldg == true) {
				query = "select b.geom, 'yellow' as color from bldg b where b.on_fire = 'F'";
			}
			else if (actBldgOnFire == true) {
				query = "select b.geom, 'red' as color from bldg b where b.on_fire = 'T'";
			}
			else
				return null;
                        gui.getTextArea().append("Query " + gui.counter++ + ": " + query + "\n");
                        return stmt.executeQuery(query);
	}
        
        public ResultSet execBldgQueryForRangeQuery(boolean actBldg, boolean actBldgOnFire, ArrayList<Integer> userPolygon, HW3GUI gui) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
			PreparedStatement stmt;
                        String query;
			if (actBldg == true && actBldgOnFire == true) {
				stmt = conn.prepareStatement("select b.geom, case b.on_fire when 'T' then 'red' else 'yellow' end as color"
						+ " from bldg b where sdo_anyinteract(b.geom, ?) = 'TRUE'");
                                query = "select b.geom, case b.on_fire when 'T' then 'red' else 'yellow' end as color"
						+ " from bldg b where sdo_anyinteract(b.geom, ?) = 'TRUE'";
			}
			else if (actBldg == true) {
				stmt = conn.prepareStatement("select b.geom, 'yellow' as color from bldg b"
						+ " where b.on_fire = 'F' and sdo_anyinteract(b.geom, ?) = 'TRUE'");
                                query = "select b.geom, 'yellow' as color from bldg b"
						+ " where b.on_fire = 'F' and sdo_anyinteract(b.geom, ?) = 'TRUE'";
                                        
			}
			else if (actBldgOnFire == true) {
				stmt = conn.prepareStatement("select b.geom, 'red' as color from bldg b"
						+ " where b.on_fire = 'T' and sdo_anyinteract(b.geom, ?) = 'TRUE'");
                                query = "select b.geom, 'red' as color from bldg b"
						+ " where b.on_fire = 'T' and sdo_anyinteract(b.geom, ?) = 'TRUE'";
			}
			else
				return null;
			int[] sdo_elem_info_array = {1, 1003, 1};
                        double[] sdo_ordinate_array;
                        if (Objects.equals(userPolygon.get(0), userPolygon.get(userPolygon.size() - 2)) &&
                                Objects.equals(userPolygon.get(1), userPolygon.get(userPolygon.size() - 1))) {
                            sdo_ordinate_array = new double[userPolygon.size()];
                            for (int i = 0; i < userPolygon.size(); ++i)
                                sdo_ordinate_array[i] = (double)userPolygon.get(i);
                        }
                        else {
                            sdo_ordinate_array = new double[userPolygon.size() + 2];
                            for (int i = 0; i < userPolygon.size(); ++i) {
                                sdo_ordinate_array[i] = (double) userPolygon.get(i);
                            }
                            sdo_ordinate_array[userPolygon.size()] = (double) userPolygon.get(0);
                            sdo_ordinate_array[userPolygon.size() + 1] = (double) userPolygon.get(1);
                        }
                        String sdoGeoStr = getString(sdo_ordinate_array);
			JGeometry sdoGeo = new JGeometry(2003, 0, sdo_elem_info_array, sdo_ordinate_array);
			STRUCT obj = JGeometry.store(sdoGeo, conn);
			stmt.setObject(1, obj);
                        
                        gui.getTextArea().append("Query " + gui.counter++ + ": " + query.replace("?", sdoGeoStr) + "\n");
			return stmt.executeQuery();
	}
        
        public String getString(double[] sdo_ordinate_array) {
            StringBuilder str = new StringBuilder();
            str.append("sdo_geometry(2003, null, null, sdo_elem_info_array(1, 1003, 1), sdo_ordinate_array(");
            for (int i = 0; i < sdo_ordinate_array.length; ++i) {
                str.append((int)sdo_ordinate_array[i]);
                if (i != sdo_ordinate_array.length - 1)
                    str.append(", ");
            }
            str.append("))");
            return str.toString();
        }
	
	public ResultSet execHydrantQueryForWholeRegion(boolean actHydrant, HW3GUI gui) throws SQLException {	
			Statement stmt = conn.createStatement();
			String query = "select h.geom, 'green' as color from hydrant h";
			gui.getTextArea().append("Query " + gui.counter++ + ": " + query + "\n");
                        return stmt.executeQuery(query);
	}
        
        public ResultSet execHydrantQueryForRangeQuery(boolean actHydrant, ArrayList<Integer> userPolygon, HW3GUI gui) throws SQLException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
			PreparedStatement stmt = conn.prepareStatement("select h.geom, 'green' as color from hydrant h"
					+ " where sdo_anyinteract(h.geom, ?) = 'TRUE'");
                        String query = "select h.geom, 'green' as color from hydrant h"
					+ " where sdo_anyinteract(h.geom, ?) = 'TRUE'";
			int[] sdo_elem_info_array = {1, 1003, 1};
                        double[] sdo_ordinate_array;
                        if (Objects.equals(userPolygon.get(0), userPolygon.get(userPolygon.size() - 2)) &&
                                Objects.equals(userPolygon.get(1), userPolygon.get(userPolygon.size() - 1))) {
                            sdo_ordinate_array = new double[userPolygon.size()];
                            for (int i = 0; i < userPolygon.size(); ++i)
                                sdo_ordinate_array[i] = (double)userPolygon.get(i);
                        }
                        else {
                            sdo_ordinate_array = new double[userPolygon.size() + 2];
                            for (int i = 0; i < userPolygon.size(); ++i) {
                                sdo_ordinate_array[i] = (double) userPolygon.get(i);
                            }
                            sdo_ordinate_array[userPolygon.size()] = (double) userPolygon.get(0);
                            sdo_ordinate_array[userPolygon.size() + 1] = (double) userPolygon.get(1);
                        }
                        String sdoGeoStr = getString(sdo_ordinate_array);
			JGeometry sdoGeo= new JGeometry(2003, 0, sdo_elem_info_array, sdo_ordinate_array);
			STRUCT obj = JGeometry.store(sdoGeo, conn);
			stmt.setObject(1, obj);
                        gui.getTextArea().append("Query " + gui.counter++ + ": " + query.replace("?", sdoGeoStr) + "\n");
			return stmt.executeQuery();
	}
	
        public void displayBldg(ResultSet bldgResultSet, Graphics g) throws SQLException, IOException {
            while (bldgResultSet.next()) {
                STRUCT st = (STRUCT) bldgResultSet.getObject(1);
                JGeometry geom = JGeometry.load(st);
                String color = bldgResultSet.getString(2);

                double[] ordinates = geom.getOrdinatesArray();
                int lineNum = ordinates.length / 2 - 1;
                for (int i = 0; i < lineNum; ++i) {
                    int point1x = (int) ordinates[i * 2];
                    int point1y = (int) ordinates[i * 2 + 1];
                    int point2x = (int) ordinates[((i + 1) * 2) % (2 * lineNum)];
                    int point2y = (int) ordinates[((i + 1) * 2 + 1) % (2 * lineNum)];
                    switch (color) {
                        case "red":
                            g.setColor(Color.RED);
                            break;
                        case "yellow":
                            g.setColor(Color.YELLOW);
                            break;
                        case "green":
                            g.setColor(Color.GREEN);
                            break;
                        default:
                            g.setColor(Color.BLACK);
                            break;
                    }
                    g.drawLine(point1x, point1y, point2x, point2y);
                }
            }
        }
	
	public void displayHydrant(ResultSet hydrantResultSet, Graphics g) throws SQLException, IOException {
            while (hydrantResultSet.next()) {
                STRUCT st = (STRUCT) hydrantResultSet.getObject(1);
                JGeometry geom = JGeometry.load(st);
                String color = hydrantResultSet.getString(2);

                double[] tmp = geom.getPoint();
                switch (color) {
                    case "red":
                        g.setColor(Color.RED);
                        break;
                    case "yellow":
                        g.setColor(Color.YELLOW);
                        break;
                    case "green":
                        g.setColor(Color.GREEN);
                        break;
                    default:
                        g.setColor(Color.BLACK);
                        break;
                }
                g.fillRect((int)tmp[0] - 7, (int)tmp[1] - 7, 15, 15);
            }
	}
	
	public ResultSet findBldgOnFire(HW3GUI gui) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "select b.geom, 'red' as color from bldg b where b.on_fire = 'T'";
		gui.getTextArea().append("Query " + gui.counter++ + ": " + query + "\n");
                return stmt.executeQuery(query);
	}
	
	public ResultSet findBldgNearby(HW3GUI gui) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "select b.geom, 'yellow' as color from bldg b, (select b2.geom from bldg b2 where b2.on_fire = 'T') b3"
				+ " where b.on_fire = 'F' and sdo_within_distance(b.geom, b3.geom, 'distance = 100') = 'TRUE'";
		gui.getTextArea().append("Query " + gui.counter++ + ": " + query + "\n");
                return stmt.executeQuery(query);
	}
	
	public ResultSet findNearestHydrant(HW3GUI gui) throws SQLException {
		Statement stmt = conn.createStatement();
		String query = "select h.geom, 'green' as color from hydrant h, (select b.geom from bldg b where b.on_fire = 'T') b2"
					+ " where sdo_nn(h.geom, b2.geom, 'sdo_num_res = 1') = 'TRUE'";
		gui.getTextArea().append("Query " + gui.counter++ + ": " + query + "\n");
                return stmt.executeQuery(query);
	}
	
	Connection conn;
}
