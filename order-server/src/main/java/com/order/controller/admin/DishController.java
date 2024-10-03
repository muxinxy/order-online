package com.order.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.order.dto.DishDTO;
import com.order.dto.DishPageQueryDTO;
import com.order.result.PageResult;
import com.order.result.Result;
import com.order.service.DishService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品管理")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     * @param dishDTO
     * @return Result
     */
    @PostMapping
    @ApiOperation(value = "新增菜品")
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品..." + dishDTO);
        dishService.saveWithFlavors(dishDTO);
        return Result.success();
    }

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return Result<PageResult>
     */
    @GetMapping("/page")
    @ApiOperation(value = "分页查询菜品")
    public Result<PageResult> page(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品..." + dishPageQueryDTO);
        PageResult pageResult = dishService.page(dishPageQueryDTO);
        return Result.success(pageResult);
    }

}
