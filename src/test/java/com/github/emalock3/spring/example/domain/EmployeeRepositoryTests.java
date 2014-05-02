package com.github.emalock3.spring.example.domain;

import com.github.emalock3.spring.example.Application;
import java.util.Arrays;
import java.util.Collections;
import org.junit.runner.RunWith;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.hamcrest.Matchers.*;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class EmployeeRepositoryTests {
  
  @Autowired
  private EmployeeRepository empRepo;
  @Autowired
  private DepartmentRepository deptRepo;
  
  @Before
  public void setUp() {
    empRepo.deleteAll();
    deptRepo.deleteAll();
  }
  
  @Test
  public void testFindByIdIn() {
    Iterable<Employee> emps = empRepo.findByIdIn(Collections.EMPTY_LIST);
    assertThat(emps, is(not(nullValue())));
    assertThat(emps.iterator().hasNext(), is(false));
    emps = empRepo.findByIdIn(Arrays.asList(1L, 2L, 3L));
    assertThat(emps, is(not(nullValue())));
    assertThat(emps.iterator().hasNext(), is(false));
    Employee emp = empRepo.save(new Employee(null, "hoge"));
    emps = empRepo.findByIdIn(Arrays.asList(emp.getId()));
    assertThat(emps, is(not(nullValue())));
    assertThat(emps.iterator().hasNext(), is(true));
  }
  
  @Test
  public void testFindQuery() {
    Iterable<Employee> emps = empRepo.findQuery("");
    assertThat(emps, is(not(nullValue())));
    assertThat(emps.iterator().hasNext(), is(false));
    Employee emp = empRepo.save(new Employee(null, "hoge"));
    emps = empRepo.findQuery(emp.getName());
    assertThat(emps, is(not(nullValue())));
    assertThat(emps.iterator().hasNext(), is(true));
  }
  
}
