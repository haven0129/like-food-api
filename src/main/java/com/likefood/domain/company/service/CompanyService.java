package com.likefood.domain.company.service;

import com.likefood.domain.company.model.Company;

public interface CompanyService {
    Company getCompany();
    Company save(Company company);
}
