package com.aki.mybatisdemo;

import com.aki.mybatisdemo.mybatis.entity.BillOrderHeader;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author aki
 * @date 2024/4/29 16:21
 */
@Mapper
public interface BillOrderHeaderMapper {

    List<BillOrderHeader> queryByNumber1(String billOrderNumber);
    List<BillOrderHeader> queryByNumber2(String billOrderNumber);
    List<BillOrderHeader> queryByNumber3(String billOrderNumber);
    void create(BillOrderHeader billOrderHeader);
    BillOrderHeader detail(Long id);
}

