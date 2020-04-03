package com.likefood.pojoconverter;


import com.likefood.domain.company.model.Company;
import com.likefood.pojo.company.CompanyDtoU;


public interface CompanyDtoConverter {

    Company buildCompany(Company company, CompanyDtoU source);

}
