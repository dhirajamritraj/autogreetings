package com.autogreetings.daoImpl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.autogreetings.dao.GreetingsDao;
import com.autogreetings.db.DBHelper;
import com.autogreetings.model.Employee;

public class GreetingsDaoImpl implements GreetingsDao {

	public List<Employee> getListOfGreetingsEmployee() throws SQLException {
		String sql = "SELECT name,emp_id,doj,dob,email_id,profile_image FROM greetings_data WHERE "
				+ "EXTRACT(month from dob) = EXTRACT(month from current_date())"
				+ "AND EXTRACT(day from dob) = EXTRACT(day from current_date())";

		Statement stmt = DBHelper.createConnection().createStatement();

		ResultSet rs = stmt.executeQuery(sql);
		List<Employee> employeeList = new ArrayList<Employee>();
		Employee employee = new Employee();
		
		while (rs.next()) {
			employee = new Employee();
			employee.setName(rs.getString("name"));
			employee.setEmpID(rs.getString("emp_id"));
			employee.setDoj(rs.getDate("doj"));
			employee.setDob(rs.getDate("dob"));
			employee.setEmailID(rs.getString("email_id"));
			employee.setEmpImg(retrieveImage(rs));
			employeeList.add(employee);
		}
		
		return employeeList;
	}

	public File retrieveImage(ResultSet rs) throws SQLException {
		File empImage = null;
		OutputStream f = null;
		InputStream in = null;
			in = rs.getBinaryStream("profile_image");
			try {
				empImage = new File("temp.jpg");
				f = new FileOutputStream(empImage);
				int c = 0;
				while ((c = in.read()) > -1) {
					f.write(c);
				}
				f.close();
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		return empImage;
	}

}
