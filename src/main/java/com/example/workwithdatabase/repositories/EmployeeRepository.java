package com.example.workwithdatabase.repositories;

import com.example.workwithdatabase.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, CrudRepository<Employee, Long> {
}
