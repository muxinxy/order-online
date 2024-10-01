package com.order.service;

import com.order.dto.EmployeeDTO;
import com.order.dto.EmployeeLoginDTO;
import com.order.dto.EmployeePageQueryDTO;
import com.order.entity.Employee;
import com.order.result.PageResult;

public interface EmployeeService {

    /**
     * 员工登录
     * @param employeeLoginDTO
     * @return
     */
    Employee login(EmployeeLoginDTO employeeLoginDTO);

    /**
     * 保存员工信息
     * @param employee
     */
    void save(EmployeeDTO employeeDTO);

    /**
     * 分页查询员工信息
     * @param employeePageQueryDTO
     */
    PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO);

    /**
     * 修改员工状态
     * @param status
     * @param id
     */
    void status(Integer status, Long id);

}
