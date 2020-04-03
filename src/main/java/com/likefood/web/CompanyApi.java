package com.likefood.web;

import com.likefood.domain.company.model.Company;
import com.likefood.domain.company.service.CompanyService;
import com.likefood.pojo.company.CompanyDtoU;
import com.likefood.pojoconverter.CompanyDtoConverter;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Api(value = "/company", tags = "Company", description = "公司信息管理")
public class CompanyApi {
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CompanyDtoConverter companyDtoConverter;
    @ApiOperation(value = "/company/update", nickname = "修改公司信息", notes = "修改公司信息")
    @RequestMapping(value = "/company/update", method = RequestMethod.POST, produces = {"application/json"})
    @PreAuthorize("hasPermission('product', 'admin')")
    Company updateCompany(@Valid @RequestBody CompanyDtoU companyDtoU) {
        Company company = companyService.getCompany();
        company = companyDtoConverter.buildCompany(company, companyDtoU);
        return companyService.save(company);
    }

    @ApiOperation(value = "/company/detail", nickname = "获得公司信息", notes = "获得公司信息")
    @RequestMapping(value = "/company/detail", method = RequestMethod.GET, produces = {"application/json"})
    Company companyDetail() {
        return companyService.getCompany();
    }
}
