package com.likefood.pojoconverter;

import com.likefood.domain.company.model.Company;
import com.likefood.domain.product.model.Product;
import com.likefood.domain.product.model.Type;
import com.likefood.pojo.common.ConstantValue;
import com.likefood.pojo.company.CompanyDtoU;
import com.likefood.pojo.product.*;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CompanyDtoConverterImpl implements CompanyDtoConverter {
    @Override
    public Company buildCompany(Company result, CompanyDtoU source) {
        result.setAddr(source.getAddr());
        result.setAttention(source.getAttention());
        result.setCompanyname(source.getCompanyname());
        result.setPic(source.getPic());
        result.setTelephone(source.getTelephone());
        result.setScope(source.getScope());
        return result;
    }
}