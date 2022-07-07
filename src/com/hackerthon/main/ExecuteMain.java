package com.hackerthon.main;

import javax.xml.transform.TransformerConfigurationException;

import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactoryConfigurationError;

import com.hackerthon.common.TranformUtil;
import com.hackerthon.service.EmployeeService;

public class ExecuteMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EmployeeService employeeService = new EmployeeService();
		try {
			TranformUtil.rEQUESTtRANSFORM();
			employeeService.getEmployeesFromXml();
			employeeService.createEmployeeTable();
			employeeService.addEmployees();
			employeeService.displayEmployees();
		} catch (Exception e) {
		}

	}

}
