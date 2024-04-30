package com.aki.mybatisdemo;

import com.aki.mybatisdemo.mybatis.entity.BillOrderHeader;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author aki
 * @date 2024/4/29 16:38
 */
@RestController
@RequestMapping("/aki")
public class BillOrderHeaderController {

    @Autowired
    private BillOrderHeaderMapper billOrderHeaderMapper;


    @Autowired
    private RedisTemplate<String, String> redisTemplate;


    @GetMapping("/number1/{billOrderNumber}")
    public List<BillOrderHeader> queryByBillOrderNumber1(@PathVariable("billOrderNumber") String billOrderNumber) {
        return billOrderHeaderMapper.queryByNumber1(billOrderNumber);
    }
    @GetMapping("/number2/{billOrderNumber}")
    public List<BillOrderHeader> queryByBillOrderNumber2(@PathVariable("billOrderNumber") String billOrderNumber) {
        return billOrderHeaderMapper.queryByNumber2(billOrderNumber);
    }
    @GetMapping("/number3/{billOrderNumber}")
    public List<BillOrderHeader> queryByBillOrderNumber3(@PathVariable("billOrderNumber") String billOrderNumber) {
        return billOrderHeaderMapper.queryByNumber3(billOrderNumber);
    }

    @GetMapping("/{id}")
    public BillOrderHeader detail(@PathVariable("id") Long id) {
        BillOrderHeader billOrderHeader = null;
        String jsonString = redisTemplate.opsForValue().get("billOrderHeader:" + id);
        if (!StringUtils.isEmpty(jsonString)) {
            billOrderHeader = JSON.parseObject(jsonString, BillOrderHeader.class);
        } else {
            billOrderHeader = billOrderHeaderMapper.detail(id);
            if (null != billOrderHeader) {
                redisTemplate.opsForValue().set("billOrderHeader:" + id, JSON.toJSONString(billOrderHeader),5, TimeUnit.MINUTES);
            }
        }
        return billOrderHeader;
    }

    @PostMapping("/create")
    public BillOrderHeader create(@RequestBody BillOrderHeader billOrderHeader) {
        billOrderHeaderMapper.create(billOrderHeader);
        redisTemplate.opsForValue().set("billOrderHeader:" + billOrderHeader.getId(), JSON.toJSONString(billOrderHeader));
        return billOrderHeader;
    }

}
