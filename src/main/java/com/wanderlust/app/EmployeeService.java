package com.wanderlust.app;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static java.lang.String.format;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;


    private static final String CREATE_ERROR = "Failed to create contact";
    private static final String GET_ERROR = "Failed to get employee with Id = %s in database";
    private static final String UPDATE_ERROR = "Failed to update contact with Id = %s in database";
    private static final String DELETE_ERROR = "Failed to delete contact with Id = %s in database";


//    public List<Employee> getEmployees() {
//
//        return employeeDAO.getEmployees();
//    }

    public Employee create(Employee employee) {

        employee.setEmployeeId(UUID.randomUUID());
        employee.setCreationTime(LocalDateTime.now());

        try {
            employeeDAO.create(employee);
            return employee;
        } catch (Exception e) {
            throw new RuntimeException(CREATE_ERROR, e);
        }
    }

    public Employee get(UUID employeeId) {

        try {

            GetResponse response = employeeDAO.get(employeeId);
            ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());
            Employee employee = mapper.readValue(response.getSourceAsString(), Employee.class);
            return employee;
        } catch (Exception e) {
            throw new RuntimeException(format(GET_ERROR, employeeId.toString()), e);
        }
    }

//    public Employee update(UUID employeeId, Employee employee) {
//
//        try {
//            Employee oldEmployee = employeeDAO.get(employeeId);
//            employee.setEmployeeId(oldEmployee.getEmployeeId());
//            employee.setCreationTime(oldEmployee.getCreationTime());
//
//            employeeDAO.update(employee);
//            return employee;
//        } catch (Exception e) {
//            throw new RuntimeException(format(UPDATE_ERROR, employeeId.toString()), e);
//        }
//    }

    public DeleteResponse delete(UUID employeeId) {

        try {
            return employeeDAO.delete(employeeId);
        } catch (Exception e) {
            throw new RuntimeException(format(DELETE_ERROR, employeeId.toString()), e);
        }
    }
}
