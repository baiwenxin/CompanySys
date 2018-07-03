package com.bdqn.demo.service;

import com.bdqn.demo.dao.EmployeeMapper;
import com.bdqn.demo.pojo.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service(value = "employeeService")
public class EmployeeServiceImpl implements  EmployeeService
{
    @Resource
    private EmployeeMapper employeeMapper;
    @Override
    public List<Employee> getEmployeelist(Integer pageNo, Integer pageSize) {
        return employeeMapper.getEmployeelist(pageNo,pageSize);
    }

    @Override
    public int delEmployee(Integer id) {
        return employeeMapper.delEmployee(id);
    }
}
