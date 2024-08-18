package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@Tag(name = "支付微服务模块",description = "支付CRUD")
public class PayController {
    @Resource
    private PayService payService;

    @PostMapping(value = "/pay/add")
    @Operation(summary = "新增",description = "新增支付流水方法,json串做参数")
    public String add(@RequestBody Pay pay){
        log.info("*****pay：{}", pay);
        Integer result = payService.add(pay);
        return "add pay successfully, result: " + result;
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public String delete(@PathVariable("id") Integer id){
        Integer result = payService.delete(id);
        return "delete pay successfully, result: " + result;
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public String update(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        Integer result = payService.update(pay);
        return "update pay successfully, result: " + result;
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public Pay getById(@PathVariable("id") Integer id){
        return payService.getById(id);
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查所有流水",description = "查询所有支付流水方法")
    public List<Pay> getAll(){
        return payService.getAll();
    }
}
