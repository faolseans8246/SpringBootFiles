package com.example.workwithdatabase.services;

import com.example.workwithdatabase.models.Employee;
import com.example.workwithdatabase.repositories.EmployeeRepository;
import com.example.workwithdatabase.services.baseServises.RootEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements RootEmployeeService {

    @Autowired
    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }



    @Override
    public Employee saveEmployee(Employee employee) {
        return employeeRepository.save(employee);
    }


    @Override
    public List<Employee> getAll() {
        return employeeRepository.findAll();
    }

    @Override
    public Optional<Employee> getById(long id) {
        return employeeRepository.findById(id);
    }


    @Override
    public void deleteById(long id) {
        employeeRepository.deleteById(id);
    }


    @Override
    public Employee updateItems(long id, Employee employee) {

        Employee newEmployee = employeeRepository.findById(id).orElseThrow();

        newEmployee.setFirstname(employee.getFirstname());
        newEmployee.setLastname(employee.getLastname());

        return employeeRepository.save(newEmployee);

    }


    /**
     *
     * // Reja asosida berilgan ma'lumotni berilgan vaqtda bazaga saqlash ishini amalga oshiradi
     *     @Scheduled(cron = "0 44 15 * * *")
     *     public Employee saveBySchedule() {
     *
     *         Employee emplAdd = new Employee();
     *         emplAdd.setFirstname("Ism qo'shildi");
     *         emplAdd.setLastname("Familyaga qo'shildi");
     *
     *         return employeeRepository.save(emplAdd);
     *     }
     *
     */

}
