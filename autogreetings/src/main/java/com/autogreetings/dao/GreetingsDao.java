package com.autogreetings.dao;

import java.sql.SQLException;
import java.util.List;

import com.autogreetings.model.Employee;

public interface GreetingsDao {
	public List<Employee> getListOfGreetingsEmployee() throws SQLException;
}
