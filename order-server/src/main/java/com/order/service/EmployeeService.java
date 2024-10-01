package com.order.service;

import com.order.dto.EmployeeDTO;
import com.order.dto.EmployeeLoginDTO;
import com.order.entity.Employee;

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

}
