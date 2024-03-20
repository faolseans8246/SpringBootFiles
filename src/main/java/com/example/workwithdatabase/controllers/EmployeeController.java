package com.example.workwithdatabase.controllers;


import com.example.workwithdatabase.models.Employee;
import com.example.workwithdatabase.services.baseServises.RootEmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class EmployeeController {

    @Autowired
    private final RootEmployeeService rootEmployeeService;

    public EmployeeController(RootEmployeeService rootEmployeeService) {
        this.rootEmployeeService = rootEmployeeService;
    }



    @RequestMapping(value = "/saveItem", method = RequestMethod.POST)
    public ResponseEntity<?> createItems(@RequestBody Employee employee) {

        Employee newEmployee = rootEmployeeService.saveEmployee(employee);

        return ResponseEntity.ok(newEmployee);
    }

    @RequestMapping(value = "/getAllItems", method = RequestMethod.GET)
    public ResponseEntity<?> getAllItems() {
        List<Employee> allEmployee = rootEmployeeService.getAll();

        return ResponseEntity.ok(allEmployee);
    }


    @GetMapping("/getById/{id}")
    public Optional<Employee> getByIdFromTables(@PathVariable long id) {
        return rootEmployeeService.getById(id);
    }


    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteById(@PathVariable long id) {
        rootEmployeeService.deleteById(id);
        return ResponseEntity.ok("Berilgan element o'chirildi!");
    }


    @RequestMapping(value = "/updateItemById/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateItems(@PathVariable long id, @RequestBody Employee employee) {
        rootEmployeeService.updateItems(id, employee);

        return ResponseEntity.ok("Ma'lumot O'zgartirildi!");
    }

}
