package com.likefood.domain.company.service;

import com.likefood.domain.company.model.Company;

public interface CompanyService {
    /**
     * 获得公司信息
     * @return
     */
    Company getCompany();

    /**
     * 保存公司信息
     * @param company
     * @return
     */
    Company save(Company company);
}
