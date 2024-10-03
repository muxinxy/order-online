package com.order.service;

import java.util.List;

import com.order.dto.DishDTO;
import com.order.dto.DishPageQueryDTO;
import com.order.result.PageResult;

public interface DishService {

    /**
     * 新增菜品
     * @param dishDTO
     * @return void
     */
    void saveWithFlavors(DishDTO dishDTO);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return PageResult
     */
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 批量删除菜品
     * @param ids
     * @return void
     */
    void deleteBatch(List<Long> ids);

}
