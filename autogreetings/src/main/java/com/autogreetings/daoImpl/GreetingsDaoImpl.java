package com.autogreetings.daoImpl;

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
		String sql = "SELECT name,emp_id,doj,dob,email_id FROM greetings_data WHERE "
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
			employeeList.add(employee);
		}
		
		return employeeList;
	}

}
