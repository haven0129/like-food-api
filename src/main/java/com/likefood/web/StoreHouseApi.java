package com.likefood.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "/storeHouse", tags = "storeHouse", description = "仓库信息管理")
public class StoreHouseApi {

    @ApiOperation(value = "/storeHouse/add", nickname = "新增仓库信息", notes = "新增仓库信息")
    @RequestMapping(value = "/storeHouse/add", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('storeHouse', 'admin')")
    void addStoreHouse() {
    }

    @ApiOperation(value = "/storeHouse/update", nickname = "修改仓库", notes = "修改仓库")
    @RequestMapping(value = "/storeHouse/update", method = RequestMethod.POST, produces = {"application/json"})
    void updateStoreHouse() {
    }

    @ApiOperation(value = "/storeHouse/delete", nickname = "删除仓库", notes = "删除仓库")
    @RequestMapping(value = "/storeHouse/delete", method = RequestMethod.POST, produces = {"application/json"})
    void deleteStoreHouse() {
    }

    @ApiOperation(value = "/storeHouse/detail", nickname = "仓库详情", notes = "仓库详情")
    @RequestMapping(value = "/storeHouse/detail", method = RequestMethod.POST, produces = {"application/json"})
    void getStoreHouseDetail() {
    }

    @ApiOperation(value = "/storeHouse/list", nickname = "仓库列表", notes = "仓库列表")
    @RequestMapping(value = "/storeHouse/list", method = RequestMethod.POST, produces = {"application/json"})
    void getStoreHouseList() {
    }
}
