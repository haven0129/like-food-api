package com.likefood.domain.company.service;

import com.likefood.domain.company.model.Company;
import com.likefood.domain.company.repository.CompanyRepository;
import com.likefood.pojo.common.ConstantValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyServiceImpl implements CompanyService {
    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public Company save(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company getCompany() {
        return companyRepository.findFirstById(ConstantValue.COMPANY_ID);
    }
}
