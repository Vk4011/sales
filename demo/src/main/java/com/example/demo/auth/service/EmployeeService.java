package com.example.demo.auth.service;

import com.example.demo.auth.dto.SignupRequest;
import com.example.demo.auth.model.Employee;
import com.example.demo.auth.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee createEmployee(SignupRequest request) {
        if (employeeRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        if (employeeRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists");
        }
        if (employeeRepository.existsByEmployeeId(request.getEmployeeId().toString())) {
            throw new RuntimeException("Employee ID already exists");
        }

        Employee employee = new Employee();
        employee.setFullName(request.getFullName());
        employee.setDateOfBirth(LocalDate.of(
            request.getDobYear(),
            request.getDobMonth(),
            request.getDobDay()
        ));
        employee.setGender(request.getGender());
        employee.setAddress(request.getAddress());
        employee.setPhoneNumber(request.getPhoneNumber());
        employee.setEmail(request.getEmail());
        employee.setEmployeeId(request.getEmployeeId().toString());
        employee.setDateOfJoining(LocalDate.of(
            request.getDojYear(),
            request.getDojMonth(),
            request.getDojDay()
        ));
        employee.setBranch(request.getBranch());

        return employeeRepository.save(employee);
    }
}