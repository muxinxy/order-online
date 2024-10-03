package com.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealDishMapper {

    /**
     * 根据套餐id查询菜品id
     * @param setmealId
     * @return List<Long>
     */
    List<Long> selectSetmealIdsByDishIds(List<Long> dishIds);

}
