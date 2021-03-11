package com.demo.mapper;

import com.demo.bean.CSVFile;

import java.util.List;

/**
 * @Description:数据层接口
 * @Author: wangyilong
 * @Date: 2021/2/22 15:51
 */
public interface CSVFileMapper {

    /**
     * 查询全部
     * @return
     */
    List<CSVFile> findAll();

    /**
     * 根据id查询
     * @param csvid
     * @return
     */
    CSVFile findById(Integer csvid);

    /**
     * 插入一条数据
     * @param csvFile
     */
    void insert(CSVFile csvFile);

    /**
     * 根据id删除
     * @param csvid
     * @return
     */
    int delete(Integer csvid);
}
