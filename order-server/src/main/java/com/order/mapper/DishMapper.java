package com.order.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.order.annotation.AutoFill;
import com.order.dto.DishPageQueryDTO;
import com.order.entity.Dish;
import com.order.enumeration.OperationType;
import com.order.vo.DishVO;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return Integer
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**
     * 新增菜品
     * @param dish
     * @return void
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return Page<DishVO>
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

}
