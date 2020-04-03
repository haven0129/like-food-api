package com.likefood.domain.user.repository;

import com.likefood.domain.user.model.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginLogRepository extends JpaRepository<LoginLog, Long> {
}
