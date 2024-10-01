package com.order.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.order.constant.MessageConstant;
import com.order.constant.PasswordConstant;
import com.order.constant.StatusConstant;
import com.order.context.BaseContext;
import com.order.dto.EmployeeDTO;
import com.order.dto.EmployeeLoginDTO;
import com.order.dto.EmployeePageQueryDTO;
import com.order.entity.Employee;
import com.order.exception.AccountLockedException;
import com.order.exception.AccountNotFoundException;
import com.order.exception.PasswordErrorException;
import com.order.mapper.EmployeeMapper;
import com.order.result.PageResult;
import com.order.service.EmployeeService;

import ch.qos.logback.core.joran.util.beans.BeanUtil;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.DigestUtils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    /**
     * 员工登录
     *
     * @param employeeLoginDTO
     * @return
     */
    public Employee login(EmployeeLoginDTO employeeLoginDTO) {
        String username = employeeLoginDTO.getUsername();
        String password = employeeLoginDTO.getPassword();

        //1、根据用户名查询数据库中的数据
        Employee employee = employeeMapper.getByUsername(username);

        //2、处理各种异常情况（用户名不存在、密码不对、账号被锁定）
        if (employee == null) {
            //账号不存在
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }

        //密码比对
        // 对用户输入的密码进行MD5加密，然后与数据库中的密码进行比对
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!password.equals(employee.getPassword())) {
            //密码错误
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }

        if (employee.getStatus() == StatusConstant.DISABLE) {
            //账号被锁定
            throw new AccountLockedException(MessageConstant.ACCOUNT_LOCKED);
        }

        //3、返回实体对象
        return employee;
    }

    /**
     * 保存员工信息
     *
     * @param employee
     */
    public void save(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        //将DTO对象的属性拷贝到实体对象中
        BeanUtils.copyProperties(employeeDTO, employee);
        //密码加密
        employee.setPassword(DigestUtils.md5DigestAsHex(PasswordConstant.DEFAULT_PASSWORD.getBytes()));
        //设置默认状态为启用
        employee.setStatus(StatusConstant.ENABLE);
        //设置创建时间和更新时间
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        //设置添加人id和更新人id
        Long empId = BaseContext.getCurrentId();
        employee.setCreateUser(empId);
        employee.setUpdateUser(empId);
        //保存到数据库
        employeeMapper.save(employee);
    }

    /**
     * 分页查询员工信息
     *
     * @param employeePageQueryDTO
     */
    public PageResult pageQuery(EmployeePageQueryDTO employeePageQueryDTO) {
        PageHelper.startPage(employeePageQueryDTO.getPage(), employeePageQueryDTO.getPageSize());
        Page<Employee> page = employeeMapper.pageQuery(employeePageQueryDTO);
        Long total = page.getTotal();
        List<Employee> employeeList = page.getResult();
        return new PageResult(total, employeeList);
    }

    /**
     * 修改员工状态
     *
     * @param status
     * @param id
     */
    public void status(Integer status, Long id) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setStatus(status);
        employeeMapper.update(employee);
    }

    /**
     * 根据id查询员工
     *
     * @param id
     * @return
     */
    public Employee getById(Long id) {
        Employee employee = employeeMapper.getById(id);
        employee.setPassword("******");
        return employee;
    }

    /**
     * 修改员工信息
     *
     * @param employeeDTO
     */
    public void update(EmployeeDTO employeeDTO) {
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        employee.setUpdateTime(LocalDateTime.now());
        Long empId = BaseContext.getCurrentId();
        employee.setUpdateUser(empId);
        employeeMapper.update(employee);
    }
}
