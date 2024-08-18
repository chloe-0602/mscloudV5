package com.atguigu.cloud.controller;

import com.atguigu.cloud.entities.Pay;
import com.atguigu.cloud.entities.PayDTO;
import com.atguigu.cloud.resp.ResultData;
import com.atguigu.cloud.resp.ReturnCodeEnum;
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
    public ResultData<String>  add(@RequestBody Pay pay){
        log.info("*****pay：{}", pay);
        Integer result = payService.add(pay);
        return ResultData.success("add pay successfully, result: " + result);
    }

    @DeleteMapping(value = "/pay/del/{id}")
    @Operation(summary = "删除",description = "删除支付流水方法")
    public ResultData<Integer> deletePay(@PathVariable("id") Integer id){
        Integer result = payService.delete(id);
        return ResultData.success(result);
    }

    @PutMapping(value = "/pay/update")
    @Operation(summary = "修改",description = "修改支付流水方法")
    public ResultData<String> update(@RequestBody PayDTO payDTO){
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        Integer result = payService.update(pay);
        return ResultData.success("update pay successfully, result: " + result);
    }

    @GetMapping(value = "/pay/get/{id}")
    @Operation(summary = "按照ID查流水",description = "查询支付流水方法")
    public ResultData<Pay>  getById(@PathVariable("id") Integer id){
        if(id == -4) throw new RuntimeException("id不能为负数");
        Pay pay = payService.getById(id);
        return ResultData.success(pay);
    }

    @GetMapping(value = "/pay/error")
    @Operation(summary = "测试异常",description = "测试异常方法")
    public ResultData<Integer> error(){
        Integer res = Integer.valueOf(200);
        try {
           int data = 10 / 0;
        } catch (Exception e) {
            e.printStackTrace();
            return ResultData.fail(ReturnCodeEnum.RC500.getCode(), e.getMessage());
        }
        return ResultData.success(res);
    }

    @GetMapping(value = "/pay/getAll")
    @Operation(summary = "查所有流水",description = "查询所有支付流水方法")
    public ResultData<List<Pay>> getAll(){
        List<Pay> payList = payService.getAll();
        return ResultData.success(payList);
    }
}
