package com.autogreetings.dao;

import java.sql.SQLException;
import java.util.List;

import com.autogreetings.model.Employee;

public interface GreetingsDao {
	public List<Employee> getListOfBdayEmployee() throws SQLException;
	public List<Employee> getListOfWrkAnniversaries() throws SQLException;
}
