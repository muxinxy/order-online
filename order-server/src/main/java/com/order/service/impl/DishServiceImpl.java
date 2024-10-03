package com.order.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.order.constant.MessageConstant;
import com.order.constant.StatusConstant;
import com.order.dto.DishDTO;
import com.order.dto.DishPageQueryDTO;
import com.order.entity.Dish;
import com.order.entity.DishFlavor;
import com.order.exception.DeletionNotAllowedException;
import com.order.mapper.DishFlavorMapper;
import com.order.mapper.DishMapper;
import com.order.mapper.SetmealDishMapper;
import com.order.result.PageResult;
import com.order.service.DishService;
import com.order.vo.DishVO;

/**
 * 菜品服务实现类
 */
@Service
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;
    @Autowired
    private DishFlavorMapper dishFlavorMapper;
    @Autowired
    private SetmealDishMapper setmealDishMapper;


    /**
     * 新增菜品
     * @param dishDTO
     * @return
     */
    @Transactional
    public void saveWithFlavors(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO, dish);
        dishMapper.insert(dish);
        Long dishId = dish.getId();
        List<DishFlavor> flavors = dishDTO.getFlavors();
        if (flavors != null && flavors.size() > 0) {
            flavors.forEach(flavor -> {
                flavor.setDishId(dishId);
            });
            dishFlavorMapper.insertBatch(flavors);
        }
    }

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return PageResult
     */
    public PageResult page(DishPageQueryDTO dishPageQueryDTO) {
        PageHelper.startPage(dishPageQueryDTO.getPage(), dishPageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(dishPageQueryDTO);
        return new PageResult(page.getTotal(), page.getResult());
    }

    /**
     * 批量删除菜品
     * @param ids
     * @return void
     */
    @Transactional
    public void deleteBatch(List<Long> ids) {
        // 判断是否存在起售的菜品
        for (Long id : ids) {
            Dish dish = dishMapper.selectById(id);
            if (dish.getStatus() == StatusConstant.ENABLE){
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        }
        // 判断是否被套餐关联
        List<Long> dishIds = setmealDishMapper.selectSetmealIdsByDishIds(ids);
        if (dishIds != null && dishIds.size() > 0) {
            throw new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        // 删除菜品
        /*for (Long id : ids) {
            dishMapper.deleteById(id);
            // 删除菜品口味
            dishFlavorMapper.deleteByDishId(id);
        }*/

        // 批量删除菜品
        dishMapper.deleteBatch(ids);
        // 批量删除菜品口味
        dishFlavorMapper.deleteByDishIds(ids);
    }

}
