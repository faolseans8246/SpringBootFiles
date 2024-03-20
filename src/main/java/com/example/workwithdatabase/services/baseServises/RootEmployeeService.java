package com.example.workwithdatabase.services.baseServises;

import com.example.workwithdatabase.models.Employee;

import java.util.List;
import java.util.Optional;

public interface RootEmployeeService {

    public Employee saveEmployee(Employee employee);

    public List<Employee> getAll();

    public Optional<Employee> getById(long id);

    public void deleteById(long id);

    public Employee updateItems(long id, Employee employee);


}
