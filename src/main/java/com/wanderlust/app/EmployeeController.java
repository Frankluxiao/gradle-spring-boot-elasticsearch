package com.wanderlust.app;

import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.delete.DeleteResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

//    // URL:
//    // http://localhost:8080/SomeContextPath/employees
//    // http://localhost:8080/SomeContextPath/employees.xml
//    // http://localhost:8080/SomeContextPath/employees.json
//    @RequestMapping(value = "/employees",
//            method = RequestMethod.GET,
//            produces = { MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE })
//    @ResponseBody
//    public List<Employee> getEmployees() {
//
//        return employeeService.getEmployees();
//    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee
    // http://localhost:8080/SomeContextPath/employee.xml
    // http://localhost:8080/SomeContextPath/employee.json
    @RequestMapping(value = "/employee",
            method = RequestMethod.POST,
            produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee create(@RequestBody Employee employee) {

        return employeeService.create(employee);
    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    // http://localhost:8080/SomeContextPath/employee/{empNo}.xml
    // http://localhost:8080/SomeContextPath/employee/{empNo}.json
    @RequestMapping(value = "/employee/{employeeId}",
            method = RequestMethod.GET,
            produces = { MediaType.APPLICATION_JSON_VALUE,
                    MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public Employee get(@PathVariable("employeeId") UUID employeeId) {

        return employeeService.get(employeeId);
    }

//    // URL:
//    // http://localhost:8080/SomeContextPath/employee
//    // http://localhost:8080/SomeContextPath/employee.xml
//    // http://localhost:8080/SomeContextPath/employee.json
//    @RequestMapping(value = "/employee/{employeeId}",
//            method = RequestMethod.PUT,
//            produces = { MediaType.APPLICATION_JSON_VALUE,
//                    MediaType.APPLICATION_XML_VALUE })
//    @ResponseBody
//    public Employee update(@PathVariable("employeeId") UUID employeeId,
//                           @RequestBody Employee employee) {
//
//        return employeeService.update(employeeId, employee);
//    }

    // URL:
    // http://localhost:8080/SomeContextPath/employee/{empNo}
    @RequestMapping(value = "/employee/{employeeId}", //
            method = RequestMethod.DELETE, //
            produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    @ResponseBody
    public DeleteResponse delete(@PathVariable("employeeId") UUID employeeId) {

        return employeeService.delete(employeeId);
    }
}
