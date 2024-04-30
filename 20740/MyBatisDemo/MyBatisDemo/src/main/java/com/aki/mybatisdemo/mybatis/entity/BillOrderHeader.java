package com.aki.mybatisdemo.mybatis.entity;

import lombok.Data;

/**
 * @author aki
 * @date 2024/4/29 16:18
 */
@Data
public class BillOrderHeader {

    private String id;
    private String billOrderNumber;
    private String createType;
}
