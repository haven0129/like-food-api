package com.likefood.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/productSimple", tags = "ProduceSimple", description = "产品管理")
public class ProductSimpleApi {

    @ApiOperation(value = "/productSimple/add", nickname = "新增产品信息", notes = "新增产品信息")
    @RequestMapping(value = "/productSimple/add", method = RequestMethod.POST, produces = {"application/json"})
    void addProduceSimple() {
    }

    @ApiOperation(value = "/productSimple/update", nickname = "修改产品", notes = "修改产品")
    @RequestMapping(value = "/productSimple/update", method = RequestMethod.POST, produces = {"application/json"})
    void updateProduceSimple() {
    }
    @ApiOperation(value = "/productSimple/delete", nickname = "删除产品", notes = "删除产品")
    @RequestMapping(value = "/productSimple/delete", method = RequestMethod.POST, produces = {"application/json"})
    void deleteProduceSimple() {
    }

    @ApiOperation(value = "/productSimple/detail", nickname = "产品详情", notes = "产品详情")
    @RequestMapping(value = "/productSimple/detail", method = RequestMethod.POST, produces = {"application/json"})
    void getProduceSimpleDetail() {
    }

    @ApiOperation(value = "/productSimple/list", nickname = "产品列表", notes = "产品列表")
    @RequestMapping(value = "/productSimple/list", method = RequestMethod.POST, produces = {"application/json"})
    void getProduceSimpleList() {
    }
}
