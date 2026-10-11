package com.sky.controller.admin;

import com.sky.dto.DishDTO;
import com.sky.dto.DishPageQueryDTO;
import com.sky.result.PageResult;
import com.sky.result.Result;
import com.sky.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/admin/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品和对应口味
     *
     * @param dishDTO 菜品DTO
     * @return 结果
     */
    @PostMapping
    public Result save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品: {}", dishDTO);
        dishService.saveDishWithFlavor(dishDTO);
        return Result.success();
    }

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    public Result<PageResult> pageQuery(DishPageQueryDTO dishPageQueryDTO) {
        log.info("分页查询菜品: {}", dishPageQueryDTO);
        PageResult pageResult = dishService.pageQuery(dishPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 批量删除菜品
     * @return
     */
    @DeleteMapping
    public Result delete(@RequestParam List<Long> ids) {
        log.info("批量删除菜品:{}", ids);
        dishService.deletBatch(ids);
        return Result.success();
    }
}
