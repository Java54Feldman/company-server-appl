package telran.employees.net;

import telran.net.*;

import java.util.Arrays;
import java.util.stream.Collectors;

import telran.employees.*;

public class CompanyProtocol implements Protocol {
	Company company;
	
	public CompanyProtocol(Company company) {
		this.company = company;
	}

	@Override
	public Response getResponse(Request request) {
		String requestType = request.requestType();
		String requestData = request.requestData();
		Response response = null;
		try {
			response = switch(requestType) {
			case "addEmployee" -> addEmployee(requestData);
			case "getEmployee" -> getEmployee(requestData);
			case "removeEmployee" -> removeEmployee(requestData);
			case "getDepartmentBudget" -> getDepartmentBudget(requestData);
			case "getDepartments" -> getDepartments(requestData);
			case "getManagersWithMostFactor" -> getManagersWithMostFactor(requestData);
			default -> wrongTypeResponse(requestType);
			};
			
		} catch (Exception e) {
			response = wrongDataResponse(e.getMessage());
		}
		return response;
	}

	private Response wrongDataResponse(String message) {
		// TODO Auto-generated method stub
		return null;
	}

	private Response wrongTypeResponse(String requestType) {
		// TODO Auto-generated method stub
		return null;
	}

	private Response getManagersWithMostFactor(String requestData) {
		Manager[] managers = company.getManagersWithMostFactor();
		
		return new Response(ResponseCode.OK, managersToJSON(managers));
	}

	private String managersToJSON(Manager[] managers) {
		
		return Arrays.stream(managers)
				.map(Employee::getJSON)
				.collect(Collectors.joining(";"));
	}

	private Response getDepartments(String requestData) {
		// TODO Auto-generated method stub
		return null;
	}

	private Response getDepartmentBudget(String requestData) {
		// TODO Auto-generated method stub
		return null;
	}

	private Response removeEmployee(String requestData) {
		// TODO Auto-generated method stub
		return null;
	}

	private Response getEmployee(String requestData) {
		// TODO Auto-generated method stub
		return null;
	}

	private Response addEmployee(String emplJSON) {
		Employee empl = (Employee) new Employee().setObject(emplJSON);
		company.addEmployee(empl);
		return new Response(ResponseCode.OK, "");
	}

}
