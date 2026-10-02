package com.abhishek.module2.repositories;

import com.abhishek.module2.entities.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// To tell this repository to handle which entity we need to give the code like that.
// As we are not defining any methods (now it is handled by ORM) we can define this as interface.
// Jpa Repository have all the methods to interact with DB

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
