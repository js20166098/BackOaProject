package com.js.controller.system;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.js.common.enums.StatusCode;
import com.js.common.response.BaseResponse;
import com.js.dto.system.SysDictDto;
import com.js.pojo.system.SysDict;
import com.js.service.system.SysDictService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/sysDict")
@Slf4j
public class SysDictController {

    @Autowired
    private SysDictService sysDictService;

    @GetMapping("/get/{id}")
    public BaseResponse<SysDictDto> getById(@PathVariable("id") Long id) {
        SysDict sysDict = sysDictService.getById(id);
        if (Objects.isNull(sysDict)) {
            return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg());
        }
        SysDictDto sysDictDto = new SysDictDto();
        BeanUtils.copyProperties(sysDict, sysDictDto);
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), sysDictDto);
    }

    @GetMapping("/getList")
    public BaseResponse<PageInfo<SysDict>> getByEntity(@RequestBody SysDictDto sysDictDto) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(sysDictDto, sysDict);
        PageHelper.startPage(sysDictDto.getOffset(), sysDictDto.getLimit());
        List<SysDict> sysDicts = sysDictService.listByEntity(sysDict);
        PageInfo<SysDict> pageInfo = new PageInfo<>(sysDicts);
        return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg(), pageInfo);
    }

    @PostMapping("/insert")
    public BaseResponse<Boolean> insert(@RequestBody SysDictDto sysDictDto) {
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(sysDictDto, sysDict);
        int result = sysDictService.insert(sysDict);
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg());
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg());
    }

    @PutMapping("/update")
    public BaseResponse<Boolean> update(@RequestBody SysDictDto sysDictDto) {
        if (sysDictDto.getId() == null) {
            return new BaseResponse<>(StatusCode.FAIL.getCode(), "缺少参数");
        }
        SysDict sysDict = new SysDict();
        BeanUtils.copyProperties(sysDictDto, sysDict);
        int result = sysDictService.update(sysDict);
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg());
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg());
    }

    @DeleteMapping("/delete/{id}")
    public BaseResponse<Boolean> deleteOne(@PathVariable("id") Long id) {
        int result = sysDictService.deleteById(id);
        if (result > 0) {
            return new BaseResponse<>(StatusCode.SUCCESS.getCode(), StatusCode.SUCCESS.getMsg());
        }
        return new BaseResponse<>(StatusCode.FAIL.getCode(), StatusCode.FAIL.getMsg());
    }

}