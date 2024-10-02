package com.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import com.order.entity.DishFlavor;

@Mapper
public interface DishFlavorMapper {

    /**
     * 新增菜品口味
     * @param dishFlavor
     */
    @Insert("insert into dish_flavor(id, dish_id, name, value) values(#{id}, #{dishId}, #{name}, #{value})")
    void insert(DishFlavor dishFlavor);

    /**
     * 批量新增菜品口味
     * @param flavors
     */
    void insertBatch(List<DishFlavor> flavors);

}
