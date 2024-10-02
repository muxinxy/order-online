package com.order.service.impl;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.beans.BeanUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonSerializable.Base;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.order.constant.MessageConstant;
import com.order.constant.StatusConstant;
import com.order.context.BaseContext;
import com.order.dto.CategoryDTO;
import com.order.dto.CategoryPageQueryDTO;
import com.order.entity.Category;
import com.order.entity.Setmeal;
import com.order.exception.DeletionNotAllowedException;
import com.order.mapper.CategoryMapper;
import com.order.mapper.DishMapper;
import com.order.mapper.SetmealMapper;
import com.order.result.PageResult;
import com.order.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private SetmealMapper setmealMapper;

    /**
     * 添加分类
     * @param categoryDTO
     */
    public void save(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setStatus(StatusConstant.DISABLE);
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        Long empId = BaseContext.getCurrentId();
        category.setCreateUser(empId);
        category.setUpdateUser(empId);
        categoryMapper.save(category);
    }

    /**
     * 分页查询分类
     * @param categoryPageQueryDTO
     */
    public PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO) {
        PageHelper.startPage(categoryPageQueryDTO.getPage(), categoryPageQueryDTO.getPageSize());
        Page<Category> page = categoryMapper.pageQuery(categoryPageQueryDTO);
        Long total = page.getTotal();
        List<Category> categoryList = page.getResult();
        return new PageResult(total, categoryList);
    }

    /**
     * 根据id查询分类
     * @param id
     */
    public Category getById(Long id) {
        return categoryMapper.getById(id);
    }

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    public List<Category> getByType(Integer type) {
        return categoryMapper.getByType(type);
    }

    /**
     * 修改分类
     * @param categoryDTO
     * @return
     */
    public void update(CategoryDTO categoryDTO) {
        Category category = new Category();
        BeanUtils.copyProperties(categoryDTO, category);
        category.setUpdateTime(LocalDateTime.now());
        Long empId = BaseContext.getCurrentId();
        category.setUpdateUser(empId);
        categoryMapper.update(category);
    }

    /**
     * 修改分类状态
     * @param status
     * @param id
     */
    public void status(Integer status, Long id) {
        Category category = new Category();
        category.setId(id);
        category.setStatus(status);
        categoryMapper.update(category);
    }

    /**
     * 删除分类
     * @param id
     * @return
     */
    public void delete(Long id) {
        Integer dishCount = dishMapper.countByCategoryId(id);
        Integer setmealCount = setmealMapper.countByCategoryId(id);
        if (dishCount > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_DISH);
        }
        if (setmealCount > 0) {
            throw new DeletionNotAllowedException(MessageConstant.CATEGORY_BE_RELATED_BY_SETMEAL);
        }
        categoryMapper.delete(id);
    }

}
