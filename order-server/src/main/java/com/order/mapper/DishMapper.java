package com.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    /**
     * 根据id查询菜品
     * @param id
     * @return Dish
     */
    @Select("select * from dish where id = #{id}")
    Dish getById(Long id);

    /**
     * 删除菜品
     * @param id
     * @return void
     */
    @Delete("delete from dish where id = #{id}")
    void deleteById(Long id);

    /**
     * 批量删除菜品
     * @param ids
     * @return void
     */
    void deleteBatch(List<Long> ids);

    /**
     * 更新菜品
     * @param dish
     * @return void
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);

}
