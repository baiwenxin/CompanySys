package com.bdqn.demo.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.demo.pojo.Employee;
import com.bdqn.demo.service.EmployeeService;
import com.bdqn.demo.util.Page;
import jdk.nashorn.internal.runtime.JSONFunctions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("/emoloyee")
public class EmployeeController {
    @Resource(name = "employeeService")
    private EmployeeService employeeService;

    /**
     * 进入页面
     * @return
     */
    @RequestMapping(value = "/index.html")
    public  String getList(){
        return "index";
    }

    /**
     * 分页显示
     * @param index
     * @return
     */
    @RequestMapping(value = "/list")
    @ResponseBody
    public Object getEmployeepage(Integer index){
        Integer pageindex=1;
        if (index==null){
            index = pageindex;
        }
        Page<Employee> page=new Page<Employee>();
        page.setPageNo(index);
        page.setPageSize(3);
        page.setInfoCount(employeeService.getEmployeelist(0,0).size());
        List<Employee> employeeList=employeeService.getEmployeelist((index-1)*3,3);
        page.setInfolist(employeeList);
        Object json=JSON.toJSONString(page);
        System.out.println(json);
        return json;
    }
    @RequestMapping(value = "/delInfo")
    @ResponseBody
    public Object delEmployee(Integer id){
        String str="";
        Integer result=employeeService.delEmployee(id);
        if (result>0){
            str="ok";
        }else {
            str="no";
        }
        return str;
    }
}
