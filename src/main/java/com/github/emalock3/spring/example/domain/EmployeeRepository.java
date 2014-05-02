package com.github.emalock3.spring.example.domain;

import java.util.Collection;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
  Iterable<Employee> findByIdIn(Collection<Long> ids);
  Iterable<Employee> findByNameContaining(String name);
  
  @Query("select e from Employee e where e.name = ?1")
  Iterable<Employee> findQuery(String name);
}
