package com.hackerthon.service;

import org.xml.sax.SAXException;


import java.sql.Connection;
import java.util.logging.Logger;
import java.sql.DriverManager;
import javax.xml.parsers.ParserConfigurationException;
import java.sql.PreparedStatement;
import javax.xml.xpath.XPathExpressionException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.sql.Statement;
import java.io.IOException;
import com.hackerthon.model.Employee;
import java.util.ArrayList;
import java.util.Map;
import com.hackerthon.common.CommonUtil;
import com.hackerthon.common.QueryUtil;
import com.hackerthon.common.TranformUtil;

public class EmployeeService extends CommonUtil {

	private final ArrayList<Employee> employeeList = new ArrayList<Employee>();

	private static Connection connection;

	private static Statement statement;

	private PreparedStatement preStatement;
	
	
	

	public EmployeeService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(PROPERTIES.getProperty("url"), PROPERTIES.getProperty("username"),
					PROPERTIES.getProperty("password"));
		} catch (Exception e) {
		} 
	}

	
	
	
	
	
	
	public void getEmployeesFromXml() {

		try {
			int s = TranformUtil.XMLXPATHS().size();
			for (int i = 0; i < s; i++) {
				Map<String, String> l = TranformUtil.XMLXPATHS().get(i);
				Employee employee = new Employee();
				
				employee.setEmployeeId(l.get("XpathEmployeeIDKey"));
				employee.setFullname(l.get("XpathEmployeeNameKey"));
				employee.setAddress(l.get("XpathEmployeeAddressKey"));
				employee.setFacultyName(l.get("XpathFacultyNameKey"));
				employee.setDepartment(l.get("XpathDepartmentKey"));
				employee.setDesignation(l.get("XpathDesignationKey"));
				employeeList.add(employee);
				System.out.println(employee.toString() + "\n");
			}
		} catch (Exception e) {
		}
	}

	public void createEmployeeTable() {
		try {
			statement = connection.createStatement();
			statement.executeUpdate(QueryUtil.Q("q2"));
			statement.executeUpdate(QueryUtil.Q("q1"));
		} catch (Exception e) {
		}
	}

	public void addEmployees() {
		try {
			preStatement = connection.prepareStatement(QueryUtil.Q("q3"));
			connection.setAutoCommit(false);
			for(int i = 0; i < employeeList.size(); i++){
				Employee e = employeeList.get(i);
				preStatement.setString(1, e.getEmployeeId());
				preStatement.setString(2, e.getFullname());
				preStatement.setString(3, e.getAddress());
				preStatement.setString(4, e.getFacultyName());
				preStatement.setString(5, e.getDepartment());
				preStatement.setString(6, e.getDesignation());
				preStatement.addBatch();
			}
			preStatement.executeBatch();
			connection.commit();
		} catch (Exception e) {
		}
	}

	public void getEmployeeById(String employeeId) {

		Employee employee = new Employee();
		try {
			preStatement = connection.prepareStatement(QueryUtil.Q("q4"));
			preStatement.setString(1, employeeId);
			ResultSet resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				
				employee.setEmployeeId(resultSet.getString(1));
				employee.setFullname(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
			}
			ArrayList<Employee> employeeList = new ArrayList<Employee>();
			employeeList.add(employee);
			employeeOutput(employeeList);
		} catch (Exception ex) {
		}
	}

	public void deleteEmployee(String eid) {

		try {
			preStatement = connection.prepareStatement(QueryUtil.Q("q6"));
			preStatement.setString(1, eid);
			preStatement.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void displayEmployees() {

		ArrayList<Employee> employeeList = new ArrayList<Employee>();
		try {
			preStatement = connection.prepareStatement(QueryUtil.Q("q5"));
			ResultSet resultSet = preStatement.executeQuery();
			while (resultSet.next()) {
				Employee employee = new Employee();
				employee.setEmployeeId(resultSet.getString(1));
				employee.setFullname(resultSet.getString(2));
				employee.setAddress(resultSet.getString(3));
				employee.setFacultyName(resultSet.getString(4));
				employee.setDepartment(resultSet.getString(5));
				employee.setDesignation(resultSet.getString(6));
				employeeList.add(employee);
			}
		} catch (Exception e) {
		}
		employeeOutput(employeeList);
	}
	
	public void employeeOutput(ArrayList<Employee> employeeList){
		
		System.out.println("Employee ID" + "\t\t" + "Full Name" + "\t\t" + "Address" + "\t\t" + "Faculty Name" + "\t\t"
				+ "Department" + "\t\t" + "Designation" + "\n");
		System.out
				.println("================================================================================================================");
		for(int i = 0; i < employeeList.size(); i++){
			Employee employee = employeeList.get(i);
			System.out.println(employee.getEmployeeId() + "\t" + employee.getFullname() + "\t\t"
					+ employee.getAddress() + "\t" + employee.getFacultyName() + "\t" + employee.getDepartment() + "\t"
					+ employee.getDesignation() + "\n");
			System.out
			.println("----------------------------------------------------------------------------------------------------------------");
		}
		
	}
}
