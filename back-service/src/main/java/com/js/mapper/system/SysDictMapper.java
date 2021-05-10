package com.js.mapper.system;

import com.js.pojo.system.SysDict;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 数据字典(com.js.pojo.system.SysDict)表数据库访问层
 *
 * @author makejava
 * @since 2021-05-10 15:58:52
 */
@Mapper
public interface SysDictMapper {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    SysDict selectById(@Param("id") Long id);

    /**
     * 通过实体作为筛选条件查询
     *
     * @param sysDict 实例对象
     * @return 对象列表
     */
    List<SysDict> selectList(SysDict sysDict);

    /**
     * 新增数据
     *
     * @param sysDict 实例对象
     * @return 影响行数
     */
    int insert(SysDict sysDict);

    /**
     * 修改数据
     *
     * @param sysDict 实例对象
     * @return 影响行数
     */
    int update(SysDict sysDict);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(@Param("id") Long id);

    /**
     * 通过条件删除数据
     *
     * @param sysDict 主键
     * @return 影响行数
     */
    int deleteByEntity(SysDict sysDict);

    /**
     * 通过条件删批量删除数据除数据
     *
     * @param list 主键集合
     * @return 影响行数
     */
    int deleteByIds(@Param("list") List<Long> list);

    /**
     * 条件查询总数
     *
     * @return 数据总数
     */
    int countByEntity(SysDict sysDict);

    /**
     * @return
     * @Description: 通过实体作为筛选条件查询
     * @Param [code, key]
     * @Author: 渡劫 dujie
     * @Date: 2021/5/10 4:12 PM
     */
    SysDict selectByCodeAndKey(@Param("code") String code, @Param("key") String key);

}