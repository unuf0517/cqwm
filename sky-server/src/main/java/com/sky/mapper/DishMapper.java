package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.annotation.AutoFill;
import com.sky.dto.DishPageQueryDTO;
import com.sky.entity.Dish;
import com.sky.enumeration.OperationType;
import com.sky.vo.DishVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DishMapper {

    /**
     * 根据分类id查询菜品数量
     * @param categoryId
     * @return
     */
    @Select("select count(id) from dish where category_id = #{categoryId}")
    Integer countByCategoryId(Long categoryId);

    /**c
     * 新增菜品
     * @param dish
     */
    @AutoFill(value = OperationType.INSERT)
    void insert(Dish dish);

    /**
     * 分页查询菜品
     * @param dishPageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO dishPageQueryDTO);

    /**
     * 根据id查询菜品
     * @param dishId
     * @return
     */
    @Select("select * from dish where id = #{dishId}")
    Dish getById(Long dishId);

    /**
     * 根据id删除菜品
     * @param dishId
     */
    @Delete("delete from dish where id = #{dishId}")
    void deleteById(Long dishId);

    /**
     * 根据id批量删除菜品
     * @param dishIds
     */
    void deleteByIds(List<Long> dishIds);

    /**
     * 更新菜品信息
     * @param dish
     */
    @AutoFill(value = OperationType.UPDATE)
    void update(Dish dish);
}
