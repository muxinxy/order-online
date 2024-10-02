package com.order.service;

import com.order.dto.DishDTO;

public interface DishService {

    /**
     * 新增菜品
     */
    void saveWithFlavors(DishDTO dishDTO);

}
