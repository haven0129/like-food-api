package com.likefood.domain.user.repository;

import com.likefood.domain.user.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

    void deleteByUserId(Long userId);

}
