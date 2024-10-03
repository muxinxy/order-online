package com.order.service;

import com.order.dto.DishDTO;
import com.order.dto.DishPageQueryDTO;
import com.order.result.PageResult;

public interface DishService {

    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    void saveWithFlavors(DishDTO dishDTO);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    PageResult page(DishPageQueryDTO dishPageQueryDTO);

}
