package com.order.service;

import java.util.List;

import com.order.dto.CategoryDTO;
import com.order.dto.CategoryPageQueryDTO;
import com.order.entity.Category;
import com.order.result.PageResult;

public interface CategoryService {

    /**
     * 添加分类
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分页查询分类
     * @param categoryPageQueryDTO
     */
    PageResult pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    Category getById(Long id);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> getByType(Integer type);

    /**
     * 修改分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);

    /**
     * 修改分类状态
     * @param status
     * @param id
     */
    void status(Integer status, Long id);

    /**
     * 删除分类
     * @param id
     */
    void delete(Long id);

}
