package com.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.order.entity.DishFlavor;

@Mapper
public interface DishFlavorMapper {

    /**
     * 新增菜品口味
     * @param dishFlavor
     * @return void
     */
    @Insert("insert into dish_flavor(id, dish_id, name, value) values(#{id}, #{dishId}, #{name}, #{value})")
    void insert(DishFlavor dishFlavor);

    /**
     * 批量新增菜品口味
     * @param flavors
     * @return void
     */
    void insertBatch(List<DishFlavor> flavors);

    /**
     * 根据菜品id删除菜品口味
     * @param id
     * @return void
     */
    @Delete("delete from dish_flavor where dish_id = #{dishId}")
    void deleteByDishId(Long dishId);

    /**
     * 根据菜品id批量删除菜品口味
     * @param dishIds
     * @return void
     */
    void deleteByDishIds(List<Long> dishIds);

    /**
     * 根据菜品id查询菜品口味
     * @param id
     * @return List<DishFlavor>
     */
    @Select("select * from dish_flavor where dish_id = #{id}")
    List<DishFlavor> getByDishId(Long id);

}
