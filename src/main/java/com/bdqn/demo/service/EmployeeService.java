package com.bdqn.demo.service;

import com.bdqn.demo.pojo.Employee;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeService {
    List<Employee> getEmployeelist(Integer pageNo, Integer pageSize);
    int delEmployee(Integer id);
}
