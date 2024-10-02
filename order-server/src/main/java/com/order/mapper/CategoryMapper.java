package com.order.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.order.annotation.AutoFill;
import com.order.dto.CategoryDTO;
import com.order.dto.CategoryPageQueryDTO;
import com.order.entity.Category;
import com.order.enumeration.OperationType;

@Mapper
public interface CategoryMapper {

    /**
     * 添加分类
     * @param category
     */
    @Insert("insert into category (name, sort, type, status, create_time, update_time, create_user, update_user)"
    + "values (#{name}, #{sort}, #{type}, #{status}, #{createTime}, #{updateTime}, #{createUser}, #{updateUser})")
    @AutoFill(value = OperationType.INSERT)
    void save(Category category);

    /**
     * 根据id查询分类
     * @param id
     * @return
     */
    @Select("select * from category where id = #{id}")
    Category getById(Long id);

    /**
     * 分页查询分类
     * @param categoryPageQueryDTO
     */
    Page<Category> pageQuery(CategoryPageQueryDTO categoryPageQueryDTO);

    /**
     * 根据类型查询分类
     * @param type
     * @return
     */
    List<Category> getByType(Integer type);

    /**
     * 修改分类
     * @param category
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Category category);

    /**
     * 删除分类
     * @param id
     */
    @Delete("delete from category where id = #{id}")
    void delete(Long id);

}
