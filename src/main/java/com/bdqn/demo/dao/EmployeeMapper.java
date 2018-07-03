package com.bdqn.demo.dao;

import com.bdqn.demo.pojo.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    List<Employee> getEmployeelist(@Param("pageNo")Integer pageNo, @Param("pageSize")Integer pageSize);
    int delEmployee(Integer id);

}
