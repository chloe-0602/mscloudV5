package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    public String add(@RequestBody Pay pay){
        log.info("*****pay：{}", pay);
        Integer result = payService.add(pay);
        return "add pay successfully, result: " + result;
    }

    @DeleteMapping(value = "/pay/delete/{id}")
    public String delete(@PathVariable("id") Integer id){
        Integer result = payService.delete(id);
        return "delete pay successfully, result: " + result;
    }

    @PutMapping(value = "/pay/update")
    public String update(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        Integer result = payService.update(pay);
        return "update pay successfully, result: " + result;
    }

    @GetMapping(value = "/pay/get/{id}")
    public Pay getById(@PathVariable("id") Integer id){
        return payService.getById(id);
    }

    @GetMapping(value = "/pay/getAll")
    public List<Pay> getAll(){
        return payService.getAll();
    }
}
