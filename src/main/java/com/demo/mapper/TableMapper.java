package com.demo.mapper;

import org.apache.ibatis.annotations.Param;


import java.util.Map;


/**
 * @Description:数据库表操作接口
 * @Author: wangyilong
 * @Date: 2021/2/23 10:14
 */
public interface TableMapper {

    /**
     * 1 创建数据库
     * @param dbName
     */
    void createDB(@Param(value = "dbName") String dbName);

    /**
     * 2 数据库是否存在
     * @param dbName
     */
    Map<String,String> checkDBExist(@Param(value = "dbName") String dbName);

    /**
     * 3 切换数据库
     * @param dbName
     */
    void changeDB(@Param(value = "dbName") String dbName);

    /**
     * 4 创建表
     * @param map
     */
    void createNewTable(@Param(value = "myMap") Map map);

    /**
     * 5 判断数据库中表是否存在
     * @param tableName
     * @return
     */
    Map<String, String> checkTableExist(@Param(value = "tableName") String tableName);

    /**
     * 6 批量导入数据
     * @param path
     * @param dbName
     * @param tableName
     */
    void loadData(@Param(value = "path") String path,
                  @Param(value = "dbName") String dbName,
                  @Param(value = "tableName") String tableName);
}
