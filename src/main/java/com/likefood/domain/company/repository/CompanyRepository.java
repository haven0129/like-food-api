package com.likefood.domain.company.repository;

import com.likefood.domain.company.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {


    Company findFirstById(Long id);
}
